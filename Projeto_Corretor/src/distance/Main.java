package distance;

public class Main {

	public static void main(String[] args) {
		String a = "";
		System.out.println(Levenshtein.distance("cao"));
		String[]  vetor = Levenshtein.distance("cao").split(" ");
	}
}
