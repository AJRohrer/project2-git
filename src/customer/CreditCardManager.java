package customer;

import dao.CreditCardDAO;

public class CreditCardManager {

	public CreditCardManager() {}
	
	public boolean Save(CreditCard cc) {
		CreditCardDAO ccdao = new CreditCardDAO();
		return ccdao.enterCreditCard(cc);
	}
	
	public boolean Delete(CreditCard cc) {
		CreditCardDAO ccdao = new CreditCardDAO();
		return ccdao.removeCreditCard(cc.getCreditCardNumber());
	}
}
