package Designite;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.IOException;
//import java.lang.*;
import java.util.*;

public class field {

	static String path="\\_TemporaryFields.txt";
	static int totalFiles,smellyF;
	
	public int totalF() {
		return totalFiles;
	}
	public int smellF() {
		return smellyF;
	}

	public void runFunc(String args) {
	
		path=args+path;
		String[] values = {};
		String[] values2= {};
		String[] values3= {};
		double lc,nfm,ncf,nmn;
		Map<String,String> map = new HashMap<>();
		Map<String,String> map2 = new HashMap<>();
		
		//code for creating new file in the output folder
		
		try {
			File myobj = new File(path);
			if(myobj.createNewFile())
			{
				System.out.println("new file created" + myobj.getPath());
			}
			else
			{
				System.out.println("file cannot be created "+myobj.getPath());
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		
		//reading csv files from here(reading typesmetrics in the first try and catch block)
		
		
		
		String filename = args+"\\methodMetrics.csv";
		String filename2 = args+"\\fieldMetrics.csv";
		String filename3 = args+"\\typeMetrics.csv";
		File file = new File(filename);
		File file2 = new File(filename2);
		File file3 = new File(filename3);
		int total_classes=0;
		try {
			Scanner inputstream = new Scanner(file3);
			inputstream.useDelimiter("\n");
			//inputstream.close();
			//inputstream.next();
			inputstream.next();
			while(inputstream.hasNext()) {
				String data = inputstream.next();
				values = data.split(",");
				
				String Lcom = values[11];
				lc=0;
				try
				{
					lc = Double.parseDouble(Lcom);
				}
				catch(NumberFormatException e)
				{
					;
				}
				
				if(lc>0.5)
				{
					map.put(values[2],"yes");
					//System.out.println(values[2]);
					//System.out.println(values[11]);
					//System.out.println("***");
					
				}
				else
				{
					//System.out.println("Lcom is not greater than 0.5");
					//System.out.println(values[11]);
				}
				total_classes+=1;
				//inputstream.close();
			}
			//System.out.println("Total number of classes in the project is :");
			System.out.println(total_classes);
			inputstream.close();
			
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		
		//reading method metric.csv from here
		System.out.println("-----------------------------------------------");
		
		try
		{
			Scanner inputstream = new Scanner(file);
			inputstream.useDelimiter("\n");
			//inputstream.close();
			//inputstream.next();
			inputstream.next();
			while(inputstream.hasNext())
			{
				String data = inputstream.next();
				values2 = data.split(",");
				//System.out.println(values2[7]);
				String class_name=values2[2];
				if(map.get(class_name)!=null)
				{
					nfm=0;
					try
					{
						nfm = Double.parseDouble(values2[7]);
					}
					catch(NumberFormatException e)
					{
						;
					}
					if(nfm>0)
					{
						if(map2.get(class_name)==null)
						{
							map2.put(class_name,"yes");
						}
					}
					
				}
			}
			inputstream.close();
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		System.out.println("------------------------------------------------------------------------------");
		System.out.println(map2.size());
		System.out.println(map.size());
		
		//reading of fieldmetrics.csv starts from here
		System.out.println("--------------------------------------------------------------------------------");
		
		try
		{
			Scanner inputstream = new Scanner(file2);
			inputstream.useDelimiter("\n");
			//inputstream.close();
			//inputstream.next();
			inputstream.next();
			while(inputstream.hasNext())
			{
				String data = inputstream.next();
				values3 = data.split(",");
				//System.out.println(data);
				String class_name=values3[2];
				//System.out.println(class_name);
				if(map2.get(class_name)==null&&map.get(class_name)!=null)
				{
					nmn=0;
					try
					{
						nmn = Double.parseDouble(values3[4]);
					}
					catch(NumberFormatException e)
					{
						;
					}
					ncf=0;
					try
					{
						ncf = Double.parseDouble(values3[5]);
					}
					catch(NumberFormatException e)
					{
						;
					}
					if(nmn==1||ncf==1)
					{
						map2.put(class_name,"yes");
					}
				}
			}
			inputstream.close();
		}
		catch(FileNotFoundException e)
		{
			;
		}
		System.out.println("-----------------------------------------------------------");
		System.out.println(map2.size());
		
		//writing in file result.txt from here

		try
		{
			FileWriter fw = new FileWriter(path);
			
			//fw.write(String.valueOf(map2.size()));
			//fw.write("\n");
			smellyF = Integer.parseInt(String.valueOf(map2.size()));
			//fw.write(String.valueOf(total_classes));
			//fw.write("\n");
			totalFiles = Integer.parseInt(String.valueOf(total_classes));
			
			fw.write("Classes containing Field Smell are :- \n");
			int i=0;
			Set<String> keys=map2.keySet();
			for(String key:keys)
			{
				i++;
				fw.write(i+". ");
				fw.write(key);
				fw.write("\n");
			}
			fw.close();
			//System.out.println("succesfully written inside file");
			
		}
		catch (IOException e)
		{
			;
		}
		
		

	}

}

//notes about program
//map is used to store names of classes having lcom>0.5
//map2 is used to store classes containing temporary fields
//comment all the system.out.println() statements if necessary
//In result.txt file first number is No. of classes having temporary field
//second number is total number of classes in the project