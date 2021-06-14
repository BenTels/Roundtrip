package nl.bentels.test.roundtrip;

import java.time.Duration;
import java.time.LocalTime;

import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

@RestController
public class EventBroadcaster {
	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EventBroadcaster.class);
	
	public Publisher<String> publisher;
	
	@Autowired
	public EventBroadcaster(Publisher<String> publisher) {
		this.publisher = publisher;
	}

	@GetMapping(path = "/stream-flux", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	@CrossOrigin("*")
	public Flux<String> streamFlux() {
		return Flux.from(publisher);
	}

}
