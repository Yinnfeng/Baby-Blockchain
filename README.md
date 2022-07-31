# Baby-Blockchain
Implementation of a simple Blockchain
In the main file there is a simple implementation of Signature class and Keypair class. 
Using DSA algorithm keys were generated and used to sign
messages that are input from the console by the user, further the messages will be validated and a result will be printed out.
A toString and hashcode are used to generate hash values of the signature and keys used.


Reference:
https://github.com/WesternPine/KeyGenerator/blob/master/src/main/java/proj/key/gen/KeyGenerator.java
https://github.com/rafaelqg/code/blob/main/DSA.java
https://www.tutorialspoint.com/java_cryptography/java_cryptography_creating_signature.htm


Dependencies::

Gson
Bouncycastle as security providers
