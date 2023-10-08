package chap9.chain_of_responsibility;

public abstract class ProcessingObject<T> {

    protected ProcessingObject<T> successor;

    public void setSuccessor(ProcessingObject<T> successor) {
        this.successor = successor;
    }

    public T handle(T input) {
        T r = handleWork(input);
        if (successor != null) {
            return successor.handle(r);     // 후임자가 있으면 현재 객체가 처리한 작업 결과를 후임자 객체에게 넘긴다.
        }
        return r;                           // 후임자가 없으면 현재 객체가 처리한 작업 결과를 반환한다.
    }

    abstract protected T handleWork(T input);       // 실제 작업 처리
}
