package mereuta.marian.tennis01.service;

import mereuta.marian.tennis01.model.Role;
import mereuta.marian.tennis01.model.Utilisateur;
import mereuta.marian.tennis01.repository.RoleRepository;
import mereuta.marian.tennis01.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;



import java.util.List;

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
    public boolean checkIfMailIsPresent(String email) {

        if(utilisateur!=null)
            return false;

        return true;
    }
}
