package mereuta.marian.tennis01.service;

public interface MailMetierInterface {

    public boolean sendSimpleMail(String expediteur, String sujet,String message);
    public void modifierAdresseMail(String email);
}
