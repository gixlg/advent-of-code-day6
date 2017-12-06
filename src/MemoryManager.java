import java.util.*;

public class MemoryManager {

    ArrayList<Integer> currentMemoryBlocks = new ArrayList<Integer>();

    Map<ArrayList<Integer>, Integer> previousMemoryBlocks = new HashMap<ArrayList<Integer>, Integer>();

    public MemoryManager(ArrayList<Integer> currentMemoryBlocks) {
        this.currentMemoryBlocks = currentMemoryBlocks;
        previousMemoryBlocks.put(currentMemoryBlocks, 0);
    }

    public ArrayList<Integer> getCurrentMemoryBlocks() {
        return currentMemoryBlocks;
    }

    public Integer seenAtIteration(ArrayList<Integer> memoryBlocks, Integer cycleIteration) {
        Integer seenAtIteration = previousMemoryBlocks.get(memoryBlocks);
        if (seenAtIteration==null) {
            this.previousMemoryBlocks.put(memoryBlocks, cycleIteration);
        }
        return seenAtIteration;
    }

    private Integer getIndexOfMaxElement(){
        Integer max = Integer.MIN_VALUE;
        int index=0, maxIndex=0;
        for (Integer memBlock : currentMemoryBlocks) {
           if (memBlock > max){
               max = memBlock;
               maxIndex=index;
           }
           index++;
        }
        return maxIndex;
    }

    public void printCurrentMemoryBlocks() {
        for (Integer memBlock : currentMemoryBlocks) {
            System.out.print(memBlock + "\t");
        }
        System.out.println();
    }

    private int getNextMemoryBlock(int index){
        int newIndex = index+1;
        if (newIndex>=currentMemoryBlocks.size()) return 0;
        return newIndex;
    }

    private void addMemory(int index, Integer increment){
        Integer old = currentMemoryBlocks.get(index);
        currentMemoryBlocks.set(index, old+increment);
    }

    private void resetMemoryBanks(int memoryBanksIndex){
        currentMemoryBlocks.set(memoryBanksIndex, 0);
    }

    public void redistribution() {

        int indexblockOfMemoryToRedistribuite = getIndexOfMaxElement();
        Integer blockOfMemoryToRedistribuite = currentMemoryBlocks.get(indexblockOfMemoryToRedistribuite);
        resetMemoryBanks(indexblockOfMemoryToRedistribuite);

        int memoryBanksIndex = indexblockOfMemoryToRedistribuite;
        do{
            memoryBanksIndex = getNextMemoryBlock(memoryBanksIndex);
            addMemory(memoryBanksIndex, 1);
            blockOfMemoryToRedistribuite--;
        }while(blockOfMemoryToRedistribuite>0);

        printCurrentMemoryBlocks();
    }
}
