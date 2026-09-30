package io.github.fatec.event;

import io.github.fatec.event.dto.AtualizarVendaEvento;

public interface AtualizarVendaPublicador {
    void publicar(AtualizarVendaEvento evento);
}