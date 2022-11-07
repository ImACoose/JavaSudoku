import java.util.*;

public class Main {
    /**
     * @param args
     */

    // checks whether the given number is in the arraylist
    public static boolean IsMatching(ArrayList<Integer> numberList, int number){
        boolean matching = false;

        // iterate over the arraylist to check if the randomNumber matches anything in the list
        for (int j = 0; j < numberList.size(); j++){
            if (numberList.get(j) == number){
                matching = true;
            }
        }

        return matching;
    }

    public static boolean IsMatchingWithColumns(ArrayList<Integer> numberList, ArrayList<Integer> columnList, int number){
        boolean matching = false;

        // iterate over the arraylist to check if the randomNumber matches anything in the list
        for (int j = 0; j < numberList.size(); j++){
            if (numberList.get(j) == number){
                matching = true;
            }
        }

        for (int j = 0; j < columnList.size(); j++){
            if (columnList.get(j) == number){
                matching = true;
            }
        }

        return matching;
    }

    

    public static int FindMultiArrayMatch(int[][] range, int numberSelected){
        System.out.println("number selected is " + numberSelected);
        int index = 0;

        for (int j = 0; j < range.length; j++){
            for (int i = 0; i < range[j].length; i++){
                if (numberSelected == range[j][i]){
                    index = j;
                }
            }
        }


        return index;
    }

    public static void main(String[] args){
        
        ArrayList<Integer>[] arrayOfRows = new ArrayList[9];
        ArrayList<Integer>[] arrayOfColumns = new ArrayList[9];

        for (int column = 0; column < 9; column++){
            ArrayList<Integer> newColumn = new ArrayList<Integer>();
            arrayOfColumns[column] = newColumn;
        }

        int[][] columns = {{1,2,3}, {4,5,6}, {7,8,9}};

        int[] usedNumbers = {};
        // difference between arrays in java and lua is that you cannot dynamically 
        // add elements to an array in java, so we gotta use an ArrayList

        for (int row = 1; row < 10; row++ )
        {

            for (int rowNumber = 0; rowNumber < row; rowNumber++){
                System.out.println(arrayOfRows[rowNumber]);
            }

            ArrayList<Integer> newRow = new ArrayList<Integer>();

            if (row == 1){
                     
                for (int i = 0; i < 9; i++){
                    // java has a math class, but math.random works a little different
                    // can use (int) to convert the double to an int
                    int randomNumber = (int)(Math.random()*9) + 1;
                    boolean matching = IsMatching(newRow, randomNumber);

                    // if it does match, run a while loop to keep generating numbers until it doesn't match 
                    // those in the list
                    while (matching == true){
                        randomNumber = (int)(Math.random()*9) + 1;
                        matching = IsMatching(newRow, randomNumber);
                    }

                    // then add that not matching number to the table
                    newRow.add(randomNumber);
                    arrayOfColumns[i].add(randomNumber);

                }
            }
            else if ((row + 1)%3 == 0) {
                System.out.println(row + "\n is the second batch");

                // row needs to be -2 because row is already 1 ahead of tthe index
                ArrayList<Integer> previousRow = arrayOfRows[(row-2)];
                System.out.println("previous row " + previousRow);


                // For each number, check whether the column index is 1
                for (int i = 1; i < 10; i ++){
                    int columnIndex = FindMultiArrayMatch(columns, i);
                    boolean matching = false;

                    int number = (int)(Math.random()*9) + 1;

                    // if the column index is 1 {r3, r4, r5}, ensure that there are no double ups
                    // by assigning them to previous row's, column index two numbers if they don't 
                    // match {p6,p7,p8}
                    if (columnIndex == 1){
                        int[] nextSquareColumns = {previousRow.get(6), previousRow.get(7), previousRow.get(8)};
                        matching = false;

                        for(int j = 0; j < nextSquareColumns.length; j++){

                            matching = false;
            
                            for (int k = 0; k < newRow.size(); k++){
                                if (newRow.get(k) == nextSquareColumns[j]){
                                    matching = true;
                                    System.out.println("found match at newrow index " + k + " number " + newRow.get(k) + " and next columns index " + j + " at " + nextSquareColumns[j] );
                                }
                            }

                            if (matching == false){
                                System.out.println("no match found at: " + j);
                                number = nextSquareColumns[j];
                                System.out.println("Number changed to " + number);
                                break;
                            }
                        }
                    }

                    // firstly check whether the number is part of the same column set on the previous row
                    for (int j = columns[columnIndex][0]; j < (columns[columnIndex][2] + 1); j++){
                        if (previousRow.get(j-1) == number){
                            matching = true;
                            System.out.println("Match happening in thhe column indexes");
                        }
                    }
                    // then check whether it's in the number list for the current row
                    if (matching == false){
                        // iterate over the arraylist to check if the randomNumber matches anything in the list
                        for (int j = 0; j < newRow.size(); j++){

                            if (newRow.get(j) == number){
                                matching = true;
                                System.out.println("Match happening in the current row list");
                            }
                        }

                    }

                    // while it's true, repeat the process above
                    while (matching == true){
                        number = (int)(Math.random()*9) + 1;
                        matching = false;
                        for (int j = columns[columnIndex][0]; j < (columns[columnIndex][2] + 1); j++){
                            if (previousRow.get(j-1) == number){

                                matching = true;
                            }
                        }
                        // then check whether it's in the number list for the current row
                        if (matching == false){
                            // iterate over the arraylist to check if the randomNumber matches anything in the list
                            for (int j = 0; j < newRow.size(); j++){
                                if (newRow.get(j) == number){
                                    matching = true;
                                }
                            }
                        }
                    }
                    newRow.add(number);
                    arrayOfColumns[i-1].add(number);
                }

            }
            else if (row%3 == 0){
                System.out.println(row + "\n is the third batch");

                // row needs to be -2 because row is already 1 ahead of tthe index
                ArrayList<Integer> previousRow = arrayOfRows[(row-2)];
                ArrayList<Integer> previousRow2 = arrayOfRows[(row-3)];
                System.out.println("previous rows " + previousRow + " " + previousRow2);


                
                for (int i = 1; i < 10; i ++){
                    int columnIndex = FindMultiArrayMatch(columns, i);
                    boolean matching = false;

                    int highestNumber = 9;
                    int lowestNumber = 1;

                    int number = (int)(Math.random()*highestNumber) + lowestNumber;


                    // firstly check whether the number is part of the same column set on the previous row
                    for (int j = columns[columnIndex][0]; j < (columns[columnIndex][2] + 1); j++){
                        if (previousRow.get(j-1) == number || previousRow2.get(j-1) == number){
                            matching = true;
                        }
                    }
                    // then check whether it's in the number list for the current row
                    if (matching == false){
                        // iterate over the arraylist to check if the randomNumber matches anything in the list
                        for (int j = 0; j < newRow.size(); j++){

                            if (newRow.get(j) == number){
                                matching = true;
                            }
                        }

                    }

                    // while it's true, repeat the process above
                    while (matching == true){
                        number = (int)(Math.random()*9) + 1;
                        matching = false;
                        for (int j = columns[columnIndex][0]; j < (columns[columnIndex][2] + 1); j++){
                            if (previousRow.get(j-1) == number || previousRow2.get(j-1) == number){

                                matching = true;
                            }
                        }
                        // then check whether it's in the number list for the current row
                        if (matching == false){
                            // iterate over the arraylist to check if the randomNumber matches anything in the list
                            for (int j = 0; j < newRow.size(); j++){
                                if (newRow.get(j) == number){
                                    matching = true;
                                }
                            }
                        }
                    }
                    newRow.add(number);
                    arrayOfColumns[i-1].add(number);
                }
            }
            else {
                System.out.println(row + "\n is the first batch");

                
                for (int i = 0; i < 9; i++){
                    // java has a math class, but math.random works a little different
                    // can use (int) to convert the double to an int
                    int randomNumber = (int)(Math.random()*9) + 1;
                    boolean matching = IsMatchingWithColumns(newRow,arrayOfColumns[i],randomNumber);

                    // if it does match, run a while loop to keep generating numbers until it doesn't match 
                    // those in the list
                    while (matching == true){
                        randomNumber = (int)(Math.random()*9) + 1;
                        matching = IsMatchingWithColumns(newRow,arrayOfColumns[i], randomNumber);
                    }

                    // then add that not matching number to the table
                    newRow.add(randomNumber);
                    arrayOfColumns[i].add(randomNumber);
                }
            }
            arrayOfRows[row-1] = newRow;
        }

        for (int rowNumber = 0; rowNumber < 9; rowNumber++){
            System.out.println(arrayOfRows[rowNumber]);
            if ((rowNumber +1)%3 == 0){
                System.out.println("\n");
            }
        }

        System.out.print("printing columns");
        for (int rowNumber = 0; rowNumber < 9; rowNumber++){
            System.out.println(arrayOfColumns[rowNumber]);
        }

    }

}
