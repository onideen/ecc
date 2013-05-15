import java.math.BigInteger;


public class P521 {
	
	/* P-521 */
	private String p = "6864797660130609714981900799081393217269435300143305409394463459185543183397656052122559640661454554977296311391480858037121987999716643812574028291115057151";
	private String n = "6864797660130609714981900799081393217269435300143305409394463459185543183397655394245057746333217197532963996371363321113864768612440380340372808892707005449";
		
	private String SEED = "d09e8800291cb85396cc6717393284aaa0da64ba";
	private String c = "0b48bfa5f420a34949539d2bdfc264eeeeb077688e44fbf0ad8f6d0edb37bd6b533281000518e19f1b9ffbe0fe9ed8a3c2200b8f875e523868c70c1e5bf55bad637";
	private String b = "051953eb9618e1c9a1f929a21a0b68540eea2da725b99b315f3b8b489918ef109e156193951ec7e937b1652c0bd3bb1bf073573df883d2c34f1ef451fd46b503f00";
	private String Gx = "c6858e06b70404e9cd9e3ecb662395b4429c648139053fb521f828af606b4d3dbaa14b5e77efe75928fe1dc127a2ffa8de3348b3c1856a429bf97e7e31c2e5bd66";
	private String Gy = "11839296a789a3bc0045c8a5fb42c7d1bd998f54449579b446817afbd17273e662c97ee72995ef42640c550b9013fad0761353c7086a272c24088be94769fd16650";

	
	private String a = "-3";

	private EllipticCurve ec = new EllipticCurve(new BigInteger(a), new BigInteger(b, 16), new BigInteger(p));

	private BigInteger order = new BigInteger(n);
	
	private ECPointArithmetric generator = new ECPointArithmetric(ec, new BigInteger(Gx, 16), new BigInteger(Gy, 16));
	
	
	
	public BigInteger getOrder() {
		return order;
	}
	
	public ECPointArithmetric getGenerator() {
		return generator;
	}
	
}
