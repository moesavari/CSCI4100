package com.example.myempire.midterm;


        import android.os.AsyncTask;

        import org.w3c.dom.Document;
        import org.w3c.dom.Node;
        import org.w3c.dom.NodeList;

        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.net.URL;
        import java.util.ArrayList;
        import java.util.List;

        import javax.xml.parsers.DocumentBuilder;
        import javax.xml.parsers.DocumentBuilderFactory;

/*
TODO:

1. Set the type parameters for this class.  This AsyncTask will not use progress.
   Its input will be a URL string, and its result will be a list of HousingProject
   objects.  The important fields in the CSV file are as follows:
   a. latitude (column 0, header says 'X')
   b. longitude (column 1, header says 'Y')
   c. address (column 5, header says 'PROJ_ADDRESS')
   d. municipality (column 6, header says 'MUNICIPALITY')
   e. numUnits (column 9, header says 'NUM_UNITS')
2. Implement the doInBackground() method to download and process the CSV data
   into a list of HousingProject objects.
3. Implement the onPostExecute() method to handle any exceptions and pass the
   list of HousingProjects back to the listener.
*/
public class DownloadHousingTask extends AsyncTask<Object, Object, HousingProject> {
    private Exception exception = null;
    private HousingDownloadListener listener = null;

    public DownloadHousingTask(HousingDownloadListener listener) {
        this.listener = listener;
    }

    /**
     * loadCSVLines()
     *
     * @arg inStream The input stream from which to read the CSV data
     *
     * @return A list of strings, each of which will be one line of CSV data
     *
     * This function is included to help you process the CSV file.  This function
     * downloads all of the data from the provided InputStream, and returns a list of
     * lines.  Since we are downloading a CSV file, these lines will consist of
     * comma-separated data (like the example given in Listing 1).
     **/
    private List<String> loadCSVLines(InputStream inStream) throws IOException {
        List<String> lines = new ArrayList<>();

        BufferedReader in = new BufferedReader(new InputStreamReader(inStream));

        String line = null;
        while ((line = in.readLine()) != null) {
            lines.add(line);
        }

        return lines;
    }

    @Override
    protected HousingProject doInBackground(Object... params) {

         float latitude = 0;
         float longitude = 0;
         String address = null;
         String municipality = null;
         int numUnits = 0;
         Exception exception = null;
         HousingDownloadListener listener = null;

        try {
            URL url = new URL(params[0]);

            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(url.openStream());
            doc.getDocumentElement().normalize();

            NodeList latWord = doc.getElementsByTagName("X");
            if (latWord.getLength() > 0) {
                Node def = latWord.item(0);
                latitude = Float.parseFloat(def.getTextContent())
            } else {
                latitude = 0;
            }

            NodeList longWord = doc.getElementsByTagName("Y");
            if (longWord.getLength() > 0) {
                Node def = longWord.item(0);
                longitude = Float.parseFloat(def.getTextContent());
            } else {
                longitude = 0;
            }

            NodeList addWord = doc.getElementsByTagName("PROJ_ADDRESS");
            if (addWord.getLength() > 0) {
                Node def = addWord.item(0);
                address = def.getTextContent();
            } else {
                address = "";
            }

            NodeList muniWord = doc.getElementsByTagName("MUNICIPALITY");
            if (muniWord.getLength() > 0) {
                Node def = muniWord.item(0);
                municipality = def.getTextContent();
            } else {
                municipality = "";
            }

            NodeList numUnitsWord = doc.getElementsByTagName("NUM_UNITS");
            if (numUnitsWord.getLength() > 0) {
                Node def = numUnitsWord.item(0);
                numUnits = Integer.parseInt(def.getTextContent());
            } else {
                numUnits = 0;
            }
        } catch (Exception e) {
            exception = e;
        }

        HousingProject house = new HousingProject(latitude, longitude, address, municipality, numUnits);
        return house;
    }


    // TODO:  Implement the doInBackGround() method
    //        This method will download the CSV data from the URL
    //        parameter (params[0]), extract the relevant data from
    //        the file, creating a list of HousingProject objects.
    //        The HousingProject list will be the result.

    // TODO:  Implement the onPostExecute() method
    //        This method will handle exceptions, and pass the result data
    //        back to the listener
}
