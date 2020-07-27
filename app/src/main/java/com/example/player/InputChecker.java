package com.example.player;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class InputChecker {
    private Context context;

    public InputChecker(Context context) {
        this.context = context;
    }
    boolean isInputEditTextName(EditText name,String message) {
        String value = name.getText().toString().trim();
        if (value.isEmpty()) {
            name.setError(message);
            hideKeyboardFrom(name);
            return false;
        } else {
            name.setText("");
        }

        return true;
    }

    boolean isInputEditTextPassword(EditText password,String message) {
        String value = password.getText().toString().trim();
        if (value.isEmpty()) {
            password.setError(message);
            hideKeyboardFrom(password);
            return false;
        } else {
            password.setText("");
        }

        return true;
    }


    boolean isInputEditTextEmail(EditText email, String message) {
        String value = email.getText().toString().trim();
        if (value.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(value).matches()) {
            email.setError(message);
            hideKeyboardFrom(email);
            return false;
        } else {
            email.setText("");
        }
        return true;
    }



    private void hideKeyboardFrom(View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }
}

