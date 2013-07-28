package modules;

/**
 * Created with IntelliJ IDEA.
 * User: kss
 * Date: 25.07.13
 * Time: 0:23
 * To change this template use File | Settings | File Templates.
 */
public class UserStep {

    private int i,j;
    private char userSymbol;

    public UserStep(int i, int j, char userSymbol) {
        this.i = i;
        this.j = j;
        this.userSymbol = userSymbol;
    }

    public char getUserSymbol() {
        return userSymbol;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }
}
