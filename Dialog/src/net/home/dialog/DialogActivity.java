package net.home.dialog;

import android.app.Activity;
import android.os.Bundle;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class DialogActivity extends Activity {
	CharSequence[] items = { "Google", "Apple", "Microsoft" };
	boolean[] itemsChecked = new boolean[items.length];

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Button btn = (Button) findViewById(R.id.btn_dialog);
		btn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				showDialog(0);
			}
		});
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case 0:
			return new AlertDialog.Builder(this)
					.setIcon(R.drawable.ic_launcher)
					.setTitle("This is a dialog with some simple text...")
					.setPositiveButton("OK",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {
									Toast.makeText(getBaseContext(),
											"OK Clicked!", Toast.LENGTH_SHORT)
											.show();
									// TODO Auto-generated method stub

								}
							})
					.setNegativeButton("Cancle",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {
									Toast.makeText(getBaseContext(),
											"Cancle Clicked!",
											Toast.LENGTH_SHORT).show();
									// TODO Auto-generated method stub

								}
							})
					.setMultiChoiceItems(items, itemsChecked,
							new DialogInterface.OnMultiChoiceClickListener() {
								public void onClick(DialogInterface dialog,
										int which, boolean isChecked) {
									Toast.makeText(
											getBaseContext(),
											items[which]
													+ (isChecked ? " checked!"
															: " unchecked!"),
											Toast.LENGTH_SHORT).show();
								}
							}).create();
		}
		return null;
	}
}