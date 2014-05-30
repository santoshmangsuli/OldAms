package com.ams.infrastructure.persistance.jpa;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ams.domain.model.bill.Bill;
import com.ams.domain.model.bill.Payment;
import com.ams.domain.repository.BillRepository;

@Repository("BillRepository")
public class BillRepositoryImpl_JPA implements BillRepository
{

	@Autowired
	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager	entityManager;

	public Bill createBill(Bill bill)
	{
		entityManager.persist(bill);
		return bill;
	}

	public long updateBill(Bill bill)
	{
		entityManager.persist(bill);
		return 0;
	}

	public long deleteBill(long billNumber)
	{
		entityManager.remove(entityManager.find(Bill.class, billNumber));
		return 0;
	}

	public void createBills(List<Bill> bills)
	{}

	public void updateBills(Set<Bill> bills)
	{}

	public void deleteBills(Set<Bill> bills)
	{}

	public Bill findBill(long billNumber)
	{
		return entityManager.find(Bill.class, billNumber);
	}

	public Set<Bill> findBills(long userId)
	{
		return null;
	}

	public List<Bill> findBillsByPaymentStatus()
	{
		Query query = entityManager.createQuery("select b from Bill b where b.billPaymentRegister.billPaymentStatus = 'UNPAID'");
		List billList = query.getResultList();
		// CriteriaQuery<Bill> cq =
		// entityManager.getCriteriaBuilder().createQuery(Bill.class);
		// cq.from(Bill.class)
		return billList;
	}

	public Payment findPayment(long paymntNumber)
	{
		return null;
	}

	public Set<Payment> findPayments(long billNumber)
	{
		return null;
	}

}
