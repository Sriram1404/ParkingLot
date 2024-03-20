package io.greenstitch.parkinglot.repository;

import io.greenstitch.parkinglot.model.Address;
import io.greenstitch.parkinglot.model.Slot;
import io.greenstitch.parkinglot.model.Ticket;

import java.util.List;
import java.util.Set;

public interface ITicketRepository {
    public void save(Ticket ticket);

    public Ticket findById(Long id);

    public void remove(Integer slotId);
}
