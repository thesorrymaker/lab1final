public class Calculator {
    int n;
    public double[][] inputMatrix = new double[21][22];
    double tmpX[] = new double[3];
    double roots[] = new double[21];
    double inaccuracy[] = new double[21];
    double change[][] = new double[21][22];
    double check[][]=new double[21][22];
    int r;

    int iterationsCount = 0;

    public Calculator(int n, double[][] inputMatrix) {
        this.n = n;
        this.inputMatrix = inputMatrix;
    }


    public void getResul() {
        for (int i = 0; i < n; i++) {
            for (int k = 0; k < n; k++) {
                if (i != k) {
                    if (inputMatrix[i][i] < inputMatrix[k][i]) {
                        //                       System.out.println("nothing");
                        for (int l = 0; l <= n; l++) {
                            change[i][l] = inputMatrix[i][l];
                            inputMatrix[i][l] = inputMatrix[k][l];
                            inputMatrix[k][l] = change[i][l];
                        }
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            tmpX[i] = inputMatrix[i][n] / inputMatrix[i][i];
            System.out.println("x0=" + tmpX[i]);
        }

        do {
            for (int i = 0; i < n; i++) {
                roots[i] = inputMatrix[i][n] / inputMatrix[i][i];//b
                for (int j = 0; j < n; j++) {
                    if (i != j) {
                        roots[i] -= inputMatrix[i][j] / inputMatrix[i][i] * tmpX[j];//(b-ax)/a,xk
                        //roots[i] save xk+1
                    }
                }
                inaccuracy[i] = Math.abs(roots[i] - tmpX[i]);
                r = i;
            }
            tmpX = roots.clone();
            iterationsCount++;
            System.out.println("The number of iterations is:" + iterationsCount + "\nThe result is:");
            for (int i = 0; i < n; i++) {
                System.out.printf("%3.3f ", roots[i]);
                System.out.printf("\n");
            }
            System.out.println();
            System.out.println();
        } while (inaccuracy[r] > 0.001 && inaccuracy[r] != Double.POSITIVE_INFINITY);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                check[i][n]+=inputMatrix[i][j]*roots[j];
            }
            check[i][n]=check[i][n]-inputMatrix[i][n];
            System.out.println("No:"+i+" in order to check is it close to 0: "+check[i][n]);
        }
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
                System.out.printf("%3.0f ", matrix[i][j]);
            }
            System.out.println();
        }
    }

    void printResult() {
        System.out.println("The result is: ");
        for (int i = 0; i < n; i++) {
            System.out.printf("%3.3f ", roots[i]);

        }
        System.out.println();
    }

/*    void error() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= n; j++){
              check[i][n]=inputMatrix[i][j]*roots[i] ;
            }
            check[i][n]=check[i][n]-inputMatrix[i][n];
            System.out.println("check it closes to 0,or not:"+check[i]);
        }
    }*/
}
