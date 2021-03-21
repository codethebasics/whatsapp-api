package br.com.shalai.whatsapp.service;

import br.com.shalai.whatsapp.person.Person;

public interface MessageService {

    void sendMessage(String text, Person person) throws Exception;

    void getMessages(Person person) throws Exception;

}
