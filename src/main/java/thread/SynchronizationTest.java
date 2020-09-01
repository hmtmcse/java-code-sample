package thread;

public class SynchronizationTest {

    public static void main(String[] args) {
        TableData tableData = new TableData();
        FirstThreadRunner firstThreadRunner = new FirstThreadRunner(tableData);
        SecondThreadRunner secondThreadRunner = new SecondThreadRunner(tableData);

        System.out.println("Started firstThreadRunner");
//        firstThreadRunner.start();
        System.out.println("Started secondThreadRunner");
//        secondThreadRunner.start();

        System.out.println("----------------------------------------");
        System.out.println("Started firstThreadRunner");
        firstThreadRunner = new FirstThreadRunner(tableData);
        firstThreadRunner.isSync = true;
        firstThreadRunner.start();

        System.out.println("Started secondThreadRunner");
        secondThreadRunner = new SecondThreadRunner(tableData);
        secondThreadRunner.isSync = true;
        secondThreadRunner.start();
    }


}
