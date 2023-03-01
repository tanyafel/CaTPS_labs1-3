package scala_pack

import java_pack.{List_action, UserFactory}

import java.util
import java_pack.Types.UserType

import java.util.ArrayList


class SmallList extends List_action {

    private var count: Int = 0 // количество элементов списка
    var head: SmallListNode = null // первый элемент списка
    var tail: SmallListNode = null // последний элемент списка

    class SmallListNode {
        var item: UserType = _
        var next: SmallListNode = _
        var prev: SmallListNode = _

        def this(_item: UserType, _next: SmallListNode, _prev: SmallListNode)={
            this()
            item = _item
            next = _next
            prev = _prev
        }
//        def get_next: SmallListNode = next
//        def set_next(_next: SmallListNode): Unit = {
//            next = _next
//        }
//        def get_value: UserType = item
    }

    def this(arr: util.ArrayList[UserType])= {
        this()
        var cur: UserType = null
        arr.forEach((cur) => push(cur))
    }

    def set_head(node: SmallListNode) = {
        head = node
    }

    override def get_head: SmallListNode = head

    override def get_tail: SmallListNode = tail

    def get_node(pos: Int): SmallListNode = {
        var buf_pos: Int = 1
        var cur: SmallListNode = head
        while (cur!=null) {
            if (buf_pos == pos) {
                  return cur
            }
            cur = cur.next
        }
        return head
    }

    def getMiddle(h: SmallListNode) = {
        var fast = h.next
        var slow = h
        while (fast != null) {
            fast = fast.next
            if (fast != null) {
                slow = slow.next
                fast = fast.next
            }
        }
        slow
    }

    def push_node(node: SmallListNode)= {
        if (tail == null) head = node
        else {tail.next = node
        node.prev = tail}
        tail = node
        count += 1
    }

    def push(_item: UserType) = {
        val newnode = new SmallListNode(_item, null, tail)
        if (tail == null) head = newnode
        else tail.next = newnode
        tail = newnode
        count += 1
    }

    def push_to_head(_item: UserType) = {
        val newnode = new SmallListNode(_item, head, null)
        if (head == null) tail = newnode
        head = newnode
        count += 1
    }

    def insert_item_on_position(logical_position: Int, _item: UserType): Boolean = {
        var prev: SmallListNode = null
        var cur = head

        if (logical_position == get_count + 1) {
            push(_item)
            return true
        }

        if (logical_position > get_count || logical_position <= 0) {
            System.err.println("SmallList (insert_on_position): u write wrong position " + logical_position)
            System.err.println("Need 1-" + get_count)
            return false
        }

        logical_position match {
            case 1 =>
                push_to_head(_item)
                return true

            case _ =>
                var buf_pos = 1
                while (true) {
                    if (buf_pos == logical_position) {
                        val newNode = new SmallListNode(_item, cur, prev)
                        prev.next = newNode
                        count += 1
                        return true
                    }

                    prev = cur
                    cur = cur.next
                    buf_pos += 1
                }
                return true
        }
    }

    override def remove_item_from_head: UserType = {
        val buf_item: UserType = head.item
        if (count == 1) {
            System.err.println("SmallList (remove_on_position): your SmallList have only so lone element")
            System.err.println("Can u add another item in future?")
        }
        if (head != null) {
            head = head.next
            count -= 1
            buf_item
        }
        else {
            System.out.println("Error: can't delete *HEAD* element")
            null
        }
    }

    def remove_item_on_position(logical_position: Int): UserType = {
        var prev: SmallListNode = null
        var cur: SmallListNode = head

        if (count == 1) {
            System.err.println("SmallList (remove_on_position): your SmallList have only so lone element")
            System.err.println("Can u add another item in future?")
        }

        if (logical_position > get_count || logical_position <= 0) {
            System.err.println("scala_pack.BigList (remove_on_position): u write wrong position " + logical_position)
            System.err.println("Need 1-" + get_count)
            return null
        }

        var buf_cur: UserType = null

        var buf_pos = 1
        while (buf_pos <= count) {
            if (buf_pos == logical_position) if (cur eq head) {
                buf_cur = cur.item
                remove_item_from_head
                return buf_cur
            }
            else {
                buf_cur = cur.item
                prev.next = cur.next
                cur = null
                count -= 1
                return buf_cur
            }

            prev = cur
            cur = cur.next
            buf_pos += 1
        }
        return buf_cur
    }

    override def get_item_on_position(logical_position: Int): UserType = {
        var cur: SmallListNode = head

        if (logical_position > get_count || logical_position <= 0) {
            System.err.println("SmallList (get_on_position): u write wrong position " + logical_position)
            System.err.println("Need 1-" + get_count)
        }

        var buf_pos = 1
        while (cur.next != null) {
            if (buf_pos == logical_position) return cur.item
            cur = cur.next
            buf_pos += 1
        }
        return cur.item
    }

    override def change_item_on_pos(logical_position: Int, _new_value: UserType): Boolean = {
        var cur: SmallListNode = head
        if (logical_position > get_count || logical_position <= 0) {
            System.err.println("SmallList (get_on_position): u write wrong position " + logical_position)
            System.err.println("Need 1-" + get_count)
            return false
        }
        var buf_pos = 1
        while (cur != null) {
            if (buf_pos == logical_position) {
                remove_item_on_position(logical_position)
                insert_item_on_position(logical_position, _new_value)
                cur.item != null
            }

            cur = cur.next
            buf_pos += 1
        }
        cur.item != null
    }

    override def print_list: String = to_array.toString

    override def get_count: Int = count

    override def to_array: util.ArrayList[UserType] = {
        val array = new util.ArrayList[UserType]
        if (head != null) {
            var cur = head
            while (cur != null) {
                array.add(cur.item)
                cur = cur.next
            }
            array
        }
        else null
    }

    def remove_from_head: UserType = {
        val buf_result = head.item
        if (head != null) {
            head = head.next
            count -= 1
            buf_result
        }
        else {
            System.out.println("Error: can't delete *HEAD* element")
            null
        }
    }

    override def remove_list(): Unit = {
        while (count != 0) remove_from_head
    }
}