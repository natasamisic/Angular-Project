package classes;

public class Response {
	
	private int idKorisnika;
	private String uloga;
	private String token;
	
	public Response() {
		this.idKorisnika = 0;
		this.uloga = "";
		this.token = "";
	}
	
	public int getIdKorisnika() {
		return idKorisnika;
	}
	
	public void setIdKorisnika(int idKorisnika) {
		this.idKorisnika = idKorisnika;
	}

	public String getUloga() {
		return uloga;
	}

	public void setUloga(String uloga) {
		this.uloga = uloga;
	}

	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
	
	@Override
	public String toString() {
		return "Response [idKorisnika=" + idKorisnika + ", token=" + token + "]";
	}
	
}
