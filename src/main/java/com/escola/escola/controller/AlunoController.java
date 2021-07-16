package com.escola.escola.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.escola.escola.model.Aluno;
import com.escola.escola.model.SerieAluno;
import com.escola.escola.model.StatusAluno;
import com.escola.escola.model.Turno;
import com.escola.escola.respository.AlunoRepository;
import com.escola.escola.dto.RequisicaoAluno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value = "/alunos")
public class AlunoController {
    
    @Autowired
    AlunoRepository alunoRepository;

    @GetMapping(value = "")
    public ModelAndView index(){
        ModelAndView model = new ModelAndView("aluno/index");

            List<Aluno> alunos = this.alunoRepository.findAll();
            if(alunos.isEmpty()){
                return model;
            }
           model.addObject("alunos", alunos);
        
      
        
        return model;
    }

    @GetMapping(value="/new")
    public ModelAndView nnew(RequisicaoAluno requisicaoAluno){
        ModelAndView model = new ModelAndView("aluno/new");
        model.addObject("turnos", Turno.values());
        model.addObject("series", SerieAluno.values());
        model.addObject("statusaluno", StatusAluno.values());
        return model;
    }

    @PostMapping(value="")
    public ModelAndView create(@Valid RequisicaoAluno requisicaoAluno, BindingResult bindingResult){
            
            if(bindingResult.hasErrors()){
                //ModelAndView model = new ModelAndView("redirect:alunos/new");
               // return model;
                return nnew(requisicaoAluno);
            }else{

                Aluno aluno = requisicaoAluno.toAluno();
                aluno = this.alunoRepository.save(aluno);
    
                return new ModelAndView("redirect:/alunos/"+aluno.getId());
            }
    }

    @GetMapping(value = "/{id}")
    public ModelAndView show(@PathVariable Long id ){
        
        Optional<Aluno> alunoOptional = this.alunoRepository.findById(id);

        if(alunoOptional.isPresent()){
            
            Aluno aluno = alunoOptional.get();
            return new ModelAndView("aluno/show", "aluno", aluno);
        
        }else{

            return new ModelAndView("/alunos");

        }
    }

    @GetMapping(value="/{id}/edit")
    public ModelAndView edit(@PathVariable Long id, RequisicaoAluno requisicaoAluno){
        Optional<Aluno> alunoOptional = this.alunoRepository.findById(id);

        if(alunoOptional.isPresent()){
           
            Aluno aluno = alunoOptional.get();
            requisicaoAluno.fromAluno(aluno);
           
            ModelAndView model = new ModelAndView("aluno/edit");
            
            model.addObject("id", aluno.getId());
            model.addObject("turnos", Turno.values());
            model.addObject("series", SerieAluno.values());
            model.addObject("statusaluno", StatusAluno.values());

            return model;
        } else{

            return new ModelAndView("redirect:/alunos");

        }

    }

    @PostMapping(value = "/{id}")
    public ModelAndView update(@PathVariable Long id, @Valid RequisicaoAluno requisicaoAluno,
    BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            ModelAndView model = new ModelAndView("aluno/edit");
            model.addObject("id", id);
            model.addObject("turnos", Turno.values());
            model.addObject("series", SerieAluno.values());
            model.addObject("statusaluno", StatusAluno.values());
            return model;
        }else{

            Optional<Aluno> aOptional = this.alunoRepository.findById(id);
    
            if(aOptional.isPresent()){
                Aluno aluno = requisicaoAluno.toAluno(aOptional.get());
                this.alunoRepository.save(aluno);
                return new ModelAndView("redirect:/alunos/"+aluno.getId());
            }else{

                return null;

            }


        }


        

    }

    @GetMapping(value="/{id}/delete")
    public ModelAndView delete(@PathVariable Long id) {
        
        try{
            
            this.alunoRepository.deleteById(id);
            return new ModelAndView("redirect:/alunos");

        }catch(Exception e){
            e.printStackTrace();
            return new ModelAndView("redirect:/alunos");
        }
        

        
    }
    
}
