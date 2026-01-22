package zela.ticket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import zela.ticket.entity.TicketEntity;

@Repository
public interface TicketRepo extends JpaRepository<TicketEntity, Long> {
    List<TicketEntity> findByUserId(Long userId);
}
