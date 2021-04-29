import java.util.Arrays;

public class Judge {

    // the variable for the number of people in the town
    int N;

    // the two-dimensional trust array
    // the people in this array trust each other
    // person labeled a trust the person labeled b
    int [][] trust;

    // constructor
    public Judge() {

    }

    public int findJudge (int N, int [][] trust) {

        // check to make sure size of N is between 1 and 1000
        if (N < 0 || N > 1000) {
            System.out.println("Size of N must be between 1 and 1000!");
            return -1;
        }

        // Adjacency matrix
        int[][] matrix= new int[N+1][N+1]; // use +1 to avoid dealing with zero base, ignore the 0 rows/columns.

        //if element is [a,b] it means there is an edge (trust) between a->b in the graph so set adj matrix to 1
        for (int i =0 ; i <trust.length;i++) {
            matrix[trust[i][0]][trust[i][1]] = 1;
        }

        // set judge has not yet been found, set judgeChoice to -1
        int judgeChoice =-1;


        //candidate/judge row should have all columns 0 because judge doesn't trust anyone.
        for (int i = 1;i <=N; i++) {
            judgeChoice = i;
            for (int j = 1;j <= N; j++) {
                if (matrix[i][j]==1) { // if there is trust, this is not a judge,set to -1 and break both loops.
                    judgeChoice = -1;
                    break;
                }
            }
            if (judgeChoice!=-1) {break;}
        }


        //a judge would be trusted by everyone so all judge columns should be 1 except his own.
        if (judgeChoice > 0){
            for (int i =1; i<N; i++) {
                if (i != judgeChoice && matrix[i][judgeChoice]==0) { //check that no columns except own are 0.
                    judgeChoice = -1;
                    break;
                }
            }
        }

        return judgeChoice;
    }



    public static void main(String[] args) {

        Judge J1 = new Judge();

        // testing examples from handout

        int[][] trust1 = new int[][]{{1, 3}, {1, 4}, {2, 3}, {2, 4}, {4, 3}};
        System.out.println(Arrays.deepToString(trust1));
        int result1 = J1.findJudge(4, trust1);
        System.out.println("Who is the judge? " + result1);

        int[][] trust2 = new int[][]{{1, 2}, {2, 3}};
        System.out.println(Arrays.deepToString(trust2));
        int result2 = J1.findJudge(3, trust2);
        System.out.println("Who is the judge? " + result2);

        int[][] trust3 = new int[][]{{1, 3}, {2, 3},{3, 1}};
        System.out.println(Arrays.deepToString(trust3));
        int result3 = J1.findJudge(3, trust2);
        System.out.println("Who is the judge? " + result3);

        int[][] trust4 = new int[][]{{1, 3}, {2, 3}};
        System.out.println(Arrays.deepToString(trust4));
        int result4 = J1.findJudge(3, trust4);
        System.out.println("Who is the judge? " + result4);

        int[][] trust5 = new int[][]{{1, 2}};
        System.out.println(Arrays.deepToString(trust5));
        int result5 = J1.findJudge(3, trust5);
        System.out.println("Who is the judge? " + result5);



    }



}
