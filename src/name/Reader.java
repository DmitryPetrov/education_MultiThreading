package name;

public class Reader implements Runnable{

	private Buffer buffer;
	private FrameContainer container;
	private Thread thread;
	
	public Reader(Buffer buffer, FrameContainer container, String threadName) {
		this.buffer = buffer;
		this.container = container;
		thread = new Thread(this, threadName);
	}
	
	@Override
	public void run() {
		while(true) {
			Frame frame = buffer.get();
		
			if(frame == null) {
				break;
			}
			
			System.out.println(thread.getName() + "<<" + frame);

			container.put(frame);
		}
	}
	
	public Thread getThread() {
		return thread;
	}

}
