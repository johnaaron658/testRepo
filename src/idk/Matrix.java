package idk;

public class Matrix {
	
	/*PRIVATE STUFF*/
	
	private Fraction[][] INNER_MATRIX;
	
	
	/*PUBLIC STUFF*/
	
	public Vector[] row;
	public Vector[] column;
	
	
	/*CONSTRUCTORS*/
	
	public Matrix(Fraction array[][]){
		INNER_MATRIX = array;
		row = new Vector[array.length];
		column = new Vector[array[0].length];
		
		//Initializes matrix row vectors
		for(int i = 0 ; i < array.length ; i++){
			row[i] = new Vector(array[i]);
		}
		
		//Initializes matrix column vectors
		Fraction[] temp = new Fraction[array.length];
		for(int k = 0; k < array[0].length ; k++){
			for(int j = 0; j < array.length; j++)
				temp[j] = array[j][k];
			column[k] = new Vector(temp);
		}	
	}
	
	public Matrix(int matrix[][]){
		row = new Vector[matrix.length];
		column = new Vector[matrix[0].length];
		
		//Creates inner matrix with same size as int matrix
		INNER_MATRIX = new Fraction[matrix.length][matrix[0].length];
		
		//temporary Fraction array to store column entries
		Fraction[] temp = new Fraction[matrix.length];
		
		for(int i = 0; i < matrix.length; i++){
			
			for(int j = 0 ; j < matrix[0].length; j++){
				INNER_MATRIX[i][j] = new Fraction(matrix[i][j]); //For initializing INNER_MATRIX
				
				if(i == 0){										 //For initializing column vectors (but this only has to happen once in the outer loop)
					for(int k = 0; k < matrix.length; k++)
						temp[k] = new Fraction(matrix[k][j]);	
					column[j] = new Vector(temp);
				}
			}
			row[i] = new Vector(matrix[i]);
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
	
	public Fraction[][] toGrid(){
		return INNER_MATRIX;
	}
	
	/*MATRIX ALTERATIONS*/
	
	
	
	/*MATRIX PROPERTIES*/
	/*CONTENTS:
	 * -Square
	 * -Diagonal
	 * -Trace*/
	
	//Square
	public boolean square(){
		if(this.row.length == this.column.length) //IF same dimensions
			return true;
		return false; //Else
	}
	
	//Diagonal
	public boolean diagonal(){
		if(this.square() == false){
			System.out.println("Matrix is not square in the first place");
			return false;
		}
		int order = this.row.length;
		for(int i = 0; i < order; i++){			
			for(int j = 0; j < order; j++){
				
				//if one of the diagonal entries is zero or if one of the non-diagonal entries is non-zero
				if( (i == j && this.row(i).column(j).isEqualTo(new Fraction(0)) )||
					(i != j && !this.row(i).column(j).isEqualTo(new Fraction(0)) ) ) 
					return false;
			}
		}
		
		return true;
	}
	
	//Trace
	public Fraction trace(){
		if(this.square() == false){
			System.out.println("This matrix is not square. It cannot have a trace.");
			return null;
		}
		
		int order = this.row.length;
		Fraction sum = new Fraction(0); //Initialize 0 fraction
		
		//Add all entries in the diagonal
		for(int i = 0; i < order; i++){
			for (int j = 0; j < order ;j++){
				if(i == j)
					sum = sum.plus(this.row(i).column(j));
			}
		}
		return sum;
	}
	
	
	/*MATRIX COMPARISON*/
	/*Contents: 
	 * -Size comparison */
	
	//Size comparison
	public boolean hasSameSizeAs(Matrix B){
		if(this.row.length == B.row.length && this.column.length == B.column.length)
			return true;
		return false;
	}
	
	
	/*MATRIX OPERATIONS*/
	/*Contents: 
	 * -Scalar Multiplication
	 * -Transpose
	 * -Addition
	 * -Multiplication */
	
	//Scalar Multiplication
	public Matrix scale(Fraction scalar){
		for(Vector x : row){
			x = x.scale(scalar);
		}
		return this;
	}
	
	//Transpose	
	public Matrix transpose(){
		return new Matrix(this.column,this.row);
	}
	
	//Matrix Addition
	public Matrix plus(Matrix B,Fraction scalar){
		
			if(this.hasSameSizeAs(B)){
				
				B = B.scale(scalar);
				//Matrix SUM = new Matrix(this.row.length,this.column.length);
				
				for(int i = 0; i < row.length; i++){
					
					this.row[i] = this.row[i].plus(B.row[i]);
					
				}			
				return this;
			}
			System.out.println("Addition Undefined!");
			return null;
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

		if(column.length == B.row.length){ //Sufficient conditions for matrix multiplication to be defined
			
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
