package com.example.app.com.json.bind.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.example.app.com.json.bind.adapter.DisplayNameConverterAdapter;

import jakarta.json.bind.annotation.JsonbTypeAdapter;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@JsonbTypeAdapter(DisplayNameConverterAdapter.class)
@Documented
public @interface DisplayName {

}
