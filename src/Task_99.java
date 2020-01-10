import java.util.*;

public class Task_99 {
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        int h = cin.nextInt();
        int m = cin.nextInt();
        int n = cin.nextInt();

        int total = h*m*n + (h - 1);

        String[][][] mas = new String[h][m][n];

        int x1 = 0, y1 = 0, z1 = 0;//координаты начала
        int x2 = 0, y2 = 0, z2 = 0;//координаты конца

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < m; j++) {
                StringBuilder stringBuilder = new StringBuilder(cin.next());
                for (int k = 0; k < n; k++) {
                    mas[i][j][k] = String.valueOf(stringBuilder.charAt(k));
                    if(mas[i][j][k].compareTo("1") == 0 || mas[i][j][k].compareTo("2") == 0
                            || mas[i][j][k].compareTo("o") == 0){
                        total--;
                        if(mas[i][j][k].compareTo("1") == 0){
                            x1 = j;
                            y1 = k;
                            z1 = i;
                        }
                        if(mas[i][j][k].compareTo("2") == 0){
                            x2 = j;
                            y2 = k;
                            z2 = i;
                        }
                    }
                }
            }
        }

        mas[z1][x1][y1] = "0";

        Queue<Integer> stackColumn = new LinkedList<Integer>();//тут по столбцам
        Queue<Integer> stackLine = new LinkedList<Integer>();//тут в линию которые
        Queue<Integer> stackLevelUpDown = new LinkedList<Integer>();// хранение координат по этажам

        stackColumn.add(x1);
        stackLine.add(y1);
        stackLevelUpDown.add(z1);

        int[] masX = {0,0,1,-1,0};
        int[] masY = {1,-1,0,0,0};
        int[] masZ = {0,0,0,0,1};
        int[] masPerebor = {1,-1,1,-1,1};

        int number = 0;
        int x = 0, y = 0, z = 0;

        int amount = 0;
        boolean once = false;

        while(!stackColumn.isEmpty()) {

            x = stackColumn.peek();
            y = stackLine.peek();
            z = stackLevelUpDown.peek();

            int[] masXYZ = {y,y,x,x,z};
            int[] masNMH = {n,n,m,m,h};

            for (int i = 0; i < 5; i++) {
                if((masXYZ[i] + masPerebor[i] < masNMH[i] && masXYZ[i] + masPerebor[i] >= 0)
                        && mas[z + masZ[i]][x + masX[i]][y + masY[i]].compareTo(".") == 0){
                    number = Integer.parseInt(mas[z][x][y]) + 1;
                    mas[z + masZ[i]][x + masX[i]][y + masY[i]] = String.valueOf(number);
                    stackColumn.add(x + masX[i]);
                    stackLine.add(y + masY[i]);
                    stackLevelUpDown.add(z + masZ[i]);
                    total--;
                }
                else if(z + masZ[i] == z2 && x + masX[i] == x2 && y + masY[i] == y2){
                    number = Integer.parseInt(mas[z][x][y]) + 1;
                    if(amount > (number - 1)) amount = number;
                    if(once == false){
                        amount = number;
                        total--;
                        once = true;
                    }
                }
            }
            stackColumn.remove();
            stackLine.remove();
            stackLevelUpDown.remove();
        }
        System.out.println(amount*5);
    }
}

/**
 * 3 3 3
 * 1..
 * oo.
 * ...
 * ooo
 * ..o
 * .oo
 * ooo
 * o..
 * o.2
 */

/**
 * 2 5 5
 * .1...
 * .o.o.
 * .o...
 * ...o.
 * .....
 * ooooo
 * o.o.o
 * o.oo.
 * .o...
 * .o..2
 */

