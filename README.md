# Notes App

"Notes App" is a mobile application built with Android Studio, Java Spring Boot and MySQL database. It allows users to manage notes by performing actions such as creating, reading, updating, and deleting notes. The communication between the front-end and back-end is accomplished via RestAPIs.

## Application Features

### 1. All Notes - Main Screen

<img width="292" alt="image" src="https://github.com/mlk500/NotesAppAndroid/assets/57171298/3a41a5b2-a000-4a8d-a3a3-f06af2cdcaf6">

The main screen displays all the notes stored in the database. Each note is represented by a list item in the RecyclerView, displaying the note's title, and it is colored according to the priority it was set to.

### 2. Add Note Screen

<img width="292" alt="image" src="https://github.com/mlk500/NotesAppAndroid/assets/57171298/88ad59af-ce1f-4099-bef9-cffd23f03e98">

The "Add Note" screen allows users to create a new note by entering a title, content, and set a priority to it. Once saved, the note is stored in the MySQL database and displayed on the main screen.

### 3. View Note

<img width="292" alt="image" src="https://github.com/mlk500/NotesAppAndroid/assets/57171298/76ddd5c7-35a5-42d2-8038-65b5b995fd4f">

When a note is selected from the main screen, the note details are displayed. Here users can view the full note.
Users can also edit the note. The 'Save Note' button remains disabled until a change is made, after which it is automatically enabled.

### 4. Delete Note - Option 1

![delete_swipe](https://github.com/mlk500/NotesAppAndroid/assets/57171298/01c79295-2e11-48d0-8703-176541880f61)

The first option to delete a note is by swiping it in the main list

### 5. Delete Note - Option 2

<img width="292" alt="image" src="https://github.com/mlk500/NotesAppAndroid/assets/57171298/2073a52c-aad3-4abf-b4d7-f5b4bf38c2b5">

The second option for deleting a note is by clicking on the "Delete" button, when viewing note's details.


### 6. Delete All Notes

<img width="292" alt="image" src="https://github.com/mlk500/NotesAppAndroid/assets/57171298/8f04143e-94a4-4e7d-adaf-84d0ccf7b1f1">

The "Delete All Notes" button, located above the list of notes on the main screen, allows users to remove all the notes from the app and the database.

### 7. Database Schema

<img width="1512" alt="image" src="https://github.com/mlk500/NotesAppAndroid/assets/57171298/4916c46f-7923-4780-b2b7-bf4685c6e1bc">

The notes are stored in a MySQL database. Here is the database schema used for storing the notes.

