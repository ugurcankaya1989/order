package com.getir.order.service.impl;

import com.getir.order.dto.BookDTO;
import com.getir.order.dto.SoldBookDTO;
import com.getir.order.service.BookClientService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class BookClientServiceImpl implements BookClientService {
    @Override
    public void updateStockInfo(List<SoldBookDTO> soldBookDTOList) throws OptimisticLockingFailureException {
        RestTemplate restTemplate = new RestTemplate();
        String bookUrl = "http://book:8081/api/book/update-book-stock/";
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        ParameterizedTypeReference<List<SoldBookDTO>> responseType = new ParameterizedTypeReference<>() {
        };
        HttpEntity<List<SoldBookDTO>> requestEntity = new HttpEntity<>(soldBookDTOList);
        ResponseEntity responseEntity = restTemplate.exchange(bookUrl, HttpMethod.POST, requestEntity, responseType);
    }

    @Override
    public List<BookDTO> getBookListByIds(List<Long> ids) {
        RestTemplate restTemplate = new RestTemplate();
        String bookUrl = "http://book:8081/api/book/get-books-by-id-list";
        ParameterizedTypeReference<List<BookDTO>> responseType = new ParameterizedTypeReference<>() {
        };
        HttpEntity<List<Long>> requestEntity = new HttpEntity<>(ids);
        ResponseEntity<List<BookDTO>> response = restTemplate.exchange(bookUrl, HttpMethod.POST, requestEntity, responseType);
        return response.getBody();
    }
}
