#include <jni.h>
#include <string>
using namespace std;
extern "C"
JNIEXPORT jstring JNICALL
Java_com_acejob_acejob_login_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    string hello = "Hello how are you doing";
    return env->NewStringUTF(hello.c_str());
}
