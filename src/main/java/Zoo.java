import java.util.ArrayList;
import java.util.List;

import Exceptions.AnimalDansMauvaisSecteurException;
import Exceptions.LimiteVisiteurException;
import Zoo.*;
public class Zoo {
	

	private final int NB_MAX_VISITEUR_PAR_SECTEUR = 15;
	
	private int visiteurs;
	private List<Secteur> secteursAnimaux;
	
	public Zoo() {
		visiteurs = 0;
		secteursAnimaux = new ArrayList<Secteur>();
	}
	
	public void ajouterSecteur(TypeAnimal type){
		secteursAnimaux.add(new Secteur(type));
	}
	
	public void nouveauVisiteur() throws LimiteVisiteurException {
		if(visiteurs == getLimiteVisiteur() ) {
			throw new LimiteVisiteurException();
		}
		visiteurs += 1;
	}
	
	public int getLimiteVisiteur() {
		return NB_MAX_VISITEUR_PAR_SECTEUR * secteursAnimaux.size();
	}
	
	public void nouvelAnimal(Animal a) throws AnimalDansMauvaisSecteurException {
		getSecteurFromType(a.getTypeAnimal()).ajouterAnimal(a);
	}
	
	public int nombreAnimaux() {
		return secteursAnimaux.stream().mapToInt(x->x.getNombreAnimaux()).sum();
	}
	
	private Secteur getSecteurFromType(TypeAnimal type) {
		return secteursAnimaux.stream().filter(s -> s.obtenirType() == type).findFirst().orElse(null);
	}
}
