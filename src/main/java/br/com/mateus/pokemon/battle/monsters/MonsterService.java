package br.com.mateus.pokemon.battle.monsters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Configuration
public class MonsterService {

    @Autowired
    MonsterRepository monsterRepository;

    public ResponseEntity<Monster> addPokemon(Monster newMonster) {
        Monster addedMonster = monsterRepository.save(newMonster);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(addedMonster);
    }

    public ResponseEntity<Iterable<Monster>> getAllPokemons() {
        Iterable<Monster> allMonsters = monsterRepository.findAll();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(allMonsters);
    }

    public ResponseEntity<Monster> getPokemonById(Long id) {

        Optional<Monster> foundMonster = monsterRepository.findById(id);

        checkIfMonsterExists(foundMonster, id);

        return ResponseEntity.status(HttpStatus.OK).body(foundMonster.get());
    }

    public ResponseEntity<Monster> deletePokemonById(Long id) {
        Optional<Monster> foundMonster = monsterRepository.findById(id);

        checkIfMonsterExists(foundMonster, id);

        monsterRepository.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).body(foundMonster.get());
    }

    private void checkIfMonsterExists(Optional<Monster> monster, Long id) {
        if(!monster.isPresent()){
            String output = String.format("Pokemon not found for id={%d}", id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, output);
        }
    }
}
