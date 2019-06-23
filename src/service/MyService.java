package service;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class MyService {

	private CyclicBarrier cbRef;

	public MyService(CyclicBarrier cbRef) {
		super();
		this.cbRef = cbRef;
	}

	private void beginRun(int count) {
		try {
			System.out.println(Thread.currentThread().getName()
					+ " 到了 在等待其它人都到了开始起跑");
			cbRef.await();
			System.out.println(Thread.currentThread().getName() +"都到了，开始跑!");
			System.out.println(Thread.currentThread().getName() + " 到达终点，并结束第"
					+ count + "赛段");
		} catch (InterruptedException e) {
			System.out.println(Thread.currentThread().getName() +"进入了InterruptedException e " + cbRef.isBroken());
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			System.out.println(Thread.currentThread().getName() +"进入了BrokenBarrierException e "
					+ cbRef.isBroken());
			e.printStackTrace();
		}

	}

	public void testA() {
		// 比赛1个赛段
		for (int i = 0; i < 1; i++) {
			beginRun(i + 1);
		}
	}
}
