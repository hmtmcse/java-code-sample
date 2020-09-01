package thread;

public class SecondThreadRunner extends Thread {


    private TableData tableData;
    public Boolean isSync = false;

    SecondThreadRunner(TableData tableData) {
        this.tableData = tableData;
    }

    public void run() {
        if (isSync) {
            tableData.syncPrintTable(100);
        } else {
            tableData.printTable(100);
        }
    }

}