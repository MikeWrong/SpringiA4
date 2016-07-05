
###通过注解引入新功能

通过 @DeclareParents 注解，将 Encoreable 接口引入到 Performance bean 中。

```java
    @DeclareParents(value = "me.caiyuan.spring.aspect.concert.Performance+", defaultImpl = DefaultEncoreable.class)
```

- value 属性指定了那种类型的bean要引入该接口。
  在本例中,也就是所有实现Performance的类型。(标记符后面的加号是表示是Performance的所有子类,而不是Performance本身)

- defaultImpl 属性指定了为引入功能提供实现的类。
  在这里,我们指定的是DefaultEncoreable提供实现。

- @DeclareParents注解所标记的静态属性指明了要引入的接口。
  在这里,我们所引入的是Encoreable接口。