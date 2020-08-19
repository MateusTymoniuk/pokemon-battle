package br.com.mateus.pokemon.battle.monsters.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.mateus.pokemon.battle.monsters.entities.Monster;
import br.com.mateus.pokemon.battle.monsters.repositories.MonsterRepository;

@Service
public class CreateMonsterService {

	@Autowired
	MonsterRepository monsterRepository;

	public Monster execute(Monster monster) {
		Optional<Monster> optionalMonster = monsterRepository.findByName(monster.getName());

		if (optionalMonster.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot create two monsters with same name");
		}

		return monsterRepository.save(monster);
	}

}
