package scala_pack

import java_pack.UserFactory
import java_pack.Types.{CartVector2D, Dble}
import scala_pack.BigList

import java.io.{FileWriter, IOException}

object Test_scala {
    @throws[Exception]
    def main(args: Array[String]): Unit = {
        testDble()
        System.out.println("\n\nNext data type\n\n")
        testVector2D()
    }

    @throws[Exception]
    private def testDble(): Unit = {
        System.out.println("Data type: DOUBLE")
        System.out.println("Test started")
        val Biglist = new BigList
        System.out.println("\n\tgenerate data\t")
        val count = 100.0
        for (j <- 0 until 3) {
            Biglist.push_list(new SmallList)
            for (i <- 0 until 3) {
                val value = Math.round((((Math.random * ((100 - 1) + 1)) + 1) + 1) * 100) / count
                Biglist.push(new Dble(value))
            }
        }
        System.out.println(Biglist.print_list)
        System.out.println("\n\tpush to the list\t")
        val value = 13.4
        Biglist.push(new Dble(value))
        Biglist.push(new Dble(value))
        Biglist.push(new Dble(value))
        Biglist.push(new Dble(value))
        System.out.println(Biglist.print_list)
        System.out.println("\tremove sublist from list\t")
        Biglist.remove_from_head
        System.out.println(Biglist.print_list)
        System.out.println("\tremove item from head of list\t")
        Biglist.remove_item_from_head
        System.out.println(Biglist.print_list)
        System.out.println("\tpush to head of list\t")
        Biglist.push_to_head(new Dble(value))
        Biglist.push_to_head(new Dble(value))
        Biglist.push_to_head(new Dble(value))
        System.out.println(Biglist.print_list)
        System.out.println("\tinsert items in positions 3, 6, 8\t")
        Biglist.insert_item_on_position(3, new Dble(12.3))
        Biglist.insert_item_on_position(6, new Dble(12.3))
        Biglist.insert_item_on_position(8, new Dble(1.23))
        System.out.println(Biglist.print_list)
        System.out.println("\tremove items by positions 2, 4, 5\t")
        System.out.println(Biglist.print_list)
        System.out.println("")
        Biglist.remove_item_on_position(2)
        Biglist.remove_item_on_position(4)
        Biglist.remove_item_on_position(5)
        System.out.println(Biglist.print_list)
        System.out.println("\tget items in positions 1, 4, 7 \t")
        System.out.println("logical position " + 1 + ": " + Biglist.get_item_on_position(1))
        System.out.println("logical position " + 4 + ": " + Biglist.get_item_on_position(4))
        System.out.println("logical position " + 7 + ": " + Biglist.get_item_on_position(7))
        System.out.println("")
        System.out.println(Biglist.print_list)
        System.out.println("\tchange item in position 2\t")
        System.out.println(Biglist.print_list)
        Biglist.change_item_on_pos(2, new Dble(21.6))
        System.out.println()
        System.out.println(Biglist.print_list)
        System.out.println("\tbalance list (balance column 2)\t")
        System.out.println(Biglist.print_list)
        Biglist.balance_list(2)
        System.out.println()
        System.out.println(Biglist.print_list)
        System.out.println("\tsort list\t")
        System.out.println(Biglist.print_list)
        var sorted_list = new BigList
        sorted_list.push_list(Biglist.sort_list)
        System.out.println()
        System.out.println("\n\tSerialization: saving to file\t")
        sorted_list.save_list("src/scala_pack/test_double.txt", UserFactory.get_type_name_list.get(0))
        System.out.println("\n\tSerialization: loading from file\t")
        val newBiglist = new BigList
        newBiglist.load_list("src/scala_pack/test_double.txt")
        System.out.println("")
        System.out.println(newBiglist.print_list)
        System.out.println("")
        System.out.println("\nData type: DOUBLE")
        System.out.println("Test ended")
    }

    @throws[Exception]
    private def testVector2D(): Unit = {
        System.out.println("\nData type: CARTESIAN VECTOR2D")
        System.out.println("Test started")
        val Biglist = new BigList
        System.out.println("\n\tgenerate data\t")
        for (j <- 0 until 3) {
            Biglist.push_list(new SmallList)
            for (i <- 0 until 3) {
                val x = ((Math.random * ((100 - 1) + 1)).toInt + 1) + 1
                val y = ((Math.random * ((100 - 1) + 1)).toInt + 1) + 1
                Biglist.push(new CartVector2D(x, y))
            }
        }
        System.out.println(Biglist.print_list)
        System.out.println("\tpush to the list\t")
        val x = 12
        val y = 14
        Biglist.push(new CartVector2D(x, y))
        Biglist.push(new CartVector2D(x, y))
        Biglist.push(new CartVector2D(x, y))
        System.out.println(Biglist.print_list)
        System.out.println("\tremove sublist from list\t")
        Biglist.remove_from_head
        System.out.println(Biglist.print_list)
        System.out.println("\tremove item from head of list\t")
        Biglist.remove_item_from_head
        System.out.println(Biglist.print_list)
        System.out.println("\tpush to head of list\t")
        Biglist.push_to_head(new CartVector2D(x, y))
        Biglist.push_to_head(new CartVector2D(x, y))
        Biglist.push_to_head(new CartVector2D(x, y))
        System.out.println(Biglist.print_list)
        System.out.println("\tinsert items in positions 3, 6, 8\t")
        Biglist.insert_item_on_position(3, new CartVector2D(123, 321))
        Biglist.insert_item_on_position(8, new CartVector2D(123, 321))
        Biglist.insert_item_on_position(6, new CartVector2D(123, 321))

        System.out.println(Biglist.print_list)
        System.out.println("\tremove items by positions 2, 4, 5\t")
        Biglist.remove_item_on_position(2)
        Biglist.remove_item_on_position(4)
        Biglist.remove_item_on_position(5)
        System.out.println(Biglist.print_list)
        System.out.println("\tget items in positions 1, 4, 7 \t")
        System.out.println("logical position " + 1 + ": " + Biglist.get_item_on_position(1))
        System.out.println("logical position " + 4 + ": " + Biglist.get_item_on_position(4))
        System.out.println("logical position " + 7 + ": " + Biglist.get_item_on_position(7))
        System.out.println("")
        System.out.println(Biglist.print_list)
        System.out.println("\tchange item in position 2\t")
        System.out.println(Biglist.print_list)
        Biglist.change_item_on_pos(2, new CartVector2D(88, 8))
        System.out.println()
        System.out.println(Biglist.print_list)
        System.out.println("\tbalance list (balance column 2)\t")
        System.out.println(Biglist.print_list)
        Biglist.balance_list(2)
        System.out.println()
        System.out.println(Biglist.print_list)
        System.out.println("\tsort list\t")
        System.out.println(Biglist.print_list)
        var sorted_list = new BigList
        sorted_list.push_list(Biglist.sort_list)
        System.out.println()
        System.out.println("\tSerialization: saving to file\t")
        sorted_list.save_list("src/scala_pack/test_vector2d.txt", UserFactory.get_type_name_list.get(1))
        System.out.println("\n\tSerialization: loading from file\t")
        val newBiglist = new BigList()
        newBiglist.load_list("src/scala_pack/test_vector2d.txt")
        System.out.println("")
        System.out.println(newBiglist.print_list)
        System.out.println("\nData type: CARTESIAN VECTOR2D")
        System.out.println("Test ended")
    }
}
