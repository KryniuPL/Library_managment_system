package com.library.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Definiuje ustawienia głównego servletu
 */
public class LibraryWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer
{
    /**
     * Ustawia mapowanie na główny katalog
     *
     * @return "/"
     */
    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] {};
    }
    /**
     * Obsługa dispacher servletu
     *
     * @return WebConfig
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] { WebConfig.class };
    }


}
