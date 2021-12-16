package com.example.app.com.json.bind.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.json.bind.annotation.JsonbTypeAdapter;

import com.example.app.com.json.bind.adapter.CustomJsonAdaptor;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@JsonbTypeAdapter(CustomJsonAdaptor.class)
@Documented
public @interface MaskInfomation {

}
