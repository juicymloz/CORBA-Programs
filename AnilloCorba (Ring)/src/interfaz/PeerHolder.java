package interfaz;

/**
* interfaz/PeerHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from D:/Javier/Universidad/NetBeans Projects/AnilloCorba/src/peer.idl
* martes 3 de mayo de 2022 07:00:22 PM CDT
*/

public final class PeerHolder implements org.omg.CORBA.portable.Streamable
{
  public interfaz.Peer value = null;

  public PeerHolder ()
  {
  }

  public PeerHolder (interfaz.Peer initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = interfaz.PeerHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    interfaz.PeerHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return interfaz.PeerHelper.type ();
  }

}
