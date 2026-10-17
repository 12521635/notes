package com.example.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.Note;

public interface NoteService extends IService<Note> {

    Page<Note> pageNotes(long current, long size, String keyword);
}
