public class Main {

    public static void main(String[] args) {
        Memory m = new Memory();
        m.load("input.txt");
        m.execRedistributionCycles();
    }
}
