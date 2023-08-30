package com.example.morsecodeime;

import android.inputmethodservice.InputMethodService;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputConnection;
import android.widget.Button;
import android.widget.TableLayout;

public class MorseCodeIME extends InputMethodService implements OnClickListener {

    private boolean capsLock;
    private boolean caps;
    private int hapticMode;
    private long hapticUnitDuration = 100;


    /**
     * Called when the input view is created, i.e. when the keyboard is shown.
     *
     * @return the keyboard view
     */
    public View onCreateInputView() {


        // inflate the keyboard layout
        View keyboardView = this.getLayoutInflater().inflate(R.layout.keyboard_layout, null);

        // create


        // iterate through all the keys and set the listener
        TableLayout keyboard = keyboardView.findViewById(R.id.keyboard_view);
        for (int row = 0; row < keyboard.getChildCount(); row++) {
            View rowView = keyboard.getChildAt(row);
            for (int i = 0; i < ((ViewGroup) rowView).getChildCount(); i++) {
                View element = ((ViewGroup) rowView).getChildAt(i);
                if (element instanceof Button) {
                    element.setOnClickListener(this);
                }
            }
        }


        return keyboardView;
    }


    public void onClick(View v) {
        // check if the key is alphanumeric or operation
        if (v.getTag().equals("alphanumeric")) {
            CharSequence text = ((Button) v).getText();
            this.inputText(text);

            new Handler().postDelayed(() -> {
                // FIXME somehow it'll make keyboard disappear after typing a letter
                new FeedbackActivity().playSignalFeedback(text);
            }, 100);

        } else {
            this.handleOperation(v);
        }
    }

    private void inputText(CharSequence cs) {
        // pass the text to the input connection
        InputConnection ic = this.getCurrentInputConnection();
        if (ic == null) return;

        CharSequence text = this.caps ? (CharSequence) cs.toString().toUpperCase() : cs;
        // change caps based on capsLock
        if (this.caps && !this.capsLock) {
            this.caps = false;
        }
        // commit the text
        ic.setComposingText(text, 1);
        ic.commitText(text, 1);
    }

    private void handleOperation(View v) {
        InputConnection ic = this.getCurrentInputConnection();
        if (ic == null) return;

        switch (v.getTag().toString()) {
            case "shift":
                caps = !caps;
                break;
            case "backspace":
                if (TextUtils.isEmpty(ic.getSelectedText(0))) {
                    ic.deleteSurroundingText(1, 0);
                } else {
                    ic.commitText((CharSequence) "", 1);
                }
                ic.deleteSurroundingText(1, 0);
                break;
            case "symbol":
                this.getLayoutInflater().inflate(R.layout.symbol_layout, null);
                break;
            case "setting":
                // open settings page

                break;
            case "emoji":
                // TODO open emoji keyboard

                break;
            case "space":
                ic.commitText(" ", 1);
                break;
            case "done":
                // quit
                this.requestHideSelf(0);
                break;
            default:
                // TODO
                try {
                    throw new Exception("Unidentified key");
                } catch (Exception e) {
                    e.printStackTrace();
                }

        }
    }
}
