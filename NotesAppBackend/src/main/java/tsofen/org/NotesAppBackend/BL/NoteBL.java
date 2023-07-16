package tsofen.org.NotesAppBackend.BL;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import exceptions.ResourceNotFoundException;
import tsofen.org.NotesAppBackend.Beans.Note;
import tsofen.org.NotesAppBackend.repositories.NoteRepository;

@Service
public class NoteBL {

	@Autowired
	private NoteRepository noteDao;

	public Note addNote(Note note) {
		return noteDao.save(note);
	}

	public void deleteNote(int id) {
		noteDao.deleteById(id);
	}

	public List<Note> getAllNotes() {
		return noteDao.findAll();
	}

	public Note updateNote(Integer id, Note note) {
		return noteDao.findById(id).map(existingNote -> {
			existingNote.setTitle(note.getTitle());
			existingNote.setBody(note.getBody());
			existingNote.setPriority(note.getPriority());
			return noteDao.save(existingNote);
		}).orElseThrow(() -> new ResourceNotFoundException("Note not found with id " + id));
	}

	public void deleteAll() {
		noteDao.deleteAll();
	}

}
