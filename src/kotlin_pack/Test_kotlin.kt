package kotlin_pack

import java_pack.Types.CartVector2D
import java_pack.Types.Dble
import java_pack.UserFactory
import java.io.FileWriter
import java.io.IOException


object Test_kotlin {
    @Throws(Exception::class)
    @JvmStatic
    fun main(args: Array<String>) {
        testDble()
        println("\n\nNext data type\n\n")
        testVector2D()
    }

    @Throws(Exception::class)
    private fun testDble() {
        println("Data type: DOUBLE");
        println("Test started\n");
        val Biglist = BigList()
        println("\tgenerate data\t");
        val count = 100.0;
        for (j in 0..2) {
            Biglist.push(SmallList())
            for (i in 0..2) {
                val value = Math.round(((Math.random() * (100 - 1 + 1)) + 1 + 1)* count) / count
                Biglist.push(Dble(value))
            }
        }
        println(Biglist.print_list())
        println("\tpush to the list\t");
        val value = 16.7
        Biglist.push(Dble(value))
        Biglist.push(Dble(value))
        Biglist.push(Dble(value))
        println(Biglist.print_list())
        println("\tremove sublist from list\t");
        Biglist.remove_from_head()
        println(Biglist.print_list())
        println("\tremove item from head of list\t");
        Biglist.remove_item_from_head()
        println(Biglist.print_list())
        println("\tpush to head of list\t");
        Biglist.push_to_head(Dble(value))
        Biglist.push_to_head(Dble(value))
        Biglist.push_to_head(Dble(value))
        println(Biglist.print_list())
        println("\tinsert items in positions 3, 6, 8\t");
        Biglist.insert_item_on_position(3, Dble(12.3))
        Biglist.insert_item_on_position(6, Dble(12.3))
        Biglist.insert_item_on_position(8, Dble(12.3))
        println(Biglist.print_list())
        System.out.println("\n\tremove items by positions 2, 4, 5\t");
        println(Biglist.print_list())
        println("")
        Biglist.remove_item_on_position(2)
        Biglist.remove_item_on_position(4)
        Biglist.remove_item_on_position(5)
        println(Biglist.print_list())
        println("\tget items in positions 1, 4, 7 \t");
        println("logical position " + 1 + ": " + Biglist.get_item_on_position(1))
        println("logical position " + 4 + ": " + Biglist.get_item_on_position(4))
        println("logical position " + 7 + ": " + Biglist.get_item_on_position(7))
        println("")
        println(Biglist.print_list())
        println("\tchange item in position 2\t");
        println(Biglist.print_list())
        Biglist.change_item_on_pos(2, Dble(88.8))
        println(Biglist.print_list())
        println("\tbalance list (balance column 2)\t");
        println(Biglist.print_list())
        Biglist.balance_list(2)
        println()
        println(Biglist.print_list())
        println("\tsort list\t");
        println(Biglist.print_list())
        var sorted_list = Biglist.sort_list()
        println()
        println(sorted_list.print_list())
        println("\n\tSerialization: saving to file\t");
        sorted_list.save_list("src/kotlin_pack/test_double.txt", UserFactory.get_type_name_list()[0])
        println("\n\tSerialization: loading from file\t");
        val newBiglist = BigList()
        newBiglist.load_list("src/kotlin_pack/test_double.txt")
        println("")
        newBiglist.forEach { obj: Any? -> print(obj) }
        println("")
        println("Data type: DOUBLE");
        println("Test ended");
    }

    @Throws(Exception::class)
    private fun testVector2D() {
        println("\nData type: CARTESIAN VECTOR2D");
        println("Test started\n");
        val Biglist = BigList()
        println("\tgenerate data\t");
        for (j in 0..2) {
            Biglist.push(SmallList())
            for (i in 0..2) {
                val x = (Math.random() * (100 - 1 + 1)).toInt() + 1 + 1
                val y = (Math.random() * (100 - 1 + 1)).toInt() + 1 + 1
                Biglist.push(CartVector2D(x, y))
            }
        }
        println(Biglist.print_list())
        println("\tpush to the list\t");
        val x = 12
        val y = 13
        Biglist.push(CartVector2D(x, y))
        Biglist.push(CartVector2D(x, y))
        Biglist.push(CartVector2D(x, y))
        println(Biglist.print_list())
        println("\tremove sublist from list\t");
        Biglist.remove_from_head()
        println(Biglist.print_list())
        println("\tremove item from head of list\t");
        Biglist.remove_item_from_head()
        println(Biglist.print_list())
        println("\tpush to head of list\t");
        Biglist.push_to_head(CartVector2D(x, y))
        Biglist.push_to_head(CartVector2D(x, y))
        Biglist.push_to_head(CartVector2D(x, y))
        println(Biglist.print_list())
        println("\tinsert items in positions 3, 6, 8\t");
        Biglist.insert_item_on_position(3, CartVector2D(123, 321))
        Biglist.insert_item_on_position(8, CartVector2D(123, 321))
        Biglist.insert_item_on_position(6, CartVector2D(123, 321))
        println(Biglist.print_list())
        println("\tremove items by positions 2, 4, 5\t");
        Biglist.remove_item_on_position(2)
        Biglist.remove_item_on_position(4)
        Biglist.remove_item_on_position(5)
        println(Biglist.print_list())
        println("\tget items in positions 1, 4, 7 \t");
        println("logical position " + 1 + ": " + Biglist.get_item_on_position(1))
        println("logical position " + 4 + ": " + Biglist.get_item_on_position(4))
        println("logical position " + 7 + ": " + Biglist.get_item_on_position(7))
        println("")
        println(Biglist.print_list())
        println("\tchange item in position 2\t");
        println(Biglist.print_list())
        Biglist.change_item_on_pos(2, CartVector2D(88, 8))
        println()
        println(Biglist.print_list())
        println("\tbalance list (balance column 2)\t");
        println(Biglist.print_list())
        Biglist.balance_list(2)
        println()
        println(Biglist.print_list())
        println("\tsort list\t");
        println(Biglist.print_list())
        var sorted_list = Biglist.sort_list()
        println()
        println(sorted_list.print_list())
        println("\n\tSerialization: saving to file\t");
        sorted_list.save_list("src/kotlin_pack/test_vector2d.txt", UserFactory.get_type_name_list()[1])
        println("\n\tSerialization: loading from file\t");
        val newBiglist = BigList()
        newBiglist.load_list("src/kotlin_pack/test_vector2d.txt")
        println("")
        newBiglist.forEach { obj: Any? -> print(obj) }
        println("")
        println("\nData type: CARTESIAN VECTOR2D");
        println("Test ended");
    }
}
