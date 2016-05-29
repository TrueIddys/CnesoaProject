//package com.cnesoa.config;
//
//import org.hibernate.search.jpa.FullTextEntityManager;
//import org.hibernate.search.jpa.Search;
//import org.springframework.context.ApplicationEvent;
//import org.springframework.context.ApplicationListener;
//import org.springframework.stereotype.Component;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//
///**
// * Created by Maxime on 10/05/2016.
// */
//
//@Component
//public class BuildSearchIndex implements ApplicationListener{
//
//    @PersistenceContext
//    private EntityManager entityManager;
//
//
//    @Override
//    public void onApplicationEvent(ApplicationEvent applicationEvent) {
//        try {
//            FullTextEntityManager fullTextEntityManager =
//                    Search.getFullTextEntityManager(entityManager);
//            fullTextEntityManager.createIndexer().startAndWait();
//        } catch (InterruptedException e) {
//            System.out.println("Une erreur est arrivé lors de l'indexation" + e.getMessage());
//        }
//        return;
//    }
//}
