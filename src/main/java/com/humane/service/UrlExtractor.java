package com.humane.service;

import com.humane.helper.GoodReadsHelper;
import com.humane.model.BookInfo;
import com.humane.repository.BookInfoRepository;
import com.humane.util.JsoupUtil;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by ashish on 14/03/20.
 */
@Component
public class UrlExtractor {

    private JsoupUtil jsoupUtil;
    private UrlLoader urlLoader;
    private GoodReadsHelper goodReadsHelper;
    private BookInfoRepository bookInfoRepository;

    @Autowired
    public UrlExtractor(JsoupUtil jsoupUtil, UrlLoader urlLoader, GoodReadsHelper goodReadsHelper, BookInfoRepository bookInfoRepository){
        this.jsoupUtil = jsoupUtil;
        this.urlLoader = urlLoader;
        this.goodReadsHelper = goodReadsHelper;
        this.bookInfoRepository = bookInfoRepository;
    }

    public void getUrlsFromDoc(Document document){
        List<String> subUrls =  jsoupUtil.extractUrls(document);
        List<String> jpgList =  jsoupUtil.extractJpgs(document);
        List<String> pngList =  jsoupUtil.extractPngs(document);
        goodReadsHelper.extractBookInfo(document);
        List<BookInfo> allBooks = bookInfoRepository.findAll();
        urlLoader.loadUrl(subUrls);
    }
    

}
