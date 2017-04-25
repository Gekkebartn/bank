package be.vdab;

public class Rekening {
	private long rekeningNummer;
	private double saldo;
	

	public Rekening(long rekeningNummer) {
		this.rekeningNummer = rekeningNummer;
	}
	
	public long getRekeningNummer()	{
		return rekeningNummer;
	}
	
	public double getSaldo()	{
		return saldo;
	}
	
	public boolean geldigRekeningNummer()	{
		
	}
	
	
	@Override
	public String toString()	{
		String s = String.valueOf(rekeningNummer);
		return s;
	}
}
