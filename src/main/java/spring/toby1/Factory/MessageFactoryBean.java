package spring.toby1.Factory;

import org.springframework.beans.factory.FactoryBean;
import spring.toby1.domain.Message;

/**
 * Created by yuuuunmi on 2017. 11. 21..
 */
public class MessageFactoryBean implements FactoryBean<Message> {

    String text;

    public void setText(String text) {
        this.text = text;
    }

    public Message getObject() throws Exception {
        return Message.newMessage(this.text);
    }

    public Class<?> getObjectType() {
        return null;
    }

    public boolean isSingleton() {
        return false;
    }
}
