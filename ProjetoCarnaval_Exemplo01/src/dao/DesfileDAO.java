package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import enumerator.Grupos;
import negocio.Desfile;
import negocio.Entidade;
import negocio.EscolaSamba;
import negocio.Jurado;
import negocio.Quesito;
import negocio.Pessoa.Sexos;

public class DesfileDAO extends AbstractDAO implements DAO {
	
	private PreparedStatement pstmt;
	private ResultSet rs;

	private static Map<Integer,Desfile> desfiles = new HashMap<>();
	
	@Override
	public boolean cadastrar(Entidade entidade) {
		Desfile desfile = new Desfile();
		if(entidade instanceof Desfile)
		{
			desfile = (Desfile) entidade;	
		}
		else
		{
			return false;
		}

		desfile.setId( DesfileDAO.desfiles.size() );
		
		DesfileDAO.desfiles.put( desfile.getId(), desfile );
		if(desfile.equals(DesfileDAO.desfiles.get(desfile.getId())))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public boolean alterar(Entidade entidade) {
		Desfile desfile = new Desfile();
		if(entidade instanceof Desfile)
		{
			desfile = (Desfile) entidade;	
		}
		else
		{
			return false;
		}

		if (DesfileDAO.desfiles.containsKey(desfile.getId())) {
			return DesfileDAO.desfiles.put(desfile.getId(), desfile) != null;
		} else {
			return false;
		}
	}

	@Override
	public boolean excluir(Entidade entidade) {
		Desfile desfile = new Desfile();
		if(entidade instanceof Desfile)
		{
			desfile = (Desfile) entidade;	
		}
		else
		{
			return false;
		}

		return (DesfileDAO.desfiles.remove(desfile.getId())!= null);
	}

	@Override
	public List<Entidade> obterTodos() {
		List<Entidade> desfiles = new ArrayList<>();
		Connection c = getConnection();
		String sql = "select * from carnaval join (carnaval_quesitos, carnaval_quesito_jurado, carnaval_posicao_jurado, carnaval_desfile_escola) on (carnaval.id_carnaval = carnaval_quesitos.carnaval_id_carnaval and carnaval_quesitos.id_carnaval_quesitos = carnaval_quesito_jurado.carnaval_quesitos_id_carnaval_quesitos and carnaval.id_carnaval = carnaval_desfile_escola.carnaval_id_carnaval and carnaval_posicao_jurado.id_carnaval_posicao_jurado = carnaval_quesito_jurado.carnaval_posicao_jurado_id_carnaval_posicao_jurado);";
		try {
			pstmt = c.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				//desfiles.add(this.resultSet2Object(rs));
			}
		} catch (SQLException e) {
		}finally{
			closeConnection(c);
		}
		return desfiles;
	}
	
	/*private Desfile resultSet2Object(ResultSet rs) throws SQLException
	{
		Desfile d = new Desfile();
		d.setId(rs.getInt("id_integrante"));
		d.setNome(rs.getString("nome"));
		d.setEndereco(rs.getString("endereco"));
		d.setCpf(rs.getString("cpf"));
		d.setCep(rs.getString("cep"));
		d.setSexo(Sexos.from(rs.getString("sexo")));
		d.setEmail(rs.getString("email"));
		d.setTelefone(rs.getString("telefone"));
		d.setQuesitoJulgado((Quesito) new QuesitoDAO().obterPorId(rs.getInt("quesito_id_quesito")));
		return t;
	}*/

	@Override
	public Collection<Entidade> obterTodosCollection() {
		// TODO Auto-generated method stub
		return this.obterTodos();
	}

	@Override
	public Entidade obterPorId(Integer numero) {
		return DesfileDAO.desfiles.get(numero);
	}

}
