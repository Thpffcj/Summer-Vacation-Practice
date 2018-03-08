package cn.edu.nju;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@SpringBootApplication
@RestController
public class LearnElasticsearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearnElasticsearchApplication.class, args);
	}

	@Autowired
	private TransportClient client;

    //查询接口
    @GetMapping("/get/people/man")
    public ResponseEntity get(@RequestParam(name = "id", defaultValue = "") String id) {
        if (StringUtils.isEmpty(id)) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        GetResponse response = this.client.prepareGet("people", "man", id).get();
        if (!response.isExists()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(response.getSource(), HttpStatus.OK);

    }

    // 增加接口
    @PostMapping("/add/people/man")
    public ResponseEntity add(@RequestParam(name = "name") String name,
                              @RequestParam(name = "country") String country,
                              @RequestParam(name = "age") int age,
                              @RequestParam(name = "date") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date date) {
        try {
            XContentBuilder builder = XContentFactory.jsonBuilder().startObject()
                    .field("name", name)
                    .field("country", country)
                    .field("age", age)
                    .field("date", date.getTime())
                    .endObject();
            IndexResponse response = this.client.prepareIndex("people", "man")
                    .setSource(builder).get();
            return new ResponseEntity(response.getId(), HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    // 删除接口
    @DeleteMapping("/delete/people/man")
    public ResponseEntity delete(@RequestParam(name = "id", defaultValue = "") String id) {
        if (StringUtils.isEmpty(id)) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        DeleteResponse response = this.client.prepareDelete("people", "man", id).get();
        return new ResponseEntity(response.getResult().toString(), HttpStatus.OK);
    }


    // 修改接口
    @PutMapping("/update/people/man")
    public ResponseEntity update(@RequestParam(name = "id") String id,
                                 @RequestParam(name = "name", required = false) String name,
                                 @RequestParam(name = "country", required = false) String country) {
        UpdateRequest request = new UpdateRequest("people", "man", id);
        try {
            XContentBuilder builder = XContentFactory.jsonBuilder().startObject();
            if (name != null) {
                builder.field("name", name);
            }
            if (country != null) {
                builder.field("country", country);
            }
            builder.endObject();
            request.doc(builder);
            UpdateResponse response = this.client.update(request).get();
            return new ResponseEntity(response.getResult().toString(), HttpStatus.OK);
        } catch (IOException | InterruptedException | ExecutionException e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("query/people/man")
    public ResponseEntity query( @RequestParam(name = "name", required = false) String name,
                                 @RequestParam(name = "country", required = false) String country,
                                 @RequestParam(name = "age") Integer age) {
        BoolQueryBuilder boolBuilder = QueryBuilders.boolQuery();
        if (name != null) {
            boolBuilder.must(QueryBuilders.matchQuery("name", name));
        }
        if (country != null) {
            boolBuilder.must(QueryBuilders.matchQuery("country", country));
        }
        RangeQueryBuilder rangeQuery = QueryBuilders.rangeQuery("age").from(age);

        boolBuilder.filter(rangeQuery);

        SearchRequestBuilder builder = this.client.prepareSearch("people")
                .setTypes("man")
                .setSearchType(SearchType.QUERY_THEN_FETCH)
                .setQuery(boolBuilder)
                .setFrom(0)
                .setSize(10);
        SearchResponse response = builder.get();

        List<Map<String,Object>> result = new ArrayList<>();
        response.getHits().forEach((s)->result.add(s.getSource()));
        return new ResponseEntity(result, HttpStatus.OK);

    }
}
