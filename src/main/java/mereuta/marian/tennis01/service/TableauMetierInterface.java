package mereuta.marian.tennis01.service;


import mereuta.marian.tennis01.model.Tableau;

import java.util.List;

public interface TableauMetierInterface {

    public List<Tableau> showTableaux();
    public void addTableau(Tableau tableau);
    public Tableau chargeTableau(Integer id);
    public void deleteTableau(Tableau tableau);
    public Tableau getTableau(Integer id);
}
