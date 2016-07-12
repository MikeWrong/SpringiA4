package me.caiyuan.spring.web.repository;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * YUAN
 * 7/12/16.
 */
@Repository
public class JdbcSpittleRepository implements SpittleRepository {

    public List<Spittle> findSpittles(long max, int count) {
        ArrayList<Spittle> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Spittle spittle = new Spittle("message_" + i, new Date());
            list.add(spittle);
        }
        return list;
    }

}
