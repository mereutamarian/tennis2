package mereuta.marian.tennis01.controller;

import mereuta.marian.tennis01.model.Terrain;
import mereuta.marian.tennis01.repository.TerrainRepository;
import mereuta.marian.tennis01.service.MetierTerrain;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/terrain")
public class TerrrainController {

    @Autowired
    TerrainRepository terrainRepository;
    @Autowired
    MetierTerrain metierTerrain;

    private List<Terrain> terrains;
    private Terrain terrain;



    @GetMapping("/liste")
    public String showTerrains(Model model){


        terrains=metierTerrain.showTerrain();

        model.addAttribute("terrains", terrains);


        return "terrains/listeTerrains";
    }

    @GetMapping("/formTerrain")
    public String formTerrain(Model model) {

        model.addAttribute("terrain", new Terrain());

        return "terrains/addTerrain";
    }

    @GetMapping("/addTerrain")
    public String addTerain(@Valid @ModelAttribute("terrain") Terrain terrain, BindingResult bindingResult) {

        metierTerrain.setActivTerrain(terrain);

        if (bindingResult.hasErrors()) {
            return "terrains/addTerrain";
        } else {

           metierTerrain.addTerrain(terrain);
            return "redirect:/terrain/liste";
        }

    }

    @GetMapping("/edit")
    public String getHoraire(@RequestParam(name = "id") Integer id, Model model) {

         terrain= metierTerrain.getTerrain(id);



        model.addAttribute("terrain", terrain);




        return "terrains/editTerrain";
    }


    @GetMapping("/update")
    public String updateHoraire(@Valid @ModelAttribute("terrain") Terrain terrain, BindingResult bindingResult) {

        System.out.println(terrain);
        if (bindingResult.hasErrors()) {
            return "terrains/editTerrain";
        } else {


            metierTerrain.addTerrain(terrain);
            return "redirect:/terrain/liste";
        }

    }

    @GetMapping("/delete")
    public String deleteHoraire(@RequestParam(name = "id") Integer id) {

        terrain = metierTerrain.getTerrain(id);


       metierTerrain.deleteTerrain(terrain);


        return "redirect:/terrain/liste";
    }

}
