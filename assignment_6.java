package pizdravsi;
import java.io.*;
import java.util.Scanner;

public class assignment_6 {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub

		//open the input file
		File myFile = new File("taxes.txt");
		//create a scanner object
		Scanner sc = new Scanner(myFile);
		//create an output file
		PrintWriter outFile = new PrintWriter("output.txt");
		//print the headings for the columns
		outFile.printf("%-8s %-10s %-8s %-5s", "Status", "Income", "Tax", "Message");
		outFile.println();

		//number of married, single people 
		int mar=0; int sngl =0;

		//cumulative tax paid by single users
		double averageTax=0;
		//highest tax paid by anyone
		double highestTax=0;


		//scan until the end of the file
		while (sc.hasNext()) {

			//go to the next line
			String line = sc.nextLine();
			//split the line into array of strings
			String [] tokens = line.split(" ");
			//local variables for birth year and current year
			int birthYear, year;
			//income and tax variables
			double income, tax;

			//get status
			int status = Integer.parseInt(tokens[0]);

			//String to be printed based on the tax paid
			String message;


			//if married
			if (status==2) {

				//increase the count of married people
				mar++;

				//compare two birth years and save the oldest
				int year1=Integer.parseInt(tokens[1]);
				int year2=Integer.parseInt(tokens[2]);

				if(year1>year2) {
					birthYear=year2;
				}
				else
					birthYear=year1;
				//extract the current year from the date (last 4 characters are the year)	
				year=Integer.parseInt(tokens[3].substring(tokens[3].length()-4, tokens[3].length()));	
				income=Double.parseDouble(tokens[4]);
				//calll method married 
				tax=married(income, birthYear, year);

				//check if this is the highest tax paid
				if(tax>highestTax) {
					highestTax=tax;
				}


				if(tax<2000) 
					message = "Boo";
				else 
					message = "Yay!";

				//print the info to the file
				outFile.printf("%-8s %-10.2f %-8.2f %-5s", "married", income, tax, message);
				outFile.println();


			}


			//if single
			else 
			{
				//increase the count of single people
				sngl++;

				//extract birth year	
				birthYear=Integer.parseInt(tokens[1]);
				//extract income
				income = Double.parseDouble(tokens[3]);
				//current year
				year=Integer.parseInt(tokens[2].substring(tokens[2].length()-4, tokens[2].length()));
				//call method tax for single person
				tax=single(income, birthYear, year);


				averageTax=averageTax+tax;

				if(tax>highestTax) {
					highestTax=tax;
				}


				if(tax<2000) 
					message = "Boo";


				else 
					message = "Yay!";

				//print the info to the file
				outFile.printf("%-8s %-10.2f %-8.2f %-5s", "single", income, tax, message);
				outFile.println();

			}




		}


		//calculate the average tax for single people
		averageTax=averageTax/sngl;

		outFile.println("Percent of married users: "+(double) mar/(mar+sngl) *100+"%");
		outFile.println("Average tax paid by single user $: "+averageTax);
		outFile.println("Highest tax paid: $"+highestTax);

		sc.close();
		outFile.close();



	}



	//method checks if the person will be or be older than 60 at the end of the current year, and returns true if so
	public static boolean isOlder(int bYear, int year) {
		if (year-bYear>=60)
			return true;
		else 
			return false;
	}


	//method calculates tax for a single person
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


	//method calculates tax for married person
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
