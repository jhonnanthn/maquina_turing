package br.com.usjt.turing.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.usjt.turing.TuringMachine;
import br.com.usjt.turing.library.MachinesLibrary;
import br.com.usjt.turing.model.Turing;

@RestController
public class BinaryController {

	private TuringMachine tM1;
	
    @CrossOrigin(origins = "*")
	@RequestMapping(value = "/call", method = RequestMethod.GET, produces = "application/json")
	public Turing getCall(String numbers ) {
		tM1 = MachinesLibrary.EqualBinaryWords();
		Turing turing = tM1.Run(numbers, true);
		return turing;
	}
}