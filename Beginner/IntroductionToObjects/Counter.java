//Introduction to objects, very simple example

public class Counter {
    private int count;
    
    public Counter(int initialCount) {
        count = initialCount;
    }
        
    public Counter() {
        count = 0;
    }
        
    public void increment() {
        count += 1;
    }
        
    public void reset() {
        count = 0;
    }
        
    public int getValue() {
        return count;
    }
        
   public void decrease(int n) {
        count = count - n;
    }
}//END class Counter
