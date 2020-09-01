package thread;

public class FirstThreadRunner extends Thread {


    private TableData tableData;
    public Boolean isSync = false;

    FirstThreadRunner(TableData tableData) {
        this.tableData = tableData;
    }

    public void run() {
        if (isSync) {
            tableData.syncPrintTable(5);
        } else {
            tableData.printTable(5);
        }
    }

}