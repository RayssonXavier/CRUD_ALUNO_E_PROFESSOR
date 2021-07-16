package com.escola.escola.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.escola.escola.dto.RequisicaoProfessor;
import com.escola.escola.model.Professor;
import com.escola.escola.model.StatusProfessor;
import com.escola.escola.respository.ProfessorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/professores")
public class ProfessorController {
    
    @Autowired
    private ProfessorRepository professorRepository;

    @GetMapping(value="")
    public ModelAndView index(){

        List<Professor> professoress = professorRepository.findAll();
        return new ModelAndView("professor/index", "lista", professoress);
    }

    @GetMapping(value="/new")
    public ModelAndView nnew(RequisicaoProfessor requisicao){
        return new ModelAndView("professor/new","status", StatusProfessor.values());
    }

    @PostMapping(value="")
    public ModelAndView create(@Valid RequisicaoProfessor requisicao, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return nnew(requisicao);
        }else{
            Professor professor = requisicao.toProfessor();
            professor = this.professorRepository.save(professor);
            return new ModelAndView("redirect:/professores/"+professor.getId());
        }
    }
    @GetMapping(value = "/{id}")
    public ModelAndView show(@PathVariable Long id){
        
        Optional<Professor> professorOptional = professorRepository.findById(id);
        
        if(professorOptional.isPresent()){
        
            Professor professor = professorOptional.get();
            return new ModelAndView("professor/show", "professor", professor);
        
        }else{
         
            return new ModelAndView("redirect:/professores"); 
        
        }
    }
    @GetMapping(value="/{id}/edit")
    public ModelAndView edit(@PathVariable Long id, RequisicaoProfessor requisicaoProfessor){
        
        Optional<Professor> professorOptional = this.professorRepository.findById(id);
        
        if(professorOptional.isPresent()){
            
            Professor professor = professorOptional.get();
            requisicaoProfessor.fromProfessor(professor);
            ModelAndView model = new ModelAndView("professor/edit");
            
            model.addObject("id", professor.getId());
            model.addObject("status", StatusProfessor.values());

            return model;
            
        }else{
            return new ModelAndView("redirect:/professores");     
        }
    }

    @PostMapping(value = "/{id}")
    public ModelAndView update(@PathVariable Long id, @Valid RequisicaoProfessor requisicaoProfessor,
    BindingResult bindingProfessor){

        if(bindingProfessor.hasErrors()){   
            ModelAndView model = new ModelAndView("/professor/edit");
            model.addObject("id", id);
            model.addObject("status", StatusProfessor.values());
            return  model;
        }else{

            Optional<Professor> optionalProfessor = this.professorRepository.findById(id);

            if(optionalProfessor.isPresent()){

                Professor professor = requisicaoProfessor.toProfessor(optionalProfessor.get());
                this.professorRepository.save(professor);

                ModelAndView model = new ModelAndView("redirect:/professores/"+professor.getId());
                model.addObject("professor", professor);

                return model;

            }else{
                return null;
            }

        }


        
     } 

    @GetMapping(value="/{id}/delete")
     public ModelAndView delete(@PathVariable Long id){
        
        try {
              this.professorRepository.deleteById(id); 
              return new ModelAndView("redirect:/professores"); 
        } catch (Exception e) {
            return new ModelAndView("redirect:/professores"); 
        }

    }
}
