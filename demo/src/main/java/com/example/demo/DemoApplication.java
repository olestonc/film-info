package com.example.demo;

import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.persistence.dao.CategoryDao;
import com.example.demo.persistence.dao.TvShowDao;
import com.example.demo.persistence.entity.CategoryEntity;
import com.example.demo.persistence.entity.SeasonEntity;
import com.example.demo.persistence.entity.TvShowEntity;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
	@Autowired
	CategoryDao categoryDao;
	@Autowired
	TvShowDao tvShowDao;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		createEntities();

	}

	private void createEntities() {
		CategoryEntity c1 = new CategoryEntity((long) 1, "Comedy");
		CategoryEntity c2 = new CategoryEntity((long) 2, "Drama");
		CategoryEntity c3 = new CategoryEntity((long) 3, "Terror");
		CategoryEntity c4 = new CategoryEntity((long) 4, "SCI-FI");

		categoryDao.save(c1);
		categoryDao.save(c2);
		categoryDao.save(c3);
		categoryDao.save(c4);

		TvShowEntity sh1 = new TvShowEntity((long) 1, "Breaking Bad",
				"A high school chemistry teacher turns to a life of crime, producing and selling methamphetamine.",
				"Walter White, a high school chemistry teacher, discovers that he has terminal lung cancer. To secure his family's financial future, he turns to a life of crime and starts producing and selling methamphetamine with a former student, Jesse Pinkman. As their operation becomes more successful, they attract the attention of dangerous drug cartels and law enforcement.",
				2008, 18, true, c2, new TreeSet<SeasonEntity>());

		TvShowEntity sh2 = new TvShowEntity((long) 2, "The Office",
				"A mockumentary on a group of typical office workers, where the workday consists of ego clashes, inappropriate behavior, and tedium.",
				"The Office is a comedic mockumentary series that follows the everyday lives of office employees working at the Scranton, Pennsylvania, branch of the fictional Dunder Mifflin Paper Company. From the bumbling Regional Manager Michael Scott, to the flirtatious receptionist Pam Beesly, to the eccentric salesman Jim Halpert, the series provides a hilarious look at the absurdity of everyday office life.",
				2005, 14, false, c1, new TreeSet<SeasonEntity>());

		TvShowEntity sh3 = new TvShowEntity((long) 3, "Stranger Things",
				"When a young boy disappears, his mother, a police chief, and his friends must confront terrifying supernatural forces in order to get him back.",
				"Stranger Things is a science fiction-horror series set in the fictional town of Hawkins, Indiana in the 1980s. When a young boy named Will goes missing, his mother, police chief, and friends launch a desperate search to find him. Along the way, they encounter supernatural forces and a mysterious girl with psychic powers who may hold the key to Will's disappearance.",
				2016, 16, true, c4, new TreeSet<SeasonEntity>());

		tvShowDao.save(sh1);
		tvShowDao.save(sh2);
		tvShowDao.save(sh3);
		System.out.println("Entidades creadas");

	}

}
