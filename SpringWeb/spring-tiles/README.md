Spring Titles
--

> 使用 Apache Titles 3

```text
当配置TilesConfiguration的时候，所要设置的最重要的属性就是definitions；
这个属性接受一个String数组，其中每一条都指定一个Tile定义的XML文件。(对于Spring来讲，我们在"/WEB-INF/layout/"目录下查找tiles.xml）
```
- JavaConfig 配置方式
```java
	// 配置 TilesConfigurer 类解析 Tile 定义
    @Bean
    public TilesConfigurer tilesConfigurer() {
        TilesConfigurer tiles = new TilesConfigurer();
        tiles.setDefinitions("/WEB-INF/layout/tiles.xml");
        
        // 遍历"/WEB-INF/"的所有子目录来查找Tile定义
	    // tiles.setDefinitions("/WEB-INF/**/tiles.xml");
	    // 配置多个Tile定义的文件源
	    // tiles.setDefinitions("/WEB-INF/layout/tiles.xml", "/WEB-INF/views/**/tiles.xml");

        tiles.setCheckRefresh(true);
        return tiles;
    }

	// 配置 TilesViewResolver 渲染
    @Bean
    public TilesViewResolver tilesViewResolver() {
        return new TilesViewResolver();
    }
```
- XMLConfig 配置方式
```xml
    <bean id="tilesConfigurer" class="org.springframework.web.servlet.view.tiles3.TilesConfigurer">
        <property name="definitions">
            <list>
                <value>/WEB-INF/layout/tiles.xml</value>
                <!--<value>/WEB-INF/views/**/tiles.xml</value>-->
            </list>
        </property>
    </bean>

    <bean id="tilesViewResolver" class="org.springframework.web.servlet.view.tiles3.TilesViewResolver"/>
```
