package scala_pack
import java_pack.{List_action, Pair, UserFactory}
import java_pack.Types.UserType

import java.io.{BufferedReader, FileReader, PrintWriter}
import java.util
import java.util.{ArrayList, Comparator, Objects}


class BigList extends List_action {
    private var head: BigListNode = _
    private var tail: BigListNode = _
    private var count: Int = 0
    //    private var comparator: Comparator[_] = UserFactory.get_comparator(head.item.get_head().get_value().type_name())

    private class BigListNode(private var item: SmallList, private var next: BigListNode) {

        def get_item: SmallList = {
            item
        }

        def get_next: BigListNode = {
            next
        }

        def set_next(_next: BigListNode) = {
            next = _next
        }
    }

    override def get_head() = {
        if (head != null) head
        else {
            System.err.println("head is null")
            null
        }
    }

    override def get_tail() = {
        if (tail != null) tail
        else {
            System.err.println("tail is null")
            null
        }
    }

    def this(arr: Array[UserType]) = {
        this()
        for (i <- 0 until arr.length) {
            push(arr.apply(i))
        }
    }

    //    def set_comparator(_comparator: UserType) = {
    //        this.comparator = comparator
    //    }

    def this(node: SmallList#SmallListNode) = {
        this()

        }

    private def push(_list: BigList): Boolean = {
        if (_list.head == null)
            false

        var cur = _list.head
        while (cur != null) {
            push_list(cur.get_item)
            cur = cur.get_next
        }
        true
    } // добавление в конец списка (для конструктора копирования)

    def push_list(_item: SmallList) = {
        val newness = new BigListNode(item = _item, next = null)

        if (head == null) head = newness
        else {
            tail.set_next(newness)
        }

        this.tail = newness
        this.count += 1
    } // добавление в конец списка

    def push(_item: UserType) = {
        if (tail == null)
            push_list(new SmallList)

        tail.get_item push _item
    } // добавление в конец списка

    private def push_to_head(_item: SmallList) = {
        val newnode = new BigListNode(_item, head)
        if (head == null) tail = newnode
        head = newnode
        count += 1
    } // добавление в начало списка


    def push_to_head(_item: UserType) = {
        head.get_item.push_to_head(_item)
    } // добавление в начало списка

    override def remove_item_from_head(): UserType = {
        head.get_item.remove_item_from_head()
    }

    def remove_from_head(): SmallList = {
        var result = head.get_item

        if (head != null) {
            head = head.get_next
            count = count - 1
            result
        }
        else {
            System.err.println("head is null")
            null
        }
    }

    private def remove_on_position(_pos: Int): SmallList = {
        var prev: BigListNode = null
        var cur: BigListNode = head

        if (_pos > get_count || _pos <= 0) {
            System.err.println("BigList_scala (remove_on_position): u write wrong position " + _pos)
            System.err.println("Need 1-" + get_count)
            return null
        }

        var buf_cur: SmallList = null

        var buf_pos = 1
        while (buf_pos <= count) {
            if (buf_pos == _pos) {
                if (cur eq head) {
                    buf_cur = cur.get_item
                    remove_from_head
                    buf_cur
                }
                else {
                    buf_cur = cur.get_item
                    prev.set_next(cur.get_next)
                    cur = null
                    count -= 1
                    buf_cur
                }
            }
            prev = cur
            cur = cur.get_next
            buf_pos += 1
        }
        buf_cur
    }

    def remove_item_on_position(logical_position: Int): UserType = {
        val pair: Pair[SmallList, Integer] = get_physical_pos(logical_position)
        if (logical_position <= 0 || logical_position > inner_count) {
            System.err.println("BigList_scala (insert_on_position): you write wrong position " + logical_position)
            System.err.println("Need log_pos > 0")
            return null
        }
        return pair.getList.remove_item_on_position(pair.getPos)
    }

    private def insert_on_position(_pos: Int, _item: SmallList): Unit = {
        var prev: BigListNode = null
        var cur: BigListNode = head
        if (_pos == get_count + 1) {
            push_list(_item)
            return true
        }
        if (_pos > get_count || _pos <= 0) {
            System.err.println("BigList_scala (insert_on_position): u write wrong position " + _pos)
            System.err.println("Need 1-" + get_count)
            return false
        }
        _pos match {
            case 1 => {
                push_to_head(_item)
                return true
            }

            case _ => {
                var buf_pos = 1
                while (true) {
                    if (buf_pos == _pos) {
                        val newNode: BigListNode = new BigListNode(_item, cur)
                        prev.set_next(newNode)
                        count += 1
                        return true
                    }

                    prev = cur
                    cur = cur.get_next
                    buf_pos += 1
                }
            }
        }
    }

    def insert_item_on_position(logical_position: Int, _item: UserType): Boolean = {
        val pair: Pair[SmallList, Integer] = get_physical_pos(logical_position)
        if (logical_position <= 0) {
            System.err.println("BigList_scala (insert_on_position): you write wrong position " + logical_position)
            System.err.println("Need log_pos > 0")
            return false
        }
        if (logical_position >= inner_count) {
            push(_item)
            return true
        }
        if ((pair.getList eq head.get_item) && pair.getPos == 1) {
            push_to_head(_item)
            return true
        }
        pair.getList.insert_item_on_position(pair.getPos, _item)
        return false
    }

    private def get_on_position(_pos: Int): SmallList = {
        var cur: BigListNode = head
        if (_pos > get_count || _pos <= 0) {
            System.err.println("BigList_scala (get_on_position): u write wrong position " + _pos)
            System.err.println("Need 1-" + get_count)
        }
        var buf_pos = 1
        while (cur.get_next != null) {
            if (buf_pos == _pos) {
                return cur.get_item
            }

            cur = cur.get_next
            buf_pos += 1
        }
        return cur.get_item
    }

    override def get_item_on_position(logical_position: Int): UserType = {
        val pair: Pair[SmallList, Integer] = get_physical_pos(logical_position)
        if (logical_position <= 0 || logical_position > inner_count) {
            System.err.println("BigList_scala (insert_on_position): you write wrong position " + logical_position)
            System.err.println("Need log_pos > 0")
            return null
        }
        return pair.getList.get_item_on_position(pair.getPos)
    }

    override def print_list: String = {
        var result: String = ""
        var i: Int = 1
        var cur: BigListNode = head
        while (cur != null) {
            result += i + ": " + cur.get_item.to_array + "\n"
            cur = cur.get_next
            i += 1
        }
        return result
    }

    override def change_item_on_pos(logical_position: Int, _new_value: UserType): Boolean = {
        return (get_item_on_position(logical_position).parse_value(_new_value.toString)) != null
    }

    def inner_count: Int = {
        var big_count = 0
        var cur: BigListNode = head
        while (cur != null) {
            big_count += cur.get_item.get_count
            cur = cur.get_next
        }
        return big_count
    }

    override def get_count: Int = {
        val buf_count = count
        return buf_count
    }

    def forEach(action: DoWith) = {
        val arr: ArrayList[_] = new ArrayList(to_array_inner)
        for (i <- 0 until arr.size) {
            var str: String = null
            if (arr.get(i) == null) {
                str = "null "
            }
            else {
                str = arr.get(i).toString + " "
            }
            action.doWith(str)
        }
    }

    def save_list(fileName: String, usertype: String) = {
        try {
            val writer = new PrintWriter(fileName)
            try {
                writer.write(usertype + "\n")

                this.forEach(writer.print)

            } finally if (writer != null) writer.close()
        }
        System.out.println("\nList was saved!")
    }

    def load_list(fileName: String) = {
        try {
            val br = new BufferedReader(new FileReader(fileName))
            val type_data = br.readLine()

            if (!UserFactory.get_type_name_list().contains(type_data)) {
                throw new Exception("Wrong file structure")
            }

            val line = br.readLine()
            val items: Array[String] = line.split(" ")

            for (i <- 0 until items.length) {
                if (!Objects.equals(items.apply(i), "null"))
                    push(UserFactory.get_builder_by_name(type_data).parse_value(items.apply(i)).asInstanceOf[UserType])
            }
            System.out.println("\nList was loaded")
        }
    }

    private def to_array_inner: ArrayList[UserType] = {
        val arrayList: ArrayList[UserType] = new ArrayList
        var cur: BigListNode = head
        while (cur != null) {
            arrayList addAll cur.get_item.to_array

            cur = cur.get_next
        }
        return arrayList
    }

    override def to_array: ArrayList[_] = {
        val array: ArrayList[AnyRef] = new ArrayList
        if (head != null) {
            var cur: BigListNode = head
            while (cur != null) {
                array.add(cur.get_item)

                cur = cur.get_next
            }
            return array
        }
        else {
            return null
        }
    }

    private def get_physical_pos(_pos: Int): Pair[SmallList, Integer] = {
        var pair: Pair[SmallList, Integer] = null
        var cur: BigListNode = head
        var logical_pos = _pos
        while (cur != null) {
            if (logical_pos <= cur.get_item.get_count) {
                pair = new Pair[SmallList, Integer](cur.get_item, logical_pos)
                cur = null
            }
            else {
                logical_pos -= cur.get_item.get_count
            }

            if (cur != null)
                cur = cur.get_next
        }
        pair
    }

    def sort_list(): SmallList = {
        val list = new SmallList(to_array_inner)
        list.head = mergeSort(list)(list.head)
        list.tail = list.get_node(list.get_count - 1)
        return list
    }

    private def mergeSort(list: SmallList)(h: list.SmallListNode): list.SmallListNode = {
        if (h == null || h.next == null) {
            return h
        }

        val middle = list.getMiddle(h)
        val middleNext = middle.next

        middle.next = null

        val left = mergeSort(list)(h)
        val right = mergeSort(list)(middleNext)

        merge(list)(left, right)
    }

    private def merge(list: SmallList)(head11: list.SmallListNode, head22: list.SmallListNode) = {
        var left = head11
        var right = head22
        val merged = new list.SmallListNode()
        var temp = merged
        while ( {
            left != null && right != null
        }) {
            if (UserFactory.get_comparator(left.item, right.item) < 0) {
                temp.next = left
                left.prev = temp
                left = left.next
            }
            else {
                temp.next = right
                right.prev = temp
                right = right.next
            }
            temp = temp.next
        }
        while ( {
            left != null
        }) {
            temp.next = left
            left.prev = temp
            left = left.next
            temp = temp.next
        }
        while ( {
            right != null
        }) {
            temp.next = right
            right.prev = temp
            right = right.next
            temp = temp.next
            list.tail = temp
        }
        merged.next
    }

//    def sort_list: SmallList = {
//        val list = new SmallList(to_array_inner)
//        list.head = sort(list)(list.head)
//        return list
//    }
//
//    def sort(list: SmallList)(node: list.SmallListNode) : list.SmallListNode = {
//        var m, p1, p2, q: list.SmallListNode = null
//
//        if (node == null || node.next == null) {
//            return node
//        }
//
//        var p: list.SmallListNode = node
//
//        m = p
//        p = p.next
//
//        while (p != null) {
//            q = p
//            p = p.next
//
//            if (UserFactory.get_comparator(q.item, m.item) < 0) {
//                q.next = p1
//                p1 = q
//            }
//            else {
//                q.next = p2
//                p2 = q
//            }
//        }
//        p1 = sort(list)(p1)
//        p2 = sort(list)(p2)
//
//        m.next = p2
//
//        if (p1 == null) {
//            return m
//        }
//
//        q = p1
//        while(q.next != null) {
//            q.next = m;
//            q = q.next
//        }
//        return p1
//    }

    def balance_list(for_balance_count: Int): Unit = {
        val arr = new util.ArrayList(to_array_inner)
        remove_list()
        var i = 0
        while (i < arr.size) {
            push_list(new SmallList)
            var j = i
            var k = 0
            while (k < for_balance_count && j < arr.size()) {
                tail.get_item.push(arr.get(j).asInstanceOf[UserType])
                j += 1
                k += 1
            }

            i += for_balance_count
        }
    }

    override def remove_list(): Unit = {
        while (count != 0) {
            remove_from_head
        }
    }
}