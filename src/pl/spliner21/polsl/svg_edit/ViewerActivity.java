package pl.spliner21.polsl.svg_edit;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.larvalabs.svgandroid.*;
import com.caverock.androidsvg.*;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
	@SuppressLint({ "NewApi", "InlinedApi" })
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_view);
	
		Intent intent = getIntent();
		
		Uri fileUri = (Uri)intent.getExtras().get("fileuri");
		
		int engine = intent.getIntExtra("engine", WEBKIT_CODE);

		FileInputStream fis = null;
		try {
			fis = new FileInputStream(fileUri.getPath());
		} catch (FileNotFoundException e1) {
			Log.e("V_A","File not found!");
		}

		FrameLayout svgLayout = (FrameLayout)findViewById(R.id.svgViewer);
		
		switch(engine){
			
		case SVG_ANDROID:
			SVG1 svg = null;
			try {
				svg = SVG1Parser.getSVGFromInputStream(fis);
			} catch(SVG1ParseException e) {
				Log.e("SVG1-P","Cannot parse SVG file!");
				break;
			}

	        ImageView imageView = new ImageView(this);
	        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
	            // Disable hardware acceleration
	            imageView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
	        }
			imageView.setLayoutParams(svgLayout.getLayoutParams());
	        // Set the background color to white
	        imageView.setBackgroundColor(Color.WHITE);
	        // Parse the SVG file from the resource
	        // Get a drawable from the parsed SVG and set it as the drawable for the ImageView
	        imageView.setImageDrawable(svg.createPictureDrawable());



			svgLayout.addView(imageView);
			break;
			
		case SVG_ANDROID_2:
			com.larvalabs.svgandroid.SVG svg2;
			try {
				svg2 = com.larvalabs.svgandroid.SVGParser.getSVGFromInputStream(fis);	// TU i w v1 by³ problem z getSVGFromString - nie wiadomo czemu :D
			} catch(com.larvalabs.svgandroid.SVGParseException e) {
				Log.e("SVG-P","Cannot parse SVG file!");
				break;
			} 

	        ImageView imageView2 = new ImageView(this);
	        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
	            // Disable hardware acceleration
	            imageView2.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
	        }
	        // Set the background color to white
	        imageView2.setBackgroundColor(Color.WHITE);
	        // Parse the SVG file from the resource
	        // Get a drawable from the parsed SVG and set it as the drawable for the ImageView
	        imageView2.setImageDrawable(svg2.createPictureDrawable());


			imageView2.setLayoutParams(svgLayout.getLayoutParams());

			svgLayout.addView(imageView2);
			break;
			

		case ANDROID_SVG:

	        SVGImageView svgImageView = new SVGImageView(this);
	        svgImageView.setImageURI(fileUri);
	        svgLayout.addView(svgImageView,svgLayout.getLayoutParams());

			break;
		default:
			
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
