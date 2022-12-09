package app.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class User {

    private String name;
    private String email;
    private String id;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
