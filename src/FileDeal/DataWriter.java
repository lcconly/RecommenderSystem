package FileDeal;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;

public class DataWriter {
	private String fileName;
	private File writeFile;
	FileWriter p;
	public DataWriter(String fileName) throws IOException{
		// TODO Auto-generated constructor stub
		this.fileName=fileName;
		writeFile=new File(this.fileName);
		if(!writeFile.exists())
			writeFile.createNewFile();
		p=new FileWriter(writeFile,false);        
	}
	public void printLine(String s) throws IOException{
		p.write(s+"\n");
	}
	public void closeFile() throws IOException{
		p.close();
	}
}
