package tm;

public class Main 
{
	public static void main(String[] args) 
	{
		TuringMachine TM1 = MachinesLibrary.EqualBinaryWords();
	
		Turing turing = TM1.Run("33144111030", false);
		System.out.println(turing);
	}
}
