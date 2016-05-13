package com.cnesoa.domain.Person;

import com.cnesoa.domain.Person.Contact.Adresse;
import com.cnesoa.utils.Generator;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Maxime on 10/05/2016.
 */
public class ClientTest {

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithNullName(){
        new Client(null, Generator.createName(), Generator.createMail(), Generator.createTel());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithNullSurname(){
        new Client(Generator.createName(), null, Generator.createMail(), Generator.createTel());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithNullMail(){
        new Client(Generator.createName(), Generator.createName(), null, Generator.createTel());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithNullTel(){
        new Client(Generator.createName(), Generator.createName(), Generator.createMail(), null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithNullAdress(){
        new Client(Generator.createName(), Generator.createName(), Generator.createMail(), Generator.createTel(), null);
    }

    @Test
    public void shouldInstantiate(){
        String name = Generator.createName();
        String surname = Generator.createName();
        String tel = Generator.createTel();
        String mail = Generator.createMail();
        Adresse adresse = new Adresse("12", "Avenue des Lilas", "43700", "Brives Charensac");
        Client client = new Client(name, surname, mail, tel, adresse);

        assertThat(client.getId()).isNull();
        assertThat(client.getContact().getNom()).isEqualTo(name);
        assertThat(client.getContact().getPrenom()).isEqualTo(surname);
        assertThat(client.getContact().getMail()).isEqualTo(mail);
        assertThat(client.getContact().getTel()).isEqualTo(tel);
        assertThat(client.getAdresse()).isEqualTo(adresse);
    }

    @Test
    public void shouldBeEqualById(){
        Client client = new Client();
        client.setId(1L);
        Client client2 = new Client();
        client2.setId(1L);
        assertThat(client).isEqualTo(client2);
    }

    @Test
    public void shouldNotBeEqualById(){
        Client client = new Client();
        client.setId(1L);
        Client client2 = new Client();
        client2.setId(2L);
        assertThat(client).isNotEqualTo(client2);
    }
}
