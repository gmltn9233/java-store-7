package store.controller;

import store.domain.dto.OrderRequest;
import store.domain.dto.OrderResponse;
import store.domain.dto.YesNoRequest;
import store.exception.StoreException;
import store.service.StoreService;
import store.view.InputView;
import store.view.OutputView;

public class StoreController {
    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    public void sale() {
        while(true){
            OutputView.printProducts(storeService.getProducts());
            purchaseProducts();
            if(!InputView.repurchaseCheck().getResponse()){
                break;
            }
        }

    }

    private void purchaseProducts() {
        while(true){
            try{
                OrderRequest orderRequest = requestOrder();
                OrderResponse orderResponse = storeService.order(orderRequest);
                if(membershipCheck()){
                    orderResponse.setMembership();
                }
                OutputView.print(orderResponse.toString());
                break;
            }catch (StoreException e){
                System.out.println(e.getMessage());
            }
        }

    }

    private boolean membershipCheck(){
        while (true){
            try {
                YesNoRequest yesNoRequest = InputView.membershipCheck();
                return yesNoRequest.getResponse();
            }catch (StoreException e){
                System.out.println(e.getMessage());
            }
        }
    }

    private OrderRequest requestOrder() {
        while (true) {
            try {
                OrderRequest orderRequest = InputView.orderRequest();
                return orderRequest;
            } catch (StoreException e) {
                System.out.println(e.getMessage());
            }
        }
    }



}
