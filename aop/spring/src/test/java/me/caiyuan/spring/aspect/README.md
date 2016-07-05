
###通过注解引入新功能

通过 @DeclareParents 注解，将 Encoreable 接口引入到 Performance bean 中。

```java
    @DeclareParents(value = "me.caiyuan.spring.aspect.concert.Performance+", defaultImpl = DefaultEncoreable.class)
```

- value 属性指定了哪种类型的 bean 要引入该接口。
  在本例中，也就是所有实现 Performance 的类型。（标记符后面的加号表示是 Performance 的所有子类型，而不是 Performance 本身。）

- defaultImpl 属性指定了为引入功能提供实现的类。在这里，我们指定的是 DefaultEncoreable 提供实现。

- @DeclareParents 注解所标注的静态属性指明了要引入了接口。在这里，我们所引入的是 Encoreable 接口。
