package com.digenty.app.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class PageUtil {
    public static Pageable getPageable( int page,int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        return PageRequest.of(page, size, sort);
    }
}
