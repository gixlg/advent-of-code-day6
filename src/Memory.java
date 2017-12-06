import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Memory {

    MemoryManager memoryManager;

    public void load(String pathToInputFile) {
        try (BufferedReader br = new BufferedReader(new FileReader(pathToInputFile))) {

            ArrayList<Integer> memoryBlocks = new ArrayList<Integer>();
            String line = br.readLine();
            for (String memoryBlock : line.split("\\s+")) {
                memoryBlocks.add(Integer.parseInt(memoryBlock));
            }
            memoryManager = new MemoryManager(memoryBlocks);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void execRedistributionCycles() {

        int numberOfCycles = 0;
        Integer seenAtIteration;
        do {
            memoryManager.printCurrentMemoryBlocks();
            memoryManager.redistribution();
            numberOfCycles++;

            seenAtIteration = memoryManager.seenAtIteration(memoryManager.getCurrentMemoryBlocks(), numberOfCycles);
        } while (seenAtIteration == null);

        System.out.println("Redistribution cycles before a configuration is reproduced: " + numberOfCycles);
        System.out.println("Lenght of infinite loop: " + (numberOfCycles - seenAtIteration.intValue()));
    }


}
