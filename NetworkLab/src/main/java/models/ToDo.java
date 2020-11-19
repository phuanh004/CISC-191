package models;

import org.bson.BsonType;
import org.bson.codecs.pojo.annotations.*;
import org.bson.types.ObjectId;
import org.joda.time.Days;
import org.joda.time.LocalDate;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@BsonDiscriminator
public class ToDo implements Serializable {
    //User's two attribute:

    @BsonId
    @BsonRepresentation(BsonType.OBJECT_ID)
    private String id;

    @BsonProperty(value = "title")
    private String title;

    @BsonProperty(value = "description")
    private String description;

    @BsonProperty(value = "created_date")
    private String createdDate;

    @BsonProperty(value = "due_date")
    private String dueDate;

    @BsonProperty(value = "do_date")
    private String doDate;

    //Constructor:
    public ToDo() {}

    public ToDo(String title, String description, String dueDate, String doDate) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.doDate = doDate;
    }

    //Getters:
    @BsonId
    @BsonRepresentation(BsonType.OBJECT_ID)
    public String getID() {
        return this.id;
    }

    @BsonProperty(value = "title")
    public String getTitle() {
        return this.title;
    }

    @BsonProperty(value = "description")
    public String getDescription() {
        return this.description;
    }

    @BsonProperty(value = "created_date")
    public String getCreatedDate() {
        return this.createdDate;
    }

    @BsonProperty(value = "due_date")
    public String getDueDate() {
        return this.dueDate;
    }

    @BsonProperty(value = "do_date")
    public String getDoDate() {
        return doDate;
    }

    /**
     * Calculate date remaining for due date
     * @return date remaining
     */
    @BsonIgnore
    public Integer getRemainingDays() {
        if (dueDate != null){
            Date _dueDate = new Date(Long.parseLong(getDueDate()) * 1000L);

            LocalDate dueDate = new LocalDate(_dueDate);
            LocalDate currentDate = LocalDate.now();

            return Days.daysBetween(currentDate, dueDate).getDays();
        }

        return null;
    }

    @BsonIgnore
    public String getCurrentDate(){
        return String.valueOf(System.currentTimeMillis() / 1000L);
    }

    /**
     * Format the remaining days
     * @return result in display format
     */
    @BsonIgnore
    public String getFormattedRemainingDays() {
        String result = "";
        Integer remainDays = getRemainingDays();

        if (remainDays == null) { return result; }
        else if (remainDays == 0) {
            result = "Due today";
        } else if (remainDays > 0) {
            result = remainDays + " days left";
        } else if (remainDays < 0){
            result = Math.abs(remainDays) + " days ago";
        }

        return result;
    }

    //Setters:
    @BsonId
    @BsonRepresentation(BsonType.OBJECT_ID)
    public void setID(String id) {
        this.id = id;
    }

    @BsonProperty(value = "title")
    public void setTitle(String title) {
        this.title = title;
    }

    @BsonProperty(value = "description")
    public void setDescription(String description) {
        this.description = description;
    }

    @BsonProperty(value = "created_date")
    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    @BsonProperty(value = "due_date")
    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    @BsonProperty(value = "do_date")
    public void setDoDate(String doDate) {
        this.doDate = doDate;
    }


    /**
     * All type
     *
     * @param format
     * type:
     *      "yyyy.MM.dd G 'at' HH:mm:ss z" ---- 2001.07.04 AD at 12:08:56 PDT
     *      "hh 'o''clock' a, zzzz" ----------- 12 o'clock PM, Pacific Daylight Time
     *      "EEE, d MMM yyyy HH:mm:ss Z"------- Wed, 4 Jul 2001 12:08:56 -0700
     *      "yyyy-MM-dd'T'HH:mm:ss.SSSZ"------- 2001-07-04T12:08:56.235-0700
     *      "yyMMddHHmmssZ"-------------------- 010704120856-0700
     *      "K:mm a, z" ----------------------- 0:08 PM, PDT
     *      "h:mm a" -------------------------- 12:08 PM
     *      "EEE, MMM d, ''yy" ---------------- Wed, Jul 4, '01
     *
     * @return a String for display
     */
    @BsonIgnore
    public String getDateInDisplayFormat(String format, String unixTime) {
        if (unixTime == null) {
            return "";
        }
        DateFormat df = new SimpleDateFormat(format, Locale.getDefault());
        Date date = new Date(Long.parseLong(unixTime) * 1000L);

        return df.format(date);
    }

    @BsonIgnore
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("To-do - ");

        builder.append(id).append(":").append("\n");
        builder.append("title: ").append(title).append("\n");
        if (!description.isEmpty()) {
            builder.append("description: ").append(description).append("\n");
        }

        if (!dueDate.isEmpty()) {
            builder.append(getFormattedRemainingDays()).append("\n");
        }

        builder.append("-----------------------------------");

        return builder.toString();

//                "ToDo{" +
//                "id:'" + id + '\'' +
//                ", title:'" + title + '\'' +
//                ", description:'" + description + '\'' +
//                ", createdDate:'" + createdDate + '\'' +
//                ", dueDate:'" + dueDate + '\'' +
//                ", doDate:'" + doDate + '\'' +
//                ", remaining days:'" + getRemainingDays() + '\'' +
//                '}';
    }
}
