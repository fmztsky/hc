APP_ABI := armeabi #all armeabi armeabi-v7a mips x86 
APP_STL := stlport_static
#NDK_TOOLCHAIN_VERSION=4.7 #ʹ��GCC4.7
#APP_STL := gnustl_static #GNU STL
#APP_CPPFLAGS := -fexceptions -frtti #�����쳣���ܣ�������ʱ����ʶ��
APP_CPPFLAGS +=-std=c++11 #����ʹ��c++11�ĺ����ȹ���
#APP_CPPFLAGS +=-fpermissive  #������Чʱ��ʾ���ɵı�����ʽ������û���õ��Ĵ������д���Ҳ����ͨ�����룻ʹ��GNU STLʱ���ô���std::string ���벻ͨ������