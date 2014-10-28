package org.vkedco.android.builtgesturesconsumer;

/*************************************************************
 * BuiltGesturesConsumerAct.java is a an activity
 * that consumes touchscreen gestures developed
 * with the Android GestureBuilder application to
 * recognized handwritten letters a, b, and c. The
 * gesture file is in /res/raw/gestures
 * 
 * Bugs, comments to vladimir dot kulyukin at gmail dot com
 *************************************************************
 */

import java.util.ArrayList;
import android.app.Activity;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;
import android.os.Bundle;
import android.widget.Toast;

public class BuiltGesturesConsumerAct extends Activity implements
		OnGesturePerformedListener
{
	private GestureLibrary mLibrary;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		this.setContentView(R.layout.main);

		mLibrary = GestureLibraries.fromRawResource(this, R.raw.abc);
		if (!mLibrary.load()) {
			finish();
		}

		GestureOverlayView gestures = (GestureOverlayView) findViewById(R.id.gestures);
		gestures.addOnGesturePerformedListener(this);

	}

	public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture)
	{
		// 1. get the predictions
		ArrayList<Prediction> predictions = mLibrary.recognize(gesture);
		
		// 2. make sure that there is at least one prediction
		if (predictions.size() > 0)
		{
			// 3. get the best prediction
			Prediction prediction = (Prediction) predictions.get(0);
			// 4. make sure the confidence score is > 1.0
			if (prediction.score > 1.0)
			{
				// Show the spell
				Toast.makeText(this, prediction.name, Toast.LENGTH_SHORT)
						.show();
			}
		}
	}
}
