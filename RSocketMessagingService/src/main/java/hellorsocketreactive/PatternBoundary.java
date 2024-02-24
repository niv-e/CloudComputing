package hellorsocketreactive;

public class PatternBoundary {
	private String pattern;

	public PatternBoundary() {
	}

	public PatternBoundary(String pattern) {
		this();
		this.pattern = pattern;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
}
