package edu.tjrac.swant.unicorn.bean;

/**
 * Created by wpc on 2018/3/7 0007.
 */

//@Entity(foreignKeys = @ForeignKey(entity = User.class,
//parentColumns = "id",
//childColumns = "user_id")
//)
public class Space {

    public int id;
    String name;
    int weight;
    int volume;

    public  int user_id;


}
