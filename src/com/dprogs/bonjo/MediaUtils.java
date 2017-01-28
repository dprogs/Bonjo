package com.dprogs.bonjo;

import java.io.File;

import android.media.MediaMetadataRetriever;

import com.dprogs.bonjo.db.SongFile;

public class MediaUtils {

	public static SongFile parseMP3(String path) {
		SongFile sf = null;
		MediaMetadataRetriever mmr = new MediaMetadataRetriever();
		mmr.setDataSource(path);

        File f = new File(path);

        String destFolder = f.getParent();

        String fileName = f.getName();
        
        String albumName =
		     mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM);		
		String artistName =
			     mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);		
		String titleName =
			     mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);
		
        System.out.println("Song path: " + f.getParent());// /home/jigar/Desktop
        System.out.println("Song file: " + f.getName());  //1.txt
        System.out.println("Album name: " + albumName);// /home/jigar/Desktop
        System.out.println("Artist name: " + artistName);  //1.txt
        System.out.println("Title name: " + titleName);// /home/jigar/Desktop

		sf = new SongFile(destFolder, fileName, albumName, artistName, titleName);
		
		return sf;
	}
}
