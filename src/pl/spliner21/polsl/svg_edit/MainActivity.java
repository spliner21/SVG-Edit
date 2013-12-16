package pl.spliner21.polsl.svg_edit;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	static final int PICKFILE_RESULT_CODE = 100;
	static final int VIEWER_CODE = 110;
	static final int OPTIONS_RESULT_CODE = 120;
	
	// KODY SILNIKÓW 
	static final int WEBKIT_CODE = 300;
	static final int SVG_ANDROID = 310;
	static final int SVG_ANDROID_2 = 320;
	static final int ANDROID_SVG = 330;
	
	int engine = WEBKIT_CODE;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		Button openBtn = (Button)findViewById(R.id.button_open);
		Button optbBtn = (Button)findViewById(R.id.button_options);
		Button quitBtn = (Button)findViewById(R.id.button_close);
		
		// Otwieranie pliku
		openBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent fileintent = new Intent(Intent.ACTION_GET_CONTENT);
		        fileintent.setType("image/svg+xml");
		        try {
		            startActivityForResult(fileintent, PICKFILE_RESULT_CODE);
		        } catch (ActivityNotFoundException e) {
		            Log.e("tag", "No activity can handle picking a file. Showing alternatives.");
		        }
				
			}
		});

		// Ekran opcji
		optbBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent optionsIntent = new Intent(getApplicationContext(),OptionsActivity.class);
				optionsIntent.putExtra("engine", engine);
	            startActivityForResult(optionsIntent, OPTIONS_RESULT_CODE);
				
			}
		});

    	// Obs³uga przycisku wyjœcia
    	quitBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    // TODO Fix no activity available
	    if (data == null)
	        return;
	    switch (requestCode) {
		    case PICKFILE_RESULT_CODE:
		        if (resultCode == RESULT_OK) {
		            Uri FileUri = data.getData();
		            //FilePath is your file as a string
		
					Intent viewIntent = new Intent(getApplicationContext(),ViewerActivity.class);
					viewIntent.putExtra("fileuri", FileUri);
					viewIntent.putExtra("engine",engine);
					startActivityForResult(viewIntent,VIEWER_CODE);
		
		        }
		        break;
			case OPTIONS_RESULT_CODE:
			    if (resultCode == RESULT_OK) {
			        engine = data.getIntExtra("engine", engine);	
			        Toast.makeText(getApplicationContext(), "Wybrany engine: "+String.valueOf(engine), Toast.LENGTH_SHORT).show();
			    }
			    break;
			default:
			    break;
	    }
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
