public class TwoDimensionalArray {
    public static void main(String[] args){
        
        //Creating a Data
        //Example 1 
        //String[][] clothColors = new String[2][3];

        //For First Row
        // clothColors[0][0] = "red";
        // clothColors[0][1] = "blue";
        // clothColors[0][2] = "green";

        //For Second Row
        // clothColors[1][0] = "orange";
        // clothColors[1][1] = "yellow";
        // clothColors[1][2] = "violet";

        //Example 2
        String[][] clothColors = {{"red", "blue", "green"}, 
                                {"orange", "yellow", "violet"}};

        //For Loop for the formatting eme
        for(int i = 0; i < clothColors.length; i++){
            for(int j = 0; j < clothColors[i].length; j++){
                System.out.printf("%-6s ", clothColors[i][j] );
            }
            System.out.println();
        }
    }
}
