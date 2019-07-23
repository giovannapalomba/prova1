package it.unipr.mobdev.easythoraxus.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import it.unipr.mobdev.easythoraxus.models.ChronoDescriptor;


public class ChronoManager {

    private static final String TAG = ChronoManager.class.getName();
    private Context context = null;

    private String outputFileName = "loglist.txt";

    /*
            * The instance is static so it is shared among all instances of the class. It is also private
	 * so it is accessible only within the class.
            */
    private static ChronoManager instance = null;

    private ArrayList<ChronoDescriptor> logList = null;

    /*
     * The constructor is private so it is accessible only within the class.
     */
    private ChronoManager(Context context){
        Log.d(TAG,"Number Manager Created !");
        this.context = context;
        /*
         * Try to read an existing log list and load into the ArrayList
         */
        try {
            this.logList = readLogListFromFile();
            Log.d(TAG,"Log File available ! List size: " + this.logList.size());
        } catch(Exception e) {
            //If there is not an existing file create an empty ArrayList
            this.logList = new ArrayList<ChronoDescriptor>();
            Log.e(TAG,"Error Reading Log List on File: " + e.getLocalizedMessage());
        }

    }


    public static ChronoManager getInstance(Context context){
        /*
         * The constructor is called only if the static instance is null, so only the first time
         * that the getInstance() method is invoked.
         * All the other times the same instance object is returned.
         */
        if(instance == null)
            instance = new ChronoManager(context);
        return instance;
    }

    public void addLogToHead(ChronoDescriptor log){
        this.logList.add(0,log);

        try {
            saveLogListOnFile();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "Error Saving Log List on File ...", Toast.LENGTH_LONG).show();
        }
    }

    public ArrayList<ChronoDescriptor> getLogList(){
        return logList;
    }

    /**
     * Read (if available) the Bookmark list from file
     *
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    private ArrayList<ChronoDescriptor> readLogListFromFile() throws FileNotFoundException, IOException
    {
        Log.d(TAG, "Reading Bookmark List from Internal Storage ...");

        //Create a StringBuffer used to append the chat read from the file
        StringBuffer strContent = new StringBuffer("");

        int ch;

        //Open the FileInputStream using the Context object
        FileInputStream fis = context.openFileInput(outputFileName);

        //While to read char by char the file (It is not the only way ! You can also read line by line)
        while( (ch = fis.read()) != -1)
            strContent.append((char)ch);

        //Close the input stream
        fis.close();

        Log.d(TAG, "Read Internal Storage Bookamark File: " + strContent);

        //Create the Gson object to deserialize the ArrayList
        Gson gson = new Gson();

        Type collectionType = new TypeToken<Collection<ChronoDescriptor>>(){}.getType();

        //Create a Type object for the deserialization of the ArrayList of LogDescriptor
        Collection<ChronoDescriptor> dLogList = gson.fromJson(strContent.toString(), collectionType);

        //Save the retrieved list
        if(dLogList != null)
            return new ArrayList<ChronoDescriptor>(dLogList);

        //If the list is null return an empty list
        return new ArrayList<ChronoDescriptor>();
    }

    /**
     * Save the log list on file using the JSON format
     *
     * @throws FileNotFoundException
     * @throws IOException
     */
    private void saveLogListOnFile() throws FileNotFoundException, IOException
    {
        Log.d(TAG, "Saving LogDescriptor List on File ...");

        //Create the Gson object to serialize the ArrayList
        Gson gson = new Gson();

        //Create a Type object for the serialization of the ArrayList of LogDescriptor
        Type collectionType = new TypeToken<Collection<ChronoDescriptor>>(){}.getType();

        //Serialize in JSON
        String ssidListJson = gson.toJson(this.logList, collectionType);

        //Write the obtained string on file
        FileOutputStream fos = context.openFileOutput(outputFileName, Context.MODE_PRIVATE);
        fos.write(ssidListJson.getBytes());
        fos.close();
    }
}
