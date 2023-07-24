package com.atguigu.srb.core;


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;

public class Test1 {

    @Test
    public void a(){

        AutoGenerator autoGenerator = new AutoGenerator();

        // 全局输出配置
        GlobalConfig config = new GlobalConfig();
        config.setIdType(IdType.AUTO);
        config.setSwagger2(true);
        config.setServiceName("%sService");
        config.setAuthor("Mr.xu");
        String projectPath = System.getProperty("user.dir");
        config.setOutputDir(projectPath+"/src/main/java");
        autoGenerator.setGlobalConfig(config);

        // 包路径,实体类的包路径
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent("com.atguigu.srb.core");
        packageConfig.setEntity("pojo.entity");
        autoGenerator.setPackageInfo(packageConfig);

        // 配置数据源
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setDbType(DbType.MYSQL);
        dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/srb_core?serverTimezone=GMT%2B8&characterEncoding=utf-8");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("123456");
        autoGenerator.setDataSource(dataSourceConfig);

        // 其他配置
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setEntityLombokModel(true);
        strategyConfig.setLogicDeleteFieldName("is_deleted");//逻辑删除字段名
        strategyConfig.setEntityBooleanColumnRemoveIsPrefix(true);//去掉布尔值的is_前缀（确保tinyint(1)）
        strategyConfig.setRestControllerStyle(true); //restful api风格控制器
        autoGenerator.setStrategy(strategyConfig);

        autoGenerator.execute();

    }
}
