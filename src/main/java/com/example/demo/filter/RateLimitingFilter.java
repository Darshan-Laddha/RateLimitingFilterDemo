package com.example.demo.filter;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class RateLimitingFilter extends OncePerRequestFilter{
	
	private static final Logger log = LoggerFactory.getLogger(RateLimitingFilter.class);
	private static final int WINDOW_SIZE_MS = 10000;
	private static final int MAX_REQUESTS = 2;
	ConcurrentHashMap<String, RequestCounter> requestCounts = new ConcurrentHashMap<>();

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String clientKey = getClientKey(request);
		if(!clientKey.equals("valid-client")) {
			log.error("INVALID CLIENT KEY");
			 response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
             response.getWriter().write("{\"error\":\"Invalid client key\"}");
             return;
		}
		
		RequestCounter counter = requestCounts.computeIfAbsent(clientKey, k -> new RequestCounter());
		
        synchronized (counter) {
        	Long now = System.currentTimeMillis();
            // check if window expired
            if (now - counter.getWindowStart() > WINDOW_SIZE_MS) {
            	 counter.windowStart = now;
                 counter.count = 0;
            }
            counter.count++;
            if (counter.count > MAX_REQUESTS) {
                log.warn("Rate limit exceeded for client {} on {} {}", clientKey,
                        request.getMethod(), request.getRequestURI());

                response.setStatus(429);
                response.getWriter().write("{\"error\":\"Too many requests. Try again later.\"}");
                return; // 🚨 block request
            }
        }
		
	}

	private String getClientKey(HttpServletRequest request) {
		
		// TODO Auto-generated method stub
		return request.getHeader("X-API-KEY");
	}
	
	private static class RequestCounter {
	    private long windowStart = System.currentTimeMillis();
	    private int count = 0;
		public int getCount() {
			return count;
		}
		public void setCount(int count) {
			this.count = count;
		}
		public long getWindowStart() {
			return windowStart;
		}
		public void setWindowStart(long windowStart) {
			this.windowStart = windowStart;
		}
	}

}
	

