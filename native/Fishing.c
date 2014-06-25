#include <jni.h>
#include <stdio.h>
#include "com_runescape_revised_content_jni_FishingNative.h"

JNIEXPORT void JNICALL
Java_HelloWorld_displayHelloWorld(JNIEnv *env, jobject obj)
{
    printf("Fishing has started!\n");
    return;
}
