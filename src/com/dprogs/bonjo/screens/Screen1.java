package com.dprogs.bonjo.screens;

import com.dprogs.bonjo.R;
import com.dprogs.bonjo.views.OpenFileDialog;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class Screen1 extends Fragment {
	
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
				// TODO Auto-generated method stub
				OpenFileDialog builder = new OpenFileDialog(getActivity());
				builder.setTitle("Важное сообщение!")
						.setMessage("Покормите кота!")
						.setIcon(R.drawable.ic_launcher)
						.setCancelable(false)
						.setNegativeButton("ОК, иду на кухню",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog, int id) {
										dialog.cancel();
									}
								});
				AlertDialog alert = builder.create();
				alert.show();				
			}
			
		});
		
        return mView;
    }
}