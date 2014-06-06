LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := Music
LOCAL_SRC_FILES := Music.cpp

include $(BUILD_SHARED_LIBRARY)
