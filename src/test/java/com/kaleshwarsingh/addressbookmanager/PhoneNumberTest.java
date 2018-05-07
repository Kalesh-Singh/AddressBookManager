package com.kaleshwarsingh.addressbookmanager;

import org.junit.Test;
import org.junit.Assert;

public class PhoneNumberTest {
	@Test
	public void canEditPhoneNumber () throws InvalidPhoneNumberException {
		PhoneNumber phoneNumber = new PhoneNumber("1234567890");

		phoneNumber.editPhoneNumber("+19293550415");

		Assert.assertEquals(new PhoneNumber("+19293550415"), phoneNumber);
	}

	@Test(expected = InvalidPhoneNumberException.class)
	public void phoneNumberMustBeValid () throws InvalidPhoneNumberException {
		PhoneNumber phoneNumber = new PhoneNumber("123");
	}

}
