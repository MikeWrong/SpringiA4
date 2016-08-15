package me.caiyuan.spring.web.repository;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * YUAN
 * 7/12/16.
 */
@Repository
public class JdbcSpittleRepository implements SpittleRepository {

    private Random random = new Random();

    public List<Spittle> findSpittles(long max, int count) {
        ArrayList<Spittle> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Spittle spittle = new Spittle("message_" + i, new Date(), random.nextDouble(), random.nextDouble());
            list.add(spittle);
        }
        return list;
    }

}
