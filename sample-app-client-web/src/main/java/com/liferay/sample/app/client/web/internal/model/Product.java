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

package com.liferay.sample.app.client.web.internal.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Eduardo Garcia
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {

	public String getCurrency() {
		return _currency;
	}

	public String getName() {
		return _name;
	}

	public double getPrice() {
		return _price;
	}

	public void setCurrency(String currency) {
		_currency = currency;
	}

	public void setName(String name) {
		_name = name;
	}

	public void setPrice(double price) {
		_price = price;
	}

	private String _currency;
	private String _name;
	private double _price;

}