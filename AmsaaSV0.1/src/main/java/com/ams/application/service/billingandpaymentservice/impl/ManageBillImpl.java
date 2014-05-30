package com.ams.application.service.billingandpaymentservice.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.ams.application.service.applicationservice.ManageMail;
import com.ams.application.service.billingandpaymentservice.ManageBill;
import com.ams.application.service.billingandpaymentservice.assembler.BillServiceDataAssembler;
import com.ams.application.service.billingandpaymentservice.servicedata.BillDTO;
import com.ams.domain.model.bill.Bill;
import com.ams.domain.model.bill.BillItem;
import com.ams.domain.model.bill.BillSpecification;
import com.ams.domain.model.bill.Payment;
import com.ams.domain.model.measureandunits.Period;
import com.ams.domain.model.person.Person;
import com.ams.domain.model.shared.DomainException;
import com.ams.domain.repository.BillRepository;
import com.ams.domain.repository.PersonRepository;
import com.ams.domain.repository.ServiceRepository;

@Transactional
@org.springframework.stereotype.Service("ManageBillService")
public class ManageBillImpl implements ManageBill
{

	@Autowired
	private PersonRepository			personRepository;
	@Autowired
	private BillRepository			billRepository;
	@Autowired
	private ManageMail				manageMailService;
	@Autowired
	private ServiceRepository		serviceRepository;

	private Bill					bill;

	private BillServiceDataAssembler	bsdAssmblr	= new BillServiceDataAssembler();

	public void deleteBill(long billNumber)
	{
		billRepository.deleteBill(billNumber);
	}

	public void payBill(Payment pymnt)
	{
		Bill bill = this.billRepository.findBill(pymnt.getPaymntForBill()
												.getBillNumber());
		bill.makePayment(pymnt);
		this.billRepository.updateBill(bill);

		String billPaymntSuccessMessage = manageMailService.getMailTemplate("BILL_PAYMENT_SUCCESS", bill.getBillNumber(),
																pymnt.getPaymntAmount());

		String mailToParty = bill.getBilledPerson().getPersnDetail()
							.getEmailId();
		manageMailService.sendMail(mailToParty, BillSpecification.sourceEmailId, billPaymntSuccessMessage);
	}

	public Bill removeBillItem(Bill bill, String srvcCode)
	{

		return null;

	}

	public BillDTO addBillItems(BillDTO billSrvcData)
	{

		try
		{
			bill = new Bill();
			/*
			 * for (Map item : billSrvcData.getBillLineItems()) { if (item !=
			 * null && (String) item.get("srvcCode") != null && (Long)
			 * item.get("quantity") != null) { bill.addBillItem((Long)
			 * item.get("quantity"), serviceRepository.findById((String)
			 * item.get("srvcCode"))); } } bill.calculateTotalAmount();
			 */
			bill.addBillItems(billSrvcData.getBillLineItems());
			System.out.println("Total Amount:" + bill.getBillTotalAmount());

		} catch (DomainException e)
		{
			e.printStackTrace();
		}

		return bsdAssmblr.toBillDTO(bill);
	}

	public BillDTO createNewBill(BillDTO billSrvcData)
	{
		try
		{
			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

			Person billedPerson = personRepository.findById(billSrvcData.getCustomerId());
			bill = new Bill(billedPerson, formatter.parse(billSrvcData.getBillDate()),
							formatter.parse(billSrvcData.getBillDueDate()),
							new Period(formatter.parse(billSrvcData.getBillPeriodFromDate()),
										formatter.parse(billSrvcData.getBillPeriodToDate())));
			// bill.addBillItems(billSrvcData.getBillLineItems());

			for (BillItem item : billSrvcData.getBillLineItems())
			{
				if ((item != null) && ((Long) item.getBillItemQuantity() != null) && ((String)
						item.getBillItemService().getSrvcCode() != null))
				{
					bill.addBillItem((Long)
									item.getBillItemQuantity(), serviceRepository.findById((String)
																		item.getBillItemService().getSrvcCode()));
				}
			}
			bill.calculateTotalAmount();

			System.out.println("Bill total Amount:" + bill.getBillTotalAmount());
			billSrvcData = bsdAssmblr.toBillDTO(billRepository.createBill(bill));

		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return billSrvcData;
	}

	public List<Bill> getUnpaidBills()
	{
		return billRepository.findBillsByPaymentStatus();
	}
}
