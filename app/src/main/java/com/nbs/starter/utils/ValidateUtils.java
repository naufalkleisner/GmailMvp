package com.nbs.starter.utils;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

/**
 * Created by ghiyatshanif on 2/21/17.
 * purpose : validate view (especially EditText) to check if the view(s) is empty or not,
 * then show the error message
 */

public class ValidateUtils {

	public static boolean validate(View... views) {
		return validate(null, views);
	}

	public static boolean validate(String errorMessage, View... views) {
		boolean isValid = true;
		for (View view : views) {
			if (view instanceof EditText) {
				if (!isValid)
					isNotEmpty((EditText) view, errorMessage);
				else
					isValid = isNotEmpty((EditText) view, errorMessage);
			}
		}
		return isValid;
	}

	public static boolean isNotEmpty(EditText editText) {
		return isNotEmpty(editText, null);
	}

	public static boolean isNotEmpty(EditText editText, String errorMessage) {
		String text = editText.getText().toString().trim();
		if (TextUtils.isEmpty(text)) {
			if (errorMessage != null)
				editText.setError(errorMessage);
			editText.requestFocus();
			return false;
		}
		return true;
	}

}