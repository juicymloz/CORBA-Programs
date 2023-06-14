package interfaz;


/**
* interfaz/PeerPOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from D:/Javier/Universidad/NetBeans Projects/LectorEscritorCorba/src/peer.idl
* mi�rcoles 4 de mayo de 2022 06:18:06 PM CDT
*/

public abstract class PeerPOA extends org.omg.PortableServer.Servant
 implements interfaz.PeerOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("coordinador", new java.lang.Integer (0));
    _methods.put ("lector", new java.lang.Integer (1));
    _methods.put ("working", new java.lang.Integer (2));
    _methods.put ("changeWorking", new java.lang.Integer (3));
    _methods.put ("actualizarPeticiones", new java.lang.Integer (4));
    _methods.put ("obtenerPeticioneslector", new java.lang.Integer (5));
    _methods.put ("obtenerPeticionesescritor", new java.lang.Integer (6));
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
       case 0:  // interfaz/Peer/coordinador
       {
         boolean $result = false;
         $result = this.coordinador ();
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }

       case 1:  // interfaz/Peer/lector
       {
         boolean $result = false;
         $result = this.lector ();
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }

       case 2:  // interfaz/Peer/working
       {
         boolean $result = false;
         $result = this.working ();
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }

       case 3:  // interfaz/Peer/changeWorking
       {
         this.changeWorking ();
         out = $rh.createReply();
         break;
       }

       case 4:  // interfaz/Peer/actualizarPeticiones
       {
         String lectores[] = interfaz.PeerPackage.petHelper.read (in);
         String escritores[] = interfaz.PeerPackage.petHelper.read (in);
         this.actualizarPeticiones (lectores, escritores);
         out = $rh.createReply();
         break;
       }

       case 5:  // interfaz/Peer/obtenerPeticioneslector
       {
         String $result[] = null;
         $result = this.obtenerPeticioneslector ();
         out = $rh.createReply();
         interfaz.PeerPackage.petHelper.write (out, $result);
         break;
       }

       case 6:  // interfaz/Peer/obtenerPeticionesescritor
       {
         String $result[] = null;
         $result = this.obtenerPeticionesescritor ();
         out = $rh.createReply();
         interfaz.PeerPackage.petHelper.write (out, $result);
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