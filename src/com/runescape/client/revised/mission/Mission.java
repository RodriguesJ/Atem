package com.runescape.client.revised.mission;

import com.runescape.client.revised.annotation.AnnotationCodeType;
import com.runescape.client.revised.annotation.impl.FinishedAnnotation;

@FinishedAnnotation(getAnnotationType = AnnotationCodeType.FINISHED_CODE)
public interface Mission {

	public Object executeTask();
}