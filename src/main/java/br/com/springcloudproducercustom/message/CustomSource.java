package br.com.springcloudproducercustom.message;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 *  (1)
 * Custom Binding
 * Nosso binding possui duas assinaturas de métodos,
 * onde ambas retornam um MessageChannel e estão anotadas com Output.
 * O argumento da anotação será o nome do nosso binding, como temos o INPUT no Source e o OUTPUT no Sink.
 */

public interface CustomSource {

    @Output("default-channel")
    public MessageChannel sendMessageDefaultChannel();

    @Output("custom-channel")
    public MessageChannel sendMessageCustomChannel();

}
