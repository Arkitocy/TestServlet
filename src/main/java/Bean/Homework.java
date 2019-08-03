package Bean;

import Comment.KeyUtils;

import java.util.Date;

public class Homework {
    private String id;
    private String title;
    private String type;
    private Date update_time;
    private Date finish_time;
    private Date complete_time;
    private String status;

    public Homework() {

    }


    public Homework(String title, String type, Date finish_time) {
        this.id= KeyUtils.genUniqueKey();
        this.title = title;
        this.type = type;
        this.finish_time = finish_time;
        this.update_time=new Date();
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getComplete_time() {
        return complete_time;
    }

    public void setComplete_time(Date complete_time) {
        this.complete_time = complete_time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getFinish_time() {
        return finish_time;
    }

    public void setFinish_time(Date finish_time) {
        this.finish_time = finish_time;
    }
}
