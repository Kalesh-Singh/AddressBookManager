package com.kaleshwarsingh.addressbookmanager;

import org.junit.Test;
import org.junit.Assert;

public class PersonTest {

	@Test(expected = InvalidNameException.class)
	public void firstNameCannotBeEmpty () throws InvalidNameException {
		Person person = new Person("", "Singh");
	}

}
