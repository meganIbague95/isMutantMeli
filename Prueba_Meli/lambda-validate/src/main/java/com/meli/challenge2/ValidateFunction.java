package com.meli.challenge2;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.meli.Stats;
import com.meli.service.CustomExceptions;
import com.meli.service.DynamoService;
import com.meli.service.DynamoServiceImpl;

public class ValidateFunction implements RequestHandler<Request,Boolean>{

    private int cont = 0;
    private int humanCont = 0;
    private int rows = 0;
    private int columns = 0;

    DynamoService service = new DynamoServiceImpl();

    @Override
    public Boolean handleRequest(Request dna, Context context) {
        return isMutant(dna);
    }

    public String[][] transformArray(String[] array) {
        rows = array[0].length();
        columns = array.length;
        String[][] matrix = new String[array[0].length()][array.length];
        for (int i = 0; i < array.length; i++) {
            String word = array[i];
            for (int x = 0; x < word.length(); x++) {
                if(!word.substring(x, x + 1).equals("A") && !word.substring(x, x + 1).equals("C") && !word.substring(x, x + 1).equals("G") && !word.substring(x, x + 1).equals("T") && !word.substring(x, x + 1).equals("")){
                    throw new CustomExceptions("La secuencia de ADN debe traer solo las letras A,T,C,G");
                }else {
                    matrix[i][x] = word.substring(x, x + 1);
                }
            }
        }
        return matrix;
    }

    public int isMutant(String letra) {
        String palabraA = "AAAA";
        String palabraC = "CCCC";
        String palabraG = "GGGG";
        String palabraT = "TTTT";
        if (letra.contains(palabraA) || letra.contains(palabraC) || letra.contains(palabraG) || letra.contains(palabraT)) {
            cont++;
        }else {
            humanCont++;
        }
        return cont;
    }

    public void traverseMatrixHorizontally(String[][] matrix) {
        String letter = "";
        for (int x = 0; x < matrix.length; x++) {
            String word = "";
            for (int y = 0; y < matrix[x].length; y++) {
                letter = matrix[x][y];
                word = word + letter;
            }
            isMutant(word);
        }
    }

    public boolean isMutant(Request dna) {
        //cont=0;
        String[][] matrix = transformArray(dna.getDna());
        traverseMatrixHorizontally(matrix);
        traverseMatrixVertically(matrix);
        traverseRightDiagonal(matrix);
        traverseDiagonal(matrix);
        Double ratio = (double)cont/humanCont;
        Stats stats= new Stats(cont,humanCont,ratio);
        service.saveData(stats);
        if (cont > 1)
            return true;

        throw new CustomExceptions("403-forbbiden");
    }

    public void traverseMatrixVertically(String[][] matrix) {
        String letter = "";
        for (int x = 0; x < matrix.length; x++) {
            String word = "";
            for (int y = 0; y < matrix.length; y++) {
                letter = matrix[y][x];
                word = word + letter;
            }
            isMutant(word);
        }
    }

    public void traverseDiagonal(String[][] matrix) {
        String letter = "";
        int i;
        int j;
        for (int k = 0; k <= rows - 1; k++) {
            String word = "";
            i = k;
            j = 0;
            while (i >= 0) {
                letter = matrix[i][j];
                word = word + letter;
                i = i - 1;
                j = j + 1;
            }
            isMutant(word);
        }
        for (int k = 0; k < columns; k++) {
            String word = "";
            i = rows - 1;
            j = k;
            while (j <= columns - 1) {
                letter = matrix[i][j];
                word = word + letter;
                i = i - 1;
                j = j + 1;
            }
             isMutant(word);
        }
    }

    public void traverseRightDiagonal(String[][] matrix) {
        String letter = "";
        int i;
        int j;
        for (int k = 0; k < columns; k++) {
            String word = "";
            i = 0;
            j = k + 1;
            while (j <= columns - 1) {
                letter = matrix[i][j];
                word = word + letter;
                i = i + 1;
                j = j + 1;
            }
             isMutant(word);
        }
        for (int k = 0; k <= rows - 1; k++) {
            String word = "";
            i = k;
            j = 0;
            while (i <= rows - 1) {
                letter = matrix[i][j];
                word = word + letter;
                i = i + 1;
                j = j + 1;
            }
            isMutant(word);
        }
    }
}
