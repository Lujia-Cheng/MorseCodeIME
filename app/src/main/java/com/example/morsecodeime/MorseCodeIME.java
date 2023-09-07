package com.example.morsecodeime;

import static java.lang.Thread.sleep;

import android.graphics.Color;
import android.inputmethodservice.InputMethodService;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputConnection;
import android.widget.Button;
import android.widget.TableLayout;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MorseCodeIME extends InputMethodService implements OnClickListener {

    private boolean capsLocked = false;
    private boolean capped;
    private int hapticMode;
    private long hapticUnitDuration = 100;
    ExecutorService executor;
    View keyboardView;

    /**
     * Called when the input view is created, i.e. when the keyboard is shown.
     *
     * @return the keyboard view
     */
    public View onCreateInputView() {

        executor = Executors.newSingleThreadExecutor();
        // inflate the keyboard layout
        keyboardView = this.getLayoutInflater().inflate(R.layout.keyboard_layout, null);


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
        if (!v.getTag().equals("alphanumeric")) {
            this.handleOperation(v);
        } else {
            char ch = ((Button) v).getText().charAt(0);
            this.inputText(ch);

            FeedbackUtil feedback = new FeedbackUtil(7);
            ArrayList<Long> timing = feedback.charToSignals(ch);

            Button indicator = keyboardView.findViewById(R.id.indicator);
            indicator.setVisibility(View.VISIBLE);
            executor.execute(() -> {
                // getSystemService(VIBRATOR_SERVICE);// FIXME haptic feedback


                boolean on = false;
                for (long time : timing) {
                    on = !on;
                    indicator.setBackgroundColor(on ? Color.WHITE : 0x00cccccc);
                    //indicator.setVisibility(on ? View.VISIBLE : View.INVISIBLE);
                    try {
                        sleep(time);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
    }


    private void inputText(char ch) {
        // TODO support preferences for feedback group style: per-character, per-word, per-sentence
        // TODO support preferences for feedback type: haptic, visual, audio

        // check caps & input
        if (this.capped) {
            ch = Character.toUpperCase(ch);
            capped = capsLocked;
        }

        String text = Character.toString(ch);
        InputConnection ic = this.getCurrentInputConnection();
        ic.setComposingText(text, 1);
        ic.commitText(text, 1);

        // TODO  run async feedback


    }

    /**
     * For demo purposes, flash the space bar to indicate vibration.
     */
    private void flash(ArrayList<Long> timing) {

    }

    private void handleOperation(View v) {
        InputConnection ic = this.getCurrentInputConnection();
        if (ic == null) return;

        switch (v.getTag().toString()) {
            case "shift": // TODO support double click
                capped = !capped;
                break;
            case "backspace":
                if (ic.getSelectedText(0) == null) {
                    ic.deleteSurroundingText(1, 0);
                } else {
                    ic.commitText("", 1);
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
