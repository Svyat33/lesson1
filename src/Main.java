
import java.util.Scanner;


public class Main {

    private static final int SIZE = 3;
    private static final char EMPTY_VALUE=' ';
    private static final char player1Symbol = 'X';
    private static final char player2Symbol = '0';
    private static int whoWin=0;
    private static char[][] fields = new char[SIZE][SIZE];      //игровое поле
    private static int game_done = 0;


    private static int existempty = 0;

    private static void clear_fields(){
    for (int i=0;i<SIZE;i++){
        for(int j=0;j<SIZE;j++){
            fields[i][j] =    EMPTY_VALUE;
        }
    }
        game_done=0;
    }

    private static boolean check_game_status(){

             existempty=0;
        //1  po gorizontali
        int u1=0,u2=0;
        for (int i=0;i<SIZE;i++){
               u1=0;u2=0;
            for(int j=0;j<SIZE;j++){
                if(fields[i][j] ==EMPTY_VALUE){
                    existempty++;
                } else if(fields[i][j] ==player1Symbol)  {
                    u1++;
                } else {
                    u2++;
                }
            }
        }
        if (u1==3) {
            game_done=1;
            whoWin=1;
            return true;
        }
        if (u2==3) {
            game_done=1;
            whoWin=2;
            return true;
        }

         //2 po vertikali

        for (int i=0;i<SIZE;i++){
            u1=0;u2=0;
            for(int j=0;j<SIZE;j++){
                if(fields[j][i] ==EMPTY_VALUE){
                    existempty++;
                } else if(fields[j][i] ==player1Symbol)  {
                    u1++;
                } else {
                    u2++;
                }
            }
        }
        if (u1==3) {
            game_done=1;
            whoWin=1;
            return true;
        }
        if (u2==3) {
            game_done=1;
            whoWin=2;
            return true;
        }

        //3. diagonal
        u1=0;u2=0;
        for (int i=0;i<SIZE;i++){
            if(fields[i][i] ==EMPTY_VALUE){
                existempty++;
            } else if(fields[i][i] ==player1Symbol)  {
                u1++;
            } else {
                u2++;
            }
        }
        if (u1==3) {
            game_done=1;
            whoWin=1;
            return true;
        }
        if (u2==3) {
            game_done=1;
            whoWin=2;
            return true;
        }

        u1=0;
        u2=0;
        for (int i=0;i<SIZE;i++){
            if(fields[i][ (SIZE-i-1)] ==EMPTY_VALUE){
                existempty++;
            } else if(fields[i][ (SIZE-i-1)] ==player1Symbol)  {
                u1++;
            } else {
                u2++;
            }
        }
        if (u1==3) {
            game_done=1;
            whoWin=1;
            return true;
        }
        if (u2==3) {
            game_done=1;
            whoWin=2;
            return true;
        }

        if (existempty==0){
            game_done=1;
            return true;
        }
        return false;
    }


    public static void printFields(){

        for (int i=0;i<SIZE;i++){
            for(int j=0;j<SIZE;j++){
                System.out.print(i+":"+j+"["+fields[i][j]+"]\t");
            }
            System.out.println("");
        }
    }


     private static void printDoneMsg(){
         if (game_done==1){
             if (whoWin==1) {
                 System.out.println("User 1 WIN!");
             } else if (whoWin==2){
                 System.out.println("User 2 WIN!");
             } else {
                 System.out.println("done.");
             }
         }
     }

    private static int getInteger(){
        Scanner readHod  = new Scanner(System.in);
        int x=0;
        try{
            x = readHod.nextInt();
        }   catch (Exception e){
            System.out.print("Ошибка! Введите число: ");
            return getInteger();
        }
        return x;
    }

     private static void getUserStep(char us){

         Scanner readHod  = new Scanner(System.in);
         System.out.print("строка: ");
int x = getInteger();

         System.out.print("колонка: ");
         int y = getInteger();

         if (x>=0&&x<SIZE&&y>=0&&y<SIZE&& fields[x][y]==EMPTY_VALUE){
             fields[x][y] = us;
         } else {
             System.out.println("Эта ячейка ["+(x)+"]["+(y)+"] уже занята или недоступна.");
             printFields();
             getUserStep(us);
         }
     }

    private static void getMachineStep(char opponent, char mySymbol) {
        float[][] weight = new float[SIZE][SIZE];

        //default weight
        for (int i=0;i<SIZE;i++){
            for(int j=0;j<SIZE;j++){
                weight[i][j]=0;
            }
        }
        for (int i=0;i<SIZE;i++){
          weight[i][(SIZE-i-1)]+=0.25;
        }
        for (int i=0;i<SIZE;i++){
            weight[i][i]+=0.25;
        }


            //Calculate weight.

//1. gorizont
        boolean clearLineWeight;
        clearLineWeight = false;
        for (int i=0;i<SIZE;i++){
            int rowe=0;
            clearLineWeight = false;
            for(int j=0;j<SIZE;j++){
                if (fields[i][j]==opponent) {
                    rowe++;
                }

                if (fields[i][j]==mySymbol) {
                    clearLineWeight = true;
                }
            }
             if (!clearLineWeight){
                 for(int m=0;m<SIZE;m++){
                     if (fields[i][m]==EMPTY_VALUE){
                         weight[i][m]+=rowe;
                     }
                 }
             }

        }

//2. stolbci
        for (int i=0;i<SIZE;i++){
            int rowe=0;
            clearLineWeight = false;
            for(int j=0;j<SIZE;j++){
                if (fields[j][i]==opponent) {
                    rowe++;
                }

                if (fields[j][i]==mySymbol) {
                    clearLineWeight = true;
                }
            }

            if (!clearLineWeight){
                for(int j=0;j<SIZE;j++){
                    if (fields[j][i]==EMPTY_VALUE){
                        weight[j][i]+=rowe;
                    }
                }
            }

        }


        clearLineWeight = false;     int rowe=0;
        for (int i=0;i<SIZE;i++){
            if (fields[i][i]==opponent) {
                rowe++;
            }
            if (fields[i][i]==EMPTY_VALUE){
                weight[i][i]+=rowe;
            }
            if (fields[i][i]==mySymbol) {
                clearLineWeight = true;
            }
        }
        if (!clearLineWeight)    {
            for (int i=0;i<SIZE;i++){
                if (fields[i][i]==EMPTY_VALUE){
                    weight[i][i]+=rowe;
                }
            }
        }


        rowe=0;
        clearLineWeight = false;
        for (int i=0;i<SIZE;i++){
            if (fields[i][(SIZE-i-1)]==opponent) {
                rowe++;
            }

            if (fields[i][ (SIZE-i-1)]==mySymbol) {
                clearLineWeight = true;
            }
        }
        if (!clearLineWeight)    {
            for (int i=0;i<SIZE;i++){
                if (fields[i][ (SIZE-i-1)]==EMPTY_VALUE){
                    weight[i][ (SIZE-i-1)]+=rowe;
                }
            }
        }


        float maxval=-1;
        int maxI=0,maxJ=0;
        for (int i=0;i<SIZE;i++){
            for(int j=0;j<SIZE;j++){
                if (weight[i][j]>maxval){
                    maxval = weight[i][j];
                    maxI=i;maxJ=j;
                }
            }
        }
       fields[maxI][maxJ] = mySymbol;
    }


    public static void main(String[] args) {
       clear_fields();

       printFields();

        while (game_done==0){
            getUserStep(player1Symbol);
            printFields();
            if (check_game_status()){
                printDoneMsg();
                break;
            }


            System.out.println("---------------------------------");
            if (existempty>0){
                getMachineStep(player1Symbol, player2Symbol);
            }
            printFields();
            if (check_game_status()){
                printDoneMsg();
                break;
            }
            System.out.println("---------------------------------\n\n\n");


        }

    }

}
