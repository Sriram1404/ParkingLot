package io.greenstitch.parkinglot.repository;

import io.greenstitch.parkinglot.model.Ticket;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Repository
public class TicketRepositoryImpl  implements ITicketRepository{

    Map<Long, Ticket> ticketIdToTicketMap;
    Map<Integer,Long> slotIdToTicketIdMap;

    public TicketRepositoryImpl(){
        ticketIdToTicketMap = new HashMap<Long, Ticket>();
        slotIdToTicketIdMap= new HashMap<Integer,Long>();
    }
    @Override
    public void save(Ticket ticket) {
        ticketIdToTicketMap.put(ticket.getId(), ticket);
        slotIdToTicketIdMap.put(ticket.getSlot().getId(), ticket.getId());
    }

    @Override
    public Ticket findById(Long ticketId) {
        return ticketIdToTicketMap.getOrDefault(ticketId,null);
    }

    @Override
    public void remove(Integer slotId) {
        Long ticketId = slotIdToTicketIdMap.getOrDefault(slotId,null);
        if(ticketId!=null) {
            Ticket ticket = ticketIdToTicketMap.get(ticketId);
            ticket.setOutTime(LocalDateTime.now());
            ticketIdToTicketMap.put(ticketId,ticket);
            slotIdToTicketIdMap.remove(slotId);
        }
    }
}
