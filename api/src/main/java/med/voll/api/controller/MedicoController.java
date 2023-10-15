package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


// @RestController: indica para o framework que se trata de um controlador Rest, voltado para o desenvolvimento de
// aplicações web Restful e facilita que nós lidemos com requisições web (POST, GET, PUT, etc)
// pois une o Controller a um ResponseBody para todos métodos marcados pelo RequestMapping.
@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    //colocar uma classe record apos @RequestBody
    //Aqui
    @PostMapping
    @Transactional //porque está gravando dados
    public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados){
//recebe DTO e converter para o tipo medico, uma entidade JPA
        repository.save(new Medico(dados));
        //método para fazer o INSERT
    }

   /* @GetMapping
    public List<DadosListagemMedico> listar(){
        return repository.findAll().stream().map(DadosListagemMedico::new).toList();
    }*/

   /* @GetMapping
    public Page<DadosListagemMedico> listar(Pageable paginacao){
        return repository.findAll(paginacao).map(DadosListagemMedico::new);

        //no Insomnia GET  http://localhost:8080/medicos?size=1&page=2
        //size é a quantidade de dados por página

        //PARA ORDENAÇÃO:
        //http://localhost:8080/medicos?sort=nome
        //ordenação decrescente http://localhost:8080/medicos?sort=nome,desc
    }*/
    /*
   //ESTE MÉTODO FOI ALTERADO APÓS A ELABORAÇÃO DA EXCLUSÃO LÓGICA, POIS FOI REFEITO EM SEGUIDA PARA LISTAR APENAS OS CADASTROS ATIVOS, OU SEJA, ATIVO=TRUE.
    //deixar default a paginação:
    @GetMapping
    public Page<DadosListagemMedico> listar(@PageableDefault(size=10, sort = {"nome"}) Pageable paginacao){
        return repository.findAll(paginacao).map(DadosListagemMedico::new);
    }
    */
   @GetMapping
   public Page<DadosListagemMedico> listar(@PageableDefault(size=10, sort = {"nome"}) Pageable paginacao){
       return repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
   }


    @PutMapping
    @Transactional  //coloca @Transactional porque está escrevendo / atualizando BD
    public void atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados) {
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);
    }

   /*

    EXCLUSÃO DEFINITIVA DO CADASTRO NO BANCO DE DADOS

    @DeleteMapping("/{id}")
    @Transactional
    public void apagar(@PathVariable Long id){
        repository.deleteById(id);
    }*/

//EXCLUSÃO LÓGICA - MARCAR COMO INATIVO E NÃO APAGAR DO BANCO DE DADOS
    @DeleteMapping("/{id}")
    @Transactional
    public void apagar(@PathVariable Long id){
        var medico = repository.getReferenceById(id);
        medico.excluir();
    }
}
