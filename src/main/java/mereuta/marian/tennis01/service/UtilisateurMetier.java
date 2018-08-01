package mereuta.marian.tennis01.service;

import mereuta.marian.tennis01.model.Reservation;
import mereuta.marian.tennis01.model.Role;
import mereuta.marian.tennis01.model.Utilisateur;
import mereuta.marian.tennis01.repository.ReservationRepository;
import mereuta.marian.tennis01.repository.RoleRepository;
import mereuta.marian.tennis01.repository.UtilisateurRepository;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UtilisateurMetier implements UtilisateurInterfaceMetier {

    @Autowired
    UtilisateurRepository utilisateurRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    ReservationRepository reservationRepository;


    private Utilisateur utilisateur;

    @Override
    public List<Utilisateur> listUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    @Override
    public void creerUtilisateur(Utilisateur utilisateur) {

        Role role;
        float credit = 0;
        boolean actif = true;

        role = roleRepository.getOne(3);


        System.out.println(role);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        System.out.println("je suis le role" + role);

        utilisateur.setPassword(encoder.encode(utilisateur.getPassword()));

        utilisateur.setRole(role);
        utilisateur.setCredit(credit);
        utilisateur.setActif(actif);


        utilisateurRepository.save(utilisateur);
    }


    @Override
    public Utilisateur findByEmail(String email) {
        return utilisateurRepository.findByEmail(email);
    }

    @Override
    public Utilisateur getUtilisateur(Integer id) {
        return utilisateurRepository.getOne(id);
    }

    @Override
    public int getNombreHommes() {
        return utilisateurRepository.numbreHommesCount();
    }

    @Override
    public int getNombreFemmes() {
        return utilisateurRepository.numbreFemmesCount();
    }

    @Override
    public boolean checkIfMailIsPresent(String email) {

        if (utilisateur != null)
            return false;

        return true;
    }

    @Override
    public boolean checkEmailLogin(String email) {

        if (findByEmail(email) == null) {
            return false;
        } else {
            return true;
        }


    }

    @Override
    public boolean comparePassword(String password, Utilisateur utilisateur) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        if (encoder.matches(password, utilisateur.getPassword()))
            return true;

        return false;
    }

    @Override
    public Map<String, Integer> femmesEtHommes() {

        Map<String, Integer> liste = new HashMap<>();
        liste.put("femmes", getNombreFemmes());
        liste.put("hommes", getNombreHommes());


        return liste;
    }

    public Map< String,Integer> agesClientsClub() {
        List<BigInteger> listeAges = utilisateurRepository.agesToutesPersonnes();


        int nInts = listeAges.size();
        List<Integer> ages = new ArrayList<>(nInts);
        for (int i=0;i<nInts;++i) {
            ages.add((int) listeAges.get(i).longValue());
        }




        Map<String, Integer> listeAgesTranches = new HashMap<>();

        Integer i=0;
        Integer a=0;
        Integer b=0;
        Integer c=0;
        Integer d=0;
        Integer e=0;
        Integer f=0;


        for(int age : ages){
           if(age>15 && age<21){
               i++;

               listeAgesTranches.put("nombre de personnes entre 16 et 20 ans", i);
           }
           if(age>20 && age<31){
               a++;
               listeAgesTranches.put("nombre de personnes entre 20 et 30 ans", a);

           }

            if(age>30 && age<41){
                b++;
                listeAgesTranches.put("nombre de personnes entre 30 et 40 ans", b);

            }

            if(age>40 && age<51){
                c++;
                listeAgesTranches.put("nombre de personnes entre 40 et 50 ans", c);

            }

            if(age>50 && age<61){
                d++;
                listeAgesTranches.put("nombre de personnes entre 50 et 60 ans", d);

            }

            if(age>60 && age<71){
                e++;
                listeAgesTranches.put("nombre de personnes entre 70 et 80 ans", e);

            }

            if(age>60 && age<71){
                f++;
                listeAgesTranches.put("nombre de personnes entre 80 et 90 ans", f);

            }


        }


        return listeAgesTranches;

    }

    @Override
    public List<Reservation> mesReservationPassees(Utilisateur utilisateur) {
        return reservationRepository.findByUtilisateurAndDateReservationIsBeforeOrderByDateReservation( utilisateur, LocalDate.now());
    }

    @Override
    public List<Reservation> mesReservationFutures(Utilisateur utilisateur) {
        return reservationRepository.findByUtilisateurAndDateReservationIsAfterOrderByDateReservation( utilisateur, LocalDate.now());
    }
}
