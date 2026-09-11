package pruebas;

import java.time.LocalDate;

import com.ipartube.entidades.Video;
import com.ipartube.entidades.VideoDestacado;

public class VideoDestacadoPrueba {
	public static void main(String[] args) {
		VideoDestacado video = new VideoDestacado();

		video.setTitulo("                           Regreso al futuro       ");

		System.out.println("#" + video.getTitulo() + "#");

		video.setFechaDestacado(LocalDate.now());

		System.out.println(video);

		VideoDestacado vd = new VideoDestacado();

		vd.setFechaDestacado(LocalDate.now());

		System.out.println(vd);

		Video v = vd; // Generalización

//		System.out.println(v.getFechaDestacado());

		if (v instanceof VideoDestacado vd2) {
//			VideoDestacado vd2 = (VideoDestacado) v; // Particularización

			System.out.println(vd2.getFechaDestacado());
		} else {
			System.out.println("v no es un VideoDestacado");
		}

		Video v2 = new Video();

		if (v2 instanceof VideoDestacado) {
			VideoDestacado vd3 = (VideoDestacado) v2;
			System.out.println(vd3.getFechaDestacado());
		} else {
			System.out.println("v2 no es un VideoDestacado");
		}

		Object o = vd;
		
		System.out.println(o);
	}
}
