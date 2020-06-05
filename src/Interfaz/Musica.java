package Interfaz;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;

public class Musica {
	
	public static final String TYPE_A = "data"+File.separator+"Musica"+File.separator+"TypeA.wav";
	public static final String TYPE_B = "data"+File.separator+"Musica"+File.separator+"TypeB.wav";
	public InterfazTetris principal;
	
	public Clip clip;
	
	public Musica(InterfazTetris pPrincipal){
		principal = pPrincipal;
		
	}
	public void play(String pRuta){
		try{
			File a = new File(pRuta);
			if(a.exists()){
				AudioInputStream ai = AudioSystem.getAudioInputStream(a);
				clip = AudioSystem.getClip();
				clip.open(ai);
				clip.start();
				
				
			}else{
				System.out.println("El archivo no existe");
			}
		}catch(Exception e){
			JOptionPane.showMessageDialog(principal, this, "Error al abrir el archivo", 0);
		}
	}
	public void stop(){
		clip.stop();
	}
}
