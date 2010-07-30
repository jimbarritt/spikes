package com.jimbarritt.spikes.stringtemplate;

import org.junit.*;
import org.mockito.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class SayHelloTest {
    @Mock private SayHelloService sayHelloService;

    @Before
    public void onceBeforeEachTest() {
        initMocks(this);
    }

    @Test
    public void saysHelloToJim() {
        when(sayHelloService.formatHelloMessage("Jim")).thenReturn("Hello To Jim");

        String response = new SayHello(sayHelloService).sayHelloTo("Jim");

        assertThat(response, is("Hello To Jim"));

        verify(sayHelloService).formatHelloMessage("Jim");
    }

    @Test
    public void saysHelloToFranck() {
        when(sayHelloService.formatHelloMessage("Franck")).thenReturn("Hello To Franck");

        String response = new SayHello(sayHelloService).sayHelloTo("Franck");

        assertThat(response, is("Hello To Franck"));
    }



}