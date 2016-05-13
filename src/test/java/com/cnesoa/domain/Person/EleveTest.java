package com.cnesoa.domain.Person;

import com.cnesoa.utils.Generator;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Maxime on 10/05/2016.
 */
public class EleveTest {

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithNullName(){
        new Eleve(null, Generator.createName(), Generator.createMail(), Generator.createTel());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithNullSurname(){
        new Eleve(Generator.createName(), null, Generator.createMail(), Generator.createTel());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithNullMail(){
        new Eleve(Generator.createName(), Generator.createName(), null, Generator.createTel());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithNullTel(){
        new Eleve(Generator.createName(), Generator.createName(), Generator.createMail(), null);
    }

    @Test
    public void shouldInstantiate(){
        String name = Generator.createName();
        String surname = Generator.createName();
        String tel = Generator.createTel();
        String mail = Generator.createMail();
        Eleve eleve = new Eleve(name, surname, mail, tel);

        assertThat(eleve.getId()).isNull();
        assertThat(eleve.getContact().getNom()).isEqualTo(name);
        assertThat(eleve.getContact().getPrenom()).isEqualTo(surname);
        assertThat(eleve.getContact().getMail()).isEqualTo(mail);
        assertThat(eleve.getContact().getTel()).isEqualTo(tel);
    }

    @Test
    public void shouldBeEqualById(){
        Eleve eleve = new Eleve();
        eleve.setId(1L);
        Eleve eleve2 = new Eleve();
        eleve2.setId(1L);
        assertThat(eleve).isEqualTo(eleve2);
    }

    @Test
    public void shouldNotBeEqualById(){
        Eleve eleve = new Eleve();
        eleve.setId(1L);
        Eleve eleve2 = new Eleve();
        eleve2.setId(2L);
        assertThat(eleve).isNotEqualTo(eleve2);
    }
}
