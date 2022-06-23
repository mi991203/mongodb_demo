package com.mongodb.javadriver;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.rmi.runtime.Log;

import java.util.regex.Pattern;

/**
 * @author : SH35856
 * @Description: mongodb-java-driver原生驱动包
 * @date: 2022/6/23 16:05
 */
public class MongodbDriverMain {
    private static final Logger LOG = LoggerFactory.getLogger(MongodbDriverMain.class);

    private MongoClient mongoClient;

    @Before
    public void init() {
        mongoClient = new MongoClient("192.168.137.10", 27017);
    }

    @Test
    /**
     * 创建database
     */
    public void createOrUseDatabase() {
        init();
        // 有则使用。无则创建
        final MongoDatabase mongoDatabase = mongoClient.getDatabase("shao");
        LOG.info("Ok createOrUseDatabase");
    }

    @Test
    /**
     * 删除collection
     */
    public void deleteCollection() {
        init();
        final MongoDatabase mongoDatabase = mongoClient.getDatabase("shao");
        final MongoCollection<Document> table1 = mongoDatabase.getCollection("table_1");
        table1.drop();
        LOG.info("drop collection OK");
    }

    @Test
    /**
     * 创建collection
     */
    public void createCollection() {
        init();
        final MongoDatabase mongoDatabase = mongoClient.getDatabase("shao");
        mongoDatabase.createCollection("table_1");
        LOG.info("create collection OK");
    }

    @Test
    /**
     * 插入记录
     */
    public void saveDocument() {
        init();
        final MongoDatabase mongoDatabase = mongoClient.getDatabase("shao");
        final MongoCollection<Document> table1 = mongoDatabase.getCollection("table_1");
        final Document document = new Document("name", "shao").append("age", 25);
        table1.insertOne(document);
        LOG.info("document insert OK ");
    }

    @Test
    /**
     * 删除doc
     */
    public void dropDocument() {
        init();
        final MongoDatabase mongoDatabase = mongoClient.getDatabase("shao");
        final MongoCollection<Document> table1 = mongoDatabase.getCollection("table_1");
        final DeleteResult deleteResult = table1.deleteOne(Filters.eq("age", 23));
        LOG.info(deleteResult.toString());
    }

    @Test
    /**
     * 查找记录
     */
    public void findDocs() {
        init();
        final MongoDatabase mongoDatabase = mongoClient.getDatabase("shao");
        final MongoCollection<Document> table1 = mongoDatabase.getCollection("table_1");
        final FindIterable<Document> documents = table1.find();
        for (Document document : documents) {
            LOG.info("document_id:{}, name:{}, age:{}", document.get("_id"), document.get("name"), document.get("age"));
        }
    }

    @Test
    /**
     * 模糊匹配
     */
    public void likePattern() {
        init();
        final MongoDatabase mongoDatabase = mongoClient.getDatabase("shao");
        final MongoCollection<Document> table1 = mongoDatabase.getCollection("table_1");
        final Pattern pattern = Pattern.compile("^\\S{0,5}[0-9]{2,3}$", Pattern.CASE_INSENSITIVE);
        final BasicDBObject basicDBObject = new BasicDBObject();
        basicDBObject.put("name", pattern);
        final FindIterable<Document> documents = table1.find(basicDBObject);
        for (Document document : documents) {
            LOG.info("document_id:{}, name:{}, age:{}", document.get("_id"), document.get("name"), document.get("age"));
        }
    }

    @Test
    /**
     * 更新记录
     */
    public void updateDoc() {
        init();
        final MongoDatabase mongoDatabase = mongoClient.getDatabase("shao");
        final MongoCollection<Document> table1 = mongoDatabase.getCollection("table_1");
        final UpdateResult updateResult = table1.updateOne(Filters.eq("age", 23), new Document("$set", new Document("name", "shao1023")));
        LOG.info(updateResult.toString());
    }
}
