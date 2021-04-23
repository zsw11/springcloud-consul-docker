package com.zsw.provider.controller.eslaticSearch;

import com.alibaba.fastjson.JSON;
import com.sun.org.apache.regexp.internal.RE;
import com.zsw.provider.config.HtmlParseUtil;
import com.zsw.provider.entity.model.ParseDemo;
import com.zsw.provider.entity.model.ShardingUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.StringValue;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MatchPhraseQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.ScoreSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.json.Json;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author zsw
 * @date 2021/4/21 16:52
 * @description : 测试ElasticSearch    官方api 文档
 */
@Api("测试elasticSearch")
@RestController
@Slf4j
public class EsController {
    @Autowired
    private RestHighLevelClient restHighLevelClient;
    @Autowired
    private HtmlParseUtil htmlParseUtil;

    private static final String ES_INDEX = "zsw_index";
    private static final String JD_INDEX = "jd_index"; //这里要小写

    @GetMapping("/createIndex")
    public void createIndex() throws IOException {
        //创建索引
        CreateIndexRequest createIndexRequest = new CreateIndexRequest(ES_INDEX);
        CreateIndexResponse response = restHighLevelClient.indices().create(createIndexRequest, RequestOptions.DEFAULT);
        log.info("索引创建是否成功:" + response.toString());
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
            log.info("删除索引是否成功：" + delete.toString());
        }
    }

    @ApiOperation("在ES_INDEX索引下创建文档数据")
    @GetMapping("/CreateDocument")
    public void createDocument() throws IOException {
        ShardingUser shardingUser = new ShardingUser(1, "zsw", "123456");
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
        log.info("是否存在：{}", exists);
    }

    //获取文档信息
    @GetMapping("/getDocument")
    public void getDocument() throws IOException {
        GetRequest request = new GetRequest(ES_INDEX, "1");
        GetResponse response = restHighLevelClient.get(request, RequestOptions.DEFAULT);
        System.out.println("获取到的结果" + response.getSourceAsString());
    }

    //更新文档
    @GetMapping("/updateDocument")
    public void updateDocument() throws IOException {
        //创建对象
        ShardingUser shardingUser = new ShardingUser(2, "zthu", "123456");
        UpdateRequest request = new UpdateRequest(ES_INDEX, "1");
        request.timeout("1s");
        request.doc(JSON.toJSONString(shardingUser), XContentType.JSON);
        UpdateResponse response = restHighLevelClient.update(request, RequestOptions.DEFAULT);
        System.out.println(response.status());
    }

    //删除文档
    @GetMapping("/deleteDocument")
    public void deleteDocument() throws IOException {
        DeleteRequest request = new DeleteRequest(ES_INDEX, "1");
        request.timeout("1s"); // 请求超时时间
        DeleteResponse response = restHighLevelClient.delete(request, RequestOptions.DEFAULT);
        System.out.println(response.status());
    }

    // 批量操作
    @ApiOperation("批量操作")
    @GetMapping("/bulk")
    public void bulk() throws IOException {
        BulkRequest request = new BulkRequest();
        request.add(new IndexRequest(ES_INDEX).id("1")
                .source(XContentType.JSON, "field", "foo"));
        request.add(new IndexRequest(ES_INDEX).id("2")
                .source(XContentType.JSON, "field", "bar"));
        request.add(new IndexRequest(ES_INDEX).id("12")
                .source(XContentType.JSON, "field", "bar"));
        request.add(new IndexRequest(ES_INDEX).id("22")
                .source(XContentType.JSON, "field", "bar"));
        request.add(new IndexRequest(ES_INDEX).id("3")
                .source(XContentType.JSON, "field", "baz"));
        request.add(new UpdateRequest(ES_INDEX, "1").doc(XContentType.JSON, "other", "test"));
        restHighLevelClient.bulk(request, RequestOptions.DEFAULT);
    }

    @ApiOperation("查询ES_INDEX索引下所有的数据")
    @GetMapping("/searchAll")
    public void searchAll() throws IOException {
        // 获取ES_INDEX 索引下的数据
        SearchRequest searchRequest = new SearchRequest(JD_INDEX);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        // 匹配所有 以及其他查询条件
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchSourceBuilder.size(100);
//        MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder("user", "zsw");
        searchSourceBuilder.sort(new ScoreSortBuilder().order(SortOrder.DESC)); //按_score降序排序(默认,_score为元字段，文档与查询的匹配程度
        // 查询builder放到 请求中
        searchRequest.source(searchSourceBuilder);
        SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT); // 同步 查询还有异步方式，Async
        // 相应返回结果
        log.info("相应的一些结果：状态：{},时间：{},{},是否超时：{}", search.status(), search.getTook(), search.isTerminatedEarly(), search.isTimedOut());
        SearchHits hits = search.getHits();
        SearchHit[] searchHits = hits.getHits();
        for (SearchHit hit : searchHits) {
            // 遍历每个结果  SearchHit提供访问基本信息，如索引，文档ID和得分的每个搜索命中:
            String index = hit.getIndex();
            String id = hit.getId();
            float score = hit.getScore();
            log.info("--------{},{},{}", index, id, score);
            log.info(hit.getSourceAsString());
        }
    }

    @ApiOperation("爬取数据到es,索引我jd_index")
    @GetMapping("/parse/{keyword}")
    public boolean parse(@PathVariable String keyword) throws IOException {
        ArrayList<ParseDemo> parse = htmlParseUtil.parse(keyword);
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout("2m");
        for (ParseDemo i : parse) {
            System.out.println(JSON.toJSONString(i)); // 对象 转 json
            bulkRequest.add(new IndexRequest(JD_INDEX).id(i.getImg()).source(JSON.toJSONString(i), XContentType.JSON));
        }
        BulkResponse bulk = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println("---------------------爬取数据上传是否失败："+bulk.hasFailures());
        return !bulk.hasFailures();
    }

    @ApiOperation("根据关键字分页查询es，并实现关键字高亮")
    @GetMapping("Search/ByPage")
    public ArrayList<HashMap<String,Object>> search(@RequestParam int page,@RequestParam int size,@RequestParam @ApiParam("匹配字段,默认写name") String field,
                                                    @RequestParam @ApiParam("关键字") String keyword) throws IOException {
        LocalTime start = LocalTime.now();
        if (page <= 0) {
            page = 1;
        }
        // 条件搜索
        SearchRequest searchRequest = new SearchRequest(JD_INDEX);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.from(page);
        sourceBuilder.size(size);
        // 精准匹配field keyword
//      match：分词匹配   "match": { "epNo" : "我是人" }  //匹配epNo包含 '我'   ‘是’   ‘人’   的数据,会将一个整的语句拆成一个字一个字的
//　　  match_phrase：不分词匹配      "match_phrase": { "epNo" : "123456" }  //匹配epNo包含123456的数据
//　  　term：完全匹配     "term": { "epNo" : "123456" }  //匹配epNo等于123456的数据
//　  　regexp：正则匹配     "regexp": { "epNo" : "*123456*" }  //匹配epNo包含123456的数据
//　  　wildcard：表达式匹配    "wildcard": { "epNo" : "*123456*" }  //匹配epNo包含123456的数据
//　　  range：区间范围匹配    "range": { "goodsPrice": { "gte":20,"lte":50 } }  //匹配价钱在20~40中间的数据
        MatchPhraseQueryBuilder termQueryBuilder = QueryBuilders.matchPhraseQuery(field, keyword); //matchQuery 模糊匹配
        sourceBuilder.query(termQueryBuilder);
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        // keyword关键字高亮
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        HighlightBuilder.Field highlightTitle =
                new HighlightBuilder.Field("name");
        highlightTitle.highlighterType("unified"); // 高亮的类型
        highlightBuilder.field(highlightTitle);
//        HighlightBuilder.Field highlightUser = new HighlightBuilder.Field("price");
//        highlightBuilder.field(highlightUser);
        sourceBuilder.highlighter(highlightBuilder);
        //执行搜索
        searchRequest.source(sourceBuilder);
        SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        ArrayList< HashMap<String, Object>> list = new ArrayList<>();
        for (SearchHit hit : search.getHits().getHits()) {
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            // 解析高亮的字段
            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            HighlightField highlight = highlightFields.get("name");
            Text[] fragments = highlight.fragments();
            String fragmentString  = fragments[0].string();
            sourceAsMap.put("name", fragmentString);  //高亮字段替换原来的
            list.add((HashMap<String, Object>) sourceAsMap);
        }
        LocalTime end = LocalTime.now();
        Duration between = Duration.between(start, end);
        log.info("搜索一共花费了{}毫秒",between.toMillis());
        return list;
    }

}
