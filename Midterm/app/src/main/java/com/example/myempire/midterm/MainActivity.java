package com.example.myempire.midterm;

        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;

        import android.widget.Spinner;
        import android.widget.SpinnerAdapter;

        import java.util.List;


/*
TODO:

1. Add this activity as a selection listener to the dropdown
2. Make the activity class implement the right interface to be a selection listener
3. Implement the selection listener event to populate the text fields with the selected
   housing project
4. Make this activity class implement the HousingDownloadListener interface provided
5. Make this activity class implement the housingDataDownloaded() method.  This method will
   populate the spinner with housing data (using the toString() method of the HousingData
   class).
*/
public class MainActivity extends AppCompatActivity
                            implements HousingDownloadListener
                          /* TODO: Add selection listener interface */ {
    private static final String urlprovided = "http://csundergrad.science.uoit.ca/csci3230u/data/Affordable_Housing.csv";

    private List<HousingProject> housingProjects = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO:  Add this activity as a listener for the spinner
        DownloadHousingTask task = new DownloadHousingTask(this);
        task.execute(urlprovided);
        Log.i("AsyncDemo", "Looking it up");

    }


    @Override
    public void housingDataDownloaded(List<HousingProject> housingProjects) {

        Spinner spinner = (Spinner)findViewById(R.id.lstHousingProjects);
        SpinnerAdapter adapter = null;
        spinner.setAdapter(adapter);


    }
    private HousingDownloadListener listener;

    public MainActivity(HousingDownloadListener listener){ this.listener = listener; }

    // TODO:  Implement the handler method for the HousingDownloadListener interface
    //         - Populate the spinner with the downloaded data
    //        Hint:  Use an ArrayAdapter for this purpose


    // TODO:  Implement the item selection method to put the data from the selected
    //        housing project into the text fields of our UI

}
