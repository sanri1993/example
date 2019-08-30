package com.sanri.test.testmvc.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.util.Date;

@Slf4j
public class StringDateConvertor implements Converter<String, Date> {
    private static StringDateConvertor INSTANCE = new StringDateConvertor();

    private StringDateConvertor(){}

    final String[] parsePatterns = new String[]{"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm:ss.S"};
    @Override
    public Date convert(String source) {
        try {
            return DateUtils.parseDate(source,parsePatterns);
        } catch (ParseException e) {
            log.error("日期解析错误,当前日期为:" + source);
        }
        return  null;
    }

    public static StringDateConvertor getInstance() {
        return INSTANCE;
    }
}
