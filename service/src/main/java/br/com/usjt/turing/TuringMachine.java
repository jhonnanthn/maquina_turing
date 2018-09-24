package br.com.usjt.turing;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import br.com.usjt.turing.model.Turing;

public class TuringMachine {
	
	private Set<String> StateSpace;
	private Set<Transition> TransitionSpace;
	private String StartState;
	private String AcceptState;
	private String RejectState;
//	private String OrigemLigacao;
//	private String PaisLigacao;

	private String Tape;
	private String CurrentState;
	private int CurrentSymbol;

	class Transition {
		String readState;
		char readSymbol;
		String writeState;
		char writeSymbol;
		boolean moveDirection; // true is right, false is left

		boolean isConflicting(String state, char symbol) {
			if (state.equals(readState) && symbol == readSymbol) {
				return true;
			} else {
				return false;
			}
		}
	}

	public TuringMachine() {
		StateSpace = new HashSet<String>();
		TransitionSpace = new HashSet<Transition>();
		StartState = new String("");
		AcceptState = new String("");
		RejectState = new String("");
//		OrigemLigacao = new String("");
//		PaisLigacao = new String("");
		Tape = new String("");
		CurrentState = new String("");
		CurrentSymbol = 0;

	}

	public Turing Run(String input, boolean silentmode) {
		Turing turing = new Turing();
		CurrentState = StartState;
		Tape = input;

		while (!CurrentState.equals(AcceptState) && !CurrentState.equals(RejectState)) {
			boolean foundTransition = false;
			Transition CurrentTransition = null;

			if (silentmode == false) {
				if (CurrentSymbol > 0) {
					System.out.println(Tape.substring(0, CurrentSymbol) + " " + CurrentState + " "
							+ Tape.substring(CurrentSymbol));
				} else {
					System.out.println(" " + CurrentState + " " + Tape.substring(CurrentSymbol));
				}
			}

			Iterator<Transition> TransitionsIterator = TransitionSpace.iterator();
			while (TransitionsIterator.hasNext() && foundTransition == false) {
				Transition nextTransition = TransitionsIterator.next();
				if (nextTransition.readState.equals(CurrentState)
						&& nextTransition.readSymbol == Tape.charAt(CurrentSymbol)) {
					foundTransition = true;
					CurrentTransition = nextTransition;
				}
			}

			if (foundTransition == false || !(input.length() == 10 || input.length() == 15)) {
				turing.setStatus("Ligação Inválida.");
				System.err.println("There is no valid transition for this phase! (state=" + CurrentState + ", symbol="
						+ Tape.charAt(CurrentSymbol) + ")");
				return turing;
			} else {
				if (CurrentTransition.readState.equals("q7") && CurrentTransition.readSymbol == '3') {
					turing.setPais("Brasil");
				} else if (CurrentTransition.readState.equals("q1") && CurrentTransition.readSymbol != '0') {
					turing.setPais("França");
				}
				if (CurrentTransition.readState.equals("q1") || CurrentTransition.readState.equals("q10")) {
					if (CurrentTransition.readSymbol == '1')
						turing.setRegiao("Região Parisiense (Île-de-France)");
					if (CurrentTransition.readSymbol == '2')
						turing.setRegiao("Noroeste da França e dependências no Oceano Índico");
					if (CurrentTransition.readSymbol == '3')
						turing.setRegiao("Nordeste da França");
					if (CurrentTransition.readSymbol == '4')
						turing.setRegiao("Sudeste da França");
					if (CurrentTransition.readSymbol == '5')
						turing.setRegiao("Sudoeste da França e dependências no Atlântico");
					if (CurrentTransition.readSymbol == '6')
						turing.setRegiao("Telefonia Móvel");
					if (CurrentTransition.readSymbol == '7')
						turing.setRegiao("Telefones Celulares e aparelhos M2M");
				}
				CurrentState = CurrentTransition.writeState;
				char[] tempTape = Tape.toCharArray();
				tempTape[CurrentSymbol] = CurrentTransition.writeSymbol;
				Tape = new String(tempTape);
				if (CurrentTransition.moveDirection == true) {
					CurrentSymbol++;
				} else {
					CurrentSymbol--;
				}

				if (CurrentSymbol < 0)
					CurrentSymbol = 0;

				while (Tape.length() <= CurrentSymbol) {
					Tape = Tape.concat("_");
				}

			}

		}

		if (CurrentState.equals(AcceptState)) {
			turing.setStatus("Ligação válida");
			return turing;
		} else {
			return turing;
		}

	}

	public boolean addState(String newState) {
		if (StateSpace.contains(newState)) {
			return false;
		} else {
			StateSpace.add(newState);
			return true;
		}
	}

	public boolean setStartState(String newStartState) {
		if (StateSpace.contains(newStartState)) {
			StartState = newStartState;
			return true;
		} else {
			return false;
		}
	}

	public boolean setAcceptState(String newAcceptState) {
		if (StateSpace.contains(newAcceptState) && !RejectState.equals(newAcceptState)) {
			AcceptState = newAcceptState;
			return true;
		} else {
			return false;
		}

	}

	public boolean setRejectState(String newRejectState) {
		if (StateSpace.contains(newRejectState) && !AcceptState.equals(newRejectState)) {
			RejectState = newRejectState;
			return true;
		} else {
			return false;
		}
	}

	public boolean addTransition(String rState, char rSymbol, String wState, char wSymbol, boolean mDirection) {
		if (!StateSpace.contains(rState) || !StateSpace.contains(wState)) {
			return false;
		}

		boolean conflict = false;
		Iterator<Transition> TransitionsIterator = TransitionSpace.iterator();
		while (TransitionsIterator.hasNext() && conflict == false) {
			Transition nextTransition = TransitionsIterator.next();
			if (nextTransition.isConflicting(rState, rSymbol)) {
				conflict = true;
			}

		}
		if (conflict == true) {
			return false;
		} else {
			Transition newTransition = new Transition();
			newTransition.readState = rState;
			newTransition.readSymbol = rSymbol;
			newTransition.writeState = wState;
			newTransition.writeSymbol = wSymbol;
			newTransition.moveDirection = mDirection;
			TransitionSpace.add(newTransition);
			return true;
		}
	}
}