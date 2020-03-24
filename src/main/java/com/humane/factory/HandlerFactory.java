package com.humane.factory;

import com.humane.repository.BookInfoRepository;
import com.humane.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by ashish on 23/03/20.
 */
@Component
public class HandlerFactory {

    private BookInfoRepository bookInfoRepository;
    @Autowired
    public HandlerFactory(BookInfoRepository bookInfoRepository){
        this.bookInfoRepository = bookInfoRepository;
    }

    public WebsiteHandler getHandler(String url){
        if(url.contains(Constants.WEBSITE_GOODREADS))
            return new GoodReadsHandler(bookInfoRepository);
        else
            return new WebsiteHandler();
    }
}
