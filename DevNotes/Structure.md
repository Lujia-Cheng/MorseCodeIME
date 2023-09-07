# XML

> Just need two tabs: keyboard and settings

## Keyboard

> where user types

### qwerty keyboard

1-9 hide behind q row

### symbol tab \* 2

### emoji

For better UX, layout should be similar to Gboard shown below.

![GboardTheme](https://play-lh.googleusercontent.com/ej0woLmFRRKgXtdbbApswHB5s94rdA1cf7NT-3B26VkzL7MQZn2UNRy-432IQkUUTwZf=w2464-h1138-rw)

## Setting

> where user adjust the feedback speed and etc.

| Setting                                          |
| ------------------------------------------------ |
| Feedback style: flash/sound/vibration(strength); |
| Feedback per: char/word/sentence                 |
| WPM adjuster: slider 0 - 18 and 18 -             |

# Java

## Visual/Audio/Haptic feedback

### Timing

< 18 WPM: Farnsworth timing

\>= 18 WPM: standard

```
dit = 1 unit
dah = 3 units
Intra-character space (the gap between dits and dahs within a character): 1 unit
Inter-character space (the gap between the characters of a word): 3 Farnsworth-units
Word space (the gap between two words): 7 Farnsworth-units
```

## Keypad typing

> ! KeybaordView deprecated in API 29

### Candidate view section

### Character/symbol section

set each key as buttons -> bind all keys to onClick() -> transfer the content to input

exception: backspace, shift, switch to numpad, setting, done, emoji, (?)space

# Implantation

[Detailed Class design](design.md)

Translator.java: convert string to morse code, vice versa.

Feedback.java: output morse code into visual/audio/haptic. Tune timing if output speed is lower 18 WPM.

MorseCode.java: Wrap Signal into SignalSequence, similar to CharSequence.
