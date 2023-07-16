package tsofen.org.NotesAppBackend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tsofen.org.NotesAppBackend.BL.NoteBL;
import tsofen.org.NotesAppBackend.Beans.Note;

@RestController
@RequestMapping("notes")
public class NoteController {
	@Autowired
	NoteBL notebl;
	
	@PostMapping("add")
	public Note addNote(@RequestBody Note note){
		return notebl.addNote(note);
	}
	
	@DeleteMapping("delete/{id}")
	public void deleteNote(@PathVariable int id){
		notebl.deleteNote(id);
	}
	
	@GetMapping("getAll")
	
	public List<Note> getAll(){
		return this.notebl.getAllNotes();
	}
	
	@PutMapping("updateNote/{id}")
	public Note updateNote(@PathVariable Integer id, @RequestBody Note note){
	    return notebl.updateNote(id, note);
	}
	
	@DeleteMapping("deleteAll")
	public ResponseEntity<String> deleteAll(){
	    notebl.deleteAll();
	    return new ResponseEntity<>("All notes deleted", HttpStatus.OK);
	}
	

	
	
}
