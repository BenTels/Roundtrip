package nl.bentels.test.roundtrip;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

@Component
public class LoggingMessageObserver implements Observer<String> {

	
	private static final Logger LOGGER = LoggerFactory.getLogger(LoggingMessageObserver.class);

	
	@Override
	public void onSubscribe(@NonNull Disposable d) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onNext(@NonNull String t) {
		LOGGER.info(t);

	}

	@Override
	public void onError(@NonNull Throwable e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onComplete() {
		// TODO Auto-generated method stub

	}

}
