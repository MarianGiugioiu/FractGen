package com.fractgen.api.service;

import com.fractgen.api.model.Fractal;
import com.fractgen.api.repo.FractalRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

  public Fractal addFractal (Fractal fractal) {
    Fractal fractalToSave = new Fractal();

    fractalToSave.setType(fractal.getType());
    fractalToSave.setName(fractal.getName());
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
