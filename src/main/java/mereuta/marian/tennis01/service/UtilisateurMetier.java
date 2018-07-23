package mereuta.marian.tennis01.service;

import mereuta.marian.tennis01.model.Role;
import mereuta.marian.tennis01.model.Utilisateur;
import mereuta.marian.tennis01.repository.RoleRepository;
import mereuta.marian.tennis01.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


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


    private Utilisateur utilisateur;

    @Override
    public List<Utilisateur> listUtilisateurs() {
        return null;
    }

    @Override
    public void creerUtilisateur(Utilisateur utilisateur) {

        Role role;
        float credit=0;
        boolean actif=true;

        role=roleRepository.getOne(3);



        System.out.println(role);

        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();

        System.out.println("je suis le role"+role);

       utilisateur.setPassword(encoder.encode(utilisateur.getPassword()));
        utilisateur.setRole(role);
        utilisateur.setCredit(credit);
        utilisateur.setActif(actif);


        utilisateurRepository.save(utilisateur);
    }


    @Override
    public Utilisateur findByEmail(String email){
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

        if(utilisateur!=null)
            return false;

        return true;
    }


    public boolean checkEmailLogin(String email) {

        if(findByEmail(email)==null){
            return true;
        }else {
            return false;
        }
            
        
        
    }

    public boolean comparePassword(String password, Utilisateur utilisateur) {
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        
        if (encoder.matches(password, utilisateur.getPassword()))
            return true;
        
        return false;
    }

    public List<List<Map<Object, Object>>> getListFemmesHommes(){

         Map<Object,Object> map = null;
         List<List<Map<Object,Object>>> listFinale = new ArrayList<>();
         List<Map<Object,Object>> donnesFemmesHommes = new ArrayList<>();

         map=new HashMap<>();map.put("label","Femmes");
         map.put("y", utilisateurRepository.numbreFemmesCount());
         donnesFemmesHommes.add(map);

        map=new HashMap<>();map.put("label","Hommes");
        map.put("y", utilisateurRepository.numbreHommesCount());
        donnesFemmesHommes.add(map);


         return listFinale;
    }


}
