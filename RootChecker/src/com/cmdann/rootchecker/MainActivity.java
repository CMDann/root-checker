package com.cmdann.rootchecker;

import android.app.Activity;
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
