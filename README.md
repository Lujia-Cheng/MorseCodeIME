## Overview

This is an android keyboard that haptic corresponding morse code as user types. It aims to accelerate new morse code learner build up a sense of character pattern recognition instead of counting "dit"s & "dat"s.

For how morse code lookup table, timing, & technical details, please see [DevNotes](./DevNotes) folder.

## Demo

Alright, for the sake of this demonstration, we've thrown in a blinking square to help you visualize that haptic feedback. Plus, we've dialed down the speed to a leisurely 5 words per minute.

Now, the vast expanse of meticulously crafted guides on this topic can be easily found with a quick Google search. However, bear with me as I briefly cover the fundamentals, just to make sure we`re all on the same page.

You can think of Morse code as a sequence of `bits`, where a `0` represents silence, and a `1` signals a buzz. The key distinction lies in the duration of these signals; a `dit` lasts for a single unit, while a `dah` stretches out for three units, with silence marking the gap between them.

Now, let's delve into the famous distress signal, `SOS,` and its elegance in Morse code.

Ever wondered why `SOS` became the universally recognized distress call? It's because it's brilliantly straightforward! The letter `S` consists of three `dits` ( . . . ), and `O` is represented by three `dahs` ( \_ \_ \_ ). Consequently, `SOS` translates to `dit dit dit dah dah dah dit dit dit`, or as I like to put it, `short short short, long long long, short short short`. You see, you don't even need to be a Morse code expert to decipher it!

Here's an `S` ( . . . ) for you:

![GIF of "s"](./DevNotes/demo1.webm)

But there's a crucial distinction to be made. The pause between signals (the one between the `dots` in . . . ) differs from the pause between letters, like the one between `S` and `O.`

Let's illustrate this with `SOS`:

![GIF of "SOS"](./DevNotes/demo_sos.webm)

And let's not forget about spacing; spaces come with a pause of 7, ensuringwedontsuffocatewhiletryingtodecodesentenceslikethis:

![GIF of "hi all"](./DevNotes/demo_hi_all.webm)
