package controle;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import negocio.EscolaSamba;
import negocio.Torcedor;

public class TorcedorDAO {

	public static final Map<String, Torcedor> torcedores = new HashMap<>();
	
	static {
		Torcedor joaozinho = new Torcedor("joaozinho", "senha123");
		EscolaSamba escola = EscolaSambaDAO.obtemPorId("0");
		joaozinho.setNome("Joaozinho das couves");
		joaozinho.setEscolaSamba( escola );
		TorcedorDAO.gravar(joaozinho);
	}

	public static Collection<Torcedor> obtemTodos() {
		return torcedores.values();
	}

	private static String gravar(Torcedor torcedor) {
		torcedor.setId("" + torcedores.size());

		torcedores.put(torcedor.getId(), torcedor);

		return torcedor.getId();

	}
}
