package idk;

public class Vector {
	
	private Fraction[] vector;
	
	/*CONSTRUCTORS*/
	
	public Vector (Fraction[] array){
		this.vector = array;
	}
	
	public Vector (int ... array){
		int i = 0;
		vector = new Fraction[array.length];
		for(int x : array){
			vector[i] = new Fraction(x);
			i++;
		}
	}
	
	/*GETTERS*/
	
	//Length of vector array
	public int length(){
		return vector.length;
	}
	
	//Get Array
	public Fraction[] array(){
		return vector;
	}
	
	//Converts vector to string
	public String toString(boolean type){
		String s = new String();
		
		//If TRUE - ROW VECTOR
		//IF FALSE - COLUMN VECTOR
		
		if(type == true){
			for(int i = 0; i < vector.length; i++){
				s = s+this.vector[i].toString();
				s = s+" ";
			}
			return s;
		}
		for(int i = 0; i < vector.length; i++){
			s.concat(vector[i].toString());
			s.concat("\n");
		}
		return s;
	}
	
	public String toString(){
		return this.toString(true);
	}
	
	/*ACCESSING ELEMENTS*/
	
	//Access element for row vectors
	public Fraction column(int j){
		return this.array()[j]; 
	}
	
	//Access element for column vectors
	public Fraction row(int i){
		return this.array()[i];
	}
	
	/*VECTOR OPERATIONS*/
	
	//Scaling the Vector (Scalar Multiplication)
	public Vector scale(Fraction scalar){
		for(Fraction x: vector){
			x = x.times(scalar);
		}
		return new Vector(vector);
	}
	
	public Vector scale(int scalar){
		return this.scale(new Fraction(scalar));
	}
	
	//Vector Addition
	public Vector plus(Vector v2,Fraction scalar){
		//vector addition is only defined for vectors of same no. of components
		if(v2.length() == vector.length){ 
			int i = 0;
			v2 = v2.scale(scalar);
			for(Fraction x: vector){
				x = x.plus(v2.array()[i]);
				i++;
			}
			return new Vector(vector);
		}
		return new Vector(vector);
	//use try and catch for exception handling	
	}
	
	public Vector plus(Vector v2,int scalar){
		return this.plus(v2,new Fraction(scalar));
	}
	
	public Vector plus(Vector v2){
		return this.plus(v2,new Fraction(1));
	}
	
	public Vector minus(Vector v2,int scalar){
		return this.plus(v2,new Fraction((-1)*scalar));
	}
	
	public Vector minus(Vector v2){
		return this.minus(v2,1);
	}
	
	//Vector Dot Product
	public Fraction dot(Vector v2){
		Fraction sum = new Fraction();
		if(vector.length == v2.length()){
			int i = 0;
			for(Fraction x: vector){
				sum = sum.plus(x.times(v2.array()[i]));
				i++;
			}
			return sum;
		}
		return sum;
	}
}
