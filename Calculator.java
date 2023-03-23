public class Calculator {
    int n;
    public double[][] inputMatrix = new double[21][22];
    double tmpX[] = new double[21];
    double roots[] = new double[21];
    double inaccuracy[] = new double[21];
    int r;

    int iterationsCount = 0;

    public Calculator(int n, double[][] inputMatrix) {
        this.n = n;
        this.inputMatrix = inputMatrix;
    }



    public void getResul() {
        do {
            for (int i = 0; i < n; i++) {
                roots[i] = inputMatrix[i][n] / inputMatrix[i][i];//b
                for (int j = 0; j < n; j++) {
                    if (i != j) {
                        roots[i] -= inputMatrix[i][j] / inputMatrix[i][i] * tmpX[j];//(b-ax)/a
                        //roots[i] save x
                    }
                }
                inaccuracy[i] = Math.abs(roots[i] - tmpX[i]);

                r = i;
            }
            tmpX = roots.clone();
            iterationsCount++;
            System.out.println("The number of iterations is:" + iterationsCount + "\nThe result is:");
            for (int i = 0; i < n; i++) {
                System.out.printf("%3.11f ", roots[i]);
                System.out.printf("\n");
            }
            System.out.println();
            System.out.println();
        } while (inaccuracy[r] > 0.0000000001 && inaccuracy[r]!=Double.POSITIVE_INFINITY);
    }

    void printinaccuracy() {
        System.out.println("Error Analysis:" + inaccuracy[r]);
    }

    void printiterationsCount() {
        System.out.println("Number of iterations:" + iterationsCount);
    }

    void printMatrix(double[][] matrix) {
        System.out.println("System: printing matrix.");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= n; j++) {
                System.out.printf("%3f ", matrix[i][j]);
            }
            System.out.println();
        }
    }

    void printResult() {
        System.out.println("The result is: ");
        for (int i = 0; i < n; i++) {
            System.out.printf("%3.11f ", roots[i]);
        }
        System.out.println();
    }

    void error() {
        if (inaccuracy[r]==Double.POSITIVE_INFINITY) {
            System.out.println("Does not converge,so there is no answer in this way");
        }
    }
}
