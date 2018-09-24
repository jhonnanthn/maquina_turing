package br.com.usjt.turing.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.usjt.turing.TuringMachine;
import br.com.usjt.turing.library.MachinesLibrary;
import br.com.usjt.turing.model.Turing;

@RestController
public class BinaryController {

	private TuringMachine tM1;

	public BinaryController() {	
		tM1 = MachinesLibrary.EqualBinaryWords();
	}
	
	@GetMapping(value="/call")
	public Turing getCall(String numbers ) {
		Turing turing = tM1.Run(numbers, true);
		return turing;
	}
}