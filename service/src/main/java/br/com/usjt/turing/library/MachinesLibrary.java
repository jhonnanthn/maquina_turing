package br.com.usjt.turing.library;

import br.com.usjt.turing.TuringMachine;

public final class MachinesLibrary {
	
	private MachinesLibrary() {
	}

	public static TuringMachine EqualBinaryWords() {
		
		TuringMachine newTM = new TuringMachine();
		newTM.addState("q0");
		newTM.addState("q1");
		newTM.addState("q2");
		newTM.addState("q3");
		newTM.addState("q4");
		newTM.addState("q5");
		newTM.addState("q6");
		newTM.addState("q7");
		newTM.addState("q8");
		newTM.addState("qa");
		newTM.addState("qr");
		newTM.setStartState("q0");
		newTM.setAcceptState("qa");
		newTM.setRejectState("qr");

		newTM.addTransition("q0", '0', "q1", 'x', true);
		newTM.addTransition("q0", '1', "q5", 'x', true);
		newTM.addTransition("q0", '2', "q6", 'x', true);

		newTM.addTransition("q1", '1', "q4", 'x', true);
		newTM.addTransition("q1", '2', "q4", 'x', true);
		newTM.addTransition("q1", '3', "q4", 'x', true);
		newTM.addTransition("q1", '4', "q4", 'x', true);
		newTM.addTransition("q1", '5', "q4", 'x', true);
		newTM.addTransition("q1", '6', "q4", 'x', true);
		newTM.addTransition("q1", '7', "q4", 'x', true);

		newTM.addTransition("q3", '3', "q1", 'x', true);

		newTM.addTransition("q4", '0', "q4", 'x', true);
		newTM.addTransition("q4", '1', "q4", 'x', true);
		newTM.addTransition("q4", '2', "q4", 'x', true);
		newTM.addTransition("q4", '3', "q4", 'x', true);
		newTM.addTransition("q4", '4', "q4", 'x', true);
		newTM.addTransition("q4", '5', "q4", 'x', true);
		newTM.addTransition("q4", '6', "q4", 'x', true);
		newTM.addTransition("q4", '7', "q4", 'x', true);
		
		newTM.addTransition("q4", 'x', "q2", 'x', true);
		newTM.addTransition("q4", '_', "q2", 'x', true);

		newTM.addTransition("q2", 'x', "q2", 'x', true);
		newTM.addTransition("q2", '_', "qa", '_', true);
		
		newTM.addTransition("q5", '5', "q7", 'x', true);
		
		newTM.addTransition("q6", '1', "q7", 'x', true);

		newTM.addTransition("q7", '3', "q8", 'x', true);
		newTM.addTransition("q8", '3', "q1", 'x', true);

		return newTM;
	}
}