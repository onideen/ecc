import java.math.BigInteger;


public class Main {

	
	public static void main(String[] args) {
		P521 p521 = new P521();
		
		
		System.out.println(p521.getGenerator().multiply(p521.getOrder().subtract(new BigInteger("1"))).toString());
	}
	
}
