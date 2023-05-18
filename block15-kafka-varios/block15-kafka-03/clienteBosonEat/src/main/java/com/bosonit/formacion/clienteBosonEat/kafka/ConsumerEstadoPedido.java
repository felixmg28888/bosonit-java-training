package com.bosonit.formacion.clienteBosonEat.kafka;


import com.bosonit.formacion.clienteBosonEat.domain.Pedido;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ConsumerEstadoPedido {

    //List history para ir almacenando los estados del pedido
    private List<Pedido> history = new ArrayList<>();

    //Objectmapper se utiliza para el proceso contrario del productor.
    //En este caso coge el mapa y lo convieste en Pedido.
    ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "Pedido")
    //El KafkaListener va a estar escuchando en el topic en cuestión,
    //cada vez que reciba un mensaje lo pasará por parametro en el método
    //que lo añadirá al list history.
    public void addPedidoToHistory(String pedidoMapeado) throws JsonProcessingException {

        Pedido pedido = objectMapper.readValue(pedidoMapeado, Pedido.class);
        Pedido pedidoToAdd= new Pedido();

        //Lógica del método, si la lista está vacía setea los campos con el parámetro.
        //Si la lista no está vacía setea el estado con lo recibido por parámetro
        //y los demás campos con los datos del pedido original.
        if (history.isEmpty()) {
            pedidoToAdd.setPedido(pedido.getPedido());
            pedidoToAdd.setIngredientes(pedido.getIngredientes());
        } else {
            pedidoToAdd.setPedido(history.get(0).getPedido());
            pedidoToAdd.setIngredientes(history.get(0).getIngredientes());
        }
        pedidoToAdd.setEstado(pedido.getEstado());

        history.add(pedidoToAdd);
    }


    public List<Pedido> getAllEstadosPedido() {
        return history;
    }

    public Pedido getLastEstadoPedido() {
        //Lógica del método, si la lista está vacía manda una propuesta.
        //Si hay elemento lo muestra.
        if (history.isEmpty()) {
            Pedido pedidoVacio = new Pedido();
            pedidoVacio.setPedido("Aún no has realizado ningún pedido");
            pedidoVacio.setIngredientes("¿Tienes hambre?");
            pedidoVacio.setEstado("Haz un pedido aquí: https://codepen.io/FelixMgBosonit/pen/abaWLgx");

            return pedidoVacio;
        } else {
            return history.get(history.size() - 1);
        }
    }

}
