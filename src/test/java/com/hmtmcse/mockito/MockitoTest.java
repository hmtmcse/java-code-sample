package com.hmtmcse.mockito;

import org.junit.Test;
import static org.mockito.Mockito.*;

public class MockitoTest {

    @Test
    public void test1()  {
        MockitoClass test = mock(MockitoClass.class);
        when(test.isAvailable()).thenReturn(true);
    }
}
