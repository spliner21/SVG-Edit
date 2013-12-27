package pl.spliner21.polsl.svg_edit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;

public class OptionsActivity extends Activity {

	// KODY SILNIKÓW 
	static final int WEBKIT_CODE = 300;
	static final int SVG_ANDROID = 310;
	static final int SVG_ANDROID_2 = 320;
	static final int ANDROID_SVG = 330;
	static final int TPSVG_CODE = 340;
	
    int engine = WEBKIT_CODE;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_options);

	    final RadioButton webkit = (RadioButton)findViewById(R.id.webkit);
	    final RadioButton svgand = (RadioButton)findViewById(R.id.svgand);
	    final RadioButton svgand2 = (RadioButton)findViewById(R.id.svgand2);
	    final RadioButton andsvg = (RadioButton)findViewById(R.id.andsvg);
	    final RadioButton tpsvg = (RadioButton)findViewById(R.id.tpsvg);
	    
	    final Button saver = (Button)findViewById(R.id.optsavebtn);
	    
	    engine = this.getIntent().getIntExtra("engine", WEBKIT_CODE);

	    webkit.setChecked(false);
	    svgand.setChecked(false);
	    svgand2.setChecked(false);
	    andsvg.setChecked(false);
	    
	    switch(engine) {
	    case WEBKIT_CODE:
		    webkit.setChecked(true);
		    break;
	    case SVG_ANDROID:
	    	svgand.setChecked(true);
		    break;
	    case SVG_ANDROID_2:
	    	svgand2.setChecked(true);
		    break;
	    case ANDROID_SVG:
	    	andsvg.setChecked(true);
		    break;
	    case TPSVG_CODE:
	    	tpsvg.setChecked(true);
		    break;
		default:
		    break;
	    }
	    
	    webkit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked) {
				    engine = WEBKIT_CODE;
				    svgand.setChecked(false);
				    svgand2.setChecked(false);
				    andsvg.setChecked(false);
				    tpsvg.setChecked(false);
				}
			}
		});
	    svgand.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked) {
				    engine = SVG_ANDROID;
				    webkit.setChecked(false);
				    svgand2.setChecked(false);
				    andsvg.setChecked(false);
				    tpsvg.setChecked(false);
				}
			}
		});
	    svgand2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked) {
					engine = SVG_ANDROID_2;
				    webkit.setChecked(false);
				    svgand.setChecked(false);
				    andsvg.setChecked(false);
				    tpsvg.setChecked(false);
				}
			}
		});
	    andsvg.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked) {
				    engine = ANDROID_SVG;
				    webkit.setChecked(false);
				    svgand2.setChecked(false);
				    svgand.setChecked(false);
				    tpsvg.setChecked(false);
				}
			}
		});
	    tpsvg.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked) {
				    engine = TPSVG_CODE;
				    webkit.setChecked(false);
				    svgand2.setChecked(false);
				    svgand.setChecked(false);
				    andsvg.setChecked(false);
				}
			}
		});
	    
	    saver.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent returnIntent = new Intent();
				
				returnIntent.putExtra("engine", engine);
				
				setResult(RESULT_OK,returnIntent);
				finish();
			}
		});
	}

}
