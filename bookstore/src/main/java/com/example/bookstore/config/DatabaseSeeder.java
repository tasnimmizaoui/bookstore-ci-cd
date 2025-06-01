package com.example.bookstore.config;

import com.example.bookstore.models.Book;
import com.example.bookstore.repositories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.List;

@Configuration
public class DatabaseSeeder {

    @Bean
    CommandLineRunner seedDatabase(BookRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                repository.saveAll(List.of(
                        new Book("Python pour l'Analyse de Données", "Dr. Amira Bouzid", new BigDecimal("34.99"), "Un guide pratique pour maîtriser l'analyse de données avec Python, adapté aux étudiants de l'INSAT.", 45),
                        new Book("L'Art de la Guerre", "Sun Tzu", new BigDecimal("9.99"), "Un traité de stratégie militaire appliqué au monde moderne.", 60),
                        new Book("To Kill a Mockingbird", "Harper Lee", new BigDecimal("13.50"), "Un roman poignant sur la justice, le racisme et la croissance dans l'Amérique du Sud.", 40),
                        new Book("Thinking, Fast and Slow", "Daniel Kahneman", new BigDecimal("18.99"), "Une exploration des deux systèmes de pensée humaine : rapide et intuitif, lent et réfléchi.", 30),
                        new Book("Clean Code", "Robert C. Martin", new BigDecimal("42.00"), "Un guide pour écrire du code lisible, propre et maintenable pour les développeurs professionnels.", 35),
                        new Book("Deep Learning avec Python", "François Chollet", new BigDecimal("49.99"), "Une introduction complète au deep learning en utilisant la bibliothèque Keras, idéale pour les ingénieurs INSAT.", 20),
                        new Book("Projet INSAT : Architecture des Systèmes Embarqués", "Dr. Nizar Ben Neji", new BigDecimal("39.90"), "Un manuel détaillé pour concevoir et développer des systèmes embarqués en milieu académique.", 25),
                        new Book("Gestion de Projet Agile à INSAT", "INSAT Dev Club", new BigDecimal("22.99"), "Un guide illustré des méthodes agiles, Scrum et Kanban dans les projets étudiants.", 40),
                        new Book("INSAT : 25 Ans d'Innovation", "Collectif INSAT", new BigDecimal("27.50"), "Un ouvrage commémoratif retraçant l'histoire, les réussites et les perspectives de l'INSAT.", 30),
                        new Book("Cryptographie Moderne", "Prof. Houssem Eddine Guezguez", new BigDecimal("36.00"), "Introduction à la cryptographie appliquée, avec exercices corrigés pour les étudiants en cybersécurité.", 18),
                        new Book("Les Bases de Données Relationnelles", "Dr. Faten Ghozzi", new BigDecimal("33.50"), "Cours et TP pour apprendre SQL et la modélisation de données à l'INSAT.", 32),
                        new Book("Systèmes d'Exploitation : Concepts et Applications", "Prof. Karim Zidi", new BigDecimal("29.90"), "Cours structuré pour comprendre les fondements des OS avec des cas pratiques.", 28)
                ));
            }
        };
    }
}
