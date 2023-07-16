package tsofen.org.NotesAppBackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import tsofen.org.NotesAppBackend.Beans.Note;

public interface NoteRepository extends JpaRepository<Note, Integer>{
	
	public Note findById (int id);
	public Note findByTitle (String title);

}
