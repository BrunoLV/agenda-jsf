package br.com.valhala.agenda.service;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.PersistenceException;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.deltaspike.jpa.api.transaction.Transactional;

import br.com.valhala.agenda.db.dao.ContatoDao;
import br.com.valhala.agenda.modelo.Contato;
import br.com.valhala.agenda.modelo.Telefone;

@Named("contatoService")
@RequestScoped
public class ContatoService implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private ContatoDao dao;

    public ContatoService() {
        super();
    }

    @Transactional
    public void atualizar(Contato contato) {
        consiste(contato);
        dao.atualizar(contato);
    }

    private void consiste(Contato contato) {
        if (CollectionUtils.isNotEmpty(contato.getTelefones())) {
            for (Telefone telefone : contato.getTelefones()) {
                telefone.setContato(contato);
            }
        } else {
            contato.setTelefones(null);
        }
    }

    @Transactional
    public void deletar(Contato contato) {
        dao.excluir(contato);
    }

    public List<Contato> listar() {
        List<Contato> lista = dao.listar();
        return lista;
    }

    public Contato obtemPorId(Long idContato) {
        Contato contato = dao.obtemPorId(idContato);
        return contato;
    }

    @Transactional
    public Contato salvar(Contato contato) {
        consiste(contato);
        Contato contatoPersistido = null;
        try {
            contatoPersistido = dao.salvar(contato);
        } catch (PersistenceException e) {
            throw e;
        }
        return contatoPersistido;
    }

}
