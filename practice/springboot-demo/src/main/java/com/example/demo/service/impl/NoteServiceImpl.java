package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.Note;
import com.example.demo.mapper.NoteMapper;
import com.example.demo.service.NoteService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class NoteServiceImpl extends ServiceImpl<NoteMapper, Note> implements NoteService {

    @Override
    public Page<Note> pageNotes(long current, long size, String keyword) {
        LambdaQueryWrapper<Note> wrapper = new LambdaQueryWrapper<Note>()
                .like(StringUtils.hasText(keyword), Note::getTitle, keyword)
                .orderByDesc(Note::getUpdatedAt);
        return this.page(new Page<>(current, size), wrapper);
    }
}
