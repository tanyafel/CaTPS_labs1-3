package kotlin_pack

import java_pack.DoWith
import java_pack.List_action
import java_pack.Pair
import java_pack.Types.UserType
import java_pack.UserFactory
import kotlin_pack.SmallList.SmallListNode
import java.io.BufferedReader
import java.io.FileReader
import java.io.PrintWriter
import java.util.*


class BigList : List_action {
    private var count = 0 // количество элементов списка
    private var head: BigListNode? = null // первый элемент списка
    private var tail: BigListNode? = null // последний элемент списка

    inner class BigListNode(// данные узла списка
            val item: SmallList?, // указатель на след. узел
            var next: BigListNode?) {
        fun get_next(): BigListNode? {
            return next
        }

        fun get_value(): SmallList? {
            return item
        }
    }

    override fun get_head(): BigListNode {
        return head!!
    }

    override fun get_tail(): BigListNode {
        return tail!!
    }

    constructor() {
        count = 0
        tail = null
        head = tail
    } // создание пустого списка

    constructor(_list: BigList) // конструктор копирования
    {
        push(_list)
    }

    constructor(arr: Array<UserType?>) {
        for (i in arr.indices) {
            push(arr[i])
        }
    }

    private fun push(arr: Array<UserType?>) {
        for (i in arr.indices) {
            push(arr[i])
        }
    }

    private fun push(_list: BigList) // добавление в конец списка (для конструктора копирования)
    {
        var cur = _list.head
        while (cur != null) {
            push(cur.item)
            cur = cur.next
        }
    }

    fun push(_item: SmallList?) // добавление в конец списка
    {
        val newnode: BigListNode = BigListNode(_item, null)
        if (head == null) {
            head = newnode
        } else {
            tail!!.next = newnode
        }
        tail = newnode
        count++
    }

    fun push(_item: UserType?) // добавление в конец списка
    {
        if (tail == null) {
            push(SmallList())
        }
        tail!!.get_value()!!.push(_item)
    }

    private fun push_to_head(_item: SmallList) // добавление в начало списка
    {
        val newnode: BigListNode = BigListNode(_item, head)
        if (head == null) {
            tail = newnode
        }
        head = newnode
        count++
    }

    fun push_to_head(_item: UserType?) // добавление в начало списка
    {
        head!!.get_value()!!.push_to_head(_item)
    }

    override fun remove_item_from_head(): UserType? {
        return head!!.get_value()!!.remove_item_from_head()
    }

    fun remove_from_head(): SmallList? {
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

    private fun remove_on_position(_pos: Int): SmallList? {
        var prev: BigListNode? = null
        var cur = head
        if (_pos > _count || _pos <= 0) {
            System.err.println("scala_pack.BigList (remove_on_position): u write wrong position $_pos")
            System.err.println("Need 1-$_count")
            return null
        }
        var buf_cur: SmallList? = null
        var buf_pos = 1
        while (buf_pos <= count) {
            if (buf_pos == _pos) {
                if (cur === head) {
                    buf_cur = cur!!.get_value()
                    remove_from_head()
                    break
                } else {
                    buf_cur = cur!!.get_value()
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

    fun remove_item_on_position(logical_position: Int): UserType? {
        val pair = get_physical_pos(logical_position)
        if (logical_position <= 0 || logical_position > inner_count()) {
            System.err.println("scala_pack.BigList (insert_on_position): you write wrong position $logical_position")
            System.err.println("Need log_pos > 0")
            return null
        }
        return pair!!.list!!.remove_item_on_position(pair.pos)
    }

    private fun insert_on_position(_pos: Int, _item: SmallList): Boolean {
        var prev: BigListNode? = null
        var cur = head
        if (_pos == _count + 1) {
            push(_item)
            return true
        }
        if (_pos > _count || _pos <= 0) {
            System.err.println("scala_pack.BigList (insert_on_position): u write wrong position $_pos")
            System.err.println("Need 1-$_count")
            return false
        }
        when (_pos) {
            1 -> {
                push_to_head(_item)
                return true
            }

            else -> {
                var buf_pos = 1
                while (true) {
                    if (buf_pos == _pos) {
                        val newNode: BigListNode = BigListNode(_item, cur)
                        prev!!.next = newNode
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

    fun insert_item_on_position(logical_position: Int, _item: UserType?): Boolean {
        val pair = get_physical_pos(logical_position)
        if (logical_position <= 0) {
            System.err.println("scala_pack.BigList (insert_on_position): you write wrong position $logical_position")
            System.err.println("Need log_pos > 0")
            return false
        }
        if (logical_position >= inner_count()) {
            push(_item)
            return true
        }
        if (pair!!.list === head!!.item && pair!!.pos == 1) {
            push_to_head(_item)
            return true
        }
        pair!!.list!!.insert_item_on_position(pair.pos, _item)
        return false
    }

    private fun get_on_position(_pos: Int): SmallList? {
        var cur = head
        if (_pos > _count || _pos <= 0) {
            System.err.println("scala_pack.BigList (get_on_position): u write wrong position $_pos")
            System.err.println("Need 1-$_count")
        }
        var buf_pos = 1
        while (cur!!.next != null) {
            if (buf_pos == _pos) {
                return cur.get_value()
            }
            cur = cur.next
            buf_pos++
        }
        return cur.get_value()
    }

    override fun get_item_on_position(logical_position: Int): UserType? {
        val pair = get_physical_pos(logical_position)
        if (logical_position <= 0 || logical_position > inner_count()) {
            System.err.println("scala_pack.BigList (insert_on_position): you write wrong position $logical_position")
            System.err.println("Need log_pos > 0")
            return null
        }
        return pair!!.list!!.get_item_on_position(pair.pos)
    }

    override fun print_list(): String {
        var result = ""
        var i = 1
        var cur = head
        while (true) {
            result += """
                $i: ${cur!!.item!!.to_array()}
                
                """.trimIndent()
            if (cur.next == null) break
            cur = cur.next
            i++
        }
        return result
    }

    override fun change_item_on_pos(logical_position: Int, _new_value: UserType): Boolean {
        get_item_on_position(logical_position)?.parse_value(_new_value.toString())?.let {  return true }
        return false
    }

    fun inner_count(): Int {
        var big_count = 0
        var cur = head
        while (true) {
            big_count += cur!!.item!!._count
            if (cur.next == null) break
            cur = cur.next
        }
        return big_count
    }

    override fun get_count(): Int {
        return count
    }

    fun save_list(filename: String, usertype: String) {
        try {
            val writer = PrintWriter(filename)
            try {
                writer.println(usertype)
                this.forEach(writer::print)
            } finally {
                if (writer != null) writer.close()
            }
            }
        catch (e: Exception){System.err.println(e)}

        System.out.println("\nList was saved!")
    }

    fun load_list(filename: String) {
        BufferedReader(FileReader(filename)).use { br ->
            val type = br.readLine()
            if (!UserFactory.get_type_name_list().contains(type)) {
                throw java.lang.Exception("Wrong file structure")
            }
            val line = br.readLine()
            val items = line.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val arr = arrayOfNulls<UserType>(items.size)
            for (i in arr.indices)
                if (items[i] != "null")
                    arr[i] = UserFactory.get_builder_by_name(type).parse_value(items[i]) as UserType

            push(arr)
            println("\nList was loaded!")

        }
    }

    /**
     * Сортировка слиянием
     * @see mergeSort
     * @see merge
     * @see getMiddle
     * @return копия BigList
     */
    fun sort_list(): BigList {
        val list = SmallList(to_array_inner())
        list.head = mergeSort(list, list.head)
        list.tail = list.get_node(list._count - 1)
        remove_list()
        push(list)
        val newList = this
        return newList
    }

    /**
     * Разделение списка в императивном стиле
     * "разрыв" списка пополам на каждой итерации,
     * пока каждый элемент исходного списка не станет списком размерности 1
     * @param list SmallList
     * @param h SmallListNode
     * @return SmallListNode
     */
    private fun mergeSort(list: SmallList, headNode: SmallList.SmallListNode?) : SmallListNode {
        if (headNode == null || headNode.next == null) {
            return headNode!!
        }
        val middle: SmallListNode = list.getMiddle(headNode)!!
        val middleNext: SmallListNode = middle.next!!

        middle.next = null

        val left: SmallListNode = mergeSort(list, headNode)
        val right: SmallListNode = mergeSort(list, middleNext)

        return merge(list, left, right)!!
    }

    /**
     * Слияние в императивном стиле.
     * Соединение цепочки элементов попарно рядом стоящих списков,
     * получившихся в ходе "разрыва" списка.
     * Попарное сравнение "голов" рядом стоящих списков
     * @param list SmallList
     * @param head11 SmallListNode
     * @param head22 SmallListNode
     * @return SmallListNode
     */
    private fun merge(list: SmallList, head11: SmallListNode?, head22: SmallListNode?): SmallListNode? {
        var firstNode: SmallListNode? = head11
        var secondNode: SmallListNode? = head22
        val merged: SmallListNode = list.SmallListNode(null)
        var temp: SmallListNode = merged
        var tail: SmallListNode? = list.head!!.prev
        while (firstNode != null && secondNode != null) {
            if (UserFactory.get_comparator(firstNode.item!!, secondNode.item!!) < 0) {
                temp.next = firstNode
                firstNode.prev = temp
                firstNode = firstNode.next
            } else {
                temp.next = secondNode
                secondNode.prev = temp
                secondNode = secondNode.next
            }
            temp = temp.next!!
        }
        while (firstNode != null) {
            temp.next = firstNode
            firstNode.prev = temp
            firstNode = firstNode.next
            temp = temp.next!!
        }
        while (secondNode != null) {
            temp.next = secondNode
            secondNode.prev = temp
            secondNode = secondNode.next
            temp = temp.next!!
            tail = temp
        }
        return merged.next
    }

    fun forEach(action: DoWith) {
        val arr: ArrayList<*> = ArrayList(to_array_inner())
        for (i in arr.indices) {
            var str: String? = null
            if(arr.get(i) == null)
            {
                str = "null"
            }
            else {
                str = arr.get(i).toString() + " "
            }
            action.doWith(str)
        }
    }

    private fun to_array_inner(): ArrayList<UserType> {
        val arrayList: ArrayList<UserType> = ArrayList()
                var cur = head
        while (cur != null) {
            cur.item!!.to_array()?.let { arrayList.addAll(it) }
            cur = cur.next
        }
        return arrayList
    }

    override fun to_array(): ArrayList<*>? {
        val array = ArrayList<SmallList?>()
        return if (head != null) {
            var cur = head
            while (cur != null) {
                array.add(cur.item)
                cur = cur.next
            }
            array
        } else {
            null
        }
    }

    private fun get_physical_pos(_pos: Int): Pair<SmallList?, Int>? {
        var _pos = _pos
        var pair: Pair<SmallList?, Int>? = null
        var cur = head
        while (true) {
            if (_pos <= cur!!.item!!._count) {
                pair = Pair(cur.item, _pos)
                break
            } else {
                _pos -= cur.item!!._count
            }
            if (cur.next == null) break
            cur = cur.next
        }
        return pair
    }

    fun balance_list(for_balance_count: Int) {
        val arr: Vector<*> = Vector(to_array_inner())
        remove_list()
        var i = 0
        while (i < arr.size) {
            push(SmallList())
            var j = i
            var k = 0
            while (k < for_balance_count) {
                if (j >= arr.size) break
                tail!!.get_value()!!.push(arr[j] as UserType)
                j++
                k++
            }
            i += for_balance_count
        }
    }

    override fun remove_list() {
        while (count != 0) {
            remove_from_head()
        }
    }
}
