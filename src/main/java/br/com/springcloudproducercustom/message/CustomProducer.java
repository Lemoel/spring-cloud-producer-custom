package br.com.springcloudproducercustom.message;

import br.com.springcloudproducercustom.dto.MessageDTO;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.support.MessageBuilder;

/**
 * (2)
 * Já criamos nosso custom binding (CustomSource),
 * então agora precisamos utilizá-lo para publicar nossas mensagens.
 */
@EnableBinding(CustomSource.class)
public class CustomProducer {

    private String mySimpleMessage = "This is my simple message";
    private String customMessage = "This is my custom message";

    @Bean
    @InboundChannelAdapter(channel = "default-channel", poller = @Poller(fixedDelay = "2000"))
    public String sendMessageDefault() {
        return mySimpleMessage;
    }

    /**
     * o sendCustomMessage o nosso objeto de retorno é um MessageSource que encapsula, através de generics,
     * um objeto do tipo Message sendo esse um objeto criado para gerar uma mensagem com mais propriedades
     * do que uma simples string, simulando um DTO ou algum objeto mais complexo em um cenário real.
     * @return
     */
    @Bean
    @InboundChannelAdapter(channel = "custom-channel", poller = @Poller(fixedDelay = "3000"))
    public MessageSource<MessageDTO> sendCustomMessage() {
        return () -> MessageBuilder.withPayload(new MessageDTO(customMessage)).build();
    }
}
