package com.yiwxine.web;

import java.sql.Timestamp;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yiwxine.dao.NoteDao;
import com.yiwxine.domain.Note;

@Controller
public class NoteController {
  
	@Resource
	private NoteDao noteDao;
	
	@GetMapping("/addnote")
	public String addnote(){
		return "add_note";
		
	}
	
	@PostMapping("/addnote")
	public String create(String title,String content){
		Note note = new Note();
		note.setId(UUID.randomUUID().toString().replace("-", ""));
		note.setTitle(title);
		note.setContent(content);
		note.setPubtime(new Timestamp(System.currentTimeMillis()));
		noteDao.save(note);
		return "redirect:/";
	}
	
	
	@RequestMapping("/note/{noteid}/delete")
	@ResponseBody
	public String delete(@PathVariable String noteid){
		Note note = noteDao.findById(noteid);
		if(null != note && !StringUtils.isBlank(note.getId()))
			noteDao.delete(note);
		System.out.println("删除");
		return "success";
	}
	
	@RequestMapping("/note/{noteid}")
	public String details(Model model,@PathVariable String noteid){
		Note note = noteDao.findById(noteid);
		model.addAttribute("note",note);
		return "note_details";
	}
	
	@GetMapping("/note/{noteid}/update")
	public String edit(Model model,@PathVariable String noteid){
		Note note = noteDao.findById(noteid);
		model.addAttribute("note", note);
		return "note_update";
	}
	
	@PostMapping("/note/{noteid}/update")
	@ResponseBody
	public String update(Model model,@PathVariable String noteid,String title,String content){
		Note note = noteDao.findById(noteid);
		if(null != note && !StringUtils.isBlank(note.getId())){
			note.setTitle(title);
			note.setContent(content);
			noteDao.merage(note);
			System.out.println("执行修改");
		}
		return "success";
	}
}
