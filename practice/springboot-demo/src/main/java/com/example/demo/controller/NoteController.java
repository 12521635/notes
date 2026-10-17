package com.example.demo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.dto.NoteRequest;
import com.example.demo.entity.Note;
import com.example.demo.service.NoteService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping
    public Result<Page<Note>> page(@RequestParam(defaultValue = "1") long current,
                                   @RequestParam(defaultValue = "10") long size,
                                   @RequestParam(required = false) String keyword) {
        return Result.ok(noteService.pageNotes(current, size, keyword));
    }

    @GetMapping("/{id}")
    public Result<Note> getById(@PathVariable Long id) {
        Note note = noteService.getById(id);
        if (note == null) {
            return Result.fail(404, "笔记不存在");
        }
        return Result.ok(note);
    }

    @PostMapping
    public Result<Long> create(@Valid @RequestBody NoteRequest request) {
        Note note = new Note();
        note.setTitle(request.getTitle());
        note.setContent(request.getContent());
        noteService.save(note);
        return Result.ok(note.getId());
    }

    @PutMapping("/{id}")
    public Result<Boolean> update(@PathVariable Long id, @Valid @RequestBody NoteRequest request) {
        Note note = new Note();
        note.setId(id);
        note.setTitle(request.getTitle());
        note.setContent(request.getContent());
        return Result.ok(noteService.updateById(note));
    }

    @DeleteMapping("/{id}")
    public Result<Boolean> delete(@PathVariable Long id) {
        return Result.ok(noteService.removeById(id));
    }
}
