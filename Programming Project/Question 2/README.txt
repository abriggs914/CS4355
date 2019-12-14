CS4355 Cryptanalysis Final Project
VigenereCipher readme
Question 2
Dec.7/19
Avery Briggs
3471065


For my solution, I was unable to completely decrpyt the message, I was able to get as far as
obtaining what I think is the probable key though.

The steps I took were to initialize all known alphabet frequncies and come simple probabilities
of having a keyword of that length (sampleICValues). Then I attmept to evaluate each IC value to
determine the minimum difference in the extimated key's IC value. Then once I have an estimated key
length and IC values, I begin trying to estimate the key. The key is not stored as a string and it's
ASCII values are represented in the array keyShiftValues. This is used for shifting the encrypted
letter the appropriate ditance given the key's letter. I then concatenate all the punctuation and 
the estimated decrypted message ande return it to the user.