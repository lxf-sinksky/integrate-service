package com.yuepong.integrate.systemic.entity.anchorpoint;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @ClassName AnchorPoint
 * @Description 锚点实体类
 * @Author lixuefei
 * @Date 2022.6.15 11:34
 **/
@Entity
@Table(name = "t_anchor_point", uniqueConstraints = @UniqueConstraint(columnNames = {"namespace"}))
public class AnchorPoint {
    /**
     * id
     */
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "id")
    private String id;
    /**
     * 名称空间
     */
    @Column(name = "namespace")
    private String namespace;
    /**
     * 锚点值
     */
    @Column(name = "anchor_value")
    private String anchorValue;
    
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
    
    public String getAnchorValue() {
        return anchorValue;
    }
    
    public void setAnchorValue(String anchorValue) {
        this.anchorValue = anchorValue;
    }
}
