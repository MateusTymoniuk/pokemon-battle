package br.com.mateus.pokemon.battle.monsters.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mateus.pokemon.battle.monsters.entities.Monster;
import br.com.mateus.pokemon.battle.monsters.repositories.MonsterRepository;

@Service
public class FindAllMonstersService {
	
	@Autowired
    MonsterRepository monsterRepository;

	public List<Monster> execute() {
		return monsterRepository.findAll();
	}
}
