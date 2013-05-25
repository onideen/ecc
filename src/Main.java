import java.math.BigInteger;
import java.util.Random;


public class Main {

	
	public static void main(String[] args) {
		ECDomainParameters ecc = new P521();
		
		int[] d_percentes = {100, 75, 50};
		
		for (int i = 0; i < d_percentes.length; i++) {
			
			System.out.println();
			int d_bitlength = ecc.getOrder().bitLength() * d_percentes[i]/100;
			
			BigInteger d = new BigInteger(d_bitlength, new Random());
		
			long t1 = System.nanoTime();
			ECPointArithmetric ecpa = ecc.getGenerator().multiply(d);
			long t2 = System.nanoTime();
			
			System.out.println(d_percentes[i] + "% of " + ecc.getOrder().bitLength() + "\ntime: " + (t2 - t1)/1000000 + "ms");
		
		}
		
	}
	
}
