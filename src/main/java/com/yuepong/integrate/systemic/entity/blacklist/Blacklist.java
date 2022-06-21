package com.yuepong.integrate.systemic.entity.blacklist;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @ClassName Blacklist
 * @Description TODO
 * @Author lixuefei
 * @Date 2022.6.20 10:20
 **/
@Entity
@Table(name = "t_black_list", uniqueConstraints = @UniqueConstraint(columnNames = {"namespace", "black_key"}))
public class Blacklist {
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
    @Column(name = "black_key")
    private String blackKey;
    
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
    
    public String getBlackKey() {
        return blackKey;
    }
    
    public void setBlackKey(String blackKey) {
        this.blackKey = blackKey;
    }
}
