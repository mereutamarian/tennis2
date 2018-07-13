package mereuta.marian.tennis01.service;

import mereuta.marian.tennis01.model.Utilisateur;

import java.util.List;

public interface UtilisateurInterfaceMetier {

    public List<Utilisateur> listUtilisateurs();
    public void creerUtilisateur(Utilisateur utilisateur);
}
