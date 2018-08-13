package com.allen.common.bean;



import com.allen.common.db.StringConverter;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

import java.util.List;

/**
 * <pre>
 *      @author : xiaoyao
 *      e-mail  : xiaoyao@51vest.com
 *      date    : 2018/07/25
 *      desc    :
 *      version : 1.0
 * </pre>
 */
@Entity
public class UserBean {

    /**
     * collectIds : [3180,3181,3179]
     * email :
     * icon :
     * id : 7852
     * password : 123456
     * type : 0
     * username : 134679
     */

    @Property
    private String email;
    @Property
    private String icon;
    @Id
    private Long id;
    @Property
    private String password;
    @Property
    private int type;
    @Property
    private String username;
    @Convert(converter = StringConverter.class,columnType = String.class)
    private List<String> collectIds;
    @Generated(hash = 808883233)
    public UserBean(String email, String icon, Long id, String password, int type,
            String username, List<String> collectIds) {
        this.email = email;
        this.icon = icon;
        this.id = id;
        this.password = password;
        this.type = type;
        this.username = username;
        this.collectIds = collectIds;
    }
    @Generated(hash = 1203313951)
    public UserBean() {
    }
    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getIcon() {
        return this.icon;
    }
    public void setIcon(String icon) {
        this.icon = icon;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public int getType() {
        return this.type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public List<String> getCollectIds() {
        return this.collectIds;
    }
    public void setCollectIds(List<String> collectIds) {
        this.collectIds = collectIds;
    }


}
