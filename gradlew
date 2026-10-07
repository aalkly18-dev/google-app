#!/usr/bin/env sh

if [ -n "$MSYSTEM" ]; then
    case "$MSYSTEM" in
        CYGWIN*|MINGW*|MSYS*)
            CYGWIN=true
            ;;
    esac
fi

app_home=$(dirname "$0")
[ -z "$app_home" ] && app_home=.

BASENAME=$(basename "$0")

default_jvm_opts=""
MAX_FD="maximum"

warn () {
    echo "$*"
}

die () {
    echo
    echo "$*"
    echo
    exit 1
}

cygwin=false
msys=false
darwin=false
nonstop=false
case "$(uname)" in
  CYGWIN* ) cygwin=true ;;
  Darwin* ) darwin=true ;;
  MINGW* | MSYS* ) msys=true ;;
  NONSTOP* ) nonstop=true ;;
esac

CLASSPATH=$app_home/gradle/wrapper/gradle-wrapper.jar

if [ -n "$JAVA_HOME" ] ; then
    if [ -x "$JAVA_HOME/jre/sh/java" ] ; then
        JAVACMD="$JAVA_HOME/jre/sh/java"
    else
        JAVACMD="$JAVA_HOME/bin/java"
    fi
    if [ ! -x "$JAVACMD" ] ; then
        die "ERROR: JAVA_HOME is set to an invalid directory: $JAVA_HOME"
    fi
else
    JAVACMD="java"
    which java >/dev/null 2>&1 || die "ERROR: JAVA_HOME is not set."
fi

exec "$JAVACMD" $DEFAULT_JVM_OPTS $JAVA_OPTS $GRADLE_OPTS "-Dorg.gradle.appname=$app_home/gradlew" -classpath "$CLASSPATH" org.gradle.wrapper.GradleWrapperMain "$@"
