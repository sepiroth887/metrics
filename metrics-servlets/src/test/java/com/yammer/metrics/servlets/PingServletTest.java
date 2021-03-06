package com.yammer.metrics.servlets;

import org.eclipse.jetty.testing.ServletTester;
import org.junit.Before;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class PingServletTest extends AbstractServletTest {
    @Override
    protected void setUp(ServletTester tester) {
        tester.addServlet(PingServlet.class, "/ping");
    }

    @Before
    public void setUp() throws Exception {
        request.setMethod("GET");
        request.setURI("/ping");
        request.setVersion("HTTP/1.0");

        processRequest();
    }

    @Test
    public void returns200OK() throws Exception {
        assertThat(response.getStatus())
                .isEqualTo(200);
    }

    @Test
    public void returnsPong() throws Exception {
        assertThat(response.getContent())
                .isEqualTo(String.format("pong%n"));
    }

    @Test
    public void returnsTextPlain() throws Exception {
        assertThat(response.getContentType())
                .isEqualTo("text/plain;charset=ISO-8859-1");
    }

    @Test
    public void returnsUncacheable() throws Exception {
        assertThat(response.getHeader("Cache-Control"))
                .isEqualTo("must-revalidate,no-cache,no-store");

    }
}
