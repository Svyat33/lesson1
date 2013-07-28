package modules;

/**
 * Created with IntelliJ IDEA.
 * User: kss
 * Date: 24.07.13
 * Time: 21:54
 * Сюда вынесу всю работу с игровым полем
 */
public class Fields {
    public static final int SIZE = 3;
    private char[][] fields = new char[SIZE][SIZE];
    private final char EMPTY_VALUE=' ';
    private char player1Symbol='+';
    private char player2Symbol='0';

    private int game_done=0;
    private String fieldsName = "Игра А против Б";



    private Player winner;


    public Fields(String fieldsName) {
        this.fieldsName = fieldsName;

        for (int i = 0 ; i < this.SIZE ; i++){
            for (int j = 0 ; j < this.SIZE; j++) {
               fields[i][j] = this.EMPTY_VALUE;
            }
        }

    }

    /**
     *  проверить не закончилась ли игра и есть ли победитель
     * @return
     */
    public boolean check(Player p1, Player p2){
       int existempty=0;
        //1  po gorizontali
        int u1=0,u2=0;
        for (int i=0;i<SIZE;i++){
            u1=0;u2=0;
            for(int j=0;j<SIZE;j++){
                if(fields[i][j] ==EMPTY_VALUE){
                    existempty++;
                } else if(fields[i][j] ==p1.mySymbol)  {
                    u1++;
                } else {
                    u2++;
                }
            }
            if (u1==3||u2==3) break;
        }
        if (u1==3) {
            game_done=1;
            winner = p1;
            return true;
        }
        if (u2==3) {
            game_done=1;
            winner = p2;
            return true;
        }

        //2 po vertikali
        u1=0;u2=0;
        for (int i=0;i<SIZE;i++){
            u1=0;u2=0;
            for(int j=0;j<SIZE;j++){
                if(fields[j][i] ==EMPTY_VALUE){
                    existempty++;
                } else if(fields[j][i] ==p1.mySymbol)  {
                    u1++;
                } else {
                    u2++;
                }
            }
            if (u1==3||u2==3) break;
        }
        if (u1==3) {
            game_done=1;
            winner = p1;
            return true;
        }
        if (u2==3) {
            game_done=1;
            winner = p2;
            return true;
        }

        //3. diagonal
        u1=0;u2=0;
        for (int i=0;i<SIZE;i++){
            if(fields[i][i] ==EMPTY_VALUE){
                existempty++;
            } else if(fields[i][i] ==p1.mySymbol)  {
                u1++;
            } else {
                u2++;
            }
        }
        if (u1==3) {
            game_done=1;
            winner = p1;
            return true;
        }
        if (u2==3) {
            game_done=1;
            winner = p2;
            return true;
        }

        u1=0;
        u2=0;
        for (int i=0;i<SIZE;i++){
            if(fields[i][ (SIZE-i-1)] ==EMPTY_VALUE){
                existempty++;
            } else if(fields[i][ (SIZE-i-1)] ==p1.mySymbol)  {
                u1++;
            } else {
                u2++;
            }
        }
        if (u1==3) {
            game_done=1;
            winner = p1;
            return true;
        }
        if (u2==3) {
            game_done=1;
            winner = p2;
            return true;
        }

        if (existempty==0){
            game_done=1;
            winner = null;
            return true;
        }
        return false;
    }

    /**
     * Поставить по указанным координатам полученный символ.
     * @param s
     */
    public void setXY(UserStep s){
        try{
        fields[s.getI()][s.getJ()]=s.getUserSymbol();
        } catch(Exception e) {
            System.out.println("Ошибка! "+e.getMessage());
        }
    }


    public boolean checkEmptyField(int a, int b){
        if (a >= 0 && a < SIZE && b >=0 && b < SIZE){
        //    System.out.println("Значение норм...");
            if (fields[a][b]==EMPTY_VALUE){
       //         System.out.println(" тут пусто ОК");
                return true;

            }
       //     System.out.println("тут не пусто!");

        }   else {
        //    System.out.println("Нет такой ячейки! "+SIZE);
        }
        return false;
    }

    /**
     * Совпадает ли владелец поля с переданным.
     * @param a
     * @param b
     * @param ss
     * @return
     */
    public boolean checkOwner(int a, int b, char ss){
        if (a >= 0 && a <SIZE && b >= 0 && b < SIZE){
            if (fields[a][b]==ss){
                return true;
            }

        }
        return false;
    }


    public void print(){
        System.out.println(this.fieldsName);
        for (int i = 0 ; i < this.SIZE ; i++){
           for (int j = 0 ; j < this.SIZE; j++) {
               System.out.print(i+":"+j+"["+this.fields[i][j]+"]\t\t");
           }
            System.out.println();
        }
    }

    public void printGameDone(){
        System.out.println(this.fieldsName);
        for (int i = 0 ; i < this.SIZE ; i++){
            for (int j = 0 ; j < this.SIZE; j++) {
                System.out.print("["+this.fields[i][j]+"]");
            }
            System.out.println();
        }
        System.out.println("Игра завершена!");
        if (winner!=null) {
        System.out.println("Победитель: " + winner.getName());
        } else {
            System.out.println("Победила эта проклятая дружба.");
        }
    }
}
