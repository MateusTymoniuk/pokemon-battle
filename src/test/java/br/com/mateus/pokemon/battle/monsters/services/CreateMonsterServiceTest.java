package br.com.mateus.pokemon.battle.monsters.services;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.web.server.ResponseStatusException;

import br.com.mateus.pokemon.battle.monsters.entities.Monster;
import br.com.mateus.pokemon.battle.monsters.repositories.MonsterRepository;

public class CreateMonsterServiceTest {

	@Mock
	private MonsterRepository monsterRepository;

	@InjectMocks
	private CreateMonsterService createMonsterService;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldCreateMonsterIfItDoesntExist() throws Exception {
		when(monsterRepository.findByName("Test")).thenReturn(Optional.empty());

		when(monsterRepository.save(any(Monster.class))).thenReturn(new Monster());

		Monster monster = new Monster();

		assertThat(createMonsterService.execute(monster), is(notNullValue()));
	}
	
	@Test(expected = ResponseStatusException.class)
	public void shouldThrowErrorIfMonsterWithPassedNameAlreadyExists() throws Exception {
		String existingName = "Existing Name";
		
		when(monsterRepository.findByName(existingName)).thenAnswer(new Answer<Optional<Monster>>() {

			@Override
			public Optional<Monster> answer(InvocationOnMock invocation) throws Throwable {
				Object[] arguments = invocation.getArguments();
				
				if (arguments != null && arguments.length > 0 && arguments[0] != null){
					Monster monster = new Monster();
					monster.setName((String) arguments[0]);
					Optional<Monster> optionalMonster = Optional.of(monster);
					return optionalMonster;
				}
				return null;
			}
		});
		
		Monster newMonsterWithExistingName = new Monster();
		newMonsterWithExistingName.setName(existingName);
		
		createMonsterService.execute(newMonsterWithExistingName);
	}

}
