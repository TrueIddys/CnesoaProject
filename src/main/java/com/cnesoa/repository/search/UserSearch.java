package com.cnesoa.repository.search;

import com.cnesoa.domain.Animal.Animal;
import com.cnesoa.domain.Consultation.Diagnostic;
import com.cnesoa.domain.Consultation.Traitement;
import com.cnesoa.domain.Person.Client;
import com.cnesoa.domain.Person.Contact.Adresse;
import com.cnesoa.domain.Person.Contact.Contact;
import com.cnesoa.domain.Person.Eleve;
import com.cnesoa.domain.Person.Professeur;
import org.apache.lucene.search.Query;
import org.hibernate.Criteria;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Maxime on 10/05/2016.
 */

@Repository
public class UserSearch {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Search the database for the text
     * @param text
     * @return
     */
    public List search(String text){

        FullTextEntityManager fullTextEntityManager =
                Search.getFullTextEntityManager(entityManager);

        List results = new ArrayList<>();
        results.add(setUpJpaQuery(new String[]{"nom", "race", "type"}, Animal.class,
                text, fullTextEntityManager).getResultList());
        results.add(setUpJpaQuery(new String[]{"diagnosticText", "douleur", "dynamique", "palpatoire"}, Diagnostic.class,
                text, fullTextEntityManager).getResultList());
        results.add(setUpJpaQuery(new String[]{"nom", "prenom", "mail", "tel"}, Contact.class,
                text, fullTextEntityManager).getResultList());
        results.add(setUpJpaQuery(new String[]{"rue", "ville", "codePostal"}, Adresse.class,
                text, fullTextEntityManager).getResultList());
        results.add(setUpJpaQuery(new String[]{"details", "conseils"}, Traitement.class,
                text, fullTextEntityManager).getResultList());

        return results;
    }

    /**
     * Create a Jpa query based on
     * @param listeAttributes
     * @param classe
     * @param text what we're searching for
     * @param fullTextEntityManager
     * @return
     */
    private FullTextQuery setUpJpaQuery(String[] listeAttributes, Class classe,
                                        String text, FullTextEntityManager fullTextEntityManager){
        Query query = queryBuilder(listeAttributes, classe, text, fullTextEntityManager);
        FullTextQuery jpaQuery = fullTextEntityManager.createFullTextQuery(query, classe);
        jpaQuery.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return jpaQuery;
    }

    /**
     * Get the list of animals from the results of the query
     * @param results
     * @return
     */
    public List<Animal> getResultsAnimaux(List results){
        List<Animal> resultsAnimaux = new ArrayList<>();
        if (!results.get(0).equals(Collections.EMPTY_LIST)) {
            resultsAnimaux.addAll((List<Animal>)results.get(0));
        }
        if (!results.get(1).equals(Collections.EMPTY_LIST)) {
            for (Diagnostic d : (List<Diagnostic>)results.get(2)){
                resultsAnimaux.add(d.getConsultation().getAnimal());
            }
        }
        if (!results.get(4).equals(Collections.EMPTY_LIST))
            for (Traitement t : (List<Traitement>)results.get(4)){
                resultsAnimaux.add(t.getConsultation().getAnimal());
            }
        return resultsAnimaux;
    }

    /**
     * Get the list of clients from the results of the query
     * @param results
     * @return
     */
    public List<Client> getResultsClients(List results){
        List<Client> resultsClients = new ArrayList<>();
        if (!results.get(2).equals(Collections.EMPTY_LIST)){
            for (Contact c : (List<Contact>)results.get(2)){
                if (c.getPerson().getClass().equals(Client.class))
                    resultsClients.add((Client)c.getPerson());
            }
        }
        if (!results.get(3).equals(Collections.EMPTY_LIST)){
            for (Adresse a : (List<Adresse>)results.get(3)){
                resultsClients.add(a.getClient());
            }
        }
        return resultsClients;
    }

    /**
     * Get the list of eleves from the results of the query
     * @param results
     * @return
     */
    public List<Eleve> getResultsEleves(List results){
        List<Eleve> resultsEleves = new ArrayList<>();
        if (!results.get(2).equals(Collections.EMPTY_LIST)){
            for (Contact c : (List<Contact>)results.get(2)){
                if (c.getPerson().getClass().equals(Eleve.class))
                    resultsEleves.add((Eleve)c.getPerson());
            }
        }
        return resultsEleves;
    }

    /**
     * Get the list of professeur from the results of the query
     * @param results
     * @return
     */
    public List<Professeur> getResultsProfesseurs(List results){
        List<Professeur> resultsProfesseurs = new ArrayList<>();
        if (!results.get(2).equals(Collections.EMPTY_LIST)){
            for (Contact c : (List<Contact>)results.get(2)){
                if (c.getPerson().getClass().equals(Professeur.class))
                    resultsProfesseurs.add((Professeur)c.getPerson());
            }
        }
        return resultsProfesseurs;
    }


    /**
     * Create a apache query based on a
     * @param listeAttributes the list of attributes we wanna search
     * @param classe the class which contains the attributes
     * @param text the text we're searching for
     * @param fullTextEntityManager the entity manager in charge of creating the query
     * @return
     */
    private Query queryBuilder(String[] listeAttributes, Class classe,
                                String text, FullTextEntityManager fullTextEntityManager){
        QueryBuilder queryBuilderContact =
                fullTextEntityManager.getSearchFactory().buildQueryBuilder()
                        .forEntity(classe).get();

        Query apacheQuery = queryBuilderContact
                .keyword()
                .onFields(listeAttributes)
                .matching(text)
                .createQuery();

        return apacheQuery;
    }
}
