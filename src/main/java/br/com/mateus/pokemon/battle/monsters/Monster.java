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
    private long id;

    private String nome;

    public Monster() { }

    public Monster(long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

}
