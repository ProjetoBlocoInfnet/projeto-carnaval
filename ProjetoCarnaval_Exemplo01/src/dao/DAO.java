package dao;

import java.util.List;

import negocio.Entidade;

public interface DAO
{
	Integer cadastrar(Entidade entidade);
	
	Integer alterar(Entidade entidade);
	
	Integer excluir(Entidade entidade);
	
	List<Entidade> obterTodos();
	
	Entidade obterPorId(Integer numero);

}
