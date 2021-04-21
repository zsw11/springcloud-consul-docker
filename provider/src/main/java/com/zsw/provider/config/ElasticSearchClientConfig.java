package com.zsw.provider.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zsw
 * @date 2021/4/21 16:41
 * @description :  elasticSearch 配置类
 */
@Configuration
public class ElasticSearchClientConfig {

    @Bean
    public RestHighLevelClient restHighLevelClient() {
        return new RestHighLevelClient( RestClient.builder(new HttpHost("192.168.85.128", 9200, "http")));
    }
}
