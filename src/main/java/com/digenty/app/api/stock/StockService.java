package com.digenty.app.api.stock;

import com.digenty.app.api.students.Student;
import com.digenty.app.api.users.User;
import com.digenty.app.api.users.UserRepository;
import com.digenty.app.exceptions.ResourceNotFoundException;
import com.digenty.app.utils.PageUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class StockService {
    private final StockRepository stockRepository;
    private final UserRepository userRepository;

    public Page<Stock> getAllStocks(int page, int size) {
        return stockRepository.findAll(PageUtil.getPageable(page, size));
    }

    public Object getStockById(Long id) {
        return stockRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Stock not found"));
    }

    public Object getStockByName(String name) {
        return stockRepository.findByName(name).orElseThrow(() -> new ResourceNotFoundException("Stock not found"));
    }

    public Object getStockByCategory(String category) {
        return stockRepository.findByCategory(category).orElseThrow(() -> new ResourceNotFoundException("Stock not found"));
    }

    public Object getStockByStatus(String status) {
        return stockRepository.findByStatus(status).orElseThrow(() -> new ResourceNotFoundException("Stock not found"));
    }

    public Stock createStock(CreateStockDto createStockDto) {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(()->new ResourceNotFoundException("No such user exists with email: " + userEmail));;

        Stock stock = new Stock();
        stock.setName(createStockDto.getName());
        stock.setDescription(createStockDto.getDescription());
        stock.setCategory(createStockDto.getCategory());
        stock.setImagePath(createStockDto.getImagePath());
        stock.setUnit(createStockDto.getUnit());
        stock.setQuantity(createStockDto.getQuantity());
        stock.setPrice(createStockDto.getPrice());
        stock.setCostPrice(createStockDto.getCostPrice());
        stock.setUser(user);

        stockRepository.save(stock);
        return stock;
    }

    public Stock editStock(EditStockDto editStockDto, Long stockId) {
        Stock stock = stockRepository.findById(stockId)
                .orElseThrow(()->new ResourceNotFoundException("Stock not found"));

        stock.setName(editStockDto.getName());
        stock.setDescription(editStockDto.getDescription());
        stock.setCategory(editStockDto.getCategory());
        stock.setImagePath(editStockDto.getImagePath());
        stock.setUnit(editStockDto.getUnit());
        stock.setQuantity(editStockDto.getQuantity());
        stock.setPrice(editStockDto.getPrice());
        stock.setCostPrice(editStockDto.getCostPrice());

        stockRepository.save(stock);
        return stock;
    }

    public void deleteStock(Long stockId) {
        stockRepository.deleteById(stockId);
    }

    public Map<String, Object> adjustQuantity(AdjustQuantityDto adjustQuantityDto, Long stockId) {
        Stock stock = stockRepository.findById(stockId)
                .orElseThrow(()->new ResourceNotFoundException("Stock not found"));
        stock.setQuantity(adjustQuantityDto.getQuantity());
        stock.setReason(adjustQuantityDto.getReason());
        stock.setSummary(adjustQuantityDto.getSummary());

        stockRepository.save(stock);

        Map<String, Object> adjustQuantityResponse = new LinkedHashMap<String, Object>();
        adjustQuantityResponse.put("quantity", adjustQuantityDto.getQuantity());
        adjustQuantityResponse.put("reason", adjustQuantityDto.getReason());
        adjustQuantityResponse.put("summary", adjustQuantityDto.getSummary());

        return adjustQuantityResponse;
    }
}
