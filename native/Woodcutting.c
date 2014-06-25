#include <jni.h>
#include <stdio.h>
#include "com_runescape_revised_content_jni_WooducttingNative.h"

JNIEXPORT void JNICALL
Java_HelloWorld_displayHelloWorld(JNIEnv *env, jobject obj)
{
    printf("Woodcutting has started!\n");
    return;
}
