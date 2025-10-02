# Flashcards Console App (Polish / English / German)

A **console-based flashcards application** for **Polish, English, and German** words.  
Built in **Java with Spring**, it demonstrates **Dependency Injection**, **Inversion of Control (IoC)**, **profiles**, and **external YAML configuration**. 

## Purpose

The goal of this project is to combine a simple **language-learning flashcards tool** with clean software engineering practices.  

Users can:
- Add new vocabulary entries (Polish, English, German)
- List all stored words in different display modes
- Take interactive quizzes that test translations between the three languages

## Features

### Dictionary Management
- **Add new word**: input a word in Polish, English, and German, then save it to the dictionary.
- **List all words**: display the dictionary in a human-readable format.

### Quiz Mode
- A random **base language** and word are selected.
- The user must provide translations in the **other two languages**.
- Validation is **case-insensitive** and supports Polish diacritics.
- Immediate feedback is provided:
  -  Both correct  
  -  One or more incorrect (with corrections shown)
 
### Profiles (Spring Profiles)
The way words are displayed depends on the active profile:
- `original` → words shown as stored in the CSV  
- `upper` → words displayed in **UPPERCASE**  
- `lower` → words displayed in **lowercase**

The project was created as an exercise for Polish Japanese Academy
