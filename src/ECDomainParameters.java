import java.math.BigInteger;


public interface ECDomainParameters {
	public ECPointArithmetric getGenerator();
	public EllipticCurve getECCurve();
	public BigInteger getOrder();
}
