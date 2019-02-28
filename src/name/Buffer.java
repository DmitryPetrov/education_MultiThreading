package name;

public class Buffer {

	private Frame[] buffer;
	
	private boolean bufferFull;
	private boolean bufferEmpty;
	private boolean endOfTransfer;
	
	public Buffer() {
		this(10);
	}
	
	public Buffer(int buffSize) {
		buffer = new Frame[buffSize];
		bufferFull = false;
		bufferEmpty = true;
		endOfTransfer = false;
	}
	
	synchronized public void put(Frame frame) {
		while(true) {
			for(int i = 0; i < lenght(); i++) {
				if (buffer[i] != null) {
					continue;
				}
				
				buffer[i] = frame;
				
				if(bufferEmpty) {
					bufferEmpty = false;
					notifyAll();
				}
				return;
			}
			
			bufferFull = true;
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	synchronized public Frame get() {
		while(true) {
			for(int i = 0; i < lenght(); i++) {
				if (buffer[i] == null) {
					continue;
				}
				Frame frame = buffer[i];
				buffer[i] = null;
				
				if(bufferFull) {
					bufferFull = false;
					notifyAll();
				}
				return frame;
			}
			
			bufferEmpty = true;
			if(endOfTransfer) {
				return null;
			}
			
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	synchronized public void setEndOfTransfer() {
		endOfTransfer = true;
		notifyAll();
	}
	
	synchronized public boolean checkEndOfTransfer() {
		return endOfTransfer;
	}
	
	public int lenght() {
		return buffer.length;
	}
}
