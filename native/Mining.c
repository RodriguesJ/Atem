#include <jni.h>
#include <stdio.h>
#include "com_runescape_revised_content_jni_MiningNative.h"

JNIEXPORT void JNICALL
Java_HelloWorld_displayHelloWorld(JNIEnv *env, jobject obj)
{
    printf("Mining has started!\n");
    return;
}
