package com.getir.order.adapter;

import com.getir.order.dto.OrderDTO;
import com.getir.order.dto.OrderDetailDTO;
import com.getir.order.dto.SoldBookDTO;

import java.util.ArrayList;
import java.util.List;

public class SoldBookAdapter {

    public static List<SoldBookDTO> mapToSoldBookDTOList(OrderDTO orderDTO){
        List<SoldBookDTO> soldBookDTOList = new ArrayList<>();
        for (OrderDetailDTO od : orderDTO.getOrderDetailDTOList()) {
            soldBookDTOList.add(new SoldBookDTO(od.getBookId(), od.getCount()));
        }
        return soldBookDTOList;
    }
}
