package tm;

public class Turing {
	private String regiao;
	private String pais;
	private String status;

	public String getRegiao() {
		return regiao;
	}
	public String getPais() {
		return pais;
	}
	public String getStatus() {
		return status;
	}
	public void setRegiao(String regiao) {
		this.regiao = regiao;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return status + (status.equals("Ligação válida") ? " da(o) " + pais + " para a " + regiao : "");
	}
}
