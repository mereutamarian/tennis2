package mereuta.marian.tennis01.service;

import mereuta.marian.tennis01.model.Utilisateur;

import java.util.List;

public interface UtilisateurInterfaceMetier {

    public List<Utilisateur> listUtilisateurs();
    public void creerUtilisateur(Utilisateur utilisateur);

    public boolean checkIfMailIsPresent(String email);
    public Utilisateur findByEmail(String email);
    public Utilisateur getUtilisateur(Integer id);
    public int getNombreHommes();
    public int getNombreFemmes();

}
