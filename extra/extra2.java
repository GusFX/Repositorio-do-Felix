import java.util.ArrayList;

abstract class Bloco{
	private int x, y, z;
	public Bloco(){
		x = y = z = 1;
	}

	public abstract Bloco quebra();
}

class Terra extends Bloco{
	public String toString(){
		return "Criou Terra";
	}

	public Terra quebra(){
		Terra t = new Terra();
		System.out.println("Quebrou a Terra e ganhou uma Terra");
		return t;
	}
}

class Grama extends Terra{
	public String toString(){
		return "Criou Grama";
	}

	public Terra quebra(){
		Terra t = new Terra();
		System.out.println("Quebrou a Grama e ganhou uma Terra");
		return t;
	}	
}

class Pedra extends Bloco{
	public String toString(){
		return "Criou Pedra";
	}

	public Pedregulho quebra(){
		Pedregulho p = new Pedregulho();
		System.out.println("Quebrou a Pedra e ganhou um Pedregulho");
		return p;
	}
}

class Pedregulho extends Pedra{
	public String toString(){
		return "Criou Pedregulho";
	}

	public Pedregulho quebra(){
		Pedregulho p = new Pedregulho();
		System.out.println("Quebrou o Pedregulho e ganhou um Pedregulho");
		return p;
	}
}

class Madeira extends Bloco{
	public String toString(){
		return "Criou Madeira";
	}

	public Madeira quebra(){
		Madeira m = new Madeira();
		System.out.println("Quebrou a Madeira e ganhou uma Madeira");
		return m;
	}
}

class Main{
	public static void main(String[] args) {
		ArrayList<Bloco> blocos = new ArrayList<Bloco>();
		blocos.add(new Pedra());
		blocos.add(new Pedregulho());
		blocos.add(new Madeira());
		blocos.add(new Grama());
		blocos.add(new Terra());

		for (Bloco b : blocos) {
			System.out.println(b);
		}

		for (Bloco b : blocos) {
			b = b.quebra();
		}
	}
}
