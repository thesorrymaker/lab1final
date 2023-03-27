
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static int n;//n:the size of the array
    private static double[][] inputMatrix = new double[21][22];//The topic requirements are not more than 20, just write and write

    static void readData() {
        ArrayList<Double> arrayList = new ArrayList<>();
        String choice;
        System.out.print("Choose the module you want(input/file):\n");
        choice = new Scanner(System.in).nextLine();
        if ("file".equalsIgnoreCase(choice)) {
            try {
                FileInputStream path = new FileInputStream("input");
                DataInputStream inFile = new DataInputStream(path);
                BufferedReader br = new BufferedReader(new InputStreamReader(inFile));
                String data;
                while ((data = br.readLine()) != null) {
                    String[] tmp = data.split(" ");   //Split space
                    for (String s : tmp)
                        arrayList.add(Double.parseDouble(s));
                    n++;
                }
            } catch (NumberFormatException | IOException e) {
                System.out.println("Input Error!");
                System.exit(0);
            }
            System.out.println("Matrix:");
            System.out.println(n);
            System.out.println();
        } else if ("input".equalsIgnoreCase(choice)) {
            System.out.println("System: Input number of size n.");//input size
            Scanner sc = new Scanner(System.in);
            n = sc.nextInt();
            System.out.println("System: Now please input matrix (n*(n+1)).");//input array
            for (int i = 0; i < n; i++) {
                for (int j = 0; j <= n; j++) {
                    inputMatrix[i][j] = sc.nextDouble();
                }
            }
        }
    }

    public static void main(String[] args) {
        readData();
        Calculator calculator = new Calculator(n, inputMatrix);
        calculator.getResul();
        calculator.printMatrix(calculator.inputMatrix);
        calculator.printResult();
        calculator.printiterationsCount();
        calculator.printinaccuracy();
//        calculator.error();
//Output the original formula, answer, number of iterations, error analysis, and whether the answer after error analysis is wrong
    }


}
