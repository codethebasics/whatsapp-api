package br.com.shalai.whatsapp.person;

import br.com.shalai.whatsapp.message.Message;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "person")
    private List<Message> messagesSent;

    @OneToMany(mappedBy = "person")
    private List<Message> messagesReceived;
}
