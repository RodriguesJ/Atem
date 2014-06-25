/*
 * com_runescape_revised_jni_JNI.cpp
 *
 *  Created on: Dec 12, 2013
 *      Author: josh
 */
#include  <iostream>

#include "com_runescape_revised_jni_JNI.h"

using namespace std;

JNIEXPORT void JNICALL Java_com_runescape_revised_jni_JNI_helloWorld
    (JNIEnv *env, jobject jthis, jstring data) {

    jboolean iscopy;
    const char *charData = env -> GetStringUTFChars(data, &iscopy);
    cout << "Hello " << charData << endl;
    env -> ReleaseStringUTFChars(data, charData);
}
