package com.example.test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.io.IOUtils;

import com.example.app.com.jaxrs.request.model.ErrorDetailInfo;
import com.example.app.com.jaxrs.request.model.ErrorResponse;

public class AppTestRestClientFilter implements ClientResponseFilter, ClientRequestFilter {

	@Override
	public void filter(ClientRequestContext requestContext, ClientResponseContext responseContext)
			throws IOException {
		if(MediaType.APPLICATION_JSON_TYPE.equals(responseContext.getMediaType())) {
			byte[] bytes = IOUtils.toByteArray(responseContext.getEntityStream());
			JsonObject jsonObj = Json.createReader(new ByteArrayInputStream(bytes)).readObject();

			byte[] responseData;
			Status status = Status.fromStatusCode(responseContext.getStatus());
			switch (status.getFamily()) {
			case SUCCESSFUL:
				responseData = Optional.ofNullable(jsonObj.get("appResponse")).orElse(jsonObj).toString()
						.getBytes(StandardCharsets.UTF_8);
				break;
			case CLIENT_ERROR:
			case SERVER_ERROR:
				System.out.println(jsonObj.toString());
				byte[] tmpData = jsonObj.getJsonObject("commonResponseHeader").get("errorDetailInfo").toString()
						.getBytes(StandardCharsets.UTF_8);
				Jsonb jsonb = JsonbBuilder.create();
				@SuppressWarnings("unchecked") List<ErrorDetailInfo> errors = jsonb.fromJson(new ByteArrayInputStream(tmpData), ArrayList.class);
				ErrorResponse errorResponse = new ErrorResponse(errors);
				responseData = jsonb.toJson(errorResponse).getBytes(StandardCharsets.UTF_8);
				break;
			default:
				responseData = bytes;
				break;
			}
			responseContext.setEntityStream(new ByteArrayInputStream(responseData));

		}
	}

	@Override
	public void filter(ClientRequestContext requestContext) throws IOException {
		requestContext.getHeaders().add("traceID", "0000000");
	}

}
