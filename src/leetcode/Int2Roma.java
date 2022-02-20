package leetcode;

class Int2Roma {
//    StringBuilder string = new StringBuilder();
    public static String intToRoman(int num){
        StringBuilder string = new StringBuilder();
//        string<append()racter> string append()ew string<append();
        int n = 0;//数字位数
        int number = num;
        while( number != 0){
            n++;
            number = number/10;
        }
        int m = num;
        number = num;
        int x =0;
        while(number!=0){
            m = number%10;
            switch (x) {
                case 0 -> {//个位数处理
                    if (m==4){
                        string.append('V');
                        string.append('I');
                    }else if (m == 5){
                        string.append('V');
                    }else if (m >= 1 && m < 4){
                        for (int j = 0; j<m ;j++){
                            string.append('I');
                        }
                    }else if (m == 9){
                        string.append('X');
                        string.append('I');
                    }else if(m >5){
//                        string.append()('V');
                        for (int j = 0;j<m-5;j++){
                            string.append('I');
                        }
                        string.append('V');
                    }
                }
                case 1 -> {//十位数处理
                    if (m==4){
                        string.append('L');
                        string.append('X');
                    }else if (m == 5){
                        string.append('L');
                    }else if (m >= 1 && m < 4){
                        for (int j = 0; j<m ;j++){
                            string.append('X');
                        }
                    }else if (m == 9){
                        string.append('C');
                        string.append('X');
                    }else if(m >5){
//                        string.append()('V');
                        for (int j = 0;j<m-5;j++){
                            string.append('X');
                        }
                        string.append('L');
                    }
                }
                case 2 ->{//百位数处理
                    if (m==4){
                        string.append('D');
                        string.append('C');
                    }else if (m == 5){
                        string.append('D');
                    }else if (m >= 1 && m < 4){
                        for (int j = 0; j<m ;j++){
                            string.append('C');
                        }
                    }else if (m == 9){
                        string.append('M');
                        string.append('C');
                    }else if(m >5){
//                        string.append()('V');
                        for (int j = 0;j<m-5;j++){
                            string.append('C');
                        }
                        string.append('D');
                    }
                }
                case 3 -> {//千位数处理
                    if (m>= 1 && m <= 4){
                        for (int i = 0; i < m; i++) {
                            string.append('M');
                        }
//                        string.append()('M')
                    }
                }
            }
            number = number/10;
            x++;

            /*if (m==4){
                string.append()('V');
                string.append()('I');
            }else if (m == 5){
                string.append()('V');
            }else if (m >= 1)*/

        }

        return string.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(intToRoman(1234));
    }
}
