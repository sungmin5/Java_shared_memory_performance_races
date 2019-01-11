import java.util.concurrent.atomic.AtomicIntegerArray;
//https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java
//		    /util/concurrent/atomic/AtomicIntegerArray.html

		    
class GetNSet implements State {
   	private AtomicIntegerArray value;
	private byte maxval;
	
	//convert byte array to int array
	private int[] byteToInt(byte[] arr){
		int len = arr.length;
		int[] ret = new int[len];
		for (int i = 0; i < len; i++){
			ret[i] = (int) arr[i];
		}
		return ret;
	}
	
	GetNSet (byte[] v){
		maxval = 127;
		value = new AtomicIntegerArray(this.byteToInt(v));
	}
	
	GetNSet (byte[] v, byte m){
		maxval = m;
		value = new AtomicIntegerArray(this.byteToInt(v));
	}
	
	public int size() {return value.length();}
	
	public byte[] current() {
		//turn array to byte array before return
		int len = value.length();
		byte[] cur = new byte[len];
		for(int i = 0; i < len; i++){
			cur[i] = (byte) value.get(i);
		}
		return cur;
	}

	public boolean swap(int i, int j) {
		if (value.get(i) <= 0 || value.get(j) >= maxval) {
			return false;
		}
		//to set the values, we must get the value from the
		//	desirable element
		value.set(i, value.get(i)-1);
	        value.set(j, value.get(j)+1);
		return true;
	}

}
