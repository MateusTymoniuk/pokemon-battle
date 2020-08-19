package br.com.mateus.pokemon.battle.monsters.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.mateus.pokemon.battle.monsters.entities.Monster;
import br.com.mateus.pokemon.battle.monsters.repositories.MonsterRepository;

@Service
public class FindMonsterByIdService {

	@Autowired
	MonsterRepository monsterRepository;

	public Monster execute(Long id) {
		Optional<Monster> optionalMonster = monsterRepository.findById(id);

		if (!optionalMonster.isPresent()) {
			String output = String.format("Pokemon not found for id={%d}", id);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, output);
		}

		return optionalMonster.get();
	}
}
