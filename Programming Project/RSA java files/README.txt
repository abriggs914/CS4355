CS4355 Cryptanalysis Final Project
Question 2 - part c) i
Dec.2/19
Avery Briggs
3471065

This is the GUI application for the RSA question.

The algorithm should work as intended, however the decrypt method is painfully slow.
It really depends on what is entered as input though I believe. If message m is too
large, and the the value of d is also too large, the calculation for m^d%n takes a
considerable amount of time. Sometimes when this happens the application will become
unresponsive, however it is actually just hogging the main thread and therefore only
looks like it has crashed. If I had had some time, I would have tried to make a new
thread to run the decryption, or have added a progress bar.