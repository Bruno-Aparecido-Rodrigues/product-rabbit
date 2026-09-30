package io.github.fatec.event;

import io.github.fatec.event.dto.AtualizarVendaEvento;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AtualizarVendaPublicadorImpl implements AtualizarVendaPublicador {

    private final RabbitTemplate rabbitTemplate;
    private final String exchange;
    private final String routingKey;

    public AtualizarVendaPublicadorImpl(
            RabbitTemplate rabbitTemplate,
            @Value("${event.atualizar-venda.exchange}") String exchange,
            @Value("${event.atualizar-venda.queue}") String routingKey) {
        this.rabbitTemplate = rabbitTemplate;
        this.exchange = exchange;
        this.routingKey = routingKey;
    }

    @Override
    public void publicar(AtualizarVendaEvento evento) {
        try {
            rabbitTemplate.convertAndSend(exchange, routingKey, evento);
        } catch (Exception ex) {
            throw new AmqpRejectAndDontRequeueException(ex);
        }
    }
}