package com.beiyou;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.util.ReflectUtil;

import com.beiyou.annotation.DbType;
import com.beiyou.model.Bird;

import javax.lang.model.element.VariableElement;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;


public class App {

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException, InvocationTargetException {



        DbType annotation1 = AnnotationUtil.getAnnotation(Bird.class, DbType.class);

        Bird bird0 = new Bird();
        Bird bird1 = new Bird("小鸟", 10);
        Class<?> aClass = bird0.getClass();
        Class<?> birdClass = Bird.class;
        Class<?> clazz = Class.forName("com.beiyou.model.Bird");

         Object obj6 = clazz.newInstance();
        //字段
        Field[] declaredFields = clazz.getDeclaredFields();
        Field nameField = clazz.getDeclaredField("name");
        Map<String, Field> fieldMap = ReflectUtil.getFieldMap(clazz);
        Object obj = ReflectUtil.newInstance(clazz);
        //Field nameField = ReflectUtil.getField(class1, "name");
        ReflectUtil.setFieldValue(obj,"name","鸟1");
        ReflectUtil.setFieldValue(obj,"age",2);
        Object address = ReflectUtil.getFieldValue(obj, "address");
        ReflectUtil.setFieldValue(obj,"address","洛阳");

        //方法
        Method[] methods = ReflectUtil.getMethods(clazz);
        Method fly = ReflectUtil.getMethodByName(clazz, "fly");
        Object invoke = ReflectUtil.invoke(obj, "fly2", "蓝色",18);

        //获取静态方法
        Method staticFly3 = ReflectUtil.getMethodByName(clazz, "staticFly3");

        Object 红色 = ReflectUtil.invoke(obj, staticFly3, "红色", 10);
        Object 黑色 = ReflectUtil.invokeStatic(staticFly3, "黑色", 11);

        Constructor<?>[] constructors = ReflectUtil.getConstructors(clazz);
        Constructor<?> constructor0 = ReflectUtil.getConstructor(clazz);
        Constructor<?> constructor1 = ReflectUtil.getConstructor(clazz,String.class, Integer.class);

        Object o = constructor0.newInstance();
        Object 小灰 = constructor1.newInstance("小灰", 10);

        boolean b = AnnotationUtil.hasAnnotation(clazz, DbType.class);
        DbType annotation = AnnotationUtil.getAnnotation(clazz, DbType.class);
        String value = annotation.value();
        int type = annotation.type();

    }

}
