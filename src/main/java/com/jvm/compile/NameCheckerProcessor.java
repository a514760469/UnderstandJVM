package com.jvm.compile;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

public class NameCheckerProcessor extends AbstractProcessor {

	private NameChecker nameChecker;
	
	@Override
	public synchronized void init (ProcessingEnvironment processingEnv) {
		super.init(processingEnv);
		nameChecker = new NameChecker(processingEnv);
	}
	
	@Override
	public boolean process (Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		if(!roundEnv.processingOver()) {
			for (Element element : roundEnv.getRootElements()) {
				nameChecker.checkNames(element);
			}
		}
		return false;
	}

}
