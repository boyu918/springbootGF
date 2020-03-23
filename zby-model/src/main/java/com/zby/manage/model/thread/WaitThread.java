package com.zby.manage.model.thread;

/**
 * Description:
 * User: 小卡掌柜
 * Date: 2020-03-18
 * Time: 2:15 PM
 */
public class WaitThread implements Runnable{
    private String outPut;
    private Object preObj;
    private Object selfObj;
    public WaitThread(String outPut,Object preObj,Object selfObj){
        this.outPut = outPut;
        this.preObj = preObj;
        this.selfObj = selfObj;
    }

    @Override
    public void run() {
        int i = 10;
        while (i > 0){
            synchronized (preObj){
                synchronized (selfObj){
                    System.out.println(outPut);
                    i--;
                    selfObj.notify();
                }
                try {
                    preObj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
