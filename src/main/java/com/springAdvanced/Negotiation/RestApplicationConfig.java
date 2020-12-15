package com.springAdvanced.Negotiation;


import com.google.common.io.ByteStreams;
import com.springAdvanced.Negotiation.model.db.User;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@EnableAutoConfiguration
@EnableWebMvc
@ComponentScan({"com.springAdvanced.Negotiation"})
@PropertySource("classpath:application.properties")
public class RestApplicationConfig implements WebMvcConfigurer {

    private final PdfGenerator pdfGenerator;

    public RestApplicationConfig(PdfGenerator pdfGenerator) {
        this.pdfGenerator = pdfGenerator;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(pdfHttpMessageConverter());
        converters.add(0, new MappingJackson2HttpMessageConverter());
    }

    @Bean
    public HttpMessageConverter pdfHttpMessageConverter() {
        return new HttpMessageConverter() {
            @Override
            public boolean canRead(Class clazz, MediaType mediaType) {
                return false;
            }

            @Override
            public boolean canWrite(Class clazz, MediaType mediaType) {
                return mediaType == null || MediaType.APPLICATION_PDF.equals(mediaType);
            }

            @Override
            public List<MediaType> getSupportedMediaTypes() {
                return Collections.singletonList(MediaType.APPLICATION_PDF);
            }

            @Override
            public Object read(Class clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
                return null;
            }

            @Override
            public void write(Object object, MediaType contentType, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
                List<User> users;
                if (object instanceof Collection) {
                    users = ((Collection<com.springAdvanced.Negotiation.model.json.User >) object).stream().map(com.springAdvanced.Negotiation.model.json.User::convertToDBUser).collect(Collectors.toList());
                } else if (object instanceof com.springAdvanced.Negotiation.model.json.User ) {
                    users = Collections.singletonList(((com.springAdvanced.Negotiation.model.json.User ) object).convertToDBUser());
                } else {
                    users = new ArrayList<>();
                }
                ByteArrayInputStream pdfInputStream = pdfGenerator.generateUsersPDF(users);
                ByteStreams.copy(pdfInputStream, outputMessage.getBody());
            }
        };
    }

}
