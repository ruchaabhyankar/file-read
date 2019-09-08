package com.company;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        FileReader fr = new FileReader("Books.txt");
        RandomAccessFile raf = new RandomAccessFile("Books.txt","r");
        int i, count = 0, ch = -1;
        String w;
        BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br2 = new BufferedReader(fr);

        while(ch != 4) {
            System.out.println("\n\n1. Search for a specific item book by name \n2. Find the costliest book \n3. Display all books and total cost \n4. Exit \n\nEnter choice: ");
            ch = Integer.parseInt(br1.readLine());
            raf.seek(0);
            System.out.println("File pointer position = " + raf.getFilePointer());

            try {
                switch (ch) {
                    case 1: System.out.println("Enter book name: ");
                            String bookName = br1.readLine();
                            while ((w = br2.readLine()) != null) {
                                String arrOfStr[] = w.split(", ");
                                if (arrOfStr[1].equals(bookName)) {
                                    count++;
                                }
                            }

                            if (count == 0)
                                System.out.println(bookName + " does not exist in the file!");
                            else
                                System.out.println(bookName + " exists in the file!");
                            break;

                    case 2: int max = 0;
                            String costliestBook = "";
                            while((w = raf.readLine()) != null) {
                                String arrOfStr[] = w.split(", ");
                                if (Integer.parseInt(arrOfStr[2]) > max) {
                                    max = Integer.parseInt(arrOfStr[2]);
                                    costliestBook = arrOfStr[1];
                                }
                            }

                            System.out.println("Costliest book: " + costliestBook + "\nPrice: Rs." + max);
                            break;

                    case 3: int total = 0;
                            while((w = raf.readLine()) != null) {
                                System.out.println(w);
                                String arrOfStr[] = w.split(", ");
                                total = total + Integer.parseInt(arrOfStr[2]);
                            }
                            System.out.println("Total price = " + total);
                            break;

                    case 4:
                        break;
                }
            }catch(Exception e)
            {
                System.out.println("Exception caught: " + e);
            }
        }
        raf.close();
        fr.close();

    }
}
