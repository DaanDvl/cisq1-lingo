package nl.hu.cisq1.lingo.trainer;

import nl.hu.cisq1.lingo.trainer.data.SpringGameRepository;
import nl.hu.cisq1.lingo.trainer.domain.Game;
import org.springframework.boot.CommandLineRunner;

public class TrainerTestDataFixtures implements CommandLineRunner {
    private final SpringGameRepository repository;

    public TrainerTestDataFixtures(SpringGameRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) {
        this.repository.save(new Game("pizza"));
        this.repository.save(new Game("oranje"));
        this.repository.save(new Game("wanorde"));
    }
}
