package com.yhb.taobaohelper.utils;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import org.bson.Document;

/**
 * Created by Administrator on 2018/3/25.
 */

public class MongoDBUtil {
    static MongoClientURI uri = null;
    static MongoClient mongoClient = null;
    static MongoDatabase db = null;

      MongoDBUtil() {

    }

    public static void getUserTaoToken() {
        uri = new MongoClientURI("mongodb://5imyh:5imyh13695700114@123.249.83.36:12415/MYH");
        mongoClient = new MongoClient(uri);
        db = mongoClient.getDatabase(uri.getDatabase());
        MongoCollection<Document> collection=   db.getCollection("userstaotokens");
        MongoCursor<Document> cursor1=  collection.find(Filters.eq("openid", "oB8nA1IsaHSE5h-WEj4PFf339_NE")).iterator();


         while (cursor1.hasNext()) {
         org.bson.Document _doc = (Document) cursor1.next();
         System.out.println(_doc.toString());
         }
         cursor1.close();
    }
}
