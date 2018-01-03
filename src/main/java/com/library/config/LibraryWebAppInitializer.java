package com.library.config;

import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;
import java.io.IOException;

public class LibraryWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] { RootConfig.class };
    }
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] { WebConfig.class };
    }

    @Override
    protected void customizeRegistration(Dynamic registration)
    {
       // DispatcherServlet ds=new DispatcherServlet();
        //registration=context.add
        registration.setMultipartConfig(new MultipartConfigElement("kryniu/Desktop/library_application/uploads",2097152,4194304,0));
    }

    @Bean
    public MultipartResolver multipartResolver() throws IOException
    {
        CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver();
        multipartResolver.setUploadTempDir(new FileSystemResource("kryniu/Desktop/library_application/uploads"));
        multipartResolver.setMaxUploadSize(2097152);//2 MB MEMORY
        multipartResolver.setMaxInMemorySize(0);
        return multipartResolver;
    }
}
