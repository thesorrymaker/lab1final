import java.util.Scanner;

public class test1 {

    public static void main(String[] args) {
        int n;

        Scanner s = new Scanner(System.in);
        System.out.print("System: Input number of size n.\n");
        n = s.nextInt();
        double[][] A = new double[n][n];
        double[][] b = new double[n][1];
        System.out.println("System: Now please input matrix A：");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                A[i][j] = s.nextDouble();
            }
        }
        System.out.println("System: Now please input matrix b：");
        for (int i = 0; i < n; i++) {
            b[i][0] = s.nextDouble();
        }
        simple(A, b);
//        double[][] A = {{10, -1, 0}, {-1,10,-2}, {0,-4,10}};
//        double[][] b = {{9}, {7}, {6}};
//        double[][] A = {{1, 2, -2}, {1, 1, 1}, {2, 2, 1}};
//        double[][] b = {{1}, {2}, {3}};
//        double[][] A = {{-4, 1, 1, 1}, {1,-4, 1,1}, {1,1,-4,1},{1,1,1,-4}};
//        double[][] b = {{1}, {1}, {1},{1}};
    }

    public static void simple(double[][] A, double[][] b) {
        int n = A.length;
        double[][] D = new double[n][n];
        double[][] LU = new double[n][n];
        double[][] D_inverse;
        double[][] B;
        double x1[][] = new double[n][1];
        double x2[][];
//         初始化D
        for (int i = 0; i < n; i++) {
            D[i][i] = A[i][i];
        }
//        初始化L+U
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    LU[i][i] = 0;
                } else {
                    LU[i][j] = -A[i][j];
                }
            }
        }

        D_inverse = getInverseMatrix(D, n);
        B = getMultiplyMatrix(D_inverse, LU);
        double[][] f = getMultiplyMatrix(D_inverse, b);
//        print(B);
        for (int i = 0; i < n; i++) {
            x1[i][0] = 0;
        }
        boolean flag = true;
        int z = 0;//迭代次数
        while (flag) {
            flag = false;
            x2 = getSumMatrix(getMultiplyMatrix(B, x1), f);
            z++;
            for (int i = 0; i < n; i++) {
                if (Math.abs(x2[i][0] - x1[i][0]) > 0.000000001) {
                    flag = true;
                }
            }
            System.out.println("The number of iterations is:" + z + "\nThe result is:");
            print(x2);
            x1 = x2;
        }

    }

    public static double[][] getInverseMatrix(double[][] A, int n) {
        double[][] U = new double[n][n];
        double[][] L = new double[n][n];
        double[][] Uinv = new double[n][n];
        double[][] Linv = new double[n][n];
        int size = n;
        // fill 1 at L's diagonal
        for (int i = 0; i < size; i++) {
            L[i][i] = 1.0;
        }
        // calculate the U's first row
        for (int j = 0; j < size; j++) {
            U[0][j] = A[0][j];
        }
        // calculate the L's first column
        for (int i = 1; i < size; i++) {
            L[i][0] = A[i][0] / U[0][0];
        }
        // iterative calculation of other rows and columns
        for (int k = 1; k < size; k++) {
            // the k_th row of U
            for (int j = k; j < size; j++) {
                // sum(L_kt*U_tj),t
                double s = 0.0;
                for (int t = 0; t < k; t++) {
                    s += L[k][t] * U[t][j];
                }
                // U_kj = A_kj - s
                U[k][j] = A[k][j] - s;
            }
            // the k_th column of L
            for (int i = k; i < size; i++) {
                // sum(L_it*U_tk),t
                double s = 0.0;
                for (int t = 0; t < k; t++) {
                    s += L[i][t] * U[t][k];
                }
                // L_ik = (A_ik - s) / U_kk
                L[i][k] = (A[i][k] - s) / U[k][k];
            }
        }

        // calculate L_inv
        for (int j = 0; j < size; j++) {
            for (int i = j; i < size; i++) {
                if (i == j) Linv[i][j] = 1 / L[i][j];
                else if (i < j) Linv[i][j] = 0;
                else {
                    double s = 0.0;
                    for (int k = j; k < i; k++) {
                        s += L[i][k] * Linv[k][j];
                    }
                    Linv[i][j] = -Linv[j][j] * s;
                }
            }
        }
        // calculate U_inv
        for (int j = 0; j < size; j++) {
            for (int i = j; i >= 0; i--) {
                if (i == j) Uinv[i][j] = 1 / U[i][j];
                else if (i > j) Uinv[i][j] = 0;
                else {
                    double s = 0.0;
                    for (int k = i + 1; k <= j; k++) {
                        s += U[i][k] * Uinv[k][j];
                    }
                    Uinv[i][j] = -1 / U[i][i] * s;
                }
            }
        }
        double[][] inv = new double[n][n];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                for (int k = 0; k < size; k++) {
                    inv[i][j] += Uinv[i][k] * Linv[k][j];
                }
            }
        }

//        print(L);
//        print(U);
//        print(Linv);
//        print(Uinv);
        return inv;
    }

    public static double[][] getMultiplyMatrix(double[][] a, double[][] b) {
        //当a的列数与矩阵b的行数不相等时，不能进行点乘，返回null
        if (a[0].length != b.length)
            return null;
        //c矩阵的行数y，与列数x
        int y = a.length;
        int x = b[0].length;
        double c[][] = new double[y][x];
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                //c矩阵的第i行第j列所对应的数值，等于a矩阵的第i行分别乘以b矩阵的第j列之和
                for (int k = 0; k < b.length; k++) {
                    c[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return c;
    }

    public static double[][] getSumMatrix(double a[][], double b[][]) {
        //当a的列数与矩阵b的行数不相等时，不能进行相加，返回null
        if (a.length != b.length && a[0].length != b[0].length)
            return null;
        //c矩阵的行数y，与列数x
        int x = a.length;
        int y = a[0].length;
        double c[][] = new double[x][y];
        for (int i = 0; i < x; i++)
            for (int j = 0; j < y; j++)
                //c矩阵的第i行第j列所对应的数值，等于a矩阵的第i行分别乘以b矩阵的第j列之和
                c[i][j] = a[i][j] + b[i][j];
        return c;
    }

    public static void print(double[][] M) {
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M[0].length; j++) {
                System.out.printf("%12.9f" ,M[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }


}
