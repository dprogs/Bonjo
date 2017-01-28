package com.dprogs.bonjo.screens;

import java.io.File;

import com.dprogs.bonjo.R;
import com.dprogs.bonjo.db.DBStorage;
import com.dprogs.bonjo.db.SongFile;
import com.lamerman.FileDialog;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class Screen1 extends Fragment {
	//FileDialog
	//https://code.google.com/archive/p/android-file-dialog/
	
	final int REQUEST_SAVE = 35;
	final int REQUEST_LOAD = 36;
	
	View mView;
	Button pickFileButton;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
		mView = inflater.inflate(R.layout.fragment_1, container, false);
		
		pickFileButton = (Button) mView.findViewById(R.id.pickFileButton);
		
		pickFileButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent intent = new Intent(getActivity(), FileDialog.class); intent.putExtra(FileDialog.START_PATH, "/sdcard");

			    //can user select directories or not
			    intent.putExtra(FileDialog.CAN_SELECT_DIR, true);

			    //alternatively you can set file filter
			    intent.putExtra(FileDialog.FORMAT_FILTER, new String[] { "mp3" });

			    startActivityForResult(intent, REQUEST_SAVE);
			    
//			    Intent intent = new Intent()
//			    .setType("*/mp3")
//			    .setAction(Intent.ACTION_GET_CONTENT);
//
//			    startActivityForResult(Intent.createChooser(intent, "Select a file"), 123);				

//				// TODO Auto-generated method stub
//				OpenFileDialog builder = new OpenFileDialog(getActivity());
//				builder.setTitle("Важное сообщение!")
//						.setMessage("Покормите кота!")
//						.setIcon(R.drawable.ic_launcher)
//						.setCancelable(false)
//						.setNegativeButton("ОК, иду на кухню",
//								new DialogInterface.OnClickListener() {
//									public void onClick(DialogInterface dialog, int id) {
//										dialog.cancel();
//									}
//								});
//				AlertDialog alert = builder.create();
//				alert.show();				
			}
			
		});
		
        return mView;
    }

	@Override
	public synchronized void onActivityResult(int requestCode, int resultCode, Intent data) {
//	    super.onActivityResult(requestCode, resultCode, data);
//	    if(requestCode==123 && resultCode==Activity.RESULT_OK) {
//	        Uri selectedfile = data.getData(); //The uri with the location of the file
//	    }
		if (resultCode == Activity.RESULT_OK) {

	        String filePath = data.getStringExtra(FileDialog.RESULT_PATH);

	        if (requestCode == REQUEST_SAVE) {
	            System.out.println("Saving... " + filePath);
	            
	            File f = new File(filePath);

	            System.out.println("Song path: " + f.getParent());// /home/jigar/Desktop
	            System.out.println("Song file: " + f.getName());  //1.txt
	            
	            DBStorage.getDatabase().addSong(new SongFile(f.getParent(), f.getName(), "Jazzy", "U2", "2017 forever"));
	            DBStorage.readSongs();
	            
	        } else if (requestCode == REQUEST_LOAD) {
	            System.out.println("Loading...");
	        }

	    } else if (resultCode == Activity.RESULT_CANCELED) {
//	        Logger.getLogger(AccelerationChartRun.class.getName()).log(
//	                Level.WARNING, "file not selected");
	    	Log.w("", "File not selected");
	    }		
	}
}