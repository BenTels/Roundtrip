package nl.bentels.test.roundtrip;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;

public class RoundtripWebsocketHandler extends TextWebSocketHandler implements ObservableOnSubscribe<String> {

	private final List<ObservableEmitter<String>> emitterList = new ArrayList<>();
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		String payload = "%s : %s".formatted(ZonedDateTime.now().toString(), message.getPayload());
		
		emitterList.forEach(emitter -> ForkJoinPool.commonPool().execute(() -> emitter.onNext(payload)));
	}


	@Override
	public void subscribe(@NonNull ObservableEmitter<@NonNull String> emitter) throws Throwable {
		emitterList.add(emitter);
	}

	
	
}
