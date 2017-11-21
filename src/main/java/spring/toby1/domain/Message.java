package spring.toby1.domain;

/**
 * Created by yuuuunmi on 2017. 11. 21..
 */
public class Message {

    String text;

    private Message(String text){
        this.text = text;
    }

    public String getText(){
        return text;
    }

    public static Message newMessage(String text){
        return new Message(text);
    }
    

}
