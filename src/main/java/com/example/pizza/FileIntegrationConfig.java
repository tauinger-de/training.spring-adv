package com.example.pizza;

import com.example.pizza.order.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.convert.ConversionService;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.filters.SimplePatternFileListFilter;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Configuration
@EnableIntegration
public class FileIntegrationConfig {

    //
    // constants
    //

    private static final Logger LOG = LoggerFactory.getLogger(FileIntegrationConfig.class);
    private static final String FILE_DATA_DIR = "./data";

    @Bean
    public MessageChannel fileChannel() {
        return new DirectChannel();
    }


    @Bean
    @InboundChannelAdapter(value = "fileChannel", poller = @Poller(fixedDelay = "5000"))
    public MessageSource<File> fileReadingMessageSource() {
        FileReadingMessageSource sourceReader = new FileReadingMessageSource();
        sourceReader.setDirectory(new File(FILE_DATA_DIR));
        sourceReader.setFilter(new SimplePatternFileListFilter("order*.json"));
        return sourceReader;
    }


    @Bean
    @ServiceActivator(inputChannel = "fileChannel")
    public MessageHandler fileWritingMessageHandler(ObjectMapper jsonMapper, OrderService orderService) {
        return msg -> {
            if (msg.getPayload() instanceof File) {
                File file = (File) msg.getPayload();
                LOG.debug("Handling message for order file: " + file.getAbsolutePath());
                try {
                    OrderFileRequest orderFileRequest = jsonMapper.readValue(file, OrderFileRequest.class);
                    orderService.placeOrder(
                            orderFileRequest.phoneNumber,
                            orderFileRequest.productQuantities
                    );
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    file.delete();
                }
            }
        };
    }


    // this fixes the problem that the application won't start anymore after adding spring-integration libraries since
    // a second ConversionService instance is added by those. the solution is to make the MVC one a 'primary' bean, which
    // is used in case of ambiguity
    @Bean
    @Primary
    public ConversionService primaryConversionService(@Qualifier("mvcConversionService") ConversionService conversionService) {
        return conversionService;
    }


    public static class OrderFileRequest {
        public String phoneNumber;
        public Map<String, Integer> productQuantities;
    }
}
