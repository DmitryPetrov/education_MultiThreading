package name;

public class Frame implements Comparable<Frame>{
	
	private String type;
	private byte[] data;
	private int index;
	
	public Frame(byte[] data) {
		setData(data);
		setIndex(-1);
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public byte[] getData() {
		return data;
	}
	
	public void setData(byte[] data) {
		this.data = data;
	}
	
	public int getIndex() {
		return index;
	}
	
	public void setIndex(int index) {
		this.index = index;
	}
	
	public String toString() {
	    StringBuilder result = new StringBuilder();
	    
		if(type.equals(new String().getClass().getSimpleName())) {
		    result.append("(").append(index).append(")").append(new String(data));
		} else {
			for(int i = 0; i < data.length; i++) {
			    result.append(data[i]);
			}
		}
		return result.toString();
	}

    @Override
    public int compareTo(Frame anotherFrame) {
        int result = 0;
        if (index > anotherFrame.getIndex()) {
            result = 1;
        } else if (index < anotherFrame.getIndex()) {
            result = -1;
        }
        return result;
    }
}
