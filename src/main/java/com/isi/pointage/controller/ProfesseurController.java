package com.isi.pointage.controller;

import com.isi.pointage.entity.Professeur;
import com.isi.pointage.repository.IProfesseur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfesseurController {
    @Autowired
    private IProfesseur prof;
    @GetMapping(value = "/")
    public String index() {
        return "index";
    }

    @PostAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping(value = "professeur/add")
    public String add(Model model) {
        Professeur professeur = new Professeur();
        model.addAttribute("professeur",new  Professeur());
        return "professeur/add";
    }
    @PostMapping(value = "/professeur/save")
    public String save(Professeur professeur, Model model) {
        model.addAttribute("professeur",new  Professeur());
        System.out.println(professeur.getEmail());
        prof.save(professeur);
        return "professeur/add";
    }
    @PostAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping(value = "/professeur/list")
    public String getAll(Model model) {
        model.addAttribute("professeur", prof .findAll());
        return "professeur/all";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Professeur professeur = prof.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid professeur Id:" + id));

        model.addAttribute("professeur", professeur);
        return "professeur/edit";
    }
    @PostMapping("/update/{id}")
    public String updateProfesseur(@PathVariable("id") Integer id, @Validated Professeur professeur,
                                   BindingResult result, Model model) {

        prof.save(professeur);
        return "professeur/all";
    }

    @GetMapping("/delete/{id}")
    public String deleteProfesseur(@PathVariable("id") Integer id, Model model) {
        Professeur professeur = prof.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid professeur Id:" + id));
        prof.delete(professeur);
        return "redirect:/professeur/all";
    }
    @GetMapping("/logout")
    public String logout (HttpServletRequest request) throws ServletException {
        request.logout();
        return "redirect:/";
    }
}
