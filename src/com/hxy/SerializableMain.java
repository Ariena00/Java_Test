package com.hxy;

import java.io.*;
import java.util.Date;

/**
 * @program: Java_Test
 * @description: 实现java序列化
 * @author: Hu Xiangying
 * @create: 2018-07-30 14:03
 **/

public class SerializableMain {
    /**
     * 序列化对象到文件
     * @param fileName
     */
    public static void serialize(String fileName) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
            out.writeObject("序列化的日期是: "); //序列化一个字符串对象到文件
            out.writeObject(new Date()); //序列化一个日期对象
            User user = new User("李四", "980112", 21);
            out.writeObject(user); //序列化一个用户对象

            out.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 反序列化对象到文件
     * @param fileName
     */
    public static void deserialize(String fileName) {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));

            String str = (String) in.readObject(); //刚才的字符串对象
            Date date = (Date) in.readObject(); //日期对象
            User user = (User) in.readObject(); //会员对象
/**
 * 实现serializable接口的对象序列化文件进行反序列化不走构造方法，载入的是该类对象的一个持久化状态，再将这个状态赋值给该类的另一个变量
 *
 * 实现externalizable接口的对象序列化文件进行反序列化先走构造方法得到控对象，然后调用readExternal方法读取序列化文件中的内容给对应的属性赋值。
 */
            System.out.println(str);
            System.out.println(date);
            System.out.println(user);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        //serialize("test");
        deserialize("test");
    }

}
class User implements Serializable {
    private String userName;
    private String userPassword;
    private transient int userAge; //使用transient关键字修饰的变量不会被序列化

    public User() {
        this.userAge = 20;
    }

    public User(String userName, String userPassword, int userAge) {
        super();
        this.userName = userName;
        this.userPassword = userPassword;
        this.userAge = userAge;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    @Override
    public String toString() {
        return "UserInfo [userName=" + userName + ", userPassWord=" + userPassword + ", userAge=" +
                (userAge == 0? "NOT SET" : userAge) + "]";
    }
}