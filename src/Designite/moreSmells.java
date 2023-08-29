package Designite;


import java.io.File;
import java.util.*;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class moreSmells {
	
	public static int t1,s1,s2,s3;
	
	public int totalC() {
		return t1;
	}
	public int smell1() {
		return s1;
	}
	public int smell2() {
		return s2;
	}
	public int smell3() {
		return s3;
	}
	
	
	public static double findMedian(Vector<Integer> a, int n)
    {
        // First we sort the array
		Collections.sort(a);
 
        // check for even case
        if (n % 2 != 0)
            return (double) a.elementAt(n/2);
 
        return (double)(a.elementAt((n - 1) / 2) + a.elementAt(n / 2)) / 2.0;
    }
	
	
	public void moreSmellsFunc(String inp)
	{
		String[] values={};
		Vector<String> result=new Vector<String>();		//for lazy class
		Vector<String> feature_envy_result=new Vector<String>();
		Vector<String> large_class_result=new Vector<String>();
		Vector<Integer> vec = new Vector<Integer>(); 
		Set<String> hash_Set = new HashSet<String>();
		int totalclasses=0;
		double medianCBO=0;
		String filename = inp+"\\class.csv";
		//String filename = "C:\\Users\\iampr\\Desktop\\test\\output\\o6\\class.csv";
		File file = new File(filename);
		try {
			Scanner inputstream = new Scanner(file);
			inputstream.useDelimiter("\n");
			inputstream.next();
			while(inputstream.hasNext()) {
				String data = inputstream.next();
				values = data.split(",");
				//System.out.println("I am if condition an I am true");
				//System.out.println(values[2]);
				
				if(values.length<29)
				{
					//System.out.println("I am if condition an I am true");
					//System.out.println(values[1]);
					continue;
				}
				
				if(values[2].equals("enum") || values[2].equals("anonymous"))
				{
					//System.out.println("If condition of enum and anonyous type is ");
					//System.out.println(values[2]);
					continue;
				}
				totalclasses++;
				String loc=values[29];
				int i=Integer.parseInt(loc);
				vec.add(i);
				//System.out.println("value of Loc is ");
				//System.out.println(loc);
				//System.out.println();
				
			}
			inputstream.close();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		//System.out.println("total classes are ====================================================================");
		//System.out.println(totalclasses);
		double median=findMedian(vec,vec.size());
		//System.out.println("Median of the Loc is ");
		//System.out.println(median);
		File file2=new File(filename);
		try {
			Scanner inputstream = new Scanner(file2);
			inputstream.useDelimiter("\n");
			inputstream.next();
			while(inputstream.hasNext()) {
				String data = inputstream.next();
				values = data.split(",");
				if(values.length<29)
				{
					//System.out.println("I am if condition an I am true");
					//System.out.println(values[1]);
					continue;
				}
				if(values[2].equals("enum") || values[2].equals("anonymous"))
				{
					continue;
				}
				String NOM=values[10];
				int nom_i=Integer.parseInt(NOM);
				//System.out.println(NOM);
				if(nom_i==0)
				{
					hash_Set.add(values[1]);
					//System.out.println(values[1]);
					continue;
				}
//				String loc=values[7];
				double loc_i=Double.parseDouble(values[29]);
				double wmc=Double.parseDouble(values[4]);
				double nom=Double.parseDouble(values[10]);
				if(nom==0)
				{
					continue;
				}
				double ratio=wmc/nom;
				if(loc_i<median && ratio<=2.0)
				{
					hash_Set.add(values[1]);
					//System.out.println("Ratio condition is true");
					//System.out.println(loc_i);
					//System.out.println(median);
					//System.out.println(ratio);
					//System.out.println(values[1]);
					//System.out.println(values[2]);
					continue;
				}
			}
			inputstream.close();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		//System.out.println("CBO Rules ==============================================================");
		Vector<Integer> vecCBO = new Vector<Integer>(); 
		File file3=new File(filename);
		try {
			Scanner inputstream = new Scanner(file3);
			inputstream.useDelimiter("\n");
			inputstream.next();
			while(inputstream.hasNext()) {
				String data = inputstream.next();
				values = data.split(",");
				if(values.length<29)
				{
					//System.out.println("I am if condition an I am true");
					//System.out.println(values[1]);
					continue;
				}
				if(values[2].equals("enum") || values[2].equals("anonymous"))
				{
					continue;
				}
				String CBO=values[3];
				int cbo_i=Integer.parseInt(CBO);
				//System.out.println(CBO);
				vecCBO.add(cbo_i);
			}
			inputstream.close();
		    medianCBO=findMedian(vecCBO,vecCBO.size());
			//System.out.println("Median of the CBO is ");
			//System.out.println(medianCBO);
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		File file4=new File(filename);
		try {
			Scanner inputstream = new Scanner(file4);
			inputstream.useDelimiter("\n");
			inputstream.next();
			while(inputstream.hasNext()) {
				String data = inputstream.next();
				values = data.split(",");
				if(values.length<29)
				{
					//System.out.println("I am if condition an I am true");
					//System.out.println(values[1]);
					continue;
				}
				if(values[2].equals("enum") || values[2].equals("anonymous"))
				{
					continue;
				}
				String cbo=values[3];
				int cbo_i=Integer.parseInt(cbo);
//				//System.out.println(cbo_i);
				//System.out.println("value of median cbo is ");
				//System.out.println(medianCBO);
				String DIT=values[5];
				int dit_i=Integer.parseInt(DIT);
				if(cbo_i<medianCBO && dit_i>1)
				{
					hash_Set.add(values[1]);
					//System.out.println(cbo_i);
					//System.out.println(dit_i);
					//System.out.println(values[1]);
					continue;
				}
			}
			inputstream.close();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		
		
		//System.out.println("==============================================================");
		int i=0;
		for(String cls:hash_Set)
		{
			result.add(cls);
		}
		for(i=0;i<result.size();i++)
		{
			//System.out.println(result.elementAt(i));
		}
		System.out.println("Lazy classes = "+result.size());
		//System.out.println("Feature envy ===========================================================");
		File file5=new File(filename);
		try {
			Scanner inputstream = new Scanner(file5);
			inputstream.useDelimiter("\n");
			inputstream.next();
			while(inputstream.hasNext()) {
				String data = inputstream.next();
				values = data.split(",");
				if(values.length<29)
				{
					//System.out.println("I am if condition an I am true");
					//System.out.println(values[1]);
					continue;
				}
				if(values[2].equals("enum") || values[2].equals("anonymous"))
				{
					continue;
				}
				String fcbo=values[3];
				int fcbo_i=Integer.parseInt(fcbo);
				//System.out.println(fcbo);
				if(fcbo_i>5)
				{
					feature_envy_result.add(values[1]);
					//System.out.println(values[1]);
					continue;
				}
				String flcom=values[7];
				int flcom_i=Integer.parseInt(flcom);
				if(flcom_i>2)
				{
					feature_envy_result.add(values[1]);
					//System.out.println(values[1]);
					continue;
				}
			}
			inputstream.close();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		for(i=0;i<feature_envy_result.size();i++)
		{
			//System.out.println(feature_envy_result.elementAt(i));
		}
		System.out.println("FE classes = "+feature_envy_result.size());
		//System.out.println("Large class =================================================================");
		
		File file6=new File(filename);
		try {
			Scanner inputstream = new Scanner(file6);
			inputstream.useDelimiter("\n");
			inputstream.next();
			while(inputstream.hasNext()) {
				String data = inputstream.next();
				values = data.split(",");
				if(values.length<29)
				{
					//System.out.println("I am if condition an I am true");
					//System.out.println(values[1]);
					continue;
				}
				if(values[2].equals("enum") || values[2].equals("anonymous"))
				{
					continue;
				}
				String large_loc=values[29];
				int large_loc_i=Integer.parseInt(large_loc);
				String large_nom=values[10];
				int large_nom_i=Integer.parseInt(large_nom);
				//System.out.println(large_loc_i);
				if(large_loc_i>300 && large_nom_i>5)
				{
					large_class_result.add(values[1]);
					//System.out.println(values[1]);
					continue;
				}
				String large_cbo=values[3];
				int large_cbo_i=Integer.parseInt(large_cbo);
				if(large_cbo_i>10)
				{
					large_class_result.add(values[1]);
					//System.out.println(values[1]);
					continue;
				}
				String large_dit=values[5];
				int large_dit_i=Integer.parseInt(large_dit);
				if(large_dit_i>5)
				{
					large_class_result.add(values[1]);
					//System.out.println(values[1]);
					continue;
				}
			}
			inputstream.close();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		for(i=0;i<large_class_result.size();i++)
		{
			//System.out.println(large_class_result.elementAt(i));
		}
		System.out.println("Large classes = "+large_class_result.size());
		System.out.println("TC = "+totalclasses);
		
		
		//file 1-------------------------------------------------------
		try {
			File myobj1 = new File(inp+"\\_LazyClassSmell.txt");
			if(myobj1.createNewFile())
			{
				System.out.println("new file created for LazyClassSmell" + myobj1.getPath());
				FileWriter fw1 = new FileWriter(inp+"\\_LazyClassSmell.txt");
				fw1.write("List of classes having Lazy Class Smell are:\n\n");
				for(i=0;i<result.size();i++)
				{
					fw1.write((i+1)+".    "+result.elementAt(i)+"\n");
					//System.out.println(large_class_result.elementAt(i));
				}
				fw1.close();
				//System.out.println("succesfully written inside file");
			}
			else
			{
				System.out.println("file cannot be created "+myobj1.getPath());
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		//file 2-------------------------------------------------------
		try {
			File myobj2 = new File(inp+"\\_FeatureEnvy.txt");
			if(myobj2.createNewFile())
			{
				System.out.println("new file created for FeatureEnvy" + myobj2.getPath());
				FileWriter fw2 = new FileWriter(inp+"\\_FeatureEnvy.txt");
				fw2.write("List of classes having Feature Envy Smell are:\n\n");
				for(i=0;i<feature_envy_result.size();i++)
				{
					fw2.write((i+1)+".    "+feature_envy_result.elementAt(i)+"\n");
					//System.out.println(large_class_result.elementAt(i));
				}
				fw2.close();
				//System.out.println("succesfully written inside file");
			}
			else
			{
				System.out.println("file cannot be created "+myobj2.getPath());
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		//file 3-------------------------------------------------------
		
		try {
			File myobj3 = new File(inp+"\\_LargeClass.txt");
			if(myobj3.createNewFile())
			{
				System.out.println("new file created for FeatureEnvy" + myobj3.getPath());
				FileWriter fw3 = new FileWriter(inp+"\\_LargeClass.txt");
				fw3.write("List of classes having Large Class Smell are:\n\n");
				for(i=0;i<large_class_result.size();i++)
				{
					fw3.write((i+1)+".    "+large_class_result.elementAt(i)+"\n");
					//System.out.println(large_class_result.elementAt(i));
				}
				fw3.close();
				//System.out.println("succesfully written inside file");
			}
			else
			{
				System.out.println("file cannot be created "+myobj3.getPath());
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		
		t1=totalclasses;
		s1=result.size();
		s2=feature_envy_result.size();
		s3=large_class_result.size();
		
		
	}

}
