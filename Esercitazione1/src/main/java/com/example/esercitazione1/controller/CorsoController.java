package com.example.esercitazione1.controller;

import com.example.esercitazione1.dominio.Corso;
import com.example.esercitazione1.repository.CorsoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class CorsoController
{
    private final CorsoRepository corsoRepository;

    public CorsoController(CorsoRepository corsoRepository) {
        this.corsoRepository = corsoRepository;
    }

    @GetMapping("/getCorso")
    public ResponseEntity<Corso> getCorso()
    {
        Corso corso = new Corso(1,"Elettrotecnica","Corso Facoltativo");
        return new ResponseEntity<>(corso, HttpStatus.OK);
    }
    @PostMapping("/postCorso")
    public ResponseEntity<?> createCorso(@RequestBody Corso corso)
    {
        List<Corso> corsi = corsoRepository.findAll();
        HashMap<String,String> hash = new HashMap<>();
        hash.put("Error", "duplicate");
        for(Corso corso2 :corsi)
        {
            if(corso2.getId() == corso.getId())
            {
                return new ResponseEntity<>(hash,HttpStatus.OK);
            }
        }
        corsoRepository.save(corso);
        return new ResponseEntity<>(corso,HttpStatus.CREATED);
    }
    @PostMapping("/postCorso2")public ResponseEntity<?> creaCorso1(@RequestBody Corso corso)
    {
        if (corso.getNome().contains("corso_") && corso.getNome().length() > 8)
        {
            return new ResponseEntity<>(corso, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(corso, HttpStatus.NO_CONTENT);}
   @GetMapping("/getCorso2")
    public ResponseEntity<?> findAll()
   {
    List<Corso> corsoList = corsoRepository.findAll();
    return new ResponseEntity<>(corsoList,HttpStatus.OK);
   }
    @GetMapping("/getCorso3/{id}")public ResponseEntity <?> findOne(long id)
    {    final Corso[] c = new Corso[1];
        corsoRepository.findAll().forEach(course ->
    {
        if (course.getId() == id)
        {
            c[0] = course;
        }
    });
        if (c[0] == null) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity <> (c[0], HttpStatus.OK);
    }

    @GetMapping("/getCorso3/{id}/{campo}")
    public ResponseEntity <?> findOneCampo(@PathVariable long id,@PathVariable String campo)
    {    final Corso[] c = new Corso[1];
        corsoRepository.findAll().forEach(course ->
        {        if (course.getId() == id)
        {
            c[0] = course;
        }
        });
        if (c[0] == null) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity <> ((campo.equalsIgnoreCase("descrizione")) ?
                c[0].getDescrizione() : c[0].getNome(), HttpStatus.OK);}
    @GetMapping("/getNumeroCorsi")
    public ResponseEntity<?>  getNumeroCorsi(){
        return new ResponseEntity<>(corsoRepository.findAll().size(),HttpStatus.OK);
    }
    @GetMapping("/getNumeroCorsi2")
    public ResponseEntity<?> getNumeroCorsi2(){
        int i = 0;
        for (Corso corso : corsoRepository.findAll())
            if (corso.getNome().toLowerCase().contains("corso_")) i++;
        return new ResponseEntity<>(i,HttpStatus.OK);
    }
    @GetMapping("/getLunghezza")
    public ResponseEntity<?> getLunghezza(){
        List<Corso> list = new ArrayList<>();
        for (Corso corso : corsoRepository.findAll())
            if (corso.getDescrizione() != null && corso.getDescrizione().length() <20) list.add(corso);
        return new ResponseEntity<>(list,HttpStatus.OK);
    }
   @PutMapping("/putCorso/{id}")
    public ResponseEntity<?> updatecorso(@PathVariable ("id") int id,@RequestBody Corso corso)
       {
           Corso corso1 = new Corso(3,"aramaico","corso per alberi");
           if (corso1.getId() == id){
               return new ResponseEntity<>(corso1,HttpStatus.CREATED);
       }
           return new ResponseEntity<>(corso,HttpStatus.NO_CONTENT);
     }
    @DeleteMapping("/deleteCorso/{id}")
    public ResponseEntity<?> deletecorso(@PathVariable int id)
    {
        System.out.println("Corso:" + id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
