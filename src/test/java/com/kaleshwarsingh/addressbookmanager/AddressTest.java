package com.kaleshwarsingh.addressbookmanager;

import org.junit.Test;
import org.junit.Assert;

public class AddressTest {
	
	@Test
	public void canEditAddress () throws InvalidAddressException {
		Address address = new Address(2205, "4th St NW", "Wasington", "DC", "20059");
		
		address.editAddress(14620, "115th Ave", "Jamaica", "NY", "11436");

		Assert.assertEquals(new Address(14620, "115th Ave", "Jamaica", "NY", "11436"), address);
	}	

	@Test(expected = InvalidAddressException.class)
	public void houseNumberMustBeAnPositive () throws InvalidAddressException {
		Address address = new Address(-1, "4th St NW", "Wasington", "DC", "20059");
	}

}
