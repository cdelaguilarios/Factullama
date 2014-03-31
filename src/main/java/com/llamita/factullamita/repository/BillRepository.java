package com.llamita.factullamita.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.llamita.factullamita.model.Bill;
import com.llamita.factullamita.model.BillDetail;

@Repository
public class BillRepository extends HibernateRepository{

	public List<Bill> listBill() {
		Criteria criteria = getSession().createCriteria(Bill.class);
		criteria.add(Restrictions.eq("state", "1"));
		return criteria.list();
	}
	
	public Bill getBill(Integer id){
		return (Bill) getSession().get(Bill.class, id);
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
        System.out.println("Guardando bill ....");

        getSession().saveOrUpdate(bill);

	}
	
	public void addOrUpdateBillDetail(BillDetail detail){

        getSession().saveOrUpdate(detail);
	}

    public void addOrUpdateBillDetail(BillDetail detail, Bill bill) {
        detail.setBill(bill);
        getSession().saveOrUpdate(detail);
    }
	
}
