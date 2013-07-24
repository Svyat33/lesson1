
import java.util.Scanner;


public class Main {

    private static final int SIZE = 3;
    private static final char EMPTY_VALUE=' ';
    private static final char player1Symbol = 'X';
    private static final char player2Symbol = '0';
    private static int whoWin=0;
    private static char[][] fields = new char[SIZE][SIZE];
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

    private static void check_game_status(){

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
        }
        if (u2==3) {
            game_done=1;
            whoWin=2;
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
        }
        if (u2==3) {
            game_done=1;
            whoWin=2;
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
        }
        if (u2==3) {
            game_done=1;
            whoWin=2;
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
        }
        if (u2==3) {
            game_done=1;
            whoWin=2;
        }


    }


    public static void display2(){

        for (int i=0;i<SIZE;i++){
            for(int j=0;j<SIZE;j++){
                System.out.print(i+":"+j+"["+fields[i][j]+"]\t");
            }
            System.out.println("");
        }
    }




     private static void getUserStep(char us){

         Scanner readHod  = new Scanner(System.in);
         System.out.print("Stroka: ");
         int x = readHod.nextInt();
         System.out.print("Kolonka: ");
         int y = readHod.nextInt();
         if (x>=0&&x<SIZE&&y>=0&&y<SIZE&& fields[x][y]==EMPTY_VALUE){
             fields[x][y] = us;
         } else {
             System.out.println("Eta yachejka ["+(x)+"]["+(y)+"] uge zanyata."+fields[x][y]);
             display2();
             getUserStep(us);
         }
     }

    private static void calcstep(char opponent,char mySymbol) {
        int[][] weight = new int[SIZE][SIZE];

        for (int i=0;i<SIZE;i++){
            for(int j=0;j<SIZE;j++){
                weight[i][j]=0;
            }
        }
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

     //         display(weight);
        int maxI=0,maxJ=0,maxval=-1;
     //   System.out.println("Max I"+maxI+" maxJ "+maxJ);
        for (int i=0;i<SIZE;i++){
            for(int j=0;j<SIZE;j++){
                if (weight[i][j]>maxval){
                    maxval = weight[i][j];
                    maxI=i;maxJ=j;
                }
            }
        }
     //  System.out.println("Max I"+maxI+" maxJ "+maxJ);
       fields[maxI][maxJ] = mySymbol;
    }


    public static void main(String[] args) {
       clear_fields();

       display2();

        while (game_done==0){
            getUserStep(player1Symbol);
            display2();
            check_game_status();
            if (game_done==1){
                if (whoWin==1) {
                System.out.println("User 1 WIN!");
                } else {
                    System.out.println("User 2 WIN!");
                }
            }
          //  System.out.println("Avail "+existempty);
            System.out.println("---------------------------------");
            if (existempty>0){
                calcstep(player1Symbol,player2Symbol);
            }
            display2();
            check_game_status();
            if (game_done==1){
                if (whoWin==1) {
                    System.out.println("User 1 WIN!");
                } else {
                    System.out.println("User 2 WIN!");
                }
            }
            if (existempty==0) {
                game_done=1;
                System.out.println("no win.");
            }
        }

    }

}
