import java.math.BigInteger;


public class P256 implements ECDomainParameters {
	
	/* P-256 */
	private String p = "115792089210356248762697446949407573530086143415290314195533631308867097853951";
	private String n = "115792089210356248762697446949407573529996955224135760342422259061068512044369";
		
//	private String SEED = "c49d360886e704936a6678e1139d26b7819f7e90";
//	private String c = "7efba1662985be9403cb055c75d4f7e0ce8d84a9c5114abcaf3177680104fa0d";
	private String b = "5ac635d8aa3a93e7b3ebbd55769886bc651d06b0cc53b0f63bce3c3e27d2604b";
	private String Gx = "6b17d1f2e12c4247f8bce6e563a440f277037d812deb33a0f4a13945d898c296";
	private String Gy = "4fe342e2fe1a7f9b8ee7eb4a7c0f9e162bce33576b315ececbb6406837bf51f5";
	
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

	@Override
	public EllipticCurve getECCurve() {
		return ec;
	}
	
}
