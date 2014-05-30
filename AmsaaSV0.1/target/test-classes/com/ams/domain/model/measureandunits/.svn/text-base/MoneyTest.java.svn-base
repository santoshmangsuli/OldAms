package com.ams.domain.model.measureandunits;

import java.math.BigDecimal;

import com.ams.domain.model.measureandunits.Currency;
import com.ams.domain.model.measureandunits.Money;
import com.ams.domain.model.shared.DomainException;

public class MoneyTest
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		Money money = new Money(new BigDecimal(1000), Currency.EUR);
		System.out.println(money);
		Money m1 = new Money(new BigDecimal(5000), Currency.INR);
		try
		{
			System.out.println(m1.divideBy(money));

		} catch (DomainException e)
		{
			e.printStackTrace();
		}
	}

}
