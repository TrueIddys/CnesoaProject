package com.cnesoa.domain.Person;

import com.cnesoa.utils.Generator;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Maxime on 10/05/2016.
 */
public class ProfesseurTest {

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithNullName(){
        new Professeur(null, Generator.createName(), Generator.createMail(), Generator.createTel());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithNullSurname(){
        new Professeur(Generator.createName(), null, Generator.createMail(), Generator.createTel());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithNullMail(){
        new Professeur(Generator.createName(), Generator.createName(), null, Generator.createTel());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithNullTel(){
        new Professeur(Generator.createName(), Generator.createName(), Generator.createMail(), null);
    }

    @Test
    public void shouldInstantiate(){
        String name = Generator.createName();
        String surname = Generator.createName();
        String tel = Generator.createTel();
        String mail = Generator.createMail();
        Professeur professeur = new Professeur(name, surname, mail, tel);

        assertThat(professeur.getId()).isNull();
        assertThat(professeur.getContact().getNom()).isEqualTo(name);
        assertThat(professeur.getContact().getPrenom()).isEqualTo(surname);
        assertThat(professeur.getContact().getMail()).isEqualTo(mail);
        assertThat(professeur.getContact().getTel()).isEqualTo(tel);
    }

    @Test
    public void shouldBeEqualById(){
        Professeur professeur = new Professeur();
        professeur.setId(1L);
        Professeur professeur2 = new Professeur();
        professeur2.setId(1L);
        assertThat(professeur).isEqualTo(professeur2);
    }

    @Test
    public void shouldNotBeEqualById(){
        Professeur professeur = new Professeur();
        professeur.setId(1L);
        Professeur professeur2 = new Professeur();
        professeur2.setId(2L);
        assertThat(professeur).isNotEqualTo(professeur2);
    }
}
