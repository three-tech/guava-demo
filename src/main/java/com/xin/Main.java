package com.xin;

/**
 * @author three
 * @since 2019/6/4 17:04
 * <p>
 *
 * </p>
 */
public class Main {
	private static ThreadLocal<Boolean> b1 = new ThreadLocal<>();
	private static ThreadLocal<Boolean> i1 = new ThreadLocal<>();

	public static void main(String[] args) {
		// System.out.println(Sub.B);
		System.out.println(b1);
		System.out.println(i1);
	}

	static int inc() {
		Integer x;
		try {
			x = 1;
			return x;
		} catch (Exception e) {
			x = 2;
			return x;
		} finally {
			x = 3;
		}
	}

	static class Parent {
		public static int A = 1;

		static {
			A = 2;
		}
	}

	static class Sub extends Parent {
		public static int B = A;
	}

	static class DeadLoopClass {
		static {
			if (true) {
				while (true) {
				}
			}
		}
	}

	static class ThisEscape {
		//final常量会保证在构造器内完成初始化（但是仅限于未发生this逃逸的情况下，具体可以看多线程对final保证可见性的实现）
		final int i;
		//尽管实例变量有初始值，但是还实例化完成
		int j = 0;
		static ThisEscape obj;

		public ThisEscape() {
			i = 1;
			j = 1;
			//将this逃逸抛出给线程B
			obj = new ThisEscape();
		}

		public static void main(String[] args) {
			//线程A：模拟构造器中this逃逸,将未构造完全对象引用抛出
        /*Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                //obj = new ThisEscape();
            }
        });*/
			//线程B：读取对象引用，访问i/j变量
			Thread threadB = new Thread(() -> {
				//可能会发生初始化失败的情况解释：实例变量i的初始化被重排序到构造器外，此时1还未被初始化
				ThisEscape objB = obj;
				try {
					System.out.println(objB.j);
				} catch (NullPointerException e) {
					System.out.println("发生空指针错误：普通变量j未被初始化");
				}
				try {
					System.out.println(objB.i);
				} catch (NullPointerException e) {
					System.out.println("发生空指针错误：final变量i未被初始化");
				}

			});
			//threadA.start();
			threadB.start();
		}
	}
}
