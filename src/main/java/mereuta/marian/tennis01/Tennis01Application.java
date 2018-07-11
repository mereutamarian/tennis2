package mereuta.marian.tennis01;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
public class Tennis01Application {

//	public class AnnulationService{
//		private int heureAnnulation = 24;
//
//		public Annulation(int newHeureAnnul){
//			heureAnnulation = newHeureAnnul;
//		}


	public static void main(String[] args) {




		ApplicationContext context = SpringApplication.run(Tennis01Application.class, args);




	}
}