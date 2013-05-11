package com.cmdann.rootchecker;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final Button button1 = (Button) findViewById(R.id.button1);
		final Button button2 = (Button) findViewById(R.id.button2);

		// Device text views
		final TextView Text1 = (TextView) findViewById(R.id.textView5);
		final TextView Text2 = (TextView) findViewById(R.id.TextView09);
		final TextView Text3 = (TextView) findViewById(R.id.TextView06);
		final TextView Text4 = (TextView) findViewById(R.id.TextView05);

		button1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				String command[] = { "su", "-c", "ls", "/data" };
				Shell shell = new Shell();
				String text = shell.sendShellCommand(command);
				if ((text.indexOf("app") > -1) || (text.indexOf("anr") > -1)
						|| (text.indexOf("user") > -1)
						|| (text.indexOf("data") > -1)) {
					// setNewTextInTextView("This phone is not rooted");
					// //Debugging
					button2.setBackgroundResource(R.drawable.checkmark);
				} else {
					// setNewTextInTextView("This phone is rooted!");
					// //Debugging
					button2.setBackgroundResource(R.drawable.xmark);
				}
				// setNewTextInTextView(text); //Debugging
				// The end-user-visible name for the end product.
				String curModel = Build.MODEL;
				// The name of the overall product.
				String curProduct = Build.PRODUCT;
				// The manufacturer of the product/hardware.
				String curMan = Build.MANUFACTURER;
				// The brand (e.g., carrier) the software is customized for, if
				// any.
				String curBrand = Build.BRAND;
				// The name of the industrial design.
				String curDevice = Build.DEVICE;
				String curVer = Build.VERSION.RELEASE;

				// Set the current information
				Text1.setText(curDevice);
				Text2.setText(curModel);
				Text3.setText(curMan);
				Text4.setText(curVer);
			}
		});
	}

	public void setNewTextInTextView(String text) {
		TextView tv = new TextView(this);
		tv.setText(text);
		setContentView(tv);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (event.getAction() == KeyEvent.ACTION_DOWN) {
			switch (keyCode) {
			case KeyEvent.KEYCODE_BACK:

				finish();
				System.exit(0);

				return true;
			}

		}
		return super.onKeyDown(keyCode, event);
	}

}
