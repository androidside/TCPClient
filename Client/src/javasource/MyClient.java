package javasource;
import java.io.*;
import java.net.*;
import java.util.Arrays;
import java.util.Objects;

public class MyClient {
	public static void main(String[] args) throws IOException, InterruptedException {

		String serverHostname = new String ("192.168.0.104");

		if (args.length > 0)
			serverHostname = args[0];
		System.out.println ("Attemping to connect to host " +
				serverHostname + " on port 23.");

		Socket echoSocket = null;
		PrintWriter out = null;
		BufferedReader in = null;

		try {
			echoSocket = new Socket(serverHostname, 23);
			out = new PrintWriter(echoSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(
					echoSocket.getInputStream()));
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host: " + serverHostname);
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for "
					+ "the connection to: " + serverHostname);
			System.exit(1);
		}

		BufferedReader stdIn = new BufferedReader(
				new InputStreamReader(System.in));
		String userInput;
		String s = null;
		System.out.println("Write commands..");
		boolean test;
		char [] cbuf = new char [1];
		int numRead = 0 ;

		while ((userInput = stdIn.readLine()) != null) {
			
			if(Objects.equals(userInput, new String ("DL"))){
			
				readFileAndDownload("CCMG.txt",out);
			}

		}



		out.close();
		in.close();
		stdIn.close();
		echoSocket.close();
	}

	static void readFileAndDownload(String fileName, PrintWriter out) throws InterruptedException, IOException{
		boolean endOfFile = false;
		InputStream is = null;
		int c = 0;
		byte[] buffer = new byte[1];
		//OutputStream out = null;
		System.out.println("DL");
		out.println(new String ("DL"));
		System.out.println("Aborting previous program.. AB & ST");
		out.println(new String ("AB"));
		Thread.sleep(1000);
		out.println(new String ("ST"));
		Thread.sleep(1000);
		System.out.println("DL");
		out.println(new String ("DL"));
		Thread.sleep(2000);
		System.out.println("Opening file"); 
		URL pathFile = MyClient.class.getResource("/textFiles/"+fileName);
		//Try to open .txt file
		try {
			is = pathFile.openStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		do{
			try {
				c = is.read(buffer,0,1);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if(c>0){

				System.out.print((char)(buffer[0]));
				out.print((char)buffer[0]);
				Thread.sleep(1);
			}
			else{	
				//out.println();
				endOfFile = true;
			}
			//Now we have removed the 'header'
		}while(endOfFile == false);
		Thread.sleep(200);
		out.println(new String("\\"));
		System.out.println(new String("\\"));
		Thread.sleep(1000);
		out.println(new String("BP"));
		System.out.println(new String("BP"));
		System.out.println("Program Burned!");
		Thread.sleep(3000);
		System.out.println("Resetting controller..");
		out.println(new String("RS"));
		System.out.println(new String("RS"));
		Thread.sleep(1000);
		is.close();

		System.out.println("Done!");



	}
}
