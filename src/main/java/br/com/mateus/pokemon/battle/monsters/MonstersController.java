package br.com.mateus.pokemon.battle.monsters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MonstersController {

    @Autowired
    MonsterService monsterService;

    @RequestMapping(value = "/pokemon", method = RequestMethod.POST)
    public ResponseEntity<Monster> addPokemon(@RequestBody Monster monster) {
            return monsterService.addPokemon(monster);
    }

    @RequestMapping(value = "/pokemons", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Monster>> getAllPokemons() {
        return monsterService.getAllPokemons();
    }

    @RequestMapping(value = "/pokemon/{id}", method = RequestMethod.GET)
    public ResponseEntity<Monster> getPokemonById(@PathVariable("id") Long id) {
        return monsterService.getPokemonById(id);
    }

    @RequestMapping(value = "/pokemon/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Monster> deletePokemonById(@PathVariable("id") Long id) {
        return monsterService.deletePokemonById(id);
    }
}
