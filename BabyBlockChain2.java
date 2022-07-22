import jdk.dynalink.Operation;

import java.security.*;
import java.util.ArrayList;
import java.util.Scanner;

public class BabyBlockChain2 {
            public static void main(String[] args) throws Exception{

        try {
            //Accepting text from user

            Account account = new Account();
            account.genAccount();
            System.out.println(account);
            account.amount =10;
            account.upDateBalance(0);
            account.printBalance();
            account.getBalance();


            Scanner scanner = new Scanner(System.in);
            System.out.println("Input correct name of candidate");
            String message = scanner.nextLine();
            System.out.println("name of candidate = " + message);

            Signer signer = new Signer();
            byte[] sign = signData(message.getBytes(), signer.getPrivateKey());
            PublicKey pubKey = signer.getPubkey();

            validateMessageSignature(pubKey, message.getBytes(), sign);
            account.upDateBalance(account.balance- account.amount);
            System.out.println("new balance = " + account.upDateBalance(account.balance- account.amount));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // the class for key pair generation.

        public static class Signer {
            private PrivateKey privateKey;

            private PublicKey publicKey;

            public PublicKey getPubkey() {
                return publicKey;
            }


            public Signer() throws NoSuchAlgorithmException {
                    KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("DSA");
                    SecureRandom secRan = new SecureRandom();
                    keyPairGen.initialize(512, secRan);
                    KeyPair keyP = keyPairGen.generateKeyPair();
                    this.publicKey = keyP.getPublic();
                    this.privateKey = keyP.getPrivate();
                    System.out.println("Keys generated : " + keyP.hashCode());
                }


            public PrivateKey getPrivateKey() {
                return privateKey;
            }

            public void setPrivateKey(PrivateKey privateKey) throws NoSuchAlgorithmException {
                this.privateKey = privateKey;
            }

            public PublicKey getPublicKey() {
                return publicKey;
            }

            public void setPublicKey(PublicKey publicKey) {

                this.publicKey = publicKey;
            }


        }



    //Class for generation and validation of digital signatures.
        public static void validateMessageSignature(PublicKey publicKey, byte[] message, byte[] signature) throws
                NoSuchAlgorithmException, InvalidKeyException, SignatureException{
            Signature userSig = Signature.getInstance("DSA");
            userSig.initVerify(publicKey);
            userSig.update(message);
            if (userSig.verify(signature)) {


                System.out.println("The message is properly signed.= " + signature.hashCode());
            }
            else {
                System.err.println("It is not possible to validate the signature. Check signature!");
            }

        }

        public static byte[] signData(byte[] message, PrivateKey privateKey) throws  NoSuchAlgorithmException,
                InvalidKeyException, SignatureException{
            Signature signature = Signature.getInstance("DSA");
            signature.initSign(privateKey);
            signature.update(message);
            byte[] sign = signature.sign();
            return sign;
        }


    public static class Account {


        //Attributes
        PublicKey accountID;
        ArrayList<ArrayList<Object>> wallet;
        int balance;
        int amount;

        


        //Getters and Setters
        public PublicKey getAccountID() {return accountID;}
        public void setAccountID(PublicKey accountID) {this.accountID = accountID;}

        public ArrayList<ArrayList<Object>> getWallet() {return wallet;}
        public void setWallet(ArrayList<ArrayList<Object>> wallet) {this.wallet = wallet;}

        //Constructor:
        public Account() {

        }
        //methods:

        public Account genAccount(){
        /*genAccount method allows creation of an account and returns
        an object of the account class in this case a new account(account1). */

            Account account = new Account();

            account.accountID = this.accountID;
            account.balance = this.balance;
             PublicKey accountID = this.accountID;
            return account;
        }
        public ArrayList<KeyPair> genWalletArray(){
            ArrayList<KeyPair> wallet = new ArrayList<>();

            System.out.println("Wallet array generated : ");
            return wallet;
        }

        public void addKeyPairToWallet( KeyPair keyPair){
            wallet.add(new ArrayList<>());
        }
        public int upDateBalance(int balance){
            this.balance = balance;
            this.amount = amount;
            this.balance = balance + amount;

            return balance;
        }


        public int getBalance(){return this.balance;}
        public void printBalance(){ System.out.println("Current Balance = "+ this.balance);  }


        public Operation createOperation (){
            return null;
        }

    }




}



