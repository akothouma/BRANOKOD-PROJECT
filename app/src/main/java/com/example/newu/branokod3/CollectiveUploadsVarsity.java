package com.example.newu.branokod3;

class CollectiveUploadsVarsity {
    String desc;
    String much;
    String duration;
    String type;
    String posttime;
    String uri;
    String contacturl;

    public CollectiveUploadsVarsity() {
    }

    public CollectiveUploadsVarsity(String desc, String much, String duration, String type, String posttime, String uri, String contacturl) {
        this.desc = desc;
        this.much = much;
        this.duration = duration;
        this.type = type;
        this.posttime = posttime;
        this.uri = uri;
        this.contacturl = contacturl;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getMuch() {
        return much;
    }

    public void setMuch(String much) {
        this.much = much;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPosttime() {
        return posttime;
    }

    public void setPosttime(String posttime) {
        this.posttime = posttime;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getContacturl() {
        return contacturl;
    }

    public void setContacturl(String contacturl) {
        this.contacturl = contacturl;
    }
}
