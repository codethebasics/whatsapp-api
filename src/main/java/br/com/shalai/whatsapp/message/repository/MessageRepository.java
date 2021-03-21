package br.com.shalai.whatsapp.message.repository;

import br.com.shalai.whatsapp.message.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "messages", path = "message")
public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findByTextEquals(@Param("text") String text);

}
