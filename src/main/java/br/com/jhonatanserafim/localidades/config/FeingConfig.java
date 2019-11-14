package br.com.jhonatanserafim.localidades.config;

import br.com.jhonatanserafim.localidades.config.decoder.Utf8GzipDecoder;
import feign.codec.Decoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeingConfig {

    @Bean
    public Decoder decoder(ObjectFactory<HttpMessageConverters> messageConverters) {
        return new Utf8GzipDecoder(messageConverters);
    }
}
