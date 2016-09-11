package chawla.fireapp;

/**
 * Created by Tushar on 9/10/16.
 */
public class Job_class { private String Job_title_;
    private String Job_description_;
    private String location_;
    private String cost;
    private String time_;
    private String job_status_;
    private String uploader_name;
    private String assignee_name;
    private String Catogory;



    public Job_class(String job_title_, String job_description_, String location_, String cost, String time_, String job_status_, String uploader_name, String assignee_name, String Catogory) {
        Job_title_ = job_title_;
        Job_description_ = job_description_;
        this.location_ = location_;
        this.cost = cost;
        this.time_ = time_;
        this.job_status_ = job_status_;
        this.uploader_name=uploader_name;
        this.Catogory = Catogory;
        this.assignee_name = assignee_name;
    }


    public String getJob_title_() {
        return Job_title_;
    }

    public void setJob_title_(String job_title_) {
        Job_title_ = job_title_;
    }

    public String getJob_description_() {
        return Job_description_;
    }

    public void setJob_description_(String job_description_) {
        Job_description_ = job_description_;
    }

    public String getLocation_() {
        return location_;
    }

    public void setLocation_(String location_) {
        this.location_ = location_;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String isJob_status_() {
        return job_status_;
    }

    public void setJob_status_(String job_status_) {
        this.job_status_ = job_status_;
    }

    public String getTime_() {
        return time_;
    }

    public void setTime_(String time_) {
        this.time_ = time_;
    }

    public String getJob_status_() {
        return job_status_;
    }

    public String getUploader_name() {
        return uploader_name;
    }

    public void setUploader_name(String uploader_name) {
        this.uploader_name = uploader_name;

    }

    public String getAssignee_name() {
        return assignee_name;
    }

    public void setAssignee_name(String assignee_name) {
        this.assignee_name = assignee_name;
    }

    public String getCatogory() {
        return Catogory;
    }

    public void setCatogory(String catogory) {
        Catogory = catogory;
    }
}

