package br.com.mateus.pokemon.battle.monsters.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mateus.pokemon.battle.monsters.entities.Monster;

public interface MonsterRepository extends JpaRepository<Monster, Long> {
	
	public Optional<Monster> findByName(String name);
}
