package java_pack.Types;

import java.util.Comparator;

public interface UserType extends  Comparator
{
    String type_name();
    Object create();
    Object clone();
    Object read_value();
    Object parse_value(String ss);
    Comparator get_type_Comparator();
}
