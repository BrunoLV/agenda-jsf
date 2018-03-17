package br.com.valhala.agenda.db.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.valhala.agenda.modelo.Contato;

public class ContatoDao {

    private static final String JPQL_QUERY_CONSULTA_POR_ID = "SELECT DISTINCT c FROM Contato c WHERE c.id = :idContato";
    private static final String JPQL_QUERY_LISTA_CONTATOS  = "SELECT DISTINCT c FROM Contato c";
    @Inject
    private EntityManager       entityManager;

    public ContatoDao() {
        super();
    }

    public void atualizar(Contato contato) {
        entityManager.merge(contato);

    }

    public void excluir(Contato contato) {
        entityManager.remove(entityManager.find(Contato.class, contato.getId()));

    }

    public List<Contato> listar() {
        TypedQuery<Contato> consulta = entityManager.createQuery(JPQL_QUERY_LISTA_CONTATOS, Contato.class);
        consulta.setHint("javax.persistence.loadgraph", entityManager.createEntityGraph("comTelefones"));
        List<Contato> lista = consulta.getResultList();
        return lista;
    }

    public Contato obtemPorId(Long idContato) {
        TypedQuery<Contato> consulta = entityManager.createQuery(JPQL_QUERY_CONSULTA_POR_ID, Contato.class);
        consulta.setParameter("idContato", idContato);
        consulta.setHint("javax.persistence.loadgraph", entityManager.createEntityGraph("comTelefones"));
        Contato contato = consulta.getSingleResult();
        return contato;
    }

    public Contato salvar(Contato contato) {
        entityManager.persist(contato);
        return contato;
    }

}
