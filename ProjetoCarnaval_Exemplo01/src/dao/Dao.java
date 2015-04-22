package dao;

import java.util.List;

import negocio.Entidade;

public interface DAO
{
	boolean cadastrar(Entidade entidade);
	
	boolean alterar(Entidade entidade);
	
	boolean excluir(Entidade entidade);
	
	List<Entidade> obterTodos();
	
	Entidade obterPorId(Integer numero);

}
