package activitatFTPm9;

import java.io.*;
import java.util.Scanner;

import org.apache.commons.net.ftp.*;

public class ClientFTP2 {
	
	public static void main (String[] args) {
		Scanner teclado = new Scanner(System.in);
		
		//Servidor FTP
		FTPClient client = new FTPClient();
		System.out.println("Introduce el servidor FTP: exemple ->  ftp.urv.es ");
		String ServerFTP = teclado.nextLine();
		System.out.println("Ens connectem al servidor: "+ServerFTP);
			
		//Usuari FTP
		System.out.println("Introdueix usuari: Exemple -> anonymous");
		String usuari = teclado.next();
		
		System.out.println("Introdueix contrasenya: Exemple -> guest");
		String contrasenya = teclado.next();
		
		try {
			
			client.connect(ServerFTP);
			boolean login = client.login(usuari, contrasenya);
			
			if (login)
				
				System.out.println("Login correcte... ");
				
			else {
				
				System.out.println("Login incorrecte... ");
				client.disconnect();
				System.exit(1);
				
			}
			
			System.out.println("Directori actual: "+client.printWorkingDirectory());
			FTPFile[] files = client.listFiles();
			System.out.println("Fitxers al directori actual: "+files.length);
			
			//Array par a visualitzar el tipus de fitxer
			String tipus[] = {"Fitxer", "Directori", "Enlla� simbolic"};
			
			for (int i=0; i<files.length; i++) {
				
				System.out.println("\t"+files[i].getName()+"=>"+tipus[files[i].getType()]);
				
			}
			
			boolean logout = client.logout();
				
			if (logout)
				
				System.out.println("Logout del servidor FTP... ");
			
			else
				
				System.out.println("Error en fer un logout... ");
			
			client.disconnect();
			System.out.println("Desconnectat... ");
			
		} catch (IOException ioe) {
			
			ioe.printStackTrace();
			
		}
		
		
		
	}

}