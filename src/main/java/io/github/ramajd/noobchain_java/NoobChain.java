package io.github.ramajd.noobchain_java;

import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class NoobChain {

    public static ArrayList<Block> blockchain = new ArrayList<>();
    public static int difficulty = 5;

    public static void main(String[] args) {

        blockchain.add(new Block("Hi, I'm the first block", "0"));
        System.out.println("Trying to mine block 1 ...");
        blockchain.getLast().mineBlock(difficulty);

        blockchain.add(new Block("Yo, I'm the second block", blockchain.getLast().hash));
        System.out.println("Trying to mine block 2 ...");
        blockchain.getLast().mineBlock(difficulty);

        blockchain.add(new Block("This is the Third one", blockchain.getLast().hash));
        System.out.println("Trying to mine block 3 ...");
        blockchain.getLast().mineBlock(difficulty);

        blockchain.add(new Block("added the 4th block", blockchain.getLast().hash));
        System.out.println("Trying to mine block 4 ...");
        blockchain.getLast().mineBlock(difficulty);


        System.out.println("Blockchain is valid: " + isChainValid());

        String blockChainJson = new GsonBuilder()
                .setPrettyPrinting()
                .create().toJson(blockchain);

        System.out.println(blockChainJson);
    }

    public static boolean isChainValid() {
        Block currentBlock;
        Block previousBlock;
        String hashTarget = new String(new char[difficulty]).replace('\0', '0');

        for (int i = 1; i < blockchain.size(); i++) {
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i - 1);

            if (!currentBlock.hash.equals(currentBlock.calculateHash())) {
                System.out.println("Current hashes are not equal");
                return false;
            }
            if (!previousBlock.hash.equals(currentBlock.previousHash)) {
                System.out.println("Previous hashes are not equal");
                return false;
            }
            if (!currentBlock.hash.substring(0, difficulty).equals(hashTarget)) {
                System.out.println("This block hasn't been mined");
                return false;
            }
        }
        return true;
    }
}