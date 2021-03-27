package com.fractgen.api.controller;

import com.fractgen.api.exception.ResourceNotFoundException;
import com.fractgen.api.model.Fractal;
import com.fractgen.api.service.FractalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/fractal")
public class FractalController {
  @Autowired
  FractalService fractalService;

  @GetMapping(value = {"", "/"})
  public ResponseEntity<List<Fractal>> getAllFractals () {
    List<Fractal> fractalList = fractalService.getAllFractals();
    return new ResponseEntity<>(fractalList, HttpStatus.OK);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<Fractal> getFractalById (@PathVariable("id") long id){
    Fractal fractal = fractalService.getFractalById(id)
      .orElseThrow(() -> new ResponseStatusException(
        HttpStatus.NOT_FOUND, "No Fractal found with this ID", new ResourceNotFoundException()
      ));
    return new ResponseEntity<>(fractal, HttpStatus.OK);
  }

  @PostMapping(value = {"", "/"})
  public ResponseEntity<Fractal> addFractal (@RequestBody Fractal fractal) {
    Fractal savedFractal = fractalService.addFractal(fractal);
    return new ResponseEntity<>(savedFractal, HttpStatus.CREATED);
  }

  @PutMapping(value = ("/{id}"))
  public ResponseEntity<Fractal> updateFractal (@PathVariable("id") long id,
                                                @RequestBody Fractal fractal) {
    if (fractalService.fractalExists(id)) {
      Fractal updatedFractal = fractalService.updateFractal(id, fractal);
      return new ResponseEntity<>(updatedFractal, HttpStatus.ACCEPTED);
    } else {
      throw new ResponseStatusException(
        HttpStatus.NOT_FOUND, "Cannot update non-existing Fractal", new ResourceNotFoundException()
      );
    }
  }

  @DeleteMapping(value = ("/{id}"))
  public ResponseEntity<HttpStatus> deleteFractal (@PathVariable("id") long id) {
    if (fractalService.fractalExists(id)) {
      fractalService.deleteFractal(id);
      return new ResponseEntity<>(HttpStatus.ACCEPTED);
    } else {
      throw new ResponseStatusException(
        HttpStatus.NOT_FOUND, "Cannot delete non-existing Fractal", new ResourceNotFoundException()
      );
    }
  }

}
