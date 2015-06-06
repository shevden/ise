package com.ds.ise.data.dao;


import com.ds.ise.entity.Note;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class NoteDAO extends CommonDAO<Note> {

    public static final String USER_ID_FIELD_NAME = "userId";

    public NoteDAO() {
        super(Note.class);
    }

    public void delete(long id) {
        super.delete(id);
    }

    public List<Note> findByUserId(long id){
        return findFilteredResults(USER_ID_FIELD_NAME, id);
    }

}
