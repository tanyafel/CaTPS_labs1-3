package java_pack;

import java_pack.Types.UserType;

import java.util.Iterator;
import java.util.Vector;
import java.util.ArrayList;

public class BigList implements List_action
{
    private int count;        // количество элементов списка
    private BigListNode head; // первый элемент списка
    private BigListNode tail; // последний элемент списка

    class BigListNode
    {
        private SmallList item;        // данные узла списка
        private BigListNode next; // указатель на след. узел

        public BigListNode(SmallList _item, BigListNode _next)
        {
            item = _item;
            next = _next;
        }

        public BigListNode get_next()
        {
            return next;
        }

        public SmallList get_value()
        {
            return (item != null) ? item : null;
        }
    };

    @Override
    public BigListNode get_head()
    {
        return head;
    }

    @Override
    public BigListNode get_tail()
    {
        return tail;
    }

    public BigList()
    {
        count = 0;
        head = tail = null;
    } // создание пустого списка

    public BigList(final BigList _list) // конструктор копирования
    {
        push(_list);
    }

    public BigList(UserType[] arr)
    {
        for (int i = 0; i < arr.length; i++)
        {
            push(arr[i]);
        }
    }

    private void push(final BigList _list) // добавление в конец списка (для конструктора копирования)
    {
        for(BigListNode cur = _list.head; cur != null; cur = cur.next)
        {
            push(cur.item);
        }
    }

    public void push(SmallList _item) // добавление в конец списка
    {
        BigListNode newnode = new BigListNode(_item, null);

        if(head == null)
        {
            head = newnode;
        }
        else
        {
            tail.next = newnode;
        }

        tail = newnode;
        count++;
    }

    public void push(UserType _item) // добавление в конец списка
    {
        if(tail==null)
        {
            push(new SmallList());
        }
        tail.get_value().push(_item);
    }

    private void push_to_head(SmallList _item) // добавление в начало списка
    {
        BigListNode newnode = new BigListNode(_item, head);

        if(head==null)
        {
            tail = newnode;
        }

        head = newnode;
        count++;
    }

    public void push_to_head(UserType _item) // добавление в начало списка
    {
        head.get_value().push_to_head(_item);
    }

    @Override
    public UserType remove_item_from_head()
    {
        return head.get_value().remove_item_from_head();
    }


    public SmallList remove_from_head()
    {
        SmallList buf_result = head.item;

        if(head != null)
        {
            head = head.next;
            count--;
            return buf_result;
        }
        else
        {
            System.out.println("Error: can't delete *HEAD* element");
            return null;
        }
    }

    private SmallList remove_on_position(int _pos)
    {
        BigListNode prev = null;
        BigListNode cur = head;

        if(_pos > get_count() || _pos <= 0)
        {
            System.err.println("scala_pack.BigList (remove_on_position): u write wrong position " + _pos);
            System.err.println("Need 1-" + get_count());
            return null;
        }

        SmallList buf_cur = null;

        for (int buf_pos = 1 ; buf_pos <= count; prev = cur, cur = cur.next, buf_pos++)
        {
            if (buf_pos == _pos)
            {
                if (cur == head)
                {
                    buf_cur = cur.get_value();
                    remove_from_head();
                    break;
                }
                else
                {
                    buf_cur = cur.get_value();
                    prev.next = cur.next;
                    cur = null;
                    count--;
                    break;
                }
            }
        }
        return buf_cur;
    }

    public UserType remove_item_on_position(int logical_position)
    {
        Pair<SmallList, Integer> pair = get_physical_pos(logical_position);

        if (logical_position <= 0 || logical_position > inner_count())
        {
            System.err.println("scala_pack.BigList (insert_on_position): you write wrong position " + logical_position);
            System.err.println("Need log_pos > 0");
            return null;
        }

        return pair.getList().remove_item_on_position(pair.getPos());
    }

    private boolean insert_on_position(int _pos, SmallList _item)
    {
        BigListNode prev = null;
        BigListNode cur = head;

        if (_pos == get_count() + 1)
        {
            push(_item);
            return true;
        }

        if (_pos > get_count() || _pos <= 0)
        {
            System.err.println("scala_pack.BigList (insert_on_position): u write wrong position " + _pos);
            System.err.println("Need 1-" + get_count());
            return false;
        }

        switch (_pos)
        {
            case 1:
            {
                push_to_head(_item);
                return true;
            }
            default:
            {
                for (int buf_pos = 1; ; prev = cur, cur = cur.next, buf_pos++)
                {
                    if (buf_pos == _pos)
                    {
                        BigListNode newNode = new BigListNode(_item, cur);
                        prev.next = newNode;
                        count++;
                        return true;
                    }
                }
            }
        }
    }

    public boolean insert_item_on_position(int logical_position, UserType _item)
    {
        Pair<SmallList, Integer> pair = get_physical_pos(logical_position);

        if (logical_position <= 0)
        {
            System.err.println("BigList (insert_on_position): you write wrong position " + logical_position);
            System.err.println("Need log_pos > 0");
            return false;
        }

        if (logical_position >= inner_count())
        {
            push(_item);
            return true;
        }

        if(pair.getList() == head.item && pair.getPos() == 1)
        {
            push_to_head(_item);
            return true;
        }

        pair.getList().insert_item_on_position(pair.getPos(), _item);
        return false;
    }

    private SmallList get_on_position(int _pos)
    {
        BigListNode cur = head;

        if(_pos > get_count() || _pos <= 0)
        {
            System.err.println("scala_pack.BigList (get_on_position): u write wrong position " + _pos);
            System.err.println("Need 1-" + get_count());
        }

        for (int buf_pos = 1; cur.next != null; cur = cur.next, buf_pos++)
        {
            if (buf_pos == _pos)
            {
                return cur.get_value();
            }
        }
        return cur.get_value();
    }

    @Override
    public UserType get_item_on_position(int logical_position)
    {
        Pair<SmallList, Integer> pair = get_physical_pos(logical_position);

        if (logical_position <= 0 || logical_position > inner_count())
        {
            System.err.println("BigList (insert_on_position): you write wrong position " + logical_position);
            System.err.println("Need log_pos > 0");
            return null;
        }

        return  pair.getList().get_item_on_position(pair.getPos());
    }

    @Override
    public String print_list()
    {
        String result = "";
        int i = 1;

        for (BigListNode cur = head;; cur = cur.next, i++)
        {
            result += i + ": " + cur.item.to_array() + "\n";

            if (  cur.next == null)
                break;
        }

        return result;
    }

    @Override
    public boolean change_item_on_pos(int logical_position, UserType _new_value)
    {
        return (get_item_on_position(logical_position).parse_value(_new_value.toString()))!=null;
    }

    public int inner_count()
    {
        int big_count = 0;

        for (BigListNode cur = head;; cur = cur.next)
        {
            big_count+=cur.item.get_count();

            if (  cur.next == null)
                break;
        }

        return big_count;
    }
    public int get_count()
    {
        int buf_count = count;
        return buf_count;

    }

    private void comp_and_swap(UserType obj, ArrayList arr, int _i, int _j, int _direction)
    {
        if (((obj.get_type_Comparator().compare(arr.get(_i),arr.get(_j)) == 1) && _direction == 1)
                                                ||
            ((obj.get_type_Comparator().compare(arr.get(_i),arr.get(_j)) == -1) && _direction == 0))
        {
            UserType buf_i = (UserType) arr.get(_i);
            UserType buf_j = (UserType) arr.get(_j);

            arr.remove(_i); arr.add(_i,buf_j);
            arr.remove(_j); arr.add(_j,buf_i);
        }
    }

    private void bitonic_merge(ArrayList arr, int _low, int _count, int _direction)
    {
        if (_count > 1)
        {
            int _buf_count = _count / 2;

            for (int i = _low; i < _low + _buf_count; i++)
            {
                comp_and_swap((UserType) arr.get(i), arr, i, i + _buf_count, _direction);
            }
            bitonic_merge(arr, _low, _buf_count, _direction);
            bitonic_merge(arr, _low + _buf_count, _buf_count, _direction);
        }
    }

    private void bitonic_sort(ArrayList arr, int _low, int _count, int _direction)
    {
        if (_count > 1)
        {
            int _buf_count = _count / 2;

            bitonic_sort(arr, _low, _buf_count, 1);
            bitonic_sort(arr,_low + _buf_count, _buf_count, 0);

            bitonic_merge(arr, _low, _count, _direction);
        }
    }

    public void sort_list()
    {
        String type_data = get_head().item.get_item_on_position(1).type_name();
        ArrayList arr = new ArrayList<>();

        for (BigListNode cur = head;; cur = cur.next)
        {
            arr.addAll(cur.item.to_array());

            if (cur.next == null)
                break;
        }

        int to_remove_buf_elements = 0;
        if (((arr.size() > 0) && ((arr.size() & (arr.size() - 1)) == 0))) // если количество элементов кратно степени 2ки
        {
            bitonic_sort(arr, 0, arr.size(), 1);
        }
        else
        {
            int buf_size = arr.size();
            to_remove_buf_elements = 0;

            while (!((arr.size() & (arr.size() - 1)) == 0))
            {
                arr.add(UserFactory.get_builder_by_name(type_data)); // добавление фиктивных элементов для получения нужной размерности (количество элементов кратно степени 2ки)
                to_remove_buf_elements++;
            }

            bitonic_sort(arr, 0, arr.size(), 1);

            remove_list();
        }

//        System.out.println(arr.toString());

        push(new SmallList());

        for (int i = 0 + to_remove_buf_elements; i < arr.size(); i++) {

            push((UserType) arr.get(i));
        }
    }

    public void forEach(DoWith action)
    {
        ArrayList arr = new ArrayList<>(to_array_inner());

        for (int i = 0; i < arr.size(); i++) {

            String str;

            if (arr.get(i) == null) str = "null ";
            else str = arr.get(i).toString() + " ";
            action.doWith(str);
        }
    }

    private ArrayList to_array_inner()
    {
        ArrayList<Object> arrayList = new ArrayList<>();

        for (BigListNode cur = head; cur != null; cur = cur.next)
        {
            arrayList.addAll(cur.item.to_array());
        }

        return  arrayList;
    }

    @Override
    public ArrayList to_array()
    {
        ArrayList<SmallList> array = new ArrayList<>();

        if (head != null)
        {
            for (BigListNode cur = head; cur != null; cur = cur.next)
            {
                array.add(cur.item);
            }
            return array;
        }
        else
        {
            return null;
        }
    }

    private Pair<SmallList, Integer> get_physical_pos(int _pos)
    {
        Pair<SmallList, Integer> pair = null;

        for (BigListNode cur = head;; cur = cur.next)
        {
            if(_pos <= cur.item.get_count())
            {
                pair = new Pair<>(cur.item, _pos);
                break;
            }
            else
            {
                _pos-=cur.item.get_count();
            }

            if (  cur.next == null)
                break;
        }
        return pair;
    }

    public void balance_list(int for_balance_count)
    {
        Vector arr = new Vector<>(to_array_inner());

        remove_list();

        for (int i = 0; i < arr.size(); i+=for_balance_count)
        {
            push(new SmallList());

            for (int j = i, k = 0; k < for_balance_count; j++, k++)
            {
                if (j >= arr.size())
                    break;
                tail.get_value().push((UserType) arr.get(j));
            }
        }
    }

    @Override
    public void remove_list()
    {
        while (count!=0)
        {
            remove_from_head();
        }
    }
}
