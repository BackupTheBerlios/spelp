package BourseCorba;


/**
* BourseCorba/ListeHistoriquesHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from bourse.idl
* dimanche 1 juin 2008 16 h 45 CEST
*/

abstract public class ListeHistoriquesHelper
{
  private static String  _id = "IDL:BourseCorba/ListeHistoriques:1.0";

  public static void insert (org.omg.CORBA.Any a, BourseCorba.Historique[] that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static BourseCorba.Historique[] extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = BourseCorba.HistoriqueHelper.type ();
      __typeCode = org.omg.CORBA.ORB.init ().create_sequence_tc (0, __typeCode);
      __typeCode = org.omg.CORBA.ORB.init ().create_alias_tc (BourseCorba.ListeHistoriquesHelper.id (), "ListeHistoriques", __typeCode);
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static BourseCorba.Historique[] read (org.omg.CORBA.portable.InputStream istream)
  {
    BourseCorba.Historique value[] = null;
    int _len0 = istream.read_long ();
    value = new BourseCorba.Historique[_len0];
    for (int _o1 = 0;_o1 < value.length; ++_o1)
      value[_o1] = BourseCorba.HistoriqueHelper.read (istream);
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, BourseCorba.Historique[] value)
  {
    ostream.write_long (value.length);
    for (int _i0 = 0;_i0 < value.length; ++_i0)
      BourseCorba.HistoriqueHelper.write (ostream, value[_i0]);
  }

}
