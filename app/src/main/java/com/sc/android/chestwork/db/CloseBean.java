package com.sc.android.chestwork.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * 衣服存储bean类
 */

@Entity
public class CloseBean {

    @Id
    private String id;
    private String kind;
    private String num;
    private String season;
    private String iamgePath;

    @Generated(hash = 244263974)
    public CloseBean(String id, String kind, String num, String season,
                     String iamgePath) {
        this.id = id;
        this.kind = kind;
        this.num = num;
        this.season = season;
        this.iamgePath = iamgePath;
    }

    @Generated(hash = 609408728)
    public CloseBean() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKind() {
        return this.kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getNum() {
        return this.num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getSeason() {
        return this.season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getIamgePath() {
        return this.iamgePath;
    }

    public void setIamgePath(String iamgePath) {
        this.iamgePath = iamgePath;
    }


}
