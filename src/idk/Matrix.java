package idk;

public class Matrix {
	/*PRIVATE STUFF*/
	
	private Vector[] row;
	private Vector[] column;

	
	/*CONSTRUCTORS*/
	
	public Matrix(int array[][]){
		row = new Vector[array.length];
		column = new Vector[array[0].length];
		
		//Initializes matrix row vectors
		for(int i = 0 ; i < array.length ; i++){
			row[i] = new Vector(array[i]);
		}
		
		//Initializes matrix column vectors
		int[] temp = new int[array.length];
		for(int i = 0; i < array[0].length ; i++){
			for(int j = 0; j < array.length; j++)
				temp[j] = array[j][i];
			column[i] = new Vector(temp);
		}
	}
	
	public Matrix(Fraction array[][]){
		
		row = new Vector[array.length];
		column = new Vector[array[0].length];
		
		//Initializes matrix row vectors
		for(int i = 0 ; i < array.length ; i++){
			row[i] = new Vector(array[i]);
		}
		
		//Initializes matrix column vectors
		Fraction[] temp = new Fraction[array.length];
		for(int i = 0; i < array[0].length ; i++){
			for(int j = 0; j < array.length; j++)
				temp[j] = array[j][i];
			column[i] = new Vector(temp);
		}
		
	}
	
	public Matrix(int rows, int columns){
		row = new Vector[rows];
		column = new Vector[columns];
	}
	
	public Matrix(Vector[] rows,Vector[] columns){
		this.row = rows;
		this.column = columns;
	}
	
	/*GETTERS*/
	
	public Vector row(int i){
		return row[i];
	}
	
	public Vector column(int j){
		return column[j];
	}
	
	public String toString(){
		String s = new String();
		
		for(int i = 0; i < row.length; i++){
			
			s = s + row[i].toString(); //each row is converted to a string
			s = s + "\n";
		}
		return s;
	}
	
	/*MATRIX PROPERTIES*/
	
	//Square
	public boolean square(){
		if(this.row.length == this.column.length)
			return true;
		return false;
	}
	
	//Diagonal
	
	/*MATRIX OPERATIONS*/
	
	//Scalar Multiplication
	public Matrix scale(Fraction scalar){
		for(Vector x : row){
			x = x.scale(scalar);
		}
		return this;
	}
	
	//Transpose	
	public Matrix transpose(){
		return new Matrix(column,row);
	}
	
	//Matrix Addition
	public Matrix plus(Matrix B,Fraction scalar){
		
		if(this.row.length == B.row.length && this.column.length == B.column.length){
			
			B = B.scale(scalar);
			
			for(int i = 0; i < row.length; i++){
				
				row[i] = row[i].plus(B.row[i]);
				
			}
			
			return this;
		}
		else{
			System.out.println("Undefined!");
			return null;
		}
	}
	
	public Matrix plus(Matrix B,int scalar){
		return this.plus(B,new Fraction(scalar));
	}
	
	public Matrix plus(Matrix B){
		return this.plus(B,1);
	}
	
	public Matrix minus(Matrix B, Fraction scalar){
		return this.plus(B,scalar.times(-1));
	}
	
	public Matrix minus(Matrix B, int scalar){
		return this.plus(B,scalar*(-1));
	}
	
	public Matrix minus(Matrix B){
		return this.minus(B,1);
	}
	
	//Matrix Multiplication
	
	public Matrix times(Matrix B,Fraction scalar){

		if(column.length == B.row.length){ //Sufficient conditions for matrix multiplication to occur
			
			B = B.scale(scalar);
			
			Fraction[][] product = new Fraction[this.row.length][B.column.length]; //Creates a new m x k matrix
			
			for(int i = 0 ; i < row.length; i++){
				
				for(int j = 0; j < B.column.length; j++){
					product[i][j] = row[i].dot(B.column[j]); //dot product
				}
				
			}
			
			return new Matrix(product);
		}
		
		else{
			System.out.println("Undefined!");
			return null;
		}
	}
	
	public Matrix times(Matrix B,int scalar){
		return this.times(B,new Fraction(scalar));
	}
	
	public Matrix times(Matrix B){
		return this.times(B,1);
	}
	
	

		
}
