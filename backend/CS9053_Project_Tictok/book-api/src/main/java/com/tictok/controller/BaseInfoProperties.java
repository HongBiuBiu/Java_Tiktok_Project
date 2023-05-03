//package com.imooc.controller;
//
//import com.github.pagehelper.PageInfo;
//import com.imooc.utils.PagedGridResult;
//import com.imooc.utils.RedisOperator;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.List;
//
//public class BaseInfoProperties {
//
//    @Autowired
//    public RedisOperator redis;
//
//    public static final Integer COMMON_START_PAGE = 1;
//    public static final Integer COMMON_PAGE_SIZE = 10;
//
//    public static final String MOBILE_SMSCODE = "mobile:smscode";
//    public static final String REDIS_USER_TOKEN = "redis_user_token";
//    public static final String REDIS_USER_INFO = "redis_user_info";
//
//    public static final String REDIS_MY_FOLLOWS_COUNTS = "redis_my_follows_counts";
//    public static final String REDIS_MY_FANS_COUNTS = "redis_my_fans_counts";
//
//    public static final String REDIS_VLOG_BE_LIKED_COUNTS = "redis_vlog_be_liked_counts";
//    public static final String REDIS_VLOGER_BE_LIKED_COUNTS = "redis_vloger_be_liked_counts";
//
//
////    public Map<String, String> getErrors(BindingResult result) {
////        Map<String, String> map = new HashMap<>();
////        List<FieldError> errorList = result.getFieldErrors();
////        for (FieldError ff : errorList) {
////            String field = ff.getField();
////            String msg = ff.getDefaultMessage();
////            map.put(field, msg);
////        }
////        return map;
////    }
//
//    public PagedGridResult setterPagedGrid(List<?> list,
//                                           Integer page) {
//        PageInfo<?> pageList = new PageInfo<>(list);
//        PagedGridResult gridResult = new PagedGridResult();
//        gridResult.setRows(list);
//        gridResult.setPage(page);
//        gridResult.setRecords(pageList.getTotal());
//        gridResult.setTotal(pageList.getPages());
//        return gridResult;
//    }
//}
