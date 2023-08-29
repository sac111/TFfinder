package Designite;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import Designite.SourceModel.*;
import Designite.utils.Constants;
import Designite.utils.FileReader;
import Designite.utils.Logger;

import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

import org.apache.commons.cli.*;

import com.github.mauricioaniche.ck.Runner;

/**
 * 
 * This is the start of the project
 */
public class Designite {
	
	//Static
	static String line1="";
	static String line2="";
	
	public static void main(String[] args) throws IOException {
		
		//GUI
		gui o=new gui();
		o.mainGui();
		String s;
		
		while(true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			s=o.pressed();
			System.out.println("value of button = "+s);
			if(s.equalsIgnoreCase("1")){
				break;
			}
		}

		line1=o.Ipass();
		line2=o.Opass();
		System.out.println(line1+"****"+line2);
		
		
	    //------------------------------------
	    

	    
		InputArgs argsObj = parseArguments();
		SM_Project project = new SM_Project(argsObj);
		Logger.logFile = getlogFileName(argsObj);
		//TODO: log the version number
		project.parse();
		project.resolve();
		project.computeMetrics();
		project.detectCodeSmells();
		if (Constants.DEBUG)
			writeDebugLog(argsObj, project);
		Logger.log("Done.");
		//Results :
		field o1 = new  field();
		o1.runFunc(line2);
		
		
		//call fmain
		Runner ro=new Runner();
		ro.fmain(line1,line2);
		
		moreSmells mso=new moreSmells();
		mso.moreSmellsFunc(line2);
		System.out.println("Completed finding all four smells in the given project");
		o.gotAns(1, o1.totalF(), o1.smellF(), mso.totalC(),mso.smell1(),mso.smell2(),mso.smell3());
		System.out.println(o1.totalF()+"**"+ o1.smellF()+"**"+ mso.totalC()+"**"+mso.smell1()+"**"+mso.smell2()+"**"+mso.smell3());
	}

	private static void writeDebugLog(InputArgs argsObj, SM_Project project) {
		PrintWriter writer = getDebugLogStream(argsObj);
		project.printDebugLog(writer);
		if (writer != null)
			writer.close();
	}

	private static InputArgs parseArguments() {
		
		
        String inputFolderPath = line1;
        String outputFolderPath =line2;
        
        InputArgs inputArgs= null;
        try
        {
        	inputArgs = new InputArgs(inputFolderPath, outputFolderPath);
        }
        catch(IllegalArgumentException ex)
        {
        		Logger.log(ex.getMessage());
        		Logger.log("Quitting..");
        		System.exit(3);
        }
        return inputArgs;
	}

	private static String getlogFileName(InputArgs argsObj) {
		String file = null;
		String timeStamp = new SimpleDateFormat("ddMMyyyy_HHmm").format(Calendar.getInstance().getTime());
		file = argsObj.getOutputFolder() + File.separator + "DesigniteLog" + timeStamp + ".txt";
		ensureOutputFolderExists(argsObj.getOutputFolder());
		return file;
	}

	private static void ensureOutputFolderExists(String outputFolder) {
		if (outputFolder == null)
			return;
		File folder = new File(outputFolder);
		
		if (folder.exists() && folder.isDirectory())
			return;
		
		try
		{
			boolean isCreated = folder.mkdirs();
			if (!isCreated)
			{
				System.out.println("Couldn't create output folder.");
			}
		}
		catch (Exception ex)
		{
			System.out.println("Couldn't create output folder. " + ex.getMessage());
		}
	}
	private static PrintWriter getDebugLogStream(InputArgs argsObj) {
		PrintWriter writer = null;
		if (!argsObj.getOutputFolder().equals("")) {
			String timeStamp = new SimpleDateFormat("ddMMyyyy_HHmm").format(Calendar.getInstance().getTime());
			String filename = argsObj.getOutputFolder() + "DesigniteDebugLog" + timeStamp + ".txt";
			try {
				writer = new PrintWriter(filename);
			} catch (FileNotFoundException ex) {
				Logger.log(ex.getMessage());
			}
		}
		return writer;
	}
}
