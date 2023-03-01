package java_pack;

import java_pack.Types.CartVector2D;
import java_pack.Types.Dble;

import java.io.FileWriter;
import java.io.IOException;

public class Test {
    public static void main(String[] args) throws Exception {

        testDble();
        System.out.println("\n\nNext data type\n\n");
        testVector2D();
    }

    private static void testDble() throws Exception {

        System.out.println("Data type: DOUBLE");
        System.out.println("Test started");

        BigList Biglist = new BigList();

        System.out.println("\n\tgenerate data\t");

        double count = 100;
        for (int j = 0; j < 3; j++) {
            Biglist.push(new SmallList());

            for (int i = 0; i < 3; i++) {
                double value = Math.round(((Math.random() * (100 - 1 + 1)) + 1 + 1)* count) / count;
                Biglist.push(new Dble(value));
            }
        }
        System.out.println(Biglist.print_list());

        System.out.println("\tpush to the list\t");
        double value = 54.321;
        Biglist.push(new Dble(value));
        Biglist.push(new Dble(value));
        Biglist.push(new Dble(value));
        System.out.println(Biglist.print_list());

        System.out.println("\tremove sublist from list\t");
        Biglist.remove_from_head();
        System.out.println(Biglist.print_list());

        System.out.println("\tremove item from head of list\t");
        Biglist.remove_item_from_head();
        System.out.println(Biglist.print_list());

        System.out.println("\tpush to head of list\t");
        Biglist.push_to_head(new Dble(value));
        Biglist.push_to_head(new Dble(value));
        Biglist.push_to_head(new Dble(value));
        System.out.println(Biglist.print_list());

        System.out.println("\tinsert items in positions 3, 6, 8\t");
        Biglist.insert_item_on_position(3, new Dble(12.3));
        Biglist.insert_item_on_position(6, new Dble(12.4));
        Biglist.insert_item_on_position(8, new Dble(12.6));
        System.out.println(Biglist.print_list());

        System.out.println("\tremove items by positions 2, 4, 5\t");
        System.out.println(Biglist.print_list());
        System.out.println("");
        Biglist.remove_item_on_position(2);
        Biglist.remove_item_on_position(4);
        Biglist.remove_item_on_position(5);
        System.out.println(Biglist.print_list());

        System.out.println("\tget items in positions 1, 4, 7 \t");
        System.out.println("logical position " + 1 + ": " + Biglist.get_item_on_position(1));
        System.out.println("logical position " + 4 + ": " + Biglist.get_item_on_position(4));
        System.out.println("logical position " + 7 + ": " + Biglist.get_item_on_position(7));
        System.out.println("");
        System.out.println(Biglist.print_list());

        System.out.println("\tchange item in position 2\t");
        System.out.println(Biglist.print_list());
        Biglist.change_item_on_pos(2, new Dble(3.4));
        System.out.println();
        System.out.println(Biglist.print_list());

        System.out.println("\tbalance list (sublist size 2)\t");
        System.out.println(Biglist.print_list());
        Biglist.balance_list(2);
        System.out.println();
        System.out.println(Biglist.print_list());

        System.out.println("\tsort list\t");
        System.out.println(Biglist.print_list());
        Biglist.sort_list();
        System.out.println(Biglist.print_list());

        System.out.println("\n\tSerialization: saving to file\t");
        Serializator.saveToFile(Biglist, "src/java_pack/test_double.txt", UserFactory.get_type_name_list().get(0));
        System.out.println("\n\tSerialization: loading from file\t");
        BigList newBiglist = Serializator.loadFile("src/java_pack/test_double.txt");

        System.out.println("");
        newBiglist.forEach(System.out::print);
        System.out.println("");

        System.out.println("\nData type: DOUBLE");
        System.out.println("Test ended");
    }

    private static void testVector2D() throws Exception {

        System.out.println("\nData type: CARTESIAN VECTOR2D");
        System.out.println("Test started");

        BigList Biglist = new BigList();

        System.out.println("\tgenerate data\t");

        for (int j = 0; j < 3; j++) {
            Biglist.push(new SmallList());

            for (int i = 0; i < 3; i++) {
                int x = ((int) (Math.random() * ((100 - 1) + 1)) + 1) + 1;
                int y = ((int) (Math.random() * ((100 - 1) + 1)) + 1) + 1;
                Biglist.push(new CartVector2D(x, y));
            }
        }
        System.out.println(Biglist.print_list());

        System.out.println("\tpush to the list\t");
        int x = 12;
        int y = 13;
        Biglist.push(new CartVector2D(x, y));
        Biglist.push(new CartVector2D(x, y));
        Biglist.push(new CartVector2D(x, y));
        System.out.println(Biglist.print_list());

        System.out.println("\tremove sublist from list\t");
        Biglist.remove_from_head();
        System.out.println(Biglist.print_list());

        System.out.println("\tremove item from head of list\t");
        Biglist.remove_item_from_head();
        System.out.println(Biglist.print_list());

        System.out.println("\tpush to head of list\t");
        Biglist.push_to_head(new CartVector2D(x, y));
        Biglist.push_to_head(new CartVector2D(x, y));
        Biglist.push_to_head(new CartVector2D(x, y));
        System.out.println(Biglist.print_list());

        System.out.println("\tinsert items in positions 3, 6, 8\t");
        Biglist.insert_item_on_position(3, new CartVector2D(123, 321));
        Biglist.insert_item_on_position(8, new CartVector2D(123, 321));
        Biglist.insert_item_on_position(6, new CartVector2D(123, 321));
        ;
        System.out.println(Biglist.print_list());

        System.out.println("\tremove items by positions 2, 4, 5\t");
        Biglist.remove_item_on_position(2);
        Biglist.remove_item_on_position(4);
        Biglist.remove_item_on_position(5);
        System.out.println(Biglist.print_list());

        System.out.println("\tget items in positions 1, 4, 7 \t");
        System.out.println("logical position " + 1 + ": " + Biglist.get_item_on_position(1));
        System.out.println("logical position " + 4 + ": " + Biglist.get_item_on_position(4));
        System.out.println("logical position " + 7 + ": " + Biglist.get_item_on_position(7));
        System.out.println("");
        System.out.println(Biglist.print_list());

        System.out.println("\tchange item in position 2\t");
        System.out.println(Biglist.print_list());
        Biglist.change_item_on_pos(2, new CartVector2D(88, 8));
        System.out.println();
        System.out.println(Biglist.print_list());

        System.out.println("\tbalance list (sublist size 2)\t");
        System.out.println(Biglist.print_list());
        Biglist.balance_list(2);
        System.out.println();
        System.out.println(Biglist.print_list());

        System.out.println("\tsort list\t");
        System.out.println(Biglist.print_list());
        Biglist.sort_list();
        System.out.println();
        System.out.println(Biglist.print_list());

        System.out.println("\n\tSerialization: saving to file\t");
        Serializator.saveToFile(Biglist, "src/java_pack/test_vector2d.txt", UserFactory.get_type_name_list().get(1));
        System.out.println("\n\tSerialization: loading from file\t");
        BigList newBiglist = Serializator.loadFile("src/java_pack/test_vector2d.txt");

        System.out.println("");
        newBiglist.forEach(System.out::print);
        System.out.println("");

        System.out.println("\nData type: CARTESIAN VECTOR2D");
        System.out.println("Test ended");
    }
}
