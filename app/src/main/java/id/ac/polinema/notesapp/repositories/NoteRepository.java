package id.ac.polinema.notesapp.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Update;
import android.os.AsyncTask;

import java.util.List;

import id.ac.polinema.notesapp.dao.NoteDao;
import id.ac.polinema.notesapp.db.AppDatabase;
import id.ac.polinema.notesapp.models.Note;

public class NoteRepository {
    private NoteDao noteDao;

    private LiveData<List<Note>> notes;

    public NoteRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        noteDao = db.noteDao();
        notes = noteDao.getAll();
    }

    public LiveData<List<Note>> getNotes() {
        return notes;
    }

    private static class InsertAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDao asyncTaskDao;

        @Override
        protected Void doInBackground(Note... notes) {
            asyncTaskDao.insert(notes);
            return null;
        }

        InsertAsyncTask(NoteDao dao) {
            asyncTaskDao = dao;
        }
    }

    public void insert(Note note) {
        new InsertAsyncTask(noteDao)
                .execute(note);
    }

    //update
    private static class UpdateAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDao asyncTaskDao;

        @Override
        protected Void doInBackground(Note... notes) {
            asyncTaskDao.insert(notes);
            return null;
        }

        UpdateAsyncTask(NoteDao dao) {
            asyncTaskDao = dao;
        }
    }

    public void update(Note note) {
        new UpdateAsyncTask(noteDao)
                .execute(note);
    }
}
