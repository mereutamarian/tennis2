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

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("terrain")
public class TerrrainController {

    @Autowired
    TerrainRepository terrainRepository;
    @Autowired
    MetierTerrain metierTerrain;

    private List<Terrain> terrains;



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
    public String addHoraire(@Valid @ModelAttribute("terrain") Terrain terrain, BindingResult bindingResult) {

        metierTerrain.setActivTerrain(terrain);

        if (bindingResult.hasErrors()) {
            return "terrains/addTerrain";
        } else {

           metierTerrain.addTerrain(terrain);
            return "redirect:horaires";
        }

    }




}
