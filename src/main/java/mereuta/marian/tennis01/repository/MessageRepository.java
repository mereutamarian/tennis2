package mereuta.marian.tennis01.repository;



import mereuta.marian.tennis01.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message,Integer> {

}
