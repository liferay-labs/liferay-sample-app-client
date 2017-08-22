/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.sample.app.client.web.internal.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.petra.json.web.service.client.JSONWebServiceClient;
import com.liferay.petra.json.web.service.client.JSONWebServiceClientImpl;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.sample.app.client.web.internal.model.Product;

import java.net.MalformedURLException;
import java.net.URL;

import java.util.Collections;
import java.util.List;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

/**
 * @author Eduardo Garcia
 */
@Component(service = SampleAppClient.class)
public class SampleAppClient {

	@Activate
	public void activate() throws Exception {
		_sampleAppServiceURL = System.getenv(_SAMPLE_APP_SERVICE_URL_ENV);

		URL url;

		try {
			url = new URL(_sampleAppServiceURL);
		}
		catch (MalformedURLException murle) {
			_log.error(
				_SAMPLE_APP_SERVICE_URL_ENV + " env variable is undefined");

			throw murle;
		}

		_jsonWebServiceClient.setHostName(url.getHost());
		_jsonWebServiceClient.setHostPort(url.getPort());
	}

	public Product getProduct(String productId) throws Exception {
		String responseJSON = _jsonWebServiceClient.doGet(
			"/products/" + productId, Collections.<String, String>emptyMap());

		return _objectMapper.readValue(
			responseJSON, new TypeReference<Product>() {});
	}

	public List<String> getProductIds() throws Exception {
		String responseJSON = _jsonWebServiceClient.doGet(
			"/products", Collections.<String, String>emptyMap());

		return _objectMapper.readValue(
			responseJSON, new TypeReference<List<String>>() {});
	}

	private static final String _SAMPLE_APP_SERVICE_URL_ENV =
		"SAMPLE_APP_SERVICE_URL";

	private static final Log _log = LogFactoryUtil.getLog(
		SampleAppClient.class);

	private static final ObjectMapper _objectMapper = new ObjectMapper();

	private final JSONWebServiceClient _jsonWebServiceClient =
		new JSONWebServiceClientImpl();
	private String _sampleAppServiceURL;

}