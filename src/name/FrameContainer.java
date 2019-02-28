package name;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class FrameContainer {

    private List<Frame> data;
    private String type;
    private int size;

    public FrameContainer() {
        this(new String().getClass().getSimpleName());
    }

    public FrameContainer(String type) {
        this.type = type;
        data = new LinkedList<Frame>();
        size = 0;
    }

    synchronized public void put(String string) {
        put(string.getBytes());
    }

    synchronized public void put(byte[] data) {
        Frame frame = new Frame(data);
        frame.setType(type);
        frame.setIndex(size++);
        put(frame);
    }

    synchronized public void put(Frame frame) {
        data.add(frame);
    }

    synchronized public Frame get() {
        if (isEmpty()) {
            return null;
        }
        size--;
        int random = new Random().nextInt(data.size());
        return data.remove(random);
    }

    synchronized public Frame get(int index) {
        Frame frame = null;

        for (int i = 0; i < data.size(); i++) {
            frame = data.get(i);
            if (frame.getIndex() == index) {
                data.remove(i);
                break;
            }
        }
        size--;
        return frame;
    }

    synchronized public boolean isEmpty() {
        return data.isEmpty();
    }

    synchronized public void sort() {
        Collections.sort(data);
    }

    public String toString() {
        sort();
        
        int size = getSize();
        StringBuilder result = new StringBuilder(size);
        
        for (int i = 0; i < size; i++) {
            result.append(data.get(i));
        }

        return result.toString();
    }
    
    public void print() {
        sort();
        
        int size = getSize();
        StringBuilder result = new StringBuilder(size);
        
        for (int i = 0; i < size; i++) {
            result.append(new String(data.get(i).getData()));
        }

        System.out.println(result.toString());
    }
    
    @Override
    public boolean equals(Object anotherContainer) {
        if (toString().equals(anotherContainer.toString())) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean equals(String StringOfAnotherContainer) {
        if (toString().equals(StringOfAnotherContainer)) {
            return true;
        } else {
            return false;
        }
    }

    synchronized public int getSize() {
        return data.size();
    }

    public String getType() {
        return type;
    }
}
