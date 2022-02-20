package heap;// BinomialQueue class
//
// CONSTRUCTION: with no parameters or a single item
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// Comparable deleteMin( )--> Return and remove smallest item
// Comparable findMin( )  --> Return smallest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// vod merge( rhs )       --> Absord rhs into this heap
// ******************ERRORS********************************
// Throws UnderflowException as appropriate

/**
 * Implements a binomial queue.
 * Note that all "matching" is based on the compareTo method.
 * @author Mark Allen Weiss
 */
public final class BinomialQueue<AnyType extends Comparable<? super AnyType>>
{
    /**
     * Construct the binomial queue.
     */
    public BinomialQueue( )
    {
        theTrees = new BinNode[ DEFAULT_TREES ];
        makeEmpty( );
    }

    /**
     * Construct with a single item.
     */
    public BinomialQueue( AnyType item )
    {
        currentSize = 1;
        theTrees = new BinNode[ 1 ];
        theTrees[ 0 ] = new BinNode<>( item, null, null );
    }


    private void expandTheTrees( int newNumTrees )
    {
        BinNode<AnyType> [ ] old = theTrees;
        int oldNumTrees = theTrees.length;

        theTrees = new BinNode[ newNumTrees ];
        for( int i = 0; i < Math.min( oldNumTrees, newNumTrees ); i++ )
            theTrees[ i ] = old[ i ];
        for( int i = oldNumTrees; i < newNumTrees; i++ )
            theTrees[ i ] = null;
    }

    /**
     * Merge rhs into the priority queue.
     * rhs becomes empty. rhs must be different from this.
     * @param rhs the other binomial queue.
     */
    public void merge( BinomialQueue<AnyType> rhs )
    {
        if( this == rhs )    // Avoid aliasing problems
            return;

        currentSize += rhs.currentSize;

        if( currentSize > capacity( ) )
        {
            int newNumTrees = Math.max( theTrees.length, rhs.theTrees.length ) + 1;
            expandTheTrees( newNumTrees );
        }

        BinNode<AnyType> carry = null;
        for( int i = 0, j = 1; j <= currentSize; i++, j *= 2 )
        {
            BinNode<AnyType> t1 = theTrees[ i ];
            BinNode<AnyType> t2 = i < rhs.theTrees.length ? rhs.theTrees[ i ] : null;

            int whichCase = t1 == null ? 0 : 1;
            whichCase += t2 == null ? 0 : 2;
            whichCase += carry == null ? 0 : 4;

            switch( whichCase )
            {
              case 0: /* No trees */
              case 1: /* Only this */
                break;
              case 2: /* Only rhs */
                theTrees[ i ] = t2;
                rhs.theTrees[ i ] = null;
                break;
              case 4: /* Only carry */
                theTrees[ i ] = carry;
                carry = null;
                break;
              case 3: /* this and rhs */
                carry = combineTrees( t1, t2 );
                theTrees[ i ] = rhs.theTrees[ i ] = null;
                break;
              case 5: /* this and carry */
                carry = combineTrees( t1, carry );
                theTrees[ i ] = null;
                break;
              case 6: /* rhs and carry */
                carry = combineTrees( t2, carry );
                rhs.theTrees[ i ] = null;
                break;
              case 7: /* All three */
                theTrees[ i ] = carry;
                carry = combineTrees( t1, t2 );
                rhs.theTrees[ i ] = null;
                break;
            }
        }

        for( int k = 0; k < rhs.theTrees.length; k++ )
            rhs.theTrees[ k ] = null;
        rhs.currentSize = 0;
    }

    /**
     * Return the result of merging equal-sized t1 and t2.
     */
    private BinNode<AnyType> combineTrees( BinNode<AnyType> t1, BinNode<AnyType> t2 )
    {
        if( t1.element.compareTo( t2.element ) > 0 )
            return combineTrees( t2, t1 );
        t2.nextSibling = t1.leftChild;
        t1.leftChild = t2;
        return t1;
    }

    /**
     * Insert into the priority queue, maintaining heap order.
     * This implementation is not optimized for O(1) performance.
     * @param x the item to insert.
     */
    public void insert( AnyType x )
    {
        merge( new BinomialQueue<>( x ) );
    }

    /**
     * Find the smallest item in the priority queue.
     * @return the smallest item, or throw UnderflowException if empty.
     */
    public AnyType findMin( ) throws Exception {
        if( isEmpty( ) )
            throw new Exception("Error");

        return theTrees[ findMinIndex( ) ].element;
    }


    /**
     * Find index of tree containing the smallest item in the priority queue.
     * The priority queue must not be empty.
     * @return the index of tree containing the smallest item.
     */
    private int findMinIndex( )
    {
        int i;
        int minIndex;

        for( i = 0; theTrees[ i ] == null; i++ )
            ;

        for( minIndex = i; i < theTrees.length; i++ )
            if( theTrees[ i ] != null &&
                theTrees[ i ].element.compareTo( theTrees[ minIndex ].element ) < 0 )
                minIndex = i;

        return minIndex;
    }

    /**
     * Remove the smallest item from the priority queue.
     * @return the smallest item, or throw UnderflowException if empty.
     */
    public AnyType deleteMin( ) throws Exception {
        if( isEmpty( ) )
            throw new Exception("Error");

        int minIndex = findMinIndex( );
        AnyType minItem = theTrees[ minIndex ].element;

        BinNode<AnyType> deletedTree = theTrees[ minIndex ].leftChild;//根节点下面的其他的子节点   第一个子节点 就一个的

        // Construct H'' 构造 h''
        BinomialQueue<AnyType> deletedQueue = new BinomialQueue<>( );
        deletedQueue.expandTheTrees( minIndex );//拆开之后 刚好 形成的小树个数等于 minIndex

        deletedQueue.currentSize = ( 1 << minIndex ) - 1;//根据二进制计算出所有元素的个数
        for( int j = minIndex - 1; j >= 0; j-- )
        {
            deletedQueue.theTrees[ j ] = deletedTree;
            deletedTree = deletedTree.nextSibling;
            deletedQueue.theTrees[ j ].nextSibling = null;
        }

        // Construct H'
        theTrees[ minIndex ] = null;
        currentSize -= deletedQueue.currentSize + 1;
        //将删除根节点的  形成的小树 queue  与原来的合并
        merge( deletedQueue );

        return minItem;
    }

    /**
     * Test if the priority queue is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty( )
    {
        return currentSize == 0;
    }

    /**
     * Make the priority queue logically empty.
     */
    public void makeEmpty( )
    {
        currentSize = 0;
        for( int i = 0; i < theTrees.length; i++ )
            theTrees[ i ] = null;
    }

    private static class BinNode<AnyType>
    {
            // Constructors
        BinNode( AnyType theElement )
        {
            this( theElement, null, null );
        }

        BinNode( AnyType theElement, BinNode<AnyType> lt, BinNode<AnyType> nt )
        {
            element     = theElement;
            leftChild   = lt;
            nextSibling = nt;
        }

        AnyType          element;     // The data in the node
        BinNode<AnyType> leftChild;   // Left child
        BinNode<AnyType> nextSibling; // Right child
    }

    private static final int DEFAULT_TREES = 1;

    private int currentSize;                // # items in priority queue
    private BinNode<AnyType> [ ] theTrees;  // An array of tree roots


    /**
     * Return the capacity. 容量
     */
    private int capacity( )
    {
        return ( 1 << theTrees.length ) - 1;
    }

    public static void main( String [ ] args ) throws Exception {
        int numItems = 10000;
        BinomialQueue<Integer> h  = new BinomialQueue<>( );
        BinomialQueue<Integer> h1 = new BinomialQueue<>( );
        int i = 37;
        int n = 0;
        int m = 0;
        System.out.println( "Starting check." );

        for( i = 37; i != 0; i = ( i + 37 ) % numItems ) {
            if (i % 2 == 0) {
                h1.insert(i);
                System.out.println(i);
                m++;
            } else {
                h.insert(i);
                System.out.println("this: " + i);
            }
            n++;
        }
        System.out.println(n);
        System.out.println(m);
        System.out.println(h1.currentSize);
        System.out.println(h1.theTrees.length);

        System.out.println(h.theTrees.length);
        h.merge( h1 );
        System.out.println(h.theTrees.length);
        System.out.println(h);
//        for( i = 1; i < numItems; i++ ){
//            System.out.println(h.deleteMin());
//        }
            /*if( h.deleteMin( ) != i ) {
                System.out.println(h1.deleteMin());
                System.out.println("Oops! " + i);
            }*/
        System.out.println( "Check done." );
    }
}
