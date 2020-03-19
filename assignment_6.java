package pizdravsi;
import java.io.*;
import java.util.Scanner;

public class assignment_6 {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub

		File myFile = new File("taxes.txt");
		Scanner sc = new Scanner(myFile);
		PrintWriter outFile = new PrintWriter("output.txt");
		outFile.printf("%-8s %-10s %-8s %-5s", "Status", "Income", "Tax", "Message");
		outFile.println();
		int mar=0; int sngl =0;
		int total=0;
		double averageTax=0;
		double highestTax=0;
		
		while (sc.hasNext()) {
		   
			String line = sc.nextLine();
			String [] tokens = line.split(" ");
			int birthYear, year;
			double income, tax;
			int status = Integer.parseInt(tokens[0]);
		
			String message;
			
			if (status==2) {
				
				mar++;
				total++;
				
				int year1=Integer.parseInt(tokens[1]);
				int year2=Integer.parseInt(tokens[2]);
				if(year1>year2) {
					birthYear=year2;
				}
				else
					birthYear=year1;
				
			year=Integer.parseInt(tokens[3].substring(tokens[3].length()-4, tokens[3].length()));	
			income=Double.parseDouble(tokens[4]);
			tax=married(income, birthYear, year);
			if(tax>highestTax) {
				highestTax=tax;
			}
			
			
			
			if(tax<2000) 
				message = "Boo";
				
		
			else 
				message = "Yay!";
			
			outFile.printf("%-8s %-10.2f %-8.2f %-5s", "married", income, tax, message);
			outFile.println();
			
				
			}
			
			else 
			{
				sngl++;
				total++;
				
			birthYear=Integer.parseInt(tokens[1]);
			income = Double.parseDouble(tokens[3]);
			year=Integer.parseInt(tokens[2].substring(tokens[2].length()-4, tokens[2].length()));
			tax=single(income, birthYear, year);
			
		
			averageTax=averageTax+tax;
					
			if(tax>highestTax) {
				highestTax=tax;
			}
				
			
			if(tax<2000) 
				message = "Boo";
				
		
			else 
				message = "Yay!";
			
			outFile.printf("%-8s %-10.2f %-8.2f %-5s", "single", income, tax, message);
			outFile.println();
			
			}
			
			
			
			
		}
		
		averageTax=averageTax/sngl;
		
		outFile.println("Percent of married users: "+(double) mar/total *100+"%");
		outFile.println("Average tax paid by single user $: "+averageTax);
		outFile.println("Highest tax paid: $"+highestTax);
		
		sc.close();
		outFile.close();
		
		
		
	}
	
	
	public static boolean isOlder(int bYear, int year) {
		if (year-bYear>=60)
			return true;
		else 
			return false;
	}
	
	public static double single(double income, int bYear, int year) {
		
		double tax;
		if(income <=10000) {
			tax=income*0.05;
			
		}
		else {
			tax=500+income*0.1;
		}
		
		if (isOlder(bYear, year)) {
			tax=tax-tax*0.1;
			
		}
		
		return tax;
		
	}
	
	public static double married(double income, int bYear, int year) {
		
		double tax;
		if(income <=10000) {
			tax=income*0.06;
			
		}
		else {
			tax=600+income*0.11;
		}
		
		if (isOlder(bYear, year)) {
			tax=tax-tax*0.1;
			
		}
		
		return tax;
		
	}

}
