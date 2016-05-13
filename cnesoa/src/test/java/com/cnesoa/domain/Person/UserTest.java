package com.cnesoa.domain.Person;

import com.cnesoa.utils.Generator;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Maxime on 10/05/2016.
 */
public class UserTest {

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithNullName(){
        new User(null, Generator.createName(), Generator.createMail(), Generator.createTel());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithNullSurname(){
        new User(Generator.createName(), null, Generator.createMail(), Generator.createTel());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithNullMail(){
        new User(Generator.createName(), Generator.createName(), null, Generator.createTel());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithNullTel(){
        new User(Generator.createName(), Generator.createName(), Generator.createMail(), null);
    }

    @Test
    public void shouldInstantiate(){
        String name = Generator.createName();
        String surname = Generator.createName();
        String tel = Generator.createTel();
        String mail = Generator.createMail();
        User user = new User(name, surname, mail, tel);

        assertThat(user.getId()).isNull();
        assertThat(user.getContact().getNom()).isEqualTo(name);
        assertThat(user.getContact().getPrenom()).isEqualTo(surname);
        assertThat(user.getContact().getMail()).isEqualTo(mail);
        assertThat(user.getContact().getTel()).isEqualTo(tel);
    }

    @Test
    public void shouldBeEqualById(){
        User user = new User();
        user.setId(1L);
        User user2 = new User();
        user2.setId(1L);
        assertThat(user).isEqualTo(user2);
    }

    @Test
    public void shouldNotBeEqualById() {
        User user = new User();
        user.setId(1L);
        User user2 = new User();
        user2.setId(2L);
        assertThat(user).isNotEqualTo(user2);
    }
}


