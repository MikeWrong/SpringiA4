### Lifecycle

```
    constructor -> init -> process -> destroy
```

```java
    @Component
    public class BeanObject {

        public BeanObject() {
            System.out.println("constructor");
        }

        public void process() {
            System.out.println("process");
        }

        @PostConstruct
        public void init() {
            System.out.println("init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("destroy");
        }

    }
```
