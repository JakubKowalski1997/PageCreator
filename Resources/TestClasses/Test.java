package TestClasses;

/**
 * Created by Konrad on 2017-07-04.
 */
public abstract class Test {

    private String unitName;
    private int errCnt = 0;

    Test(String unit) {
        unitName = unit;
    }

    protected void reportError(String msg) {
        System.out.println("In unit " + unitName + " " + msg);
        ++errCnt;
    }

    protected void reportResults() {
        System.out.println("Errors in unit " + unitName + ": " + errCnt);
    }

    public void test() {
        System.out.println("Started testing unit: " + unitName);
    }
}
