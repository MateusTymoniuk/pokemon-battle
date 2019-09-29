package br.com.mateus.pokemon.battle.monsters;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Monster {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private Double strength;

    private Integer level;

    public Monster() { }

    public Monster(
            Long id,
            String name,
            Double strength,
            Integer level
    ) {
        this.id = id;
        this.name = name;
        this.strength = strength;
        this.level = level;
    }

}
