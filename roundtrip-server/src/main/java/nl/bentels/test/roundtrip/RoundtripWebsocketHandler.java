package nl.bentels.test.roundtrip;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;

public class RoundtripWebsocketHandler extends TextWebSocketHandler implements ObservableOnSubscribe<String>, Publisher<String> {

	private final List<ObservableEmitter<String>> emitterList = new ArrayList<>();
	private final List<Subscriber<? super String>> observerList = new ArrayList<>();
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		String payload = "%s : %s".formatted(ZonedDateTime.now().toString(), message.getPayload());
		
		emitterList.forEach(emitter -> ForkJoinPool.commonPool().execute(() -> emitter.onNext(payload)));
		observerList.forEach(observer -> ForkJoinPool.commonPool().execute(() -> 
				observer.onNext(payload)  
		));
	}


	@Override
	public void subscribe(@NonNull ObservableEmitter<@NonNull String> emitter) throws Throwable {
		emitterList.add(emitter);
	}

	@Override
	public void subscribe(Subscriber<? super String> s) {
		observerList.add(s);
		s.onSubscribe(new Subscription() {
			
			@Override
			public void request(long n) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void cancel() {
				// TODO Auto-generated method stub
				
			}
		});
	}

	
	
	
}
