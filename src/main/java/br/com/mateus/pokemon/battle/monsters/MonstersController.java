package br.com.mateus.pokemon.battle.monsters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
public class MonstersController {

    @Autowired
    MonsterRepository monsterRepository;

    @RequestMapping(value = "/pokemon", method = RequestMethod.POST)
    public ResponseEntity<Monster> addPokemon(@RequestBody Monster newMonster) {
        Monster addedMonster = monsterRepository.save(newMonster);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(addedMonster);
    }

    @RequestMapping(value = "/pokemons", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Monster>> getAllPokemons() {
        Iterable<Monster> allMonsters = monsterRepository.findAll();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(allMonsters);
    }

    @RequestMapping(value = "/pokemon/{id}", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Monster>> getPokemonById(@PathVariable("id") Long id) {
        Iterable<Monster> allMonsters = monsterRepository.findAllById(Collections.singleton(id));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(allMonsters);
    }
}
