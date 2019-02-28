package name;

public class PoolOfThreads {

    FrameContainer outputContainer;
    FrameContainer inputContainer;
    Buffer buffer;

    Thread[] writers;
    Thread[] readers;

    public PoolOfThreads(FrameContainer outputContainer,
            FrameContainer inputContainer, Buffer buffer) {
        this.outputContainer = outputContainer;
        this.inputContainer = inputContainer;
        this.buffer = buffer;
    }

    
    public void createAllThreads(int count) {
        createWriters(count);
        createReaders(count);
    }

    public void createWriters(int count) {
        writers = new Thread[count];
        for (int i = 0; i < count; i++) {
            writers[i] = new Writer(buffer, outputContainer, "WR_" + i)
                    .getThread();
        }
    }

    public void createReaders(int count) {
        readers = new Thread[count];
        for (int i = 0; i < count; i++) {
            readers[i] = new Reader(buffer, inputContainer, "RD_" + i)
                    .getThread();
        }
    }

    
    public void startAllThreads() throws InterruptedException {
        startWriters();
        startReaders();
    }

    public void startWriters() {
        for (Thread thread : writers) {
            thread.start();
        }
    }

    public void startReaders() {
        for (Thread thread : readers) {
            thread.start();
        }
    }

    
    public void joinWriters() throws InterruptedException {
        for (Thread thread : writers) {
            thread.join();
        }
    }

    public void joinReaders() throws InterruptedException {
        for (Thread thread : readers) {
            thread.join();
        }
    }

    
    public Thread[] getWriters() {
        return writers;
    }

    public Thread[] getReaders() {
        return readers;
    }
}
