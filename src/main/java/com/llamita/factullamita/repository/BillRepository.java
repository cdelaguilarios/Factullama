package com.llamita.factullamita.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.llamita.factullamita.model.Bill;
import com.llamita.factullamita.model.BillDetail;

@Repository
@Transactional
public class BillRepository extends HibernateRepository{

	public List<Bill> listBill(){
		Criteria criteria = getSession().createCriteria(Bill.class);
		return criteria.list();
	}
	
	public Bill getBillByField(String field, Object value){
		Bill ret = null;
		Criteria criteria = getSession().createCriteria(Bill.class);
		criteria.add(Restrictions.eq(field, value));
		List<Bill> bills = criteria.list();
		if(bills!=null && bills.size()>0){
			ret = bills.get(0);
		}
		return ret;
	}
	
	public void addOrUpdateBill(Bill bill){
		Transaction tx = null;
		try{
			System.out.println("Guardando bill ....");
			tx = (Transaction) getSession().beginTransaction();
			bill.setStatus("1");
			getSession().saveOrUpdate(bill);
			tx.commit();
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Error al guardar : "+e);
			try{
    			tx.rollback();
    		}catch(RuntimeException rbe){
    			System.out.println("Couldn’t roll back transaction "+ rbe);
    		}
		}
	}
	
	public void addOrUpdateBillDetail(BillDetail detail){
		Transaction tx = null;
		try{
			tx = (Transaction) getSession().beginTransaction();
			getSession().saveOrUpdate(detail);
			tx.commit();
		}catch(Exception e){
			try{
    			tx.rollback();
    		}catch(RuntimeException rbe){
    			System.out.println("Couldn’t roll back transaction "+ rbe);
    		}
		}
	}
	
}
