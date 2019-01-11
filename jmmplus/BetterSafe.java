import java.util.concurrent.locks.ReentrantLock; 
//https://winterbe.com/posts/2015/04/30/java8-concurrency-
//                   tutorial-synchronized-locks-eamples/


class BetterSafe implements State{
	private byte[] value;
	private byte maxval;
	private ReentrantLock lock;
	
	BetterSafe(byte[] v){
		lock = new ReentrantLock();
		maxval = 127;
		value = v;
	}
	
	BetterSafe(byte[] v, byte m){
		lock = new ReentrantLock();
		maxval = m;
		value = v;
	}
	
	public int size() {return value.length;}

	public byte[] current(){return value;}
	
	//lock guarding the critical section where values are evaluated and
	//	changed. 
	public boolean swap(int i, int j){
		//apply lock
		lock.lock();
		if(value[i] <= 0 || value[j] >= maxval){
			//release lock
			lock.unlock();
			return false;
		}
		value[i]--;
		value[j]++;
		//release lock 
		lock.unlock();

		return true;
	}
}
