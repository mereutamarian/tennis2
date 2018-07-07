package mereuta.marian.tennis01;


import mereuta.marian.tennis01.model.Ecran;
import mereuta.marian.tennis01.model.Horaire;
import mereuta.marian.tennis01.repository.EcranRepository;
import mereuta.marian.tennis01.repository.HoraireRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.format.annotation.DateTimeFormat;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import static java.time.temporal.ChronoUnit.HOURS;


@SpringBootApplication
public class Tennis01Application {


	public static void main(String[] args) {




		ApplicationContext context = SpringApplication.run(Tennis01Application.class, args);




	}
}