public interface Queue<E> {
	/**
     * Returns true if the Queue is currently empty
     * @return 
     *		true if the queue is empty 
     */
    public boolean isEmpty();

	/**
     * Add the reference to E at the rear of
     * the queue. Assumes s is not null
     * @param s
     *		The (non null) reference to the new element
     */
    public void enqueue(E o);

	/**
     * Removes the reference to E at the front of
     * the queue. Assumes the queue is not empty
     * @return 
     *		The reference to removed E
     */
    public E dequeue();
}
