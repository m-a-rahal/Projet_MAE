package io_classes;

public class ProgressBar {
	String name;
	int length = 40;
	double max;

	
	public ProgressBar(String name, double max) {
		super();
		this.name = name;
		this.max = max;
	}

	public ProgressBar(String name) {
		super();
		this.name = name;
	}

	public String progress(double step, double max) {
		this.max = Math.abs(max);
		return progress(step);
	}
	
	public String progress(double step) {
		String text = "                                                      \r|";
		max = Math.abs(max); step = Math.abs(step);if (max < step) {double tmp = max; max = step; step = tmp;}
		
		double rate = ((step / max) * 100);
		int p = (int) (rate/100*length);
		for (int i = 0; i < length; i++) {
			if(i <= p) 
				text += "=";
			else
				text += " ";
		}
		return text+ "| "+name+" "+String.format("%.1f", rate)+"%\r";
	}
}
