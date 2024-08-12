package demoworld.model;

/**
 * Represents a game Value that has a minimum amount, a maximum amount,
 * and a current amount. If the current amount would ever go past the maximum
 * it should be set back to the maximum value. If the current amount would ever go below the minimum
 * it should be set back to the minimum value
 */
public class Value {

    private int min;
    private int max;
    private int current;

    /**
     * Constructs a new value with values given for the initial boundaries and its initial current state.
     * Boundaries are enforced on all current values. If the maximum is below the minimum it should be set
     * to match minimum. If the current is below the minimum it should be set to match minimum.
     * If the current is above new maximum it should be set to match maximum
     * @param min the initial minimum amount
     * @param max the initial maximum amount
     * @param current the initial current amount
     */
    public Value(int min,
                 int max,
                 int current){
        this.min = min;
        this.max = max < min ? min : max;
        this.current = current < min ? min : Math.min(current, this.max);
    }

    /**
     * Gets the values minimum amount
     * @return the minimum amount
     */
    public int min(){
        return min;
    }

    /**
     * Gets the values maximum amount
     * @return the maximum amount
     */
    public int max(){
        return max;
    }

    /**
     * Gets the values current amount
     * @return the current amount
     */
    public int current(){
        return current;
    }

    /**
     * Set the values maximum amount to a new maximum. Then enforces the boundaries on any current
     * amounts held by the value. If the new maximum is below the minimum it should be set to match
     * that minimum. If the current is above the new maximum it should be set to match that maximum
     * @param min the value the minimum amount is being set to
     */
    public void setMin(int min){
        this.min = min;
        if (this.max < min) {
            this.max = min;
        }
        if (this.current < min) {
            this.current = min;
        }
    }

    /**
     * Set the values maximum amount to a new maximum. Then enforces the boundaries on any current amounts
     * held by the value. If the new maximum is below the minimum it should be set to match that minimum.
     * If the current is above the new maximum it should be set to match that maximum
     * @param max the value the maximum amount is being set to
     */
    public void setMax(int max){
        this.max = max < this.min ? this.min : max;
        if (this.current > this.max) {
            this.current = this.max;
        }
    }

    /**
     * Set the values current amount to a new current. Then ensures that the new current will fit into
     * the boundaries given. If the new current is below the minimum it should be set to match that minimum.
     * If the new current is above the maximum it should be set to match that maximum
     * @param value the value the current amount is being set to
     */
    public void setCurrent(int value){
        if (value < this.min) {
            this.current = this.min;
        } else if (value > this.max) {
            this.current = this.max;
        } else {
            this.current = value;
        }
    }

    /**
     * Add an amount to the values current amount. Then ensures that the new current will fit into the
     * boundaries given. If the new current is below the minimum it should be set to match that minimum.
     * If the new current is above the maximum it should be set to match that maximum
     * @param value the value being added to the current amount
     */
    public void addToCurrent(int value){
        setCurrent(this.current + value);
    }

    /**
     * Add an amount to the values current max amount. Then ensures that the new max will fit into the
     * boundaries given. If the new maximum is below the minimum it should be set to match that minimum.
     * If the current is above the new maximum it should be set to match that maximum
     * @param value amount being added to the values max amount
     */
    public void addToMax(int value){
        setMax(this.max + value);
    }
}
