package HappyBabyBlock;

import java.util.Date;

public class Block {

    public String hash;
    public String previousHash;
    private final String data;
    private final long timeStamp; //as number of milliseconds since 1/1/1970.
    public int nonce;

    //BabyBlock.Block Constructor.
    public Block(String data, String previousHash ) {
        this.data =data;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
    }

    //Calculate new hash based on blocks contents
    public String calculateHash() {
        return HappyStringUtil.applySha256(
                previousHash +
                        timeStamp +
                        nonce +
                        data );
    }
    //Increases nonce value until hash target is reached.
    public void mineBlock(int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0'); //Create a string with difficulty * "0"
        while(!hash.substring( 0, difficulty).equals(target)) {
            nonce ++;
            hash = calculateHash();
        }
        System.out.println("BabyBlock.Block Mined!!! : " + hash);
    }
}

