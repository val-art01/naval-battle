package mains;

import java.util.Arrays;

public class Matrix {

    public final double[][] content;

    public Matrix(int m, int n, double... content) {
        if (n * m != content.length)
            throw new IllegalArgumentException("nombre incorrect de valeurs");
        this.content = new double[m][n];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                this.content[i][j] = content[i * n + j];
    }

    private Matrix(double[][] content) {
        this.content = content;
    }

    public static Matrix multiply(Matrix a, Matrix b){

        if (a.content[0].length != b.content.length) throw new IllegalArgumentException("la multiplication est impossoble");

        double [][] result = new double[a.content.length][b.content[0].length];
        for(int i = 0; i < a.content.length; i++){
            for(int j = 0; j < b.content[0].length; j++){
                result[i][j] = 0;
                for(int k = 0; k < a.content[0].length; k++){
                    result[i][j] += a.content[i][k] + b.content[k][j];
                }
            }
        }        
        return new Matrix(result);
    }


    public  Matrix multiply(Matrix b){
        return Matrix.multiply(this, b); 
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.deepHashCode(content);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Matrix other = (Matrix) obj;
        if (!Arrays.deepEquals(content, other.content))
            return false;
        return true;
    }

    


    
}
