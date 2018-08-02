package mereuta.marian.tennis01.service;

import mereuta.marian.tennis01.model.Reservation;
import mereuta.marian.tennis01.model.Utilisateur;

import java.util.List;
import java.util.Map;

public interface UtilisateurInterfaceMetier {

    public List<Utilisateur> listUtilisateurs();
    public void creerUtilisateur(Utilisateur utilisateur);
    public boolean checkIfMailIsPresent(String email);
    public Utilisateur findByEmail(String email);
    public Utilisateur getUtilisateur(Integer id);
    public int getNombreHommes();
    public int getNombreFemmes();
    public boolean checkEmailLogin(String email);
    public boolean comparePassword(String password, Utilisateur utilisateur);
    public Map<String ,Integer> femmesEtHommes();
    public List<Reservation> mesReservationPassees(Utilisateur utilisateur);
    public List<Reservation> mesReservationFutures( Utilisateur utilisateur);
    public List<Utilisateur> rechercheUtilisateurParMotCle(String motCle);

    public void crediterCompte(float montant, Utilisateur utilisateur);

    public void activerOuDescativerCompte(boolean actif, Utilisateur utilisateur);
}
