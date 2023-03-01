package kotlin_pack

import java_pack.List_action
import java_pack.Types.UserType


class SmallList : List_action {
    private var count = 0 // количество элементов списка
    var head: SmallListNode? = null // первый элемент списка
    var tail: SmallListNode? = null // последний элемент списка
    inner class SmallListNode(
            var item: UserType? // данные узла списка
    ){
        var next: SmallListNode? = null// указатель на след. узел
        var prev: SmallListNode? = null// указатель на пред. узел
    }

    constructor() {
        count = 0
        tail = null
        head = tail
    }

    constructor(arr: ArrayList<UserType>) {
        for (cur in arr) {
            push(cur)
        }
    }

    fun set_head(node: SmallListNode?) {
        head = node
    }

    override fun get_head(): SmallListNode {
        return head!!
    }

    override fun get_tail(): SmallListNode {
        return tail!!
    }

    fun get_node(pos: Int) :SmallListNode? {
        var buf_pos: Int = 1
        var cur: SmallListNode? = head
        while (cur!=null) {
            if (buf_pos == pos) {
                return cur
            }
            buf_pos++
            cur = cur.next!!
        }
        return head
    }

    fun push_node(node: SmallListNode) {
        if (tail == null) {
            head = node
        } else {
            tail!!.next = node
        }
        tail = node
        count++
    }

    fun push(_item: UserType?) {
        val newnode: SmallListNode = SmallListNode(_item)
        newnode.next = null

        if (head == null) {
            head = newnode
            newnode.prev = null
        } else {
            newnode.prev = tail
            tail!!.next = newnode
        }
        tail = newnode
        count++
    }

    fun push_to_head(_item: UserType?) {
        val newnode: SmallListNode = SmallListNode(_item)

        if (head == null) {
            newnode.next = null
            newnode.prev = null
            tail = newnode
            head = tail
            count++
        }
        else {
            newnode.next = head
            newnode.prev = null
            head!!.prev = newnode
            head = newnode
            count++
        }
    }

    fun insert_item_on_position(logical_position: Int, _item: UserType?): Boolean {
        var prev: SmallListNode? = null
        var cur = head
        if (logical_position == _count + 1) {
            push(_item)
            return true
        }
        if (logical_position > _count || logical_position <= 0) {
            System.err.println("SmallList (insert_on_position): u write wrong position $logical_position")
            System.err.println("Need 1-$_count")
            return false
        }
        when (logical_position) {
            1 -> {
                push_to_head(_item)
                return true
            }

            else -> {
                var buf_pos = 1
                while (true) {
                    if (buf_pos == logical_position) {
                        val newNode: SmallListNode = SmallListNode(_item)
                        newNode.next = cur
                        newNode.prev = prev
                        prev!!.next = newNode
                        cur!!.prev= newNode
                        count++
                        return true
                    }
                    prev = cur
                    cur = cur!!.next
                    buf_pos++
                }
            }
        }
    }

    override fun remove_item_from_head(): UserType? {
        if (count == 1) {
            System.err.println("SmallList (remove_on_position): your SmallList have only so lone element")
            System.err.println("Can u add another item in future?")
        }
        return if (head != null) {
            head = head!!.next
            count--
            return head!!.item
        } else {
            println("Error: can't delete *HEAD* element")
            null
        }
    }

    fun remove_item_on_position(logical_position: Int): UserType? {
        var prev: SmallListNode? = null
        var cur = head
        if (count == 1) {
            System.err.println("SmallList (remove_on_position): your SmallList have only so lone element")
            System.err.println("Can u add another item in future?")
        }
        if (logical_position > _count || logical_position <= 0) {
            System.err.println("scala_pack.BigList (remove_on_position): u write wrong position $logical_position")
            System.err.println("Need 1-$_count")
            return null
        }
        var buf_cur: UserType? = null
        var buf_pos = 1
        while (buf_pos <= count) {
            if (buf_pos == logical_position) {
                if (cur === head) {
                    buf_cur = cur!!.item
                    remove_item_from_head()
                    break
                } else {
                    buf_cur = cur!!.item
                    prev!!.next = cur.next
                    cur = null
                    count--
                    break
                }
            }
            prev = cur
            cur = cur!!.next
            buf_pos++
        }
        return buf_cur
    }

    override fun get_item_on_position(logical_position: Int): UserType? {
        var cur = head
        if (logical_position > _count || logical_position <= 0) {
            System.err.println("SmallList (get_on_position): u write wrong position $logical_position")
            System.err.println("Need 1-$_count")
        }
        var buf_pos = 1
        while (cur!!.next != null) {
            if (buf_pos == logical_position) {
                return cur.item
            }
            cur = cur.next
            buf_pos++
        }
        return cur.item
    }

    override fun change_item_on_pos(logical_position: Int, _new_value: UserType): Boolean {
        var cur = head
        if (logical_position > _count || logical_position <= 0) {
            System.err.println("SmallList (get_on_position): u write wrong position $logical_position")
            System.err.println("Need 1-$_count")
            return false
        }
        var buf_pos = 1
        while (cur!!.next != null) {
            if (buf_pos == logical_position) {
                remove_item_on_position(logical_position)
                insert_item_on_position(logical_position, _new_value)
                break
            }
            cur = cur.next
            buf_pos++
        }
        return cur.item != null
    }

    override fun print_list(): String {
        return to_array().toString()
    }

    override fun get_count(): Int {
        return count
    }

    override fun to_array(): ArrayList<UserType>? {
        val array = ArrayList<UserType>()
        return if (head != null) {
            var cur = head
            while (cur != null) {
                cur.item?.let { array.add(it) }
                cur = cur.next
            }
            array
        } else {
            null
        }
    }

    fun remove_from_head(): UserType? {
        val buf_result = head!!.item
        return if (head != null) {
            head = head!!.next
            count--
            buf_result
        } else {
            println("Error: can't delete *HEAD* element")
            null
        }
    }

    override fun remove_list() {
        while (count != 0) {
            remove_from_head()
        }
    }

    /**
     * Получить "середину" списка
     * @param h - SmallListNode
     * @return Unit
     */
    fun getMiddle(h: SmallListNode): SmallListNode {
        var fast = h.next
        var slow = h
        while (fast != null)
        {
            fast=fast.next
            if(fast!=null)
            {
                slow = slow.next!!
                fast = fast.next
            }
        }
        return slow
    }
}
