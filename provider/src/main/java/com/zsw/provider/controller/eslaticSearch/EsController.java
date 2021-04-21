package com.zsw.provider.controller.eslaticSearch;

import com.alibaba.fastjson.JSON;
import com.zsw.provider.entity.model.ShardingUser;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.StringValue;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.json.Json;
import java.io.IOException;

/**
 * @author zsw
 * @date 2021/4/21 16:52
 * @description : 测试ElasticSearch
 */
@Api("测试elasticSearch")
@RestController
@Slf4j
public class EsController {
    @Autowired
    private RestHighLevelClient restHighLevelClient;
    private static final String ES_INDEX = "zsw_index";

    @GetMapping("/createIndex")
    public void createIndex() throws IOException {
        //创建索引
        CreateIndexRequest createIndexRequest = new CreateIndexRequest(ES_INDEX);
        CreateIndexResponse response = restHighLevelClient.indices().create(createIndexRequest, RequestOptions.DEFAULT);
        log.info(String.valueOf(response));
    }

    @GetMapping("/delIndex")
    public void delIndex() throws IOException {
        //测试索引是否存在
        GetIndexRequest getIndexRequest = new GetIndexRequest(ES_INDEX);
        boolean exists = restHighLevelClient.indices().exists(getIndexRequest, RequestOptions.DEFAULT);
        log.info(String.valueOf(exists));
        if (exists) {
            DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest(ES_INDEX);
            AcknowledgedResponse delete = restHighLevelClient.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
            log.info(String.valueOf(delete));
        }
    }

    @GetMapping("/CreateDocument")
    public void createDocument() throws IOException {
        ShardingUser shardingUser = new ShardingUser(1,"zsw","123456");
        IndexRequest indexRequest = new IndexRequest(ES_INDEX); //创建请求
        indexRequest.id("1").timeout(TimeValue.timeValueSeconds(1)); //规则
        indexRequest.source(JSON.toJSONString(shardingUser), XContentType.JSON);
        //客户端发送请求，获取相应的结果
        IndexResponse response = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
        log.info(response.status().toString(), response);
    }

    //判断是否存在
    @GetMapping("/exitDocument")
    public void exitDocument() throws IOException {
        GetRequest request = new GetRequest(ES_INDEX, "1");
        //不获取返回的_source 的上下文
        request.fetchSourceContext(new FetchSourceContext(false));
        request.storedFields("_none");

        boolean exists = restHighLevelClient.exists(request, RequestOptions.DEFAULT);
       log.info("是否存在：{}",exists);
    }

    //获取文档信息
    @GetMapping("/getDocument")
    public void getDocument() throws IOException {
        GetRequest request = new GetRequest(ES_INDEX, "1");
        GetResponse response = restHighLevelClient.get(request, RequestOptions.DEFAULT);
        System.out.println("获取到的结果"+response.getSourceAsString());
    }

    //更新文档
    @GetMapping("/updateDocument")
    public void updateDocument() throws IOException {
        //创建对象
        ShardingUser shardingUser = new ShardingUser(2, "zthu", "123456");
        UpdateRequest request = new UpdateRequest(ES_INDEX, "1");
        request.timeout("1s");
        request.doc(JSON.toJSONString(shardingUser),XContentType.JSON);
        UpdateResponse response = restHighLevelClient.update(request, RequestOptions.DEFAULT);
        System.out.println(response.status());
    }

    //删除文档
    @GetMapping("/delete")
    public void deleteDocument() throws IOException{
        DeleteRequest request = new DeleteRequest(ES_INDEX, "1");
        request.timeout("1s");

        DeleteResponse response = restHighLevelClient.delete(request, RequestOptions.DEFAULT);
        System.out.println(response.status());
    }

}
