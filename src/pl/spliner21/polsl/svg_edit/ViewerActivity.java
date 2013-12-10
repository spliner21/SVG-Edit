package pl.spliner21.polsl.svg_edit;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.webkit.WebView;

public class ViewerActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_view);
	
		Intent intent = getIntent();
		
		String fileUri = intent.getStringExtra("fileuri");
		
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(fileUri);
		} catch (FileNotFoundException e1) {
			Log.e("V_A","File not found!");
		}
		
		StringBuffer fileContent = new StringBuffer("");

		byte[] buffer = new byte[1024];

		try {
			while (fis.read(buffer) != -1) {
			    fileContent.append(new String(buffer));
			}
		} catch (IOException e) {
			Log.e("V_A","Input-Output Exception!");
		}	
		
		WebView webview = new WebView(this);
		
		setContentView(webview);

		webview.loadData(fileContent.toString(), "image/svg+xml", "UTF8");
		
	}

}
