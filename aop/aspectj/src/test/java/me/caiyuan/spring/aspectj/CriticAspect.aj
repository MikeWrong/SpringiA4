package me.caiyuan.spring.aspectj;

public aspect CriticAspect {

    pointcut performance(): execution(* perform(..));

    after(): performance() {
        System.out.println(criticismEngine.getCriticism());
        System.out.println("----------------------");
        System.out.println("this : " + thisJoinPoint.getThis());
        System.out.println("target : " + thisJoinPoint.getTarget());
    }

    private CriticismEngine criticismEngine;

    public void setCriticismEngine(CriticismEngine criticismEngine) {
        this.criticismEngine = criticismEngine;
    }

}
