import java.util.InputMismatchException;
import java.util.Scanner;

public class Soberwalk_problem {
    static int takeinp(String message,Scanner sc) {
        System.out.println(message);
        int x= 0;
        try {
            x = sc.nextInt();
            if (x < 0) {
                System.out.println("Please enter a number greater than 0");
                x = takeinp(message,sc);
            }
        }
        catch (InputMismatchException e) {
            System.out.println("There was an error");
            x = takeinp(message,sc);

        }
        return x;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n=takeinp("Please enter the no of steps: ",sc);
        char c = 'R';
        int x = 0, y = 0;
        int u = 0;
        while(n>0){
            switch(c){
                case 'R':
                    x +=  u;
                    u+=10;
                    c ='U';
                    break;
                case 'U':
                    y += u;
                    u +=10;
                    c = 'L';
                    break;
                case 'L':
                    x -= u;
                    u +=10;
                    c = 'D';
                    break;
                case 'D':
                    y -= u ;
                    u+=10;
                    c = 'R';
                    break;
            }
            n--;
        }
        System.out.println(x+" "+y);
    }


    }

