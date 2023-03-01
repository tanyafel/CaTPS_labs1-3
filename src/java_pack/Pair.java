package java_pack;

public class Pair <SmallList, Integer>
{
    private SmallList list;
    private int pos;

    public Pair(SmallList _list, int _pos)
    {
        this.list = _list;
        this.pos = _pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public int getPos() {
        return pos;
    }

    public void setList(SmallList list) {
        this.list = list;
    }

    public SmallList getList() {
        return list;
    }
}
