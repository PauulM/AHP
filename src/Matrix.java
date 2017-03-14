import java.util.ArrayList;

/**
 * Created by pawma on 14.03.2017.
 */
public class Matrix {

    private ArrayList<ArrayList<Float>> rowsVector;
    private int rows;
    private int cols;
    private boolean initialized;

    public Matrix(int rows, int cols){
        initialized = false;
        this.rows = rows;
        this.cols = cols;
        rowsVector = new ArrayList<>();
    }

    public void initializeWithInt(float val){
        try {
            if (initialized) throw new Exception();
            ArrayList<Float> nextRow;
            for (int i = 0; i < rows; i++) {
                nextRow = new ArrayList<>();
                for (int j = 0; j < cols; j++) {
                    nextRow.add(val);
                }
                rowsVector.add(nextRow);
            }
        }
        catch(Exception ex){
            System.out.println("MATRIX ALREADY INITIALIZED");
        }
    }

    public Float getValueByIndex(int rowIndex, int colIndex) throws RuntimeException{
        return rowsVector.get(rowIndex).get(colIndex);
    }

    public void setValueByIndex(Float value, int rowIndex, int colIndex)throws RuntimeException{
        rowsVector.get(rowIndex).set(colIndex, value);
    }

    public ArrayList<Float> getRowByIndex(int rowIndex)throws RuntimeException{
        return rowsVector.get(rowIndex);
    }

    public ArrayList<Float> getColByIndex(int colIndex)throws RuntimeException{
        ArrayList<Float> result = new ArrayList<>();
        for(int i=0;i<rows;i++){
            result.add(rowsVector.get(i).get(colIndex));
        }
        return result;
    }

//    public ArrayList<ArrayList<Float>> getRowsVector() {
//        return rowsVector;
//    }

//    public void setRowsVector(ArrayList<ArrayList<Float>> rowsVector) {
//        this.rowsVector = rowsVector;
//    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }
}
