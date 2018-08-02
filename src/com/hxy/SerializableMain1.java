package com.hxy;

import java.io.*;
import java.util.Date;

/**
 * @program: Java_Test
 * @description: 使用Externalizable的序列化方法
 * @author: Hu Xiangying
 * @create: 2018-07-30 15:00
 **/

public class SerializableMain1 {
    public static void serialize(String fileName) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
            out.writeObject("序列化的日期是: ");
            out.writeObject(new Date());
            UserInfo user = new UserInfo("李四", "980112", 21);
            out.writeObject(user); //进入writeExternal方法，写入userName, userPassword值

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

            String str = (String) in.readObject();//刚才的字符串对象
            Date date = (Date) in.readObject();//日期对象
            UserInfo user = (UserInfo) in.readObject();//会员对象。执行生成器UserInfo()方法，初始化userAge的值为20
                                                       //这是与Serializable序列化方法不同之处
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
class UserInfo implements Externalizable {
    private String userName;
    private String userPassword;
    private int userAge;

    public UserInfo() {
        this.userAge = 20;
    }

    public UserInfo(String userName, String userPassword, int userAge) {
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
        return "UserInfo [userName=" + userName + ", userPassWord=" + userPassword + ",userAge=" +
                (userAge == 0? "NOT SET" : userAge) + "]";
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(userName);
        out.writeObject(userPassword);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        userName =(String) in.readObject();
        userPassword = (String) in.readObject();
    }
}
