package com.example.francis.examinationsystem.util;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * rxbus类似于eventbus
 * 用法：
 * 需先定义一个event类来确定接收的对象（RegisterEvent）
 * 注册：
 *  RxBus.getDefault().post(new RegisterEvent(etRegisterLoginName.getText().toString().trim()));
 * 接收：
 * RxBus.getDefault().toObservable(RegisterEvent.class)
 *  .subscribe(new Action1<RegisterEvent>() {
 *   @Override
 *   public void call(RegisterEvent registerEvent) {
 *       etLoginName.setText(registerEvent.getLoginName());
 *       }
 *   }));
 * Created by wzn on 2017/1/14.
 */

public class RxBus {
    private static volatile RxBus defaultInstance;

    private final Subject<Object, Object> bus;

    // PublishSubject只会把在订阅发生的时间点之后来自原始Observable的数据发射给观察者
    public RxBus() {
        bus = new SerializedSubject<>(PublishSubject.create());
    }

    // 单例RxBus
    public static RxBus getDefault() {
        if (defaultInstance == null) {
            synchronized (RxBus.class) {
                if (defaultInstance == null) {
                    defaultInstance = new RxBus();
                }
            }
        }
        return defaultInstance;
    }

    // 发送一个新的事件
    public void post(Object o) {
        bus.onNext(o);
    }

    // 根据传递的 eventType 类型返回特定类型(eventType)的 被观察者
    public <T> Observable<T> toObservable(Class<T> eventType) {
        return bus.ofType(eventType);
    }
}
