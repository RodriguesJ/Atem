package com.runescape.client.revised.mission.impl;

import com.runescape.client.revised.annotation.AnnotationCodeType;
import com.runescape.client.revised.annotation.impl.EditAnnotation;
import com.runescape.client.revised.mission.Mission;

@EditAnnotation(getAnnotationType = AnnotationCodeType.EDIT_CODE)
public class EditMission implements Mission {

	@Override
	public Object executeTask() {
		return null;
	}
}