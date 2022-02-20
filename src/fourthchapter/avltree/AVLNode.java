package fourthchapter.avltree;

public class AVLNode {
    final static int HEIGHT_TOLERANCE = 1;
    private int value;
    private AVLNode left;
    private AVLNode right;
    private int height;


    /*
     * 叶子节点构造方法
     * */
    public AVLNode(int value) {
        this(value, null, null, 0);
    }

    public AVLNode(int value, AVLNode left, AVLNode right, int height) {
        this.value = value;
        this.left = left;
        this.right = right;
        this.height = height;
    }

    public AVLNode insert(AVLNode currentNode, int value) {
        if (currentNode == null) {
            currentNode = new AVLNode(value);
        } else {
            if (value < currentNode.value) {
                //二叉树特性 递归调用insert 向当前节点的左子树进行插入
                currentNode.left = insert(currentNode.left, value);
                //如果现节点 左子树 比 现节点 右子树 高度 高于 容忍度 1
                if (getHeight(currentNode.left) - getHeight(currentNode.right) > HEIGHT_TOLERANCE) {
                    if (value < currentNode.left.value) {    //左单旋转 插入在左子树 左单旋转
                        //单旋转
                        currentNode = singleRightRotation(currentNode);

                    } else if (value > currentNode.left.value) {  //插入在右子树   双旋转
                        currentNode = DoubleLeftRightRotation(currentNode);
                    }
                }
            }else if (value > currentNode.value){
                currentNode.right = insert(currentNode.right,value);
                if (getHeight(currentNode.right)-getHeight(currentNode.left) > HEIGHT_TOLERANCE ){
                    if (value > currentNode.right.value){
                        currentNode = DoubleRightLeftRotation(currentNode);
                    }else if (value < currentNode.right.value){
                        currentNode = DoubleRightLeftRotation(currentNode);
                    }
                }
            }
            updateHeight(currentNode);
        }
        return currentNode;
    }



    public AVLNode find(AVLNode node, int value) {
//        AVLNode node = this;
        //if(node == null) return null;
//        AVLNode nodefinded;

        if (node == null || node.value == value) {
            return node;
        } else if (node.value < value) {
            return find(node.right, value);
        } else {
            return find(node.left, value);
        }
    }

    private AVLNode delete(int value) {
        return delete(this, value);
    }

    private AVLNode delete(AVLNode node, int value) {
        if (node.value == value) {
            node = removeNode(node);
        } else {
            if (value < node.value) {
                node.left = delete(node.left, value);
                //删除之后 右子树高
                if (getHeight(node.right) - getHeight(node.left) > HEIGHT_TOLERANCE) {
                    if (node.right.left == null) {
                        node = singleLeftRotation(node);
                    } else {
                        node = DoubleRightLeftRotation(node);
                    }
                }
                //updateHeight(node);
                //return node;
            } else if (value > node.value) {
                node.right = delete(node.right, value);
                if (getHeight(node.left) - getHeight(node.right) > HEIGHT_TOLERANCE) {
                    if (node.left.right == null) {
                        node = singleRightRotation(node);
                    } else {
                        node = DoubleLeftRightRotation(node);
                    }
                }
            }
            updateHeight(node);
        }
        return node;
    }

    private AVLNode removeNode(AVLNode node)  {
        //删除叶子节点
        if (node.left == null && node.right == null) {
            node = null;
//            删除的节点只有左子树或者只有右子树
        } else if (node.left == null || node.right == null) {
            //如果只有右子树使用 右子树根节点代替原来节点
            if (node.left == null) {
                node = node.right;
            } else {
                node = node.left;
            }
            //既有右子树又有左子树
        } else {

            AVLNode leftChildMaxParent = getChildMax(node.left);
            AVLNode leftChildMax = leftChildMaxParent.right;
            if (leftChildMax == null) {
                leftChildMax = leftChildMaxParent;
                leftChildMax.right = node.right;
//                leftChildMax.right = node.right;
            } else {
                leftChildMax.left = node.left;
                leftChildMax.right = node.right;
                leftChildMaxParent.right = null;
            }
            node = leftChildMax;
        }
        return node;
    }

    /*
     * 获取左子树的最大
     * */
    private AVLNode getChildMax(AVLNode node) {
        if (node == null) return null;
        AVLNode parentNode = node;
        AVLNode childNode = parentNode.right;
        while (childNode != null) {
            if (childNode.right != null) {
                parentNode = childNode;
                childNode = childNode.right;
            } else {
                break;
            }
        }
        return parentNode;
    }

    private AVLNode reviseNode(AVLNode node){
        if (node == null){
            return null;
        }else{
            node.left = reviseNode(node.left);
            node.right = reviseNode(node.right);
            if (getHeight(node.left) - getHeight(node.right) > HEIGHT_TOLERANCE){
                if (node.left.right == null){
                    node = singleLeftRotation(node);
                }else{
                    node = DoubleLeftRightRotation(node);
                }
            }else if (getHeight(node.right)-getHeight(node.left) > HEIGHT_TOLERANCE){
                if (node.right.left == null){
                    node = singleRightRotation(node);
                }else {
                    node = DoubleRightLeftRotation(node);
                }
            }
            updateHeight(node);
            return node;
        }
    }
    private AVLNode DoubleRightLeftRotation(AVLNode A) {
        A.left = singleRightRotation(A.left);
        return singleLeftRotation(A);
    }

    private AVLNode DoubleLeftRightRotation(AVLNode A) {
        A.left = singleLeftRotation(A.left);
        return singleRightRotation(A);
    }



/*
    public AVLNode find(int value){
        AVLNode node = this;
        while(node!=null){
            if (node.value == value){
                return node;
            }else if (value < node.value){
                node = node.left;
            }else {
                node = node.right;
            }
        }
        return node;
    }
*/

    private void updateHeight(AVLNode currentNode) {
        currentNode.height = getHeight(currentNode);
    }

    private AVLNode singleRightRotation(AVLNode A) {
        AVLNode B = A.left;
        A.left = B.right;
        B.right = A;
        A.height = Math.max(getHeight(A.left), getHeight(A.right)) + 1;
        B.height = Math.max(getHeight(B.left), A.height) + 1;
        return B;
    }

    private AVLNode singleLeftRotation(AVLNode A) {
        AVLNode B = A.right;
        A.right = B.left;
        B.left = A;
        A.height = Math.max(getHeight(A.left), getHeight(A.right)) + 1;
        B.height = Math.max(getHeight(B.right), A.height) + 1;
        return B;
    }

    private int getHeight(AVLNode currentNode) {
        if (currentNode == null) {
            return -1;
        } else {
            int leftHeight = getHeight(currentNode.left);
            int rightHeight = getHeight(currentNode.right);
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }
    public void toString(AVLNode node){
        if (node == null){
            return ;
        }
        System.out.println(value+" ");
        toString(node.left);
        toString(node.right);
    }
}
