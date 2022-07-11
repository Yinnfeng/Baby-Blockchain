import java.security.*;
import java.util.Scanner;

public class  Main {
    public static void main(String[] args) throws Exception{

        try {
            //Accepting text from user
            Signer signer = new Signer();

            Scanner scanner = new Scanner(System.in);
            System.out.println("Input correct message");
            String message = scanner.nextLine();
            System.out.println("message = " + message);

            byte[] sign = signMesssage(message.getBytes(), signer.getPrivateKey());
            //validation base on message check
            PublicKey pubKey = signer.getPubkey();
            //correct signature
            System.out.println("Example1 with a valid signature :");
            validateMessageSignature(pubKey, message.getBytes(), sign);
            //Incorrect signature
            System.out.println("Example2 with an invalid signature where input message is different or not the original :");
            Scanner sc = new Scanner(System.in);
            System.out.println("Input incorrect message2");
            String message2 = scanner.nextLine();
            System.out.println("message2 = " + message2);

            //validation base on signature check
            String message3 = "this gave me headache to complete";
            Signer secondSigner = new Signer();
            PublicKey pubKey2 = secondSigner.getPubkey();
            byte[] sign2 = signMesssage(message3.getBytes(), secondSigner.getPrivateKey());
            System.out.println("Example with an invalid signature using signature that does not match the current message : " + message3 + sign2.hashCode());
            validateMessageSignature(pubKey, message.getBytes(), sign2);

            System.out.println("Example with an invalid signature using public key from another user : " + message3 + sign.hashCode());
            validateMessageSignature(pubKey2, message.getBytes(), sign);

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

        public static byte[] signMesssage(byte[] message, PrivateKey privateKey) throws  NoSuchAlgorithmException,
                InvalidKeyException, SignatureException{
            Signature signature = Signature.getInstance("DSA");
            signature.initSign(privateKey);
            signature.update(message);
            byte[] sign = signature.sign();
            return sign;
        }

    }



