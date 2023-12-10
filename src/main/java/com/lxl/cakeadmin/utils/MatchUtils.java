package com.lxl.cakeadmin.utils;

import org.springframework.util.AntPathMatcher;


public class MatchUtils{

    private static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    private MatchUtils() {
    }

    public static boolean isMatch(Object match, Object[] matchList) {
        for (Object pattern : matchList) {
            if (match instanceof String && pattern instanceof String) {
                if (PATH_MATCHER.match((String) pattern, (String) match)) {
                    return true;
                }
            } else if (match instanceof Integer && pattern instanceof Integer) {
                if (match.equals(pattern)) {
                    return true;
                }
            }
        }
        return false;
    }

}
