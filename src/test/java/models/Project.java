package models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Project {
    private String name;
    private String announcement;
    private boolean isShowAnnouncement;
    private int typeOfProject;
    private boolean isCompleted;
    private User user;
}
