package com.fractgen.api.service;

import com.fractgen.api.exception.ResourceNotFoundException;
import com.fractgen.api.model.Fractal;
import com.fractgen.api.dto.NameImageClass;
import com.fractgen.api.repo.FractalRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FractalService {
  @Autowired
  FractalRepo fractalRepo;

  public List<Fractal> getAllFractals () {
    return fractalRepo.findAll();
  }

  public Optional<Fractal> getFractalById (long id){
    return fractalRepo.findById(id);
  }

  public List<NameImageClass> getFractalParts (long id) throws ResourceNotFoundException{
    Fractal fractal = fractalRepo.findById(id).orElseThrow(ResourceNotFoundException::new);
    ArrayList<String> ids = new ArrayList<>();
    String string  = "";
    if (fractal != null) {
      string = fractal.getOptions();
      String[] list = string.split("}");
      for(int i = 1; i < list.length - 1; i++){
        int pos1 = list[i].indexOf("id") + 4;
        int pos2 = list[i].indexOf(',',pos1);
        String nr = list[i].substring(pos1);
        if (pos2 != -1)
          nr = list[i].substring(pos1,pos2);
        ids.add(nr);
      }
    }
    ArrayList<NameImageClass> fractals = new ArrayList<>();
    for (String idPart : ids){
      Fractal fractalPart = fractalRepo.findById(Long.parseLong(idPart)).orElse(null);
      fractals.add(new NameImageClass(fractalPart.getId(),fractalPart.getName(),fractalPart.getDataURL()));
    }
    return fractals;
  }

  public Fractal addFractal (Fractal fractal) {
    Fractal fractalToSave = new Fractal();

    fractalToSave.setType(fractal.getType());
    fractalToSave.setName(fractal.getName());
    fractalToSave.setStatus(fractal.isStatus());
    fractalToSave.setDescription(fractal.getDescription());
    fractalToSave.setLastModified(fractal.getLastModified());
    fractalToSave.setOptions(fractal.getOptions());
    fractalToSave.setDataURL(fractal.getDataURL());
    fractalToSave.setProfile((fractal.getProfile()));

    return fractalRepo.save(fractalToSave);
  }

  public Fractal updateFractal (long id, Fractal fractal) {
    Fractal fractalToUpdate = fractalRepo.findById(id).get();

    if (fractal.getType() != null) {
      fractalToUpdate.setType(fractal.getType());
    }

    if (fractal.getName() != null) {
      fractalToUpdate.setName(fractal.getName());
    }

    if (fractal.getDescription() != null) {
      fractalToUpdate.setDescription(fractal.getDescription());
    }

    fractalToUpdate.setStatus(fractal.isStatus());

    if (fractal.getLastModified() != null) {
      fractalToUpdate.setLastModified(fractal.getLastModified());
    }

    if (fractal.getOptions() != null) {
      fractalToUpdate.setOptions(fractal.getOptions());
    }

    if (fractal.getDataURL() != null) {
      fractalToUpdate.setDataURL(fractal.getDataURL());
    }

    if (fractal.getProfile() != null) {
      fractalToUpdate.setProfile(fractal.getProfile());
    }

    return fractalRepo.save(fractalToUpdate);
  }

  public void deleteFractal (long id) {
    fractalRepo.deleteById(id);
  }

  public boolean fractalExists (long id) {
    return fractalRepo.existsById(id);
  }
}
