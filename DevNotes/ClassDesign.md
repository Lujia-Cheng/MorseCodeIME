# MyIMService.java

> An otherwise standalone, functioning keyboard service

# MorseCodeHelper.java

> Translate between strings and binary telegraphically signals

Store lookup table

wrap char <-> enum:Signal

Use key/map for char <-> signals

## From String to telegraph

First write down "MORSE CODE"

```
MORSE CODE
-- --- .-. ... . / -.-. --- -.. .
```

Then space out all its letters

```
M  O   R   S   E   C    O   D   E
-- --- .-. ... . / -.-. --- -.. .
```

Again, stretch DAH ( - ) to its true length in real time, which is 3 DIT ( . ) length

```
         1         2         3         4         5         6         7         8
12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789

M         O             R         S       E       C             O             D         E
___ ___   ___ ___ ___   . ___ .   . . .   .       ___ . ___ .   ___ ___ ___   ___ . .   .
```

And translate them into to telegraph circuit signals

```
___ ___   ___ ___ ___   . ___ .   . . .   .       ___ . ___ .   ___ ___ ___   ___ . .   .
### ###   ### ### ###   # ### #   # # #   #       ### # ### #   ### ### ###   ### # #   #
```

Finally, convert them into binary

```
### ###   ### ### ###   # ### #   # # #   #       ### # ### #   ### ### ###   ### # #   #
11101110001110111011100010111010001010100010000000111010111010001110111011100011101010001
```

# Feedback.java

> Give visual/audio/haptic feedback as user types

## Timing

| Words per Minute |     | >= 18 | < 18             |
| ---------------- | --: | ----- | ---------------- |
| dit              |   1 | unit  | unit             |
| dah              |   3 | units | units            |
| element_space    |   1 | unit  | unit             |
| letter_space     |   3 | units | Farnsworth-units |
| word_space       |   7 | units | Farnsworth-units |

> Note that the Farnsworth technique would be used, if the transmission speed is lower than 18 WPM. It is basically adding extra delay between character to preserve rhythm recognition. Read more at [this article from ARRL.org](http://www.arrl.org/files/file/Technology/x9004008.pdf)

As a result if we merge

```
M         O             R         S       E       C             O             D         E
___ ___   ___ ___ ___   . ___ .   . . .   .       ___ . ___ .   ___ ___ ___   ___ . .   .
### ###   ### ### ###   # ### #   # # #   #       ### # ### #   ### ### ###   ### # #   #

   _              ___   _      ___         _______
11101110001110111011100010111010001010100010000000111010111010001110111011100011101010001
   ^               ^    ^       ^             ^
element_space     dah  dit      |             |
                           letter_space   word_space
```

# Quick Math for Farnsworth

# Links that might be helpful
