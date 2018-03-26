package net.no5no3.microservices.router.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

public class AuthorizeZuulPreFilter extends ZuulFilter {
    @Autowired
    private StringRedisTemplate template;
    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return PRE_DECORATION_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        if (request.getRequestURI().startsWith("/api/account/authorize")) {
        } else {
            String token = request.getHeader("token");
            if (StringUtils.isEmpty(token) || template.opsForValue().getOperations().getExpire(token)< 0) {
                return true;
            }//end
        }
        return false;
    }

    @Override
    public Object run() {
        RequestContext requestContext = RequestContext.getCurrentContext();

        HttpServletResponse response = requestContext.getResponse();
        try {
            response.sendError(401);
        } catch (IOException e) {
            e.printStackTrace();
            requestContext.setSendZuulResponse(false);
        } finally {
        }
        return null;
    }
}
