package dao;

import java.util.Collection;
import java.util.List;

import negocio.Entidade;

public interface DAO
{
	boolean cadastrar(Entidade entidade);
	
	boolean alterar(Entidade entidade);
	
	boolean excluir(Entidade entidade);
	
	List<Entidade> obterTodos();
	
	Collection<Entidade> obterTodosCollection();

	Entidade obterPorId(Integer numero);
}
