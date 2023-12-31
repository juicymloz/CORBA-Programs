package interfaz;


/**
* interfaz/_PeerStub.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from D:/Javier/Universidad/NetBeans Projects/BullyCorba/src/peer.idl
* viernes 29 de abril de 2022 12:32:58 PM CDT
*/

public class _PeerStub extends org.omg.CORBA.portable.ObjectImpl implements interfaz.Peer
{

  public void enviarHora (int id, String msj)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("enviarHora", true);
                $out.write_long (id);
                $out.write_string (msj);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                enviarHora (id, msj        );
            } finally {
                _releaseReply ($in);
            }
  } // enviarHora

  public void enviarMensaje (int id, String msj)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("enviarMensaje", true);
                $out.write_long (id);
                $out.write_string (msj);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                enviarMensaje (id, msj        );
            } finally {
                _releaseReply ($in);
            }
  } // enviarMensaje

  public void coordinador ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("coordinador", true);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                coordinador (        );
            } finally {
                _releaseReply ($in);
            }
  } // coordinador

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:interfaz/Peer:1.0"};

  public String[] _ids ()
  {
    return (String[])__ids.clone ();
  }

  private void readObject (java.io.ObjectInputStream s) throws java.io.IOException
  {
     String str = s.readUTF ();
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     org.omg.CORBA.Object obj = orb.string_to_object (str);
     org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl) obj)._get_delegate ();
     _set_delegate (delegate);
   } finally {
     orb.destroy() ;
   }
  }

  private void writeObject (java.io.ObjectOutputStream s) throws java.io.IOException
  {
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     String str = orb.object_to_string (this);
     s.writeUTF (str);
   } finally {
     orb.destroy() ;
   }
  }
} // class _PeerStub
