package name;

public class Writer implements Runnable{

	private FrameContainer container;
	private Buffer buffer;
	private Thread thread;
	
	public Writer(Buffer buffer, FrameContainer container, String threadName) {
		this.buffer = buffer;
		this.container = container;
		thread = new Thread(this, threadName);
	}
	
	@Override
	public void run() {	
		while(true) {
			Frame frame = container.get();
			
			if(frame == null) {
				break;
			}
			
			System.out.println("\t" + thread.getName() + ">>" + frame);
			
			buffer.put(frame);
		}
	}
	
	public Thread getThread() {
		return thread;
	}	
}
