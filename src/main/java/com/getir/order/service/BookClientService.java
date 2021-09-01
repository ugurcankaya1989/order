package com.getir.order.service;

import com.getir.order.dto.BookDTO;
import com.getir.order.dto.SoldBookDTO;

import java.util.List;

public interface BookClientService {
    void updateStockInfo(List<SoldBookDTO> soldBookDTOList);
    List<BookDTO> getBookListByIds(List<Long> ids);
}
