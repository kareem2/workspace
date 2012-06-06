package net.home.dialog;

import android.app.Activity;
import android.os.Bundle;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import android.app.ProgressDialog;
import android.os.Handler;
import android.os.Message;

public class DialogActivity extends Activity {
	CharSequence[] items = { "Google", "Apple", "Microsoft" };
	boolean[] itemsChecked = new boolean[items.length];

	private ProgressDialog _progressDialog;
	private int _progress = 0;
	private Handler _progressHandler;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Button btn = (Button) findViewById(R.id.btn_dialog);
		btn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// showDialog(0);
				showDialog(1);
				_progress = 0;
				_progressDialog.setProgress(0);
				_progressHandler.sendEmptyMessage(0);
			}
		});

		_progressHandler = new Handler() {
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				if (_progress >= 100) {
					_progressDialog.dismiss();
				} else {
					_progress++;
					_progressDialog.incrementProgressBy(1);
					_progressHandler.sendEmptyMessageDelayed(0, 100);
				}
			}
		};
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
		case 1:
			_progressDialog = new ProgressDialog(this);
			_progressDialog.setIcon(R.drawable.ic_launcher);
			_progressDialog.setTitle("Downloading files...");
			_progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			_progressDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Hide",
					new DialogInterface.OnClickListener() {

						public void onClick(DialogInterface dialog,
								int whichButton) {
							Toast.makeText(getBaseContext(), "Hide clicked!",
									Toast.LENGTH_SHORT).show();
						}
					});
			_progressDialog.setButton(DialogInterface.BUTTON_NEGATIVE,
					"Cancle", new DialogInterface.OnClickListener() {

						public void onClick(DialogInterface dialog,
								int whichButton) {
							Toast.makeText(getBaseContext(), "Cancle clicked!",
									Toast.LENGTH_SHORT).show();

						}
					});
			return _progressDialog;
		}
		return null;
	}
}