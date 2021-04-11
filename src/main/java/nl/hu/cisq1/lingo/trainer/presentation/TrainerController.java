package nl.hu.cisq1.lingo.trainer.presentation;

import nl.hu.cisq1.lingo.trainer.application.TrainerService;

import nl.hu.cisq1.lingo.trainer.presentation.dto.BeginWord;
import nl.hu.cisq1.lingo.trainer.presentation.dto.GameStatus;
import nl.hu.cisq1.lingo.trainer.presentation.dto.Guess;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("game")
public class TrainerController {

    private final TrainerService service;

    public TrainerController(TrainerService service) {
        this.service = service;
    }

    @PostMapping("start")
    public GameStatus startGame(@RequestBody BeginWord beginWord) {
        return this.service.provideNewGame(beginWord);
    }

    @PostMapping("{id}/guess")
    public GameStatus guessWord(@PathVariable("id") Long id, @RequestBody Guess guess) {
        try {
            return this.service.guess(id, guess);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage());
        }
    }

    @GetMapping("{id}")
    public GameStatus getStatus(@PathVariable("id") Long id) {
        try {
            return this.service.getStatus(id);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage());
        }
    }

}
