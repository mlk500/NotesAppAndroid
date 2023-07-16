# Notes App

"Notes App" is a mobile application built with Android Studio, Java Spring Boot and MySQL database. It allows users to manage notes by performing actions such as creating, reading, updating, and deleting notes. The communication between the front-end and back-end is accomplished via RestAPIs.

## Application Features

### 1. All Notes - Main Screen

<img width="292" alt="image" src="https://github.com/mlk500/NotesAppAndroid/assets/57171298/31cd1c86-c49d-45ca-90e6-f98bd7e8fc63">

The main screen displays all the notes stored in the database. Each note is represented by a list item in the RecyclerView, displaying the note's title, and it is colored according to the priority it was set to.

### 2. Add Note Screen

<img width="292" alt="image" src="https://github.com/mlk500/NotesAppAndroid/assets/57171298/ac77465e-e439-4366-ade7-74fdda0ecd98">

The "Add Note" screen allows users to create a new note by entering a title, content, and set a priority to it. Once saved, the note is stored in the MySQL database and displayed on the main screen.

### 3. View Note

<img width="292" alt="image" src="https://github.com/mlk500/NotesAppAndroid/assets/57171298/1020baf3-e17a-4de6-8b83-7ea7c4331651">

When a note is selected from the main screen, the note details are displayed. Here users can view the full note.
Users can also edit the note. The 'Save Note' button remains disabled until a change is made, after which it is automatically enabled.

### 4. Delete Note - Option 1

![delete_swipe](https://github.com/mlk500/NotesAppAndroid/assets/57171298/6dcee9b2-88a3-48d4-a98f-5f83dcdad064)

The first option to delete a note is by swiping it in the main list

### 5. Delete Note - Option 2

<img width="292" alt="image" src="https://github.com/mlk500/NotesAppAndroid/assets/57171298/720a5a3a-045b-43dd-9961-654c80e431e1">

The second option for deleting a note is by clicking on the "Delete" button, when viewing note's details.

### 6. Delete All Notes

<img width="292" alt="image" src="https://github.com/mlk500/NotesAppAndroid/assets/57171298/9260b597-0e85-451d-bf47-a78a31ebbd92">

The "Delete All Notes" button, located above the list of notes on the main screen, allows users to remove all the notes from the app and the database.

### 7. Database Schema

![image](https://github.com/mlk500/NotesAppAndroid/assets/57171298/37cfdbf7-2bdd-4def-b249-e7c94fb78fd3)

The notes are stored in a MySQL database. Here is the database schema used for storing the notes.

For a practical understanding of all these features, refer to the video demonstration below:

https://github.com/mlk500/NotesAppAndroid/assets/57171298/a249e7c5-9b4d-4171-86e9-b2bbf483abfa


