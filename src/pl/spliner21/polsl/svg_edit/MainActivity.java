package pl.spliner21.polsl.svg_edit;

import android.os.Bundle;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class MainActivity extends Activity {
	
	static final int PICKFILE_RESULT_CODE = 100;
	static final int VIEWER_CODE = 110;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		Button openBtn = (Button)findViewById(R.id.button_open);
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
		

    	// Obs�uga przycisku wyj�cia
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
		            String FilePath = data.getData().getPath();
		            //FilePath is your file as a string
		
					Intent viewIntent = new Intent(getApplicationContext(),ViewerActivity.class);
					viewIntent.putExtra("fileuri",FilePath);
					startActivityForResult(viewIntent,VIEWER_CODE);
		
		        }
	    }
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
