package com.yiwxine.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yiwxine.dao.NoteDao;
import com.yiwxine.domain.Note;
import com.yiwxine.util.MyPage;

@Controller
public class NotesController {

	@Resource
	private NoteDao noteDao;
	
	//显示所有笔记
	@GetMapping("/")
	public String Notes(Model model,
			@RequestParam(value = "page", defaultValue = "1", required = false) int page,
			@RequestParam(value = "pagesize", defaultValue = "4", required = false) int pagesize){
	   MyPage<Note> note = noteDao.findAll(page, pagesize);
	   model.addAttribute("note",note);
	   return "notes";
	}
}
