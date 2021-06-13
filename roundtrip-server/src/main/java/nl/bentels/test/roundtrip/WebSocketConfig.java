package nl.bentels.test.roundtrip;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import io.reactivex.rxjava3.core.Observable;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(webSocketHandler(), "/ws").setAllowedOrigins("*");

	}
	
	@Bean
	public RoundtripWebsocketHandler webSocketHandler() {
		return new RoundtripWebsocketHandler();
	}

	@Bean()
	@Lazy(value = false)
	public Object subscribe(RoundtripWebsocketHandler handler, LoggingMessageObserver observer) {
		Observable.create(handler).subscribe(observer);
		return new Object();
	}
}
