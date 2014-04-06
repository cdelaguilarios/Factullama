package com.llamita.factullamita.logic;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.llamita.factullamita.model.Bill;
import com.llamita.factullamita.model.BillDetail;
import com.llamita.factullamita.repository.BillRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ManageBillLogic {
 
    private Logger log = Logger.getLogger(ManageBillLogic.class);

    @Autowired
    private BillRepository billRepository;

    public List<Bill> listBill() {
//        List<Bill> listBillDecoded = new ArrayList<Bill>();
        List<Bill> listBill = billRepository.listBill();
        log.debug("Tamaño de la lista (antes): " + listBill.size());

//        for(Bill bill : listBill) {
//            byte[] decodeSubTotal = Base64.decodeBase64(bill.getSubtotal().getBytes());
//            byte[] decodeTotal    = Base64.decodeBase64(bill.getTotal().getBytes());
//
//            bill.setSubtotal(new String(decodeSubTotal));
//            bill.setTotal(new String(decodeTotal));
//            /** OJO **/
//            Hibernate.initialize(bill.getDetails());
//
//            listBillDecoded.add(bill);
//        }
//        log.debug("Tamaño de la lista (despues): " + listBill.size());

        return listBill;
    }

    public void addBill(Bill bill){

//        byte[] encodeSubTotal = Base64.encodeBase64(bill.getSubtotal().getBytes());
//        byte[] encodeTotal    = Base64.encodeBase64(bill.getTotal().getBytes());

        bill.setState("1");
//        bill.setSubtotal(new String(encodeSubTotal));
//        bill.setTotal(new String(encodeTotal));

        billRepository.addOrUpdateBill(bill);
    }

    
    public void addBillDetail(BillDetail detail, Bill bill) {
//        byte[] encodeUnitPrice    = Base64.encodeBase64(detail.getUnitPrice().getBytes());
//        byte[] encodeAmount = Base64.encodeBase64(detail.getAmount().getBytes());

//        detail.setUnitPrice(new String(encodeUnitPrice));
//        detail.setAmount(new String(encodeAmount));

        if (bill == null) {
            billRepository.addOrUpdateBillDetail(detail);
            return;
        }
        billRepository.addOrUpdateBillDetail(detail, bill);
    }

    public void updBill(Bill bill) {

//        byte[] encodeSubTotal = Base64.encodeBase64(bill.getSubtotal().getBytes());
//        byte[] encodeTotal    = Base64.encodeBase64(bill.getTotal().getBytes());

        bill.setState("1");
//        bill.setSubtotal(new String(encodeSubTotal));
//        bill.setTotal(new String(encodeTotal));

        billRepository.addOrUpdateBill(bill);
    }

//    public Bill getBillByNumber(String number) {
//        Bill bill = billRepository.getBillByField("number", number);
//
////        byte[] decodeSubTotal = Base64.decodeBase64(bill.getSubtotal().getBytes());
////        byte[] decodeTotal    = Base64.decodeBase64(bill.getTotal().getBytes());
////
////        bill.setSubtotal(new String(decodeSubTotal));
////        bill.setTotal(new String(decodeTotal));
//
//        return bill;
//    }

    public Bill getBill(Integer id){
        Bill bill = billRepository.getBill(id);

//        byte[] decodeSubTotal = Base64.decodeBase64(bill.getSubtotal().getBytes());
//        byte[] decodeTotal    = Base64.decodeBase64(bill.getTotal().getBytes());

//        bill.setSubtotal(new String(decodeSubTotal));
//        bill.setTotal(new String(decodeTotal));
//
        List<BillDetail> billDetails = new ArrayList<BillDetail>();

        for(BillDetail detail : bill.getDetails()){
//            byte[] decodeUnitPrice    = Base64.decodeBase64(detail.getUnitPrice().getBytes());
//            byte[] decodeAmount = Base64.decodeBase64(detail.getAmount().getBytes());

//            detail.setUnitPrice(new String(decodeUnitPrice));
//            detail.setAmount(new String(decodeAmount));
            billDetails.add(detail);
        }

        bill.setDetails(billDetails);
        return bill;
    }

    public void delBill(Integer idBill){
        if(idBill !=null){
            Bill bill = getBill(idBill);
            if(bill!=null){
                bill.setState("0");
                billRepository.addOrUpdateBill(bill);
            }
        }
    }

}
