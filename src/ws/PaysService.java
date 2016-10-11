package ws;

import java.util.ArrayList;
import java.util.List;

import persistance.DialogueBd;
import meserreurs.MonException;
import metier.Pays;

import javax.jws.WebMethod;
import javax.jws.WebParam;
//import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;

@WebService(targetNamespace = "http://ws/", endpointInterface = "ws.PaysService", portName = "PaysServiceImpPort", serviceName = "PaysServiceImpService")
public class PaysService {
	public Pays getUnPays(String nomp) throws MonException {

		Pays pr;

		try {
			pr = getUnPaysPrive(nomp);

		} catch (MonException e) {
			// System.out.println(e.getMessage());
			throw e;
		}
		return pr;
	}

	public List<Pays> getListeTousLesPays() throws MonException {

		List<Pays> mesPays;

		try {
			mesPays = getListeTousLesPaysPrive();

		} catch (MonException e) {
			// System.out.println(e.getMessage());
			throw e;
		}
		return mesPays;
	}

	///
	/// Traitements privés
	///

	private List<Pays> getListeTousLesPaysPrive() throws MonException {

		List<Object> rs;
		List<Pays> mesPays = new ArrayList<Pays>();

		int index = 0;

		try {
			DialogueBd unDialogueBd = DialogueBd.getInstance();
			String mysql = "";
			mysql = "SELECT * FROM pays ORDER BY nom_pays ASC";
			rs = unDialogueBd.lecture(mysql);
			while (index < rs.size()) {
				
				// On crée un pays
				Pays unP = new Pays();
				
				// il faut redecouper la liste pour retrouver les lignes
				unP = new Pays(rs.get(index + 0).toString().toString(), rs.get(index + 1).toString(),
						Float.parseFloat(rs.get(index + 2).toString()));
				
				// On incrémente tous les 3 champs
				index = index + 3;
				mesPays.add(unP);
			}
			return mesPays;

		} catch (MonException e) {
			// System.out.println(e.getMessage());
			throw e;
		}
	}

	private Pays getUnPaysPrive(String nomp) throws MonException {
		List<Object> rs;
		String mysql;
		try {
			DialogueBd unDialogueBd = DialogueBd.getInstance();
			mysql = "SELECT * FROM pays where nom_pays = " + "'" + nomp + "'";
			rs = unDialogueBd.lecture(mysql);
			Pays unP = new Pays();
			if (rs.size() > 0) {

				// On récupère les infos du pays
				unP = new Pays(rs.get(0).toString().toString(), rs.get(1).toString(),
						Float.parseFloat(rs.get(2).toString()));
			}
			return unP;
		} catch (MonException e) {
			// System.out.println(e.getMessage());
			throw e;
		}
	}

}
