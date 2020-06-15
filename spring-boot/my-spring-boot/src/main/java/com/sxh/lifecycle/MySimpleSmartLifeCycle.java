package com.sxh.lifecycle;

import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

/**
 * 自定义生命周期处理器SmartLifecycle测试
 * @author sxh
 * @date 2020/6/11
 */
@Component
public class MySimpleSmartLifeCycle implements SmartLifecycle {
    private boolean isRunning = false;
    
    @Override
    public void start() {
        System.out.println("MySimpleSmartLifeCycle -> start()");

        isRunning = true;
    }

    /**
     * SmartLifecycle子类的才有的方法，当isRunning方法返回true时，该方法才会被调用。
     */
    @Override
    public void stop(Runnable callback) {
        System.out.println("MySimpleSmartLifeCycle -> stop()");

        // 如果你让isRunning返回true，需要执行stop这个方法，那么就不要忘记调用callback.run()。
        // 否则在你程序退出时，Spring的DefaultLifecycleProcessor会认为你这个TestSmartLifecycle没有stop完成，程序会一直卡着结束不了，等待一定时间（默认超时时间30秒）后才会自动结束。
        callback.run();

        isRunning = false;
    }

    /**
     * 接口Lifecycle的子类的方法，只有非SmartLifecycle的子类才会执行该方法。<br/>
     * 1. 该方法只对直接实现接口Lifecycle的类才起作用，对实现SmartLifecycle接口的类无效。<br/>
     * 2. 方法stop()和方法stop(Runnable callback)的区别只在于，后者是SmartLifecycle子类的专属。
     */
    @Override
    public void stop() {
        System.out.println("MySimpleSmartLifeCycle -> stop()");
        
        isRunning = false;
    }

    /**
     * 1. 只有该方法返回false时，start方法才会被执行。
     * 2. 只有该方法返回true时，stop(Runnable callback)或stop()方法才会被执行。
     */
    @Override
    public boolean isRunning() {
        return isRunning;
    }

    /**
     * 排序标识，如果程序中有多个SmartLifecycle的类，执行顺序就根据这个标识进行确定
     * @return
     */
    @Override
    public int getPhase() {
        return 0;
    }

    /**
     * 该方法的返回值决定了是否执行start()方法，只有为true时才执行
     * @return
     */
    @Override
    public boolean isAutoStartup() {
        return true;
    }
}
