import java.math.BigInteger;


public class EllipticCurve {

	
	private BigInteger a;
	private BigInteger b;
	private BigInteger p;
	
	
	
	public EllipticCurve(BigInteger a, BigInteger b, BigInteger p) {
		this.a = a;
		this.b = b;
		this.p = p;
		
	}
	
	
	public BigInteger getA() {
		return a;
	}
	
	public BigInteger getB() {
		return b;
	}
	
	public BigInteger getP() {
		return p;
	}
	
	@Override
	public String toString() {
		return "y^2 = x^3 + ax + b(mod p) \na: " + a + "\n" +
		"b: " + b.toString(16) + "\n" +
				"p: " + p;
	}
}
