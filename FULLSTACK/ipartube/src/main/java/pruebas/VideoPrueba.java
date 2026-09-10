package pruebas;

import java.time.LocalDate;

import com.ipartube.entidades.Video;

public class VideoPrueba {
	public static void main(String[] args) {
		Video video = new Video();
		
		System.out.println(video);
		
		video.setTitulo("                           Regreso al futuro       ");
		
		System.out.println("#" + video.getTitulo() + "#");
		
		Video v = new Video(1L, LocalDate.now(), "https://...", "    Superman     ", "No veas que bueno es");
		
		System.out.println(v);
		
		System.out.println(v.getUrl());
		System.out.println(v.getTitulo());
		
		Video v2 = new Video("https://otraurl", "Otro video");
		
		System.out.println(v2);
		
		System.out.println(v2.getUrl());
		System.out.println(v2.getTitulo());
	}
}
