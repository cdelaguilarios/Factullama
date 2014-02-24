package com.llamita.factullamita.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.llamita.factullamita.model.Currency;

@Repository
@Transactional
public class CurrencyRepository extends HibernateRepository{

	public List<Currency> listCurrency(){
		Criteria criteria = getSession().createCriteria(Currency.class);
		return criteria.list();
	}
	
}
