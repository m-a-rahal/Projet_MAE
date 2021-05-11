package io_classes;

import java.io.File;
import java.util.regex.Matcher;
public class IndexedFile extends File{
	private static final long serialVersionUID = 1L;
	protected int index;
	public IndexedFile(String pathname) {
		super(pathname);
		Matcher matcher = FileManager.match(".+?([0-9]+)\\.cnf",pathname);
		if (matcher != null) {
			//System.out.println(matcher.group(1));
			this.index = Integer.parseInt(matcher.group(1));
		}
	}

	@Override
	public int compareTo(File pathname) {
		// TODO Auto-generated method stub
		return this.index - new IndexedFile(pathname.getAbsolutePath()).index;
	}

}
