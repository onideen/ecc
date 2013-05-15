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
		
		BigInteger delta = diffY.multiply(inverse(diffX)).mod(ec.getP());
		
		BigInteger xres = delta.multiply(delta).subtract(x).subtract(other.getX()).mod(ec.getP());
		BigInteger yres = delta.multiply(x.subtract(xres)).subtract(y).mod(ec.getP());
		
		return new ECPointArithmetric(ec, xres, yres);
	}
	
	
	
	public ECPointArithmetric twice() {
	
		BigInteger THREE = new BigInteger("3");
		BigInteger TWO = new BigInteger("2");
		
		/* 3x^2 */
		BigInteger x2 = x.pow(2).multiply(THREE);
		/* 2ax */
		BigInteger a2x = x.multiply(ec.getA()).multiply(TWO);
		
		BigInteger y2 = y.multiply(TWO);
		
		BigInteger u = x2.add(a2x).add(ec.getB());
		
		/* need to find inverse here */
		BigInteger delta = u.multiply(inverse(y2)).mod(ec.getP());
		
		
		BigInteger x3 = delta.pow(2).subtract(ec.getA()).subtract(x.multiply(TWO)).mod(ec.getP());		
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
	
	public BigInteger inverse(BigInteger a) {
		return a.modPow(ec.getP(), ec.getP());
	}
	
	@Override
	public String toString() {
		String s = "Elliptic curve: " + ec + "\n\n"+
			"x: " + x.toString(16) + "\ny: " + y.toString(16); 
		return s;
	}
}
