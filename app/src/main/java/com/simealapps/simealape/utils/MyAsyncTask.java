package com.simealapps.simealape.utils;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class MyAsyncTask<Params, Progress, Result> {
    private static final int CORE_POOL_SIZE = 1;
    private static final int KEEP_ALIVE = 10;
    private static final String LOG_TAG = "MyAsyncTask";
    private static final int MAXIMUM_POOL_SIZE = 10;
    private static final int MESSAGE_POST_CANCEL = 3;
    private static final int MESSAGE_POST_PROGRESS = 2;
    private static final int MESSAGE_POST_RESULT = 1;
    private static int[] myAsynStatus = null;

    public static MyAsyncTaskResult result;
    private static ThreadPoolExecutor sExecutor;

    public static InternalHandler sHandler;
    private static ThreadFactory sThreadFactory;
    private static BlockingQueue<Runnable> sWorkQueue;
    private FutureTask<Result> mFuture;
    private volatile Status mStatus = Status.PENDING;
    private WorkerRunnable<Params, Result> mWorker;
    public Result result2;

    static class C05141 implements ThreadFactory {
        AtomicInteger mCount;

        C05141() {
        }

        public Thread newThread(@NonNull Runnable runnable) {
            this.mCount = new AtomicInteger(1);
            StringBuilder sb = new StringBuilder();
            sb.append("MyAsyncTask #");
            sb.append(this.mCount.getAndIncrement());
            return new Thread(runnable, sb.toString());
        }
    }

    class C05152 extends WorkerRunnable<Params, Result> {
        C05152() {
            super();
        }

        public Result call() throws Exception {
           // Process.setThreadPriority(10);
            return MyAsyncTask.this.doInBackground(this.mParams);
        }
    }

    private static class InternalHandler extends Handler {
        private InternalHandler() {
        }

        public void handleMessage(Message msg) {
            MyAsyncTask.result = (MyAsyncTaskResult) msg.obj;
            int i = msg.what;
            if (i == 1) {
                MyAsyncTask.result.mTask.finish(MyAsyncTask.result.mData[0]);
            } else if (!(i == 2 || i == 3)) {
                return;
            }
            MyAsyncTask.result.mTask.onProgressUpdate(MyAsyncTask.result.mData);
            MyAsyncTask.result.mTask.onCancelled();
        }
    }

    private static class MyAsyncTaskResult<Data> {
        final Data[] mData;
        final MyAsyncTask mTask;

        MyAsyncTaskResult(MyAsyncTask task, Data... data) {
            this.mTask = task;
            this.mData = data;
        }
    }

    public enum Status {
        PENDING,
        RUNNING,
        FINISHED
    }

    private static abstract class WorkerRunnable<Params, Result> implements Callable<Result> {
        Params[] mParams;

        private WorkerRunnable() {
        }
    }


    public abstract Result doInBackground(Params... paramsArr);

    static int[] MyCamiAsyncTaskStatus() {
        int[] iArr = myAsynStatus;
        if (iArr == null) {
            iArr = new int[Status.values().length];
            try {
                iArr[Status.FINISHED.ordinal()] = 3;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[Status.PENDING.ordinal()] = 1;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[Status.RUNNING.ordinal()] = 2;
            } catch (NoSuchFieldError e3) {
            }
            myAsynStatus = iArr;
        }
        return iArr;
    }

    public final Status getStatus() {
        return this.mStatus;
    }


    public void onPreExecute() {
    }


    public void onPostExecute(Result result3) {
    }


    public void onProgressUpdate(Progress... progressArr) {
    }


    public void onCancelled() {
    }

    public final boolean isCancelled() {
        return this.mFuture.isCancelled();
    }

    public final boolean cancel(boolean mayInterruptIfRunning) {
        return this.mFuture.cancel(mayInterruptIfRunning);
    }

    public final Result get() throws InterruptedException, ExecutionException {
        return this.mFuture.get();
    }

    public final Result get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return this.mFuture.get(timeout, unit);
    }

    public final MyAsyncTask<Params, Progress, Result> execute(Params... params) {
        if (this.mStatus != Status.PENDING) {
            int i = MyCamiAsyncTaskStatus()[this.mStatus.ordinal()];
            if (i == 2) {
                throw new IllegalStateException("Cannot execute task: the task is already running.");
            } else if (i == 3) {
                throw new IllegalStateException("Cannot execute task: the task has already been executed (a task can be executed only once)");
            }
        }
        sWorkQueue = new LinkedBlockingQueue(10);
        sThreadFactory = new C05141();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 10, 10, TimeUnit.SECONDS, sWorkQueue, sThreadFactory);
        sExecutor = threadPoolExecutor;
        sHandler = new InternalHandler();
        this.mWorker = new C05152();
        this.mFuture = new FutureTask<Result>(this.mWorker) {

            public void done() {
                String str = "An error occured while executing doInBackground()";
                try {
                    MyAsyncTask.this.result2 = get();
                } catch (InterruptedException e) {
                    Log.w(MyAsyncTask.LOG_TAG, e);
                } catch (ExecutionException e2) {
                    throw new RuntimeException(str, e2.getCause());
                } catch (CancellationException e3) {
                    MyAsyncTask.sHandler.obtainMessage(3, new MyAsyncTaskResult(MyAsyncTask.this, null)).sendToTarget();
                    return;
                } catch (Throwable t) {
                    new RuntimeException(str, t);
                }


                MyAsyncTask.sHandler.obtainMessage(1, new MyAsyncTaskResult(MyAsyncTask.this, result2)).sendToTarget();
            }
        };
        this.mStatus = Status.RUNNING;
        onPreExecute();
        this.mWorker.mParams = params;
        sExecutor.execute(this.mFuture);
        return this;
    }


    public final void publishProgress(Progress... values) {
        sHandler.obtainMessage(2, new MyAsyncTaskResult(this, values)).sendToTarget();
    }


    public void finish(Result result3) {
        onPostExecute(result3);
        this.mStatus = Status.FINISHED;
    }
}
