package board;

public class MinimaxThread1 extends AI implements Runnable {
    private boolean isWhite;
    private int depth;
    private double alpha;
    private double beta;
    double score;
    private volatile boolean finish;
    public MinimaxThread1(boolean isWhite, int depth, double alpha, double beta, BoardControl board) {
        super(board);
        this.isWhite = isWhite;
        this.depth = depth;
        this.alpha = alpha;
        this.beta = beta;
    }

    @Override
    public void run() {
        score = minimax(isWhite, depth, alpha, beta);
//        finish = true;
        synchronized (this){
            //Ends the waiting state for a thread
            this.notify();
        }
    }
    public double getScore() throws InterruptedException {
        //Makes sure resource is used by one thread and when finished it can be used by another thread
        synchronized (this) {
            //Used to put into waiting state until it gets a notify method to run
            if(!finish) this.wait();
        }
        return score;
    }
}
