package BourseCorba;


/**
* BourseCorba/BourseHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from bourse.idl
* mardi 13 mai 2008 19 h 17 CEST
*/

abstract public class BourseHelper
{
  private static String  _id = "IDL:BourseCorba/Bourse:1.0";

  public static void insert (org.omg.CORBA.Any a, BourseCorba.Bourse that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static BourseCorba.Bourse extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (BourseCorba.BourseHelper.id (), "Bourse");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static BourseCorba.Bourse read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_BourseStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, BourseCorba.Bourse value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static BourseCorba.Bourse narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof BourseCorba.Bourse)
      return (BourseCorba.Bourse)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      BourseCorba._BourseStub stub = new BourseCorba._BourseStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static BourseCorba.Bourse unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof BourseCorba.Bourse)
      return (BourseCorba.Bourse)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      BourseCorba._BourseStub stub = new BourseCorba._BourseStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
