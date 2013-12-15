package pl.spliner21.polsl.svg_edit;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.larvalabs.svgandroid.SVG;
import com.larvalabs.svgandroid.SVG1;
import com.larvalabs.svgandroid.SVG1ParseException;
import com.larvalabs.svgandroid.SVG1Parser;
import com.larvalabs.svgandroid.SVGParseException;
import com.larvalabs.svgandroid.SVGParser;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class ViewerActivity extends Activity {

	// KODY SILNIKÓW 
	static final int WEBKIT_CODE = 300;
	static final int SVG_ANDROID = 310;
	static final int SVG_ANDROID_2 = 320;
	static final int ANDROID_SVG = 330;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_view);
	
		Intent intent = getIntent();
		
		String fileUri = intent.getStringExtra("fileuri");
		
		int engine = intent.getIntExtra("engine", WEBKIT_CODE);

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

		FrameLayout svgLayout = (FrameLayout)findViewById(R.id.svgViewer);
		
		switch(engine){
			
		case SVG_ANDROID:
			SVG1 svg;
			svg = SVG1Parser.getSVGFromResource(getResources(), R.raw.android);
			/*try {
				//SVG1Parser.getSVGFromString(fileContent.toString());
			} catch(SVG1ParseException e) {
				Log.e("SVG1-P","Cannot parse SVG file!");
				break;
			}*/

	        ImageView imageView = new ImageView(this);
			imageView.setLayoutParams(svgLayout.getLayoutParams());
	        // Set the background color to white
	        imageView.setBackgroundColor(Color.WHITE);
	        // Parse the SVG file from the resource
	        // Get a drawable from the parsed SVG and set it as the drawable for the ImageView
	        imageView.setImageDrawable(svg.createPictureDrawable());



			svgLayout.addView(imageView);
			break;
			
		case SVG_ANDROID_2:
			SVG svg2;
			try {
				svg2 = SVGParser.getSVGFromResource(getResources(), R.raw.android); // getSVGFromString(fileContent.toString());
			} catch(SVGParseException e) {
				Log.e("SVG-P","Cannot parse SVG file!");
				break;
			}

	        ImageView imageView2 = new ImageView(this);
	        // Set the background color to white
	        imageView2.setBackgroundColor(Color.WHITE);
	        // Parse the SVG file from the resource
	        // Get a drawable from the parsed SVG and set it as the drawable for the ImageView
	        imageView2.setImageDrawable(svg2.createPictureDrawable());


			imageView2.setLayoutParams(svgLayout.getLayoutParams());

			svgLayout.addView(imageView2);
			break;
		default:

			
			WebView webview = new WebView(this);
			webview.setLayoutParams(svgLayout.getLayoutParams());
					
			svgLayout.addView(webview);
			
			WebSettings settings = webview.getSettings();
			settings.setSupportZoom(true);
			settings.setDefaultZoom(WebSettings.ZoomDensity.FAR);
			settings.setBuiltInZoomControls(true);
					
			webview.loadData(fileContent.toString(), "image/svg+xml", "UTF8");
			
			break;
/*
		case WEBKIT_CODE:
			
			break;
			
			*/
		}
		
		
	}

}
