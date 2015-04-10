package dao;

import java.util.ArrayList;
import java.util.List;

import negocio.Galera;

public class GaleraDao {

	public static List<Galera> obterGalera(){
		List<Galera> galera = new ArrayList<Galera>();
		
		galera.add(new Galera("Huguinho"));
		galera.add(new Galera("Zezinho"));
		galera.add(new Galera("Luizinho"));
		
		return galera;
	}
}
