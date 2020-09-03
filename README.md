# EncryptDecrypt
One of my earlier programs. Takes command line arguments to encrypt using caesar cipher
Usage:
compile in command line with the following argument:
-mode:
  can be either enc for encryption or dec for decryption
-in:
  file name that contains the message for encryption
-out:
  file name that outputs the encrypted message
-key:
  key for the file
-alg:
  can be either unicode or shift, where unicode encrypts using the unicode table and shift just shifts the letters in the alphabet. 
Example:
  java Main -mode enc -in input.txt -out output.txt -key 10 -alg unicode  
