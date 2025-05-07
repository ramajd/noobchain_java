package io.github.ramajd.noobchain_java;

import java.util.Date;

public class Block {

    public String hash;
    public String previousHash;
    private final String data;
    private final long timestamp;
    private int nonce;

    public Block(String data, String previousHash) {
        this.data = data;
        this.previousHash = previousHash;
        this.timestamp = new Date().getTime();

        this.hash = this.calculateHash();
    }

    public String calculateHash() {
        return StringUtils.applySHA256(
                this.previousHash +
                        this.timestamp +
                        this.nonce +
                        this.data
        );
    }

    public void mineBlock(int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0');
        while (!hash.substring(0, difficulty).equals(target)) {
            nonce++;
            hash = calculateHash();
        }
        System.out.println("Block mined! " + hash);
    }
}
