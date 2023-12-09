package com.example.app.rest;

import java.util.logging.Logger;

import com.example.app.com.core.annotation.Masking;
import com.example.app.com.interceptor.ExceptionHandling;
import com.example.app.com.interceptor.SampleInterceptorAnnotation;
import com.example.app.rest.model.SampleRequest;
import com.example.domain.service.SampleService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
@ExceptionHandling
@SampleInterceptorAnnotation(eventName = "EVENT00001")
@Path(value = "sample")
public class SampleController {

	@Inject
	private SampleService service;

//	private DecoratorIF dec;

//	@Inject
//	public SampleController(DecoratorIF dec) {
//		this.dec = dec;
//	}

	public SampleController() {
		// TODO Auto-generated constructor stub
	}

	@Inject
	public SampleController(SampleService service) {
		this.service = service;
	}

	private final Logger logger = Logger.getLogger(getClass().getName());

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@Valid @Masking SampleRequest res, SampleRequest rr) {
		service.persist();
		// throw new RuntimeException();
		return Response.ok(res).build();
//		System.out.println("hogeを呼び出し");
////		dec.hoge();
////		System.out.println("hoge2を呼び出し");
////		dec.hoge2();
//		Logger parent = Logger.getLogger("com.example.app.rest");
//		System.out.println("親ロガーのデフォルトレベル");
//		if (parent.isLoggable(Level.FINE)){
//			System.out.println("FINE");
//		}else if (parent.isLoggable(Level.INFO)){
//			System.out.println("INFO");
//		}else if (parent.isLoggable(Level.SEVERE)) {
//			System.out.println("SEVERE");
//		}
//		parent.setLevel(Level.SEVERE);
//
//		Logger current = Logger.getLogger("com.example.app.rest.SampleController");
//		System.out.println("現在のロガーの設定レベル");
//		if (current.isLoggable(Level.FINE)){
//			System.out.println("FINE");
//		}else if (current.isLoggable(Level.INFO)){
//			System.out.println("INFO");
//		}else if (current.isLoggable(Level.SEVERE)) {
//			System.out.println("SEVERE");
//		}
//		
//		System.out.println(logger.getName());
//		System.out.println("親ロガーの名前");
//		System.out.println(logger.getParent().getName());
//		System.out.println(logger.getParent().getParent().getName());
//		System.out.println(logger.getParent().getParent().getParent().getName());
//		System.out.println(logger.getParent().getParent().getParent().getParent().getName());
//		System.out.println(logger.getParent().getParent().getParent().getParent().getParent().getName());
//		Logger rootLogger = Logger.getLogger("");
//		System.out.println("ルートロガーの設定レベル");
//		System.out.println(rootLogger.getLevel());
////		if (current.isLoggable(Level.FINE)){
////			System.out.println("FINE");
////		}else if (current.isLoggable(Level.INFO)){
////			System.out.println("INFO");
////		}else if (current.isLoggable(Level.SEVERE)) {
////			System.out.println("SEVERE");
////		}
//		
//		
//		RuntimeException ex = null;
//		logger.severe("nullnull");
//		logger.log(Level.SEVERE, "nullのテスト", ex);
//		logger.info(Thread.currentThread().getName());
//		//throw new RuntimeException();
//		// throw new AppBusinessException("APYC00004", null);
//		
//		// return null;
//		//throw new RuntimeException();
////		service.persist();
//		SampleResponse re = new SampleResponse();
//		re.setHoge("");
//		return Response.ok().entity(re).build();
	}

}
