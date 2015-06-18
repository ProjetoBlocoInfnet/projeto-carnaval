package negocio;

public class Nota implements Entidade
{
	private Integer id_carnaval;
	private Integer id_jurado;
	private Float nota;

	public Integer getId_carnaval() {
		return id_carnaval;
	}
	public void setId_carnaval(Integer id_carnaval) {
		this.id_carnaval = id_carnaval;
	}
	public Integer getId_jurado() {
		return id_jurado;
	}
	public void setId_jurado(Integer id_jurado) {
		this.id_jurado = id_jurado;
	}
	public Float getNota() {
		return nota;
	}
	public void setNota(Float nota) {
		this.nota = nota;
	}
}
