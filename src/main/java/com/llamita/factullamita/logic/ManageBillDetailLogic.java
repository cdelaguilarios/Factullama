package com.llamita.factullamita.logic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.llamita.factullamita.model.Bill;
import com.llamita.factullamita.model.BillDetail;
import com.llamita.factullamita.repository.BillDetailRepository;
import com.llamita.factullamita.repository.BillRepository;

@Service
public class ManageBillDetailLogic {
	@Autowired
	private BillDetailRepository billDetailRepository;
	
	public BillDetail getBillDetail(Integer id){
		return billDetailRepository.getBillDetail(id);
	}
}
