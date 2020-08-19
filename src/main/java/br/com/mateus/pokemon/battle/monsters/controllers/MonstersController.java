package br.com.mateus.pokemon.battle.monsters.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.mateus.pokemon.battle.monsters.entities.Monster;
import br.com.mateus.pokemon.battle.monsters.services.CreateMonsterService;
import br.com.mateus.pokemon.battle.monsters.services.DeleteMonsterService;
import br.com.mateus.pokemon.battle.monsters.services.FindAllMonstersService;
import br.com.mateus.pokemon.battle.monsters.services.FindMonsterByIdService;

@RestController
public class MonstersController {

	@Autowired
	FindAllMonstersService findAllMonstersService;

	@Autowired
	FindMonsterByIdService findMonsterByIdService;

	@Autowired
	CreateMonsterService createMonsterService;

	@Autowired
	DeleteMonsterService deleteMonsterService;

	@RequestMapping(value = "/pokemon", method = RequestMethod.GET)
	public ResponseEntity<List<Monster>> getAllPokemons() {
		List<Monster> allMonsters = findAllMonstersService.execute();
		return ResponseEntity.status(HttpStatus.OK).body(allMonsters);
	}

	@RequestMapping(value = "/pokemon/{id}", method = RequestMethod.GET)
	public ResponseEntity<Monster> getPokemonById(@PathVariable("id") Long id) {
		Monster monster = findMonsterByIdService.execute(id);
		return ResponseEntity.status(HttpStatus.OK).body(monster);
	}

	@RequestMapping(value = "/pokemon", method = RequestMethod.POST)
	public ResponseEntity<Monster> addPokemon(@RequestBody Monster monster) {
		Monster createdMonster = createMonsterService.execute(monster);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdMonster);
	}

	@RequestMapping(value = "/pokemon/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletePokemonById(@PathVariable("id") Long id) {
		deleteMonsterService.execute(id);
		return ResponseEntity.noContent().<Void>build();
	}
}
