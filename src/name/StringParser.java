package name;

public class StringParser {

	private FrameContainer container;
	
	public StringParser() {
		container = new FrameContainer();
	}
	
	public FrameContainer parse(String text, String pattern) {
		String[] mass = text.split(pattern);
		
		for(int i = 0; i < mass.length; i++) {
			container.put(mass[i] + pattern);
		}
		return container;
	}
	
	public FrameContainer getContainer() {
		return container;
	}
}
