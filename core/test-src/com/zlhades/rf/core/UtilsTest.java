package com.zlhades.rf.core;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import javax.servlet.http.HttpServletRequest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UtilsTest {

    private static final String REQUEST_URL = "/requestURL";
    private static final String QUERY_URL = "nam=123";
    Utils utils = Utils.getInstance();

    @Before
    public void setUp() {

    }

    @Test
    public void tesBuildURLWhenNoQueryStringThenReturnEmptyString() {

        HttpServletRequest request = mockHttpServletRequest(REQUEST_URL, null);
        String result = utils.buildURL(request);
        Assert.assertEquals(ForwardManger.FORWARD_HTTP_PREFIX + REQUEST_URL, result);
    }

    @Test
    public void tesBuildURLWhenQueryStringExistThenReturnEmptyString() {

        HttpServletRequest request = mockHttpServletRequest(REQUEST_URL, QUERY_URL);
        String result = utils.buildURL(request);
        Assert.assertEquals(ForwardManger.FORWARD_HTTP_PREFIX + REQUEST_URL + "?" + QUERY_URL, result);
    }

    private HttpServletRequest mockHttpServletRequest(String reqUrl, String queryUrl) {

        HttpServletRequest request = mock(HttpServletRequest.class);
        doReturn(queryUrl).when(request).getQueryString();
        doReturn(reqUrl).when(request).getRequestURI();
        return request;
    }

}
