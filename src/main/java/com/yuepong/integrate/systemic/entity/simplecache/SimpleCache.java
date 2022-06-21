package com.yuepong.integrate.systemic.entity.simplecache;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * @ClassName SimpleCache
 * @Description 简单缓存实体类
 * @Author lixuefei
 * @Date 2022.5.26 10:10
 **/
@Entity
@Table(name = "t_simple_cache", uniqueConstraints = @UniqueConstraint(columnNames = {"namespace", "cache_key"}))
public class SimpleCache {
    /**
     * id
     */
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "id")
    private String id;
    /**
     * 命名空间
     */
    @Column(name = "namespace")
    private String namespace;
    /**
     * key
     */
    @Column(name = "cache_key")
    private String cacheKey;
    /**
     * value
     */
    @Column(name = "cache_value")
    private String cacheValue;
    /**
     * 驱逐模式
     */
    @Column(name = "expel_model")
    private String expelModel;
    /**
     * 失效时间
     */
    @Column(name = "out_of_time")
    private Long outOfTime;
    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;
    /**
     * 驱逐日期
     */
    @Column(name = "expel_date")
    private Date expelDate;
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getNamespace() {
        return namespace;
    }
    
    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }
    
    public String getCacheKey() {
        return cacheKey;
    }
    
    public void setCacheKey(String cacheKey) {
        this.cacheKey = cacheKey;
    }
    
    public String getCacheValue() {
        return cacheValue;
    }
    
    public void setCacheValue(String cacheValue) {
        this.cacheValue = cacheValue;
    }
    
    public String getExpelModel() {
        return expelModel;
    }
    
    public void setExpelModel(String expelModel) {
        this.expelModel = expelModel;
    }
    
    public Long getOutOfTime() {
        return outOfTime;
    }
    
    public void setOutOfTime(Long outOfTime) {
        this.outOfTime = outOfTime;
    }
    
    public Date getCreateTime() {
        return createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    public Date getExpelDate() {
        return expelDate;
    }
    
    public void setExpelDate(Date expelDate) {
        this.expelDate = expelDate;
    }
    
    
}
