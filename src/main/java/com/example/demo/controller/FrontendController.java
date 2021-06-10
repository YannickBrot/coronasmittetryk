package com.example.demo.controller;

import com.example.demo.Service.KommuneService;
import com.example.demo.Service.SognService;
import com.example.demo.model.Kommune;
import com.example.demo.model.Sogn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

@Controller
public class FrontendController {

    @Autowired
    SognService sognService;
    @Autowired
    KommuneService kommuneService;

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("sogne", sognService.findAll());
        return "index";
    }

    @GetMapping("/kommuner")
    public String kommuner(Model model){
        model.addAttribute("kommuner", kommuneService.findAll());
        return "kommuner";
    }

    @GetMapping("/createSogn")
    public String createSogn(Model model){
        model.addAttribute("kommuner", kommuneService.findAll());
        return "createSogn";
    }

    @PostMapping("/createSogn")
    public String createSogn(@ModelAttribute Sogn sogn, WebRequest request){
        sognService.create(sogn);
        return "redirect:/";
    }

    @GetMapping("/createKommune")
    public String createKommune(){
        return "createKommune";
    }

    @PostMapping("/createKommune")
    public String createKommune(@ModelAttribute Kommune kommune){
        kommuneService.create(kommune);
        return "redirect:/kommuner";
    }

    @GetMapping("/opdater/{id}")
    public String opdater(@PathVariable("id") Long id, Model model){
        model.addAttribute("sogn", sognService.findById(id));
        //add kommuner to choose from
        model.addAttribute("kommuner", kommuneService.findAll());
        return "opdater";
    }

    @PostMapping("/opdater")
    public String opdater(@ModelAttribute Sogn sogn){
        sognService.update(sogn);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        sognService.deleteById(id);
        return "redirect:/";
    }
}


