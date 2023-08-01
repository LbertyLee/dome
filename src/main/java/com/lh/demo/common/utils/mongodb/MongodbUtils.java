package com.lh.demo.common.utils.mongodb;


import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.query.UpdateDefinition;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MongodbUtils {

    @Resource
    private MongoTemplate mongoTemplate;

    /**
     * 添加
     *
     * @param objectToSave objectToSave
     */
    public  <T>  void   add(T objectToSave){
        mongoTemplate.save(objectToSave);
    }

    /**
     * 删除
     *
     * @param objectToDelete 对象删除
     */
    public  <T> void delete(T objectToDelete){
        mongoTemplate.remove(objectToDelete);
    }


    /**
     * 更新
     *
     * @param query       查询
     * @param update      更新
     * @param entityClass 实体类
     */
    public void  update(Query query, Update update,Class<?> entityClass){
        mongoTemplate.upsert(query,update,entityClass);
    }

    /**
     * 更新
     *
     * @param query          查询
     * @param update         更新
     * @param collectionName 集合名称
     */
    public void  update(Query query, UpdateDefinition update, String collectionName){
        mongoTemplate.upsert(query,update,collectionName);
    }

    /**
     * 更新
     *
     * @param query          查询
     * @param update         更新
     * @param entityClass    实体类
     * @param collectionName 集合名称
     */
    public void  update(Query query, UpdateDefinition update, Class<?> entityClass, String collectionName){
        mongoTemplate.upsert(query,update,entityClass,collectionName);
    }

    /**
     * 选择
     *
     * @param query       查询
     * @param entityClass 实体类
     */
    public <T> void select(Query query, Class<T> entityClass){
        mongoTemplate.findOne(query,entityClass);
    }

    /**
     * 选择
     *
     * @param query          查询
     * @param entityClass    实体类
     * @param collectionName 集合名称
     */
    public <T> void select(Query query, Class<T> entityClass, String collectionName){
        mongoTemplate.findOne(query,entityClass,collectionName);
    }
}
