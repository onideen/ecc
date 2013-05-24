import java.math.BigInteger;


public class Test {
	
	/* From the book */
	private String p = "10103";
	private String n = "4";
		
	private String b = "4";
	private String Gx = "1";
	private String Gy = "3";

	
	private String a = "4";

	public EllipticCurve ec = new EllipticCurve(new BigInteger(a), new BigInteger(b, 16), new BigInteger(p));

	private BigInteger order = new BigInteger(n);
	
	private ECPointArithmetric generator = new ECPointArithmetric(ec, new BigInteger(Gx, 16), new BigInteger(Gy, 16));
	
	
	
	public BigInteger getOrder() {
		return order;
	}
	
	public ECPointArithmetric getGenerator() {
		return generator;
	}
	
	
	public static void main(String[] args) {
		
		Test test = new Test();
		
		ECPointArithmetric point1 = test.getGenerator();
		ECPointArithmetric point2 = new ECPointArithmetric(test.ec, new BigInteger("1"), new BigInteger("3"));
		
		
		ECPointArithmetric res = point1.twice();
		
		System.out.println(res);
		
		
		
	}
	
}
