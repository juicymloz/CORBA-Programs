package interfaz;


/**
* interfaz/PeerOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from D:/Javier/Universidad/NetBeans Projects/AnilloCorba/src/peer.idl
* martes 3 de mayo de 2022 07:00:22 PM CDT
*/

public interface PeerOperations 
{
  void soyElCoordinador (int id, String msj);
  void enviarHora (int id, String msj);
  void enviarMensaje (int id, String msj);
  void sleepPeer ();
} // interface PeerOperations