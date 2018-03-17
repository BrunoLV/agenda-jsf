package br.com.valhala.agenda.web.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.valhala.agenda.modelo.Contato;
import br.com.valhala.agenda.modelo.Telefone;
import br.com.valhala.agenda.modelo.enums.EnumTipoTelefone;
import br.com.valhala.agenda.service.ContatoService;

@Named("contatoMB")
@ViewScoped
public class ContatoMB implements Serializable {

    private static final String OUTCOME_CONTATOS = "/paginas/contatos.xhtml";

    private static final long serialVersionUID = 1L;

    @Inject
    @Named("contatoService")
    private ContatoService service;

    private Long idContato;

    private Contato contato;

    private Telefone telefone;

    private List<Contato> contatos = new ArrayList<>();

    public void adicionaTelefone() {
        contato.getTelefones().add(telefone);
        telefone = new Telefone();
    }

    public void carregaContato() {
        contato = service.obtemPorId(idContato);
    }

    public void deletar(Contato contato) {
        contatos.remove(contato);
        service.deletar(contato);
    }

    public void editaTelefone(Telefone telefone) {
        this.telefone = telefone;
        contato.getTelefones().remove(telefone);
    }

    public Contato getContato() {
        return contato;
    }

    public List<Contato> getContatos() {
        return contatos;
    }

    public Long getIdContato() {
        return idContato;
    }

    public ContatoService getService() {
        return service;
    }

    public Telefone getTelefone() {
        return telefone;
    }

    public List<EnumTipoTelefone> getTipos() {
        return Arrays.asList(EnumTipoTelefone.values());
    }

    @PostConstruct
    public void inicializa() {
        contatos = service.listar();
        telefone = new Telefone();
        contato = new Contato();
    }

    public void removeTelefone(Telefone telefone) {
        contato.getTelefones().remove(telefone);
    }

    public String salvar() {
        if (contato.getId() == null) {
            service.salvar(contato);
        } else {
            service.atualizar(contato);
        }
        return OUTCOME_CONTATOS;
    }

    public void salvar(ActionEvent event) {
        service.salvar(contato);
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    public void setContatos(List<Contato> contatos) {
        this.contatos = contatos;
    }

    public void setIdContato(Long idContato) {
        this.idContato = idContato;
    }

    public void setService(ContatoService service) {
        this.service = service;
    }

    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }

}
