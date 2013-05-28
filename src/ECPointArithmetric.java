import java.math.BigInteger;


public class ECPointArithmetric {
	
	private EllipticCurve ec;
	private BigInteger x;
	private BigInteger y;
	private BigInteger z;
	
	private static BigInteger zero = BigInteger.ZERO;
	private static BigInteger TWO = new BigInteger("2");
	
	
	public ECPointArithmetric(EllipticCurve ec, BigInteger x, BigInteger y) {
		this(ec, x, y, zero);
	}

	public ECPointArithmetric(EllipticCurve ec, BigInteger x, BigInteger y, BigInteger z) {
		this.ec = ec;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public BigInteger getX() {
		return x;
	}
	
	public BigInteger getY() {
		return y;
	}
	
	public BigInteger getZ() {
		return z;
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
	
		
		/* need to find inverse here */
		BigInteger delta = u.multiply(primeInverse(y2)).mod(ec.getP());
		
		
		BigInteger x3 = delta.pow(2).subtract(x.multiply(TWO)).mod(ec.getP());		
		BigInteger y3 = delta.multiply(x.subtract(x3)).subtract(y).mod(ec.getP());		
		
		return new ECPointArithmetric(ec, x3, y3);
		
	}
	
	
	public ECPointArithmetric projectiveAddition(ECPointArithmetric other) {
		
		BigInteger squareZ2 = other.getZ().pow(2).mod(ec.getP());
		BigInteger d1 = x.multiply(squareZ2).mod(ec.getP());
		BigInteger d3 = d1.subtract(other.getX()).mod(ec.getP());
		BigInteger d4 = y.multiply(squareZ2.multiply(other.getZ())).mod(ec.getP());
		BigInteger d6 = d4.subtract(other.getY()).mod(ec.getP());
		BigInteger d7 = d1.add(other.getX()).mod(ec.getP());
		BigInteger d8 = d4.add(other.getY()).mod(ec.getP());
		
		BigInteger z3 = other.getZ().multiply(d3).mod(ec.getP());
		
		BigInteger squareD3 = d3.pow(2).mod(ec.getP());
		
		BigInteger d = d7.multiply(d3.pow(2)).mod(ec.getP());
		BigInteger x3 = d6.pow(2).subtract(d).mod(ec.getP());
		BigInteger d9 = d.subtract(x3.multiply(TWO)).mod(ec.getP());
		BigInteger y3 = (d9.multiply(d6).subtract(d8.multiply(squareD3.multiply(d3)))).divide(TWO).mod(ec.getP());
		
		return new ECPointArithmetric(ec, x3, y3, z3);
	}
	
	
	public ECPointArithmetric projectiveTwice() {
		
		BigInteger squareZ1 = z.pow(2).mod(ec.getP());
		BigInteger p1 = x.add(squareZ1);
		BigInteger p2 = x.subtract(squareZ1);
		
		BigInteger d1 = p1.multiply(p2).multiply(new BigInteger("3")).mod(ec.getP());
		BigInteger z3 = y.multiply(z).multiply(TWO).mod(ec.getP());
		
		BigInteger squareY1 = y.pow(2).mod(ec.getP());
		BigInteger d2 = x.multiply(squareY1).multiply(new BigInteger("4")).mod(ec.getP());
		
		BigInteger x3 = d1.pow(2).subtract(d2.multiply(TWO)).mod(ec.getP());
		
		BigInteger d3 = squareY1.pow(2).multiply(new BigInteger("8")).mod(ec.getP());
		
		BigInteger y3 = d1.multiply(d2.subtract(x3)).subtract(d3).mod(ec.getP());
		
		return new ECPointArithmetric(ec, x3, y3, z3);
	}
	
	
	
	/**
	 * 
	 * @param
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

	public ECPointArithmetric projectMultiply(BigInteger k) {
		String d = k.toString(2);
		
		ECPointArithmetric Q = new ECPointArithmetric(ec, zero, zero);
		
		for(int i = 0; i < d.length(); i++) {
			Q = Q.projectiveTwice();
			
			if (d.charAt(i) == '1'){
				Q = Q.projectiveAddition(this);
			}
			
		}
		
		return Q;	
	}
}
