import java.math.BigInteger;


public class ECPointArithmetric {
	
	private EllipticCurve ec;
	private BigInteger x;
	private BigInteger y;
	
	private BigInteger zero = BigInteger.ZERO;
	
	
	public ECPointArithmetric(EllipticCurve ec, BigInteger x, BigInteger y) {
		this.ec = ec;
		this.x = x;
		this.y = y;
	}

	
	public BigInteger getX() {
		return x;
	}
	
	public BigInteger getY() {
		return y;
	}
	
	
	
	public ECPointArithmetric add(ECPointArithmetric other) {
		
		BigInteger diffY = other.getY().subtract(y);
		BigInteger diffX = other.getX().subtract(x);
		
		
		BigInteger delta = diffY.multiply(primeInverse(diffX)).mod(ec.getP());
		
		
		BigInteger xres = delta.multiply(delta).subtract(x).subtract(other.getX()).mod(ec.getP());
		BigInteger yres = delta.multiply(x.subtract(xres)).subtract(y).mod(ec.getP());
		
		return new ECPointArithmetric(ec, xres, yres);
	}
	
	
	
	public ECPointArithmetric twice() {
	
		BigInteger THREE = new BigInteger("3");
		BigInteger TWO = new BigInteger("2");
		
		/* 3x^2 */
		BigInteger x2 = x.pow(2).multiply(THREE);
	
		BigInteger y2 = y.multiply(TWO);
	
		BigInteger u = x2.add(ec.getA());
	
		
		System.out.println("inverse: " + primeInverse(y2));
		
		
		/* need to find inverse here */
		BigInteger delta = u.multiply(primeInverse(y2)).mod(ec.getP());
		
		
		BigInteger x3 = delta.pow(2).subtract(x.multiply(TWO)).mod(ec.getP());		
		BigInteger y3 = delta.multiply(x.subtract(x3)).subtract(y).mod(ec.getP());		
		
		return new ECPointArithmetric(ec, x3, y3);
		
	}
	
	/**
	 * 
	 * @param d binary string
	 * @return
	 */
	public ECPointArithmetric multiply(BigInteger k) {
	
		
		String d = k.toString(2);
		
		ECPointArithmetric Q = new ECPointArithmetric(ec, zero, zero);
		
		for(int i = 0; i < d.length(); i++) {
			Q = Q.twice();
			
			if (d.charAt(i) == '1'){
				Q = Q.add(this);
			}
			
		}
		
		return Q;
		
	}
	
	public BigInteger primeInverse(BigInteger a) {
		
		return modExp(a, ec.getP().subtract(new BigInteger("2")), ec.getP());
		
	}
	
	@Override
	public String toString() {
/*		String s = "Elliptic curve: " + ec + "\n\n"+
		"x: " + x.toString(16) + "\ny: " + y.toString(16); 
	*/
		String s = "Elliptic curve: " + ec + "\n\n"+
		"x: " + x.toString(10) + "\ny: " + y.toString(10); 
		return s;
	}
	
	public BigInteger modExp(BigInteger x, BigInteger pow, BigInteger mod) {	
		BigInteger s = BigInteger.ONE;
		char[] binary = pow.toString(2).toCharArray();
		
		for (int i = 0; i < binary.length; i++) {
			s = s.multiply(s).mod(mod);
			if (binary[i] == '1'){
				s = s.multiply(x).mod(mod);
			}
		}
		
		return s;
	}
}
