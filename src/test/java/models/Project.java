package models;

import lombok.Builder;
import lombok.Data;

public class Project {
    private String name;
    private String announcement;
    private boolean isShowAnnouncement;
    private int typeOfProject;
    private boolean isCompleted;
    private User user;


    public Project() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(String announcement) {
        this.announcement = announcement;
    }

    public boolean isShowAnnouncement() {
        return isShowAnnouncement;
    }

    public void setShowAnnouncement(boolean showAnnouncement) {
        isShowAnnouncement = showAnnouncement;
    }

    public int getTypeOfProject() {
        return typeOfProject;
    }

    public void setTypeOfProject(int typeOfProject) {
        this.typeOfProject = typeOfProject;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}
