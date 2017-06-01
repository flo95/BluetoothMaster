package uebung5;

import java.io.IOException;
import java.util.*;

import lejos.pc.comm.NXTComm;
import lejos.pc.comm.NXTCommFactory;
import lejos.pc.comm.NXTConnector;
import lejos.pc.comm.NXTInfo;

public class BluetoothMaster {
	
	private static NXTInfo[] nxts;
	private static NXTConnector conn = new NXTConnector();
	private static Scanner sc = new Scanner(System.in);
	
	public static void main(String args[]){
		start();
	}
	
	public static void start(){
		System.out.println("Suche BT NXTs...");
		nxts = conn.search("", null, NXTCommFactory.BLUETOOTH);
		
		for(int i = 0; i < nxts.length; i++){
			System.out.println(i + ": " + nxts[i].name + " " + nxts[i].deviceAddress);
		}
		
		int index = sc.nextInt();
		if(!conn.connectTo(nxts[index], NXTComm.PACKET)) {
			System.out.println("Konnte nicht verbinden...");
			System.exit(0);
		}
		
		NXTComm comm = conn.getNXTComm();
		sc.useDelimiter(System.getProperty("line.separator"));
		
		while (true) {
			try{
				System.out.println("Input Kp: ");
				String cmd = sc.next();
				comm.write(cmd.getBytes());
				System.out.println("Input Tn: ");
				cmd = sc.next();
				comm.write(cmd.getBytes());
				System.out.println("Input Tv: ");
				cmd = sc.next();
				comm.write(cmd.getBytes());
				
			} catch(IOException e){
				System.out.println(e.getMessage());
			}
		}
	}
}
