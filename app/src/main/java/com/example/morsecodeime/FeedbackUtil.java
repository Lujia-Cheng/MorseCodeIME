package com.example.morsecodeime;

import static java.lang.Thread.sleep;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FeedbackUtil {

    private static long wpm;
    private final long FARNSWORTH_TIMING_CUTOFF = 18;
    private static final Map<Character, String> codeBook = createCharToMorse();

    public FeedbackUtil(long wpm) {
        this.wpm = wpm;
    }

    public ArrayList<Long> charToSignals(char input) {
        final long unit, farnsworthUnit;
        ArrayList<Long> signals = new ArrayList<>();

        unit = farnsworthUnit = 1200 / wpm;
      /*
      if (wpm > FARNSWORTH_TIMING_CUTOFF) {
            unit = farnsworthUnit = 1200 / wpm;

       } else {
            // Lock unit duration to 18 wpm, but compensate by increasing the gap between characters
            // See https://morsecode.world/international/timing.html or http://www.arrl.org/files/file/Technology/x9004008.pdf for math
            unit = 1200 / FARNSWORTH_TIMING_CUTOFF;
            farnsworthUnit = unit + 1200 / wpm; // FIXME Figure out the calculation
        }

        */

        // Duration of a farnsworth unit (1 dit duration)
        // TODO Differentiate normal timing vs Farnsworth timing


        final long dit = unit; // Duration of a dit in milliseconds
        final long dah = 3 * unit; // Duration of a dah (3 times the dit duration)
        final long intraChar = unit; // the gap between dits and dahs within a character (1 dit duration) e.g the pause between "." and "-" in char a(.-)
        final long interChar = 3 * farnsworthUnit; // the gap between characters (3 times the dit duration) e.g the pause between "a" and "b" in "ab"
        final long space = 7 * farnsworthUnit; // the gap between words (7 times the dit duration) e.g the pause between "a" and "b" in "a b"

        String morse = codeBook.getOrDefault(input, "");
        for (char ch : morse.toCharArray()) {
            switch (ch) {
                case '.':
                    signals.add(dit);
                    signals.add(intraChar);
                    break;
                case '-':
                    signals.add(dah);
                    signals.add(intraChar);
                    break;
                case ' ':
                    signals.add(space);
                    break;
                default:
                    break;
            }

        }
        signals.set(signals.size() - 1, interChar);
        return signals;

    }

    private static Map<Character, String> createCharToMorse() {
        Map<Character, String> map = new HashMap<>();
        map.put('a', ".-");
        map.put('b', "-...");
        map.put('c', "-.-.");
        map.put('d', "-..");
        map.put('e', ".");
        map.put('f', "..-.");
        map.put('g', "--.");
        map.put('h', "....");
        map.put('i', "..");
        map.put('j', ".---");
        map.put('k', "-.-");
        map.put('l', ".-..");
        map.put('m', "--");
        map.put('n', "-.");
        map.put('o', "---");
        map.put('p', ".--.");
        map.put('q', "--.-");
        map.put('r', ".-.");
        map.put('s', "...");
        map.put('t', "-");
        map.put('u', "..-");
        map.put('v', "...-");
        map.put('w', ".--");
        map.put('x', "-..-");
        map.put('y', "-.--");
        map.put('z', "--..");

        map.put('0', "-----");
        map.put('1', ".----");
        map.put('2', "..---");
        map.put('3', "...--");
        map.put('4', "....-");
        map.put('5', ".....");
        map.put('6', "-....");
        map.put('7', "--...");
        map.put('8', "---..");
        map.put('9', "----.");
        return map;
    }
}
