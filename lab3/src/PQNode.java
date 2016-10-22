package lab3;

public class PQNode<T> {
	Handle handle;
	int key;
	T value;

	public PQNode(Handle h, int k, T v){
		this.handle=h;
		this.key=k;
		this.value=v;
	}
	
	public String toString(){
		return "<"+this.key+", "+this.value+">";
	}

}