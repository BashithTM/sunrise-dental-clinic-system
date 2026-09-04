package com.sunrise.dentalclinic.controller;

import com.sunrise.dentalclinic.dto.DentistRequest;
import com.sunrise.dentalclinic.exception.BusinessException;
import com.sunrise.dentalclinic.service.DentistService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/dentists")
public class DentistController {
    private final DentistService service;

    public DentistController(DentistService service) { this.service = service; }

    @GetMapping String list(Model model) { model.addAttribute("dentists", service.all()); return "dentists/list"; }
    @GetMapping("/new") String create(Model model) { model.addAttribute("dentistRequest", new DentistRequest("", "")); return "dentists/form"; }

    @PostMapping
    String save(@Valid @ModelAttribute DentistRequest dentistRequest, BindingResult errors, RedirectAttributes redirectAttributes) {
        if (errors.hasErrors()) return "dentists/form";
        service.create(dentistRequest);
        redirectAttributes.addFlashAttribute("success", "Dentist added");
        return "redirect:/dentists";
    }

    @GetMapping("/{id}/edit")
    String edit(@PathVariable Long id, Model model) {
        var dentist = service.get(id);
        model.addAttribute("dentist", dentist);
        model.addAttribute("dentistRequest", new DentistRequest(dentist.getName(), dentist.getSpecialization()));
        return "dentists/form";
    }

    @PostMapping("/{id}")
    String update(@PathVariable Long id, @Valid @ModelAttribute DentistRequest dentistRequest, BindingResult errors, Model model) {
        if (errors.hasErrors()) { model.addAttribute("dentist", service.get(id)); return "dentists/form"; }
        service.update(id, dentistRequest);
        return "redirect:/dentists";
    }

    @PostMapping("/{id}/toggle")
    String toggle(@PathVariable Long id) { service.toggle(id); return "redirect:/dentists"; }

    @PostMapping("/{id}/delete")
    String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            service.delete(id);
            redirectAttributes.addFlashAttribute("success", "Dentist deleted successfully");
        } catch (BusinessException exception) {
            redirectAttributes.addFlashAttribute("error", exception.getMessage());
        }
        return "redirect:/dentists";
    }
}
