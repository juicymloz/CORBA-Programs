package interfaz;


/**
* interfaz/PeerPOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from D:/Javier/Universidad/NetBeans Projects/AnilloCorba/src/peer.idl
* martes 3 de mayo de 2022 07:00:22 PM CDT
*/

public abstract class PeerPOA extends org.omg.PortableServer.Servant
 implements interfaz.PeerOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("soyElCoordinador", new java.lang.Integer (0));
    _methods.put ("enviarHora", new java.lang.Integer (1));
    _methods.put ("enviarMensaje", new java.lang.Integer (2));
    _methods.put ("sleepPeer", new java.lang.Integer (3));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {
       case 0:  // interfaz/Peer/soyElCoordinador
       {
         int id = in.read_long ();
         String msj = in.read_string ();
         this.soyElCoordinador (id, msj);
         out = $rh.createReply();
         break;
       }

       case 1:  // interfaz/Peer/enviarHora
       {
         int id = in.read_long ();
         String msj = in.read_string ();
         this.enviarHora (id, msj);
         out = $rh.createReply();
         break;
       }

       case 2:  // interfaz/Peer/enviarMensaje
       {
         int id = in.read_long ();
         String msj = in.read_string ();
         this.enviarMensaje (id, msj);
         out = $rh.createReply();
         break;
       }

       case 3:  // interfaz/Peer/sleepPeer
       {
         this.sleepPeer ();
         out = $rh.createReply();
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:interfaz/Peer:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public Peer _this() 
  {
    return PeerHelper.narrow(
    super._this_object());
  }

  public Peer _this(org.omg.CORBA.ORB orb) 
  {
    return PeerHelper.narrow(
    super._this_object(orb));
  }


} // class PeerPOA