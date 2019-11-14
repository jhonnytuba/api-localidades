package br.com.jhonatanserafim.localidades.config.decoder;

import feign.Response;
import feign.Util;
import feign.codec.Decoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.encoding.HttpEncoding;
import org.springframework.cloud.openfeign.support.SpringDecoder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.zip.GZIPInputStream;

//TODO: Workaround! Estarei codificando no https://github.com/spring-cloud/spring-cloud-openfeign para permitir configuração UTF-8
public class Utf8GzipDecoder implements Decoder {

    private Decoder decoder;

    public Utf8GzipDecoder(ObjectFactory<HttpMessageConverters> messageConverters) {
        this.decoder = new SpringDecoder(messageConverters);
    }

    @Override
    public Object decode(final Response response, Type type)
            throws IOException {
        Collection<String> encoding = response.headers()
                .getOrDefault(HttpEncoding.CONTENT_ENCODING_HEADER, null);

        if (encoding != null && encoding.contains(HttpEncoding.GZIP_ENCODING)) {
            String decompressedBody = decompress(response);
            if (decompressedBody != null) {
                Response decompressedResponse = response.toBuilder()
                        .body(decompressedBody.getBytes()).build();
                return decoder.decode(decompressedResponse, type);
            }
        }
        return decoder.decode(response, type);
    }

    private String decompress(Response response) throws IOException {
        if (response.body() == null) {
            return null;
        }
        try (GZIPInputStream gzipInputStream = new GZIPInputStream(
                response.body().asInputStream());
             BufferedReader reader = new BufferedReader(
                     new InputStreamReader(gzipInputStream, Util.UTF_8))) {
            StringBuilder outputString = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                outputString.append(line);
            }
            return outputString.toString();
        }
    }
}
