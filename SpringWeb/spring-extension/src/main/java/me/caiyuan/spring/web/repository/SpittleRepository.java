package me.caiyuan.spring.web.repository;

import java.util.List;

/**
 * YUAN
 * 7/12/16.
 */
public interface SpittleRepository {

    List<Spittle> findSpittles(long max, int count);

}
