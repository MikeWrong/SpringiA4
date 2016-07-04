package me.caiyuan.spring.aspectj;

/**
 * YUAN
 * 7/4/16.
 */
public class CriticismEngineImpl implements CriticismEngine {

    public String getCriticism() {
        int i = (int) (Math.random() * criticismPool.length);
        return criticismPool[i];
    }

    private String[] criticismPool;

    public void setCriticismPool(String[] criticismPool) {
        this.criticismPool = criticismPool;
    }

}
