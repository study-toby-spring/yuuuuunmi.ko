package spring.basic.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by yuuuunmi on 2017. 7. 29..
 */

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Hello {

    private int id;
    private String title;

    @Override
    public String toString(){
        return String.format("id : %d, title : %s", id, title);
    }


}
