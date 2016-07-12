package me.caiyuan.spring.web.repository;

import java.util.ArrayList;
import java.util.List;

/**
 * YUAN
 * 7/12/16.
 */
public class JdbcSpittleRepository implements SpittleRepository {

    public List<Spittle> findSpittles(long max, int count) {
        return new ArrayList<>();
    }

}
