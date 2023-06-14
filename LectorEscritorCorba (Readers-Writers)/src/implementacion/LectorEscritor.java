/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementacion;

import interfaz.Peer;
import interfaz.PeerHelper;
import interfaz.PeerPOA;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.BindingIteratorHolder;
import org.omg.CosNaming.BindingListHolder;
import org.omg.CosNaming.BindingType;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextHelper;
import org.omg.CosNaming.NamingContextPackage.AlreadyBound;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import org.omg.PortableServer.POAManagerPackage.AdapterInactive;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

/**
 *
 * @author Javier
 */
public class LectorEscritor extends javax.swing.JFrame {
   
    //Metodo que devuelve un ArrayList con los nombres de los Peers activos en la ejecucion del programa...
    //... obtenidos a traves del servicio de nombres de CORBA.
    public static ArrayList<String> printContext(NamingContext nc, String parent) {
        try {
            final int batchSize = 1000;
            ArrayList<String> referencias = new ArrayList<>();
            BindingListHolder bList = new BindingListHolder( );
            BindingIteratorHolder bIterator = new BindingIteratorHolder( );

            nc.list( batchSize, bList, bIterator );

            for ( int i=0; i < bList.value.length; i++ ) {
                NameComponent[] name = { bList.value[i].binding_name[0] };
                if (bList.value[i].binding_type == BindingType.ncontext) {
                    NamingContext context = NamingContextHelper.narrow(nc.resolve( name ) );
                    //Peer object = PeerHelper.narrow(nc.resolve_str("asd"));
                    printContext( context, parent + name[0].id + "." );
                } else {
                    //System.out.println( parent + name[0].id );
                    referencias.add(name[0].id);
                }
            }
            return referencias;
        } catch (Exception e) {
            System.out.println("ERROR : " + e) ;
            return null;
        }
    }
    /**
     * Creates new form BullyCorba
     */
    
    //Variables globales del programa.
    public static String id="";  //Identificador unico de cada Peer.
    public static NamingContextExt ncRef;  //Servicio de nombres.
    public static ArrayList<String> refer;  //ArrayList - Nombre de los Peers.
    public static ArrayList<String> peticionesLectores = new ArrayList<>();  //ArrayList que contendra las peticiones de los Lectores.
    public static ArrayList<String> peticionesEscritores = new ArrayList<>();  ////ArrayList que contendra las peticiones de los Escritores.
    //Dos arrays comunes que son exactamente para lo mismo que los ArrayList anteriores, esto se hizo por el tipo de dato que CORBA acepta en...
    //... los parametros de sus metodos en el IDL, en otras palabras, no podiamos pasar ArrayList por que no lo conoce pero si los Arrays comunes.
    public static String[] arrayLector = {};
    public static String[] arrayEscritor = {}; 
    public static boolean coordinador = false;  //Saber si un Peer es coordinador.
    public static boolean lector = false;  //Saber si un Peer es lector.
    public static boolean escritor = false;  //Saber si un Peer es escritor.
    public static boolean working = false;  //Variable que utilizamos para indicar quien esta utilizando el recurso.
    public LectorEscritor() {
        initComponents();
        if(coordinador){ //Muestra en su pantalla que es coordinador y no tendra botones.
            this.setTitle("Coordinador: "+id);
            this.jButton1.setVisible(false);
            this.jButton2.setVisible(false);
        }
        if(lector){
            this.setTitle("Lector: "+id);  //Muestra en pantalla que es un lector y habilita solo el boton "Entrar".
            this.jButton2.setVisible(false);
        }
        if(escritor){
            this.setTitle("Escritor: "+id);  //Muestra en pantalla que es un escritor y habilita solo el boton "Entrar".
            this.jButton2.setVisible(false);
        }
        jTextArea1.setEditable(false);  //En cualquier caso el area de texto no se puede editar.
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jButton1.setText("Entrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Salir");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(138, 138, 138)
                        .addComponent(jButton2)))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(26, 26, 26))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        NameComponent pathaux[];
        try {
            refer = printContext(ncRef, "");
            pathaux = ncRef.to_name(this.id);
            ncRef.unbind(pathaux);
        } catch (org.omg.CosNaming.NamingContextPackage.InvalidName | NotFound | CannotProceed ex) {
            
        }
    }//GEN-LAST:event_formWindowClosing

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        
        // -- Al mostrar la ventana del Peer --
        
        //1. Actualizamos los Arrays de las peticiones de los lectores y escritores en la red...
        //   respectivamente.
        //2. Si el Peer es el coordinador, entrara en un ciclo infinito utilizando un timer donde...
        //   cada seg. actualiza la pantalla con la informacion de lo que sucede con el recurso y...
        //   las peticiones.
        
        refer = printContext(ncRef, "");  //Actualiza la lista de los Peers activos en el sistema.
        if(refer.size()>1){  //Si hay mas de 1 Peer en la red.
            try {
                //Aqui lo que nos interesa es actualizar nuestros Arrays vacios de las peticiones con la informacion que tenga algún otro Peer,...
                //... es por ello que, nos moveremos a través de los Peers activos en la red hasta obtener cualquiera que no seamos...
                //... nosotros mismos. Una vez hecho solamente actualizamos dichos Arrays y rompemos el ciclo, en pocas palabras,
                //... puede solamente realizar max. 2 iteraciones el for por razonamiento logico.
                for (int i = 0; i < refer.size(); i++) {
                    if(!refer.get(i).equals(id)){  //Si dicho Peer es diferente a nosotros mismos.
                        Peer object = PeerHelper.narrow(ncRef.resolve_str(refer.get(i)));  //Obtenemos su referencia.
                        System.out.println(refer.get(i));
                        //Extraemos la informacion de sus Arrays comunes.
                        arrayLector = object.obtenerPeticioneslector();
                        arrayEscritor = object.obtenerPeticionesescritor();
                        object = PeerHelper.narrow(ncRef.resolve_str(id));  //Finalmente obtenemos nuestra propia referencia para...
                        object.actualizarPeticiones(arrayLector, arrayEscritor);  //... actualizar todos los nuestros.
                        break;  //Rompe el ciclo.
                    }
                }
            } catch (NotFound | CannotProceed | org.omg.CosNaming.NamingContextPackage.InvalidName ex) {
                Logger.getLogger(LectorEscritor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(coordinador){  //Si es el Peer coordinador.
            Timer impresionDePeticiones = new Timer(1000, new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    try{
                        String msj = "--Procesos dentro del Recurso \n";
                        refer = printContext(ncRef, "");  //Actualiza la lista de los Peers activos en el sistema.
                        for (int i = 0; i < refer.size(); i++) {  //Se mueve Peer a Peer...
                            Peer object = PeerHelper.narrow(ncRef.resolve_str(refer.get(i))); //...obteniendo la referencia y...
                            if(object.working()){  //Verificando si ocupa el recurso.
                                msj+="  "+refer.get(i)+"\n";  //Mostrara quien de los Peers esta utilizando el recurso, si es que lo hay.
                            }
                        }
                        msj +="\n--Lista de Espera-- \n\n  --Lectores\n";  
                        for (int i = 0; i < peticionesLectores.size(); i++) {
                            msj+="  "+peticionesLectores.get(i)+" \n";  //Muestra peticiones de lectores en caso de que haya.
                        }
                        msj += "\n  --Escritores\n";  
                        for (int i = 0; i < peticionesEscritores.size(); i++) {
                            msj+="  "+peticionesEscritores.get(i)+" \n";  //Muestra peticiones de escritores en caso de que haya.
                        }
                        jTextArea1.setText(msj);  //Muestra toda la cadena "msj" en el Area de texto.
                    }catch (NotFound | CannotProceed | org.omg.CosNaming.NamingContextPackage.InvalidName ex) {
                        Logger.getLogger(LectorEscritor.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            impresionDePeticiones.start();  //Cada seg ejecuta las instrucciones del Timer.
        }
    }//GEN-LAST:event_formWindowOpened

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        // -- Boton ENTRAR --
        
        // El funcionamiento general del boton es el siguiente:
        //  1. Cuando el Peer hace click verifica primeramente.
        //      1a. Que dicho Peer no se encuentre en la lista de peticiones, indicando que 1. Ya ha dado click...
        //          previamente y 2. Hay otro Peer haciendo uso del recurso en cuestion.
        //      1b. Que dicho Peer no sea el mismo que se encuentra trabajando en el recurso.
        //      Si uno de estos dos casos se cumple mostramos en pantalla un mensaje de informacion.
        //  2. Si el Peer no se encuentra en peticiones ni es el que utiliza el recurso.
        //      2a. Se mueve Peer a Peer en busca de si hay alguno utilizando el recurso.
        //          2a1. Si no hay nadie pasa directamente a utilizarlo.
        //          2a2. Si lo hay existen 3 posibles casos:
        //                  1. Si el que trabaja es un lector y yo tambien soy lector, usamos el recurso juntos.
        //                  2. Si el que trabaja es un lector y yo un escritor, paso a la lista de peticiones de escritores.
        //                  3. Si el que trabaja es un escritor, ni un lector tanto un escritor puede utilizar el recurso
        //                     hasta que este deje el recurso y pasan a la lista de peticiones respectiva.
        
        try {
            refer = printContext(ncRef, "");  //Actualiza la lista de los Peers activos en el sistema.
            Peer object;  //Objeto Peer CORBA sin referenciar.
            int i;
            if(!peticionesLectores.contains(id) && !peticionesEscritores.contains(id) && !working){  //Si no esta en la lista de peticiones y no usa el recurso...             
                for (i = 0; i < refer.size(); i++) {  //Se mueve Peer a Peer...
                    object = PeerHelper.narrow(ncRef.resolve_str(refer.get(i))); //... obteniendo su referencia...
                    if(object.working()){  //... y verifica si se encuentra trabajando en el recurso.
                        if(object.lector() && lector==true){  //Si el que trabaja es un lector y yo tambien, pasamos juntos.
                            working = true;  //Cambiamos que este Peer ahora utiliza el recurso tambien.
                            jTextArea1.setText("\n--Estoy dentro del recurso 😎🤙");  //Mostramos un msj en pantalla.
                            jButton2.setVisible(true);  //Habilita el boton "Salir".
                        }
                        else{  //Caso contrario los agregamos a la lista de peticiones respectiva.
                            if(lector){  //Si soy lector me agrego a la lista de peticiones de lectores.
                                peticionesLectores.add(id);
                                arrayLector = new String[peticionesLectores.size()];
                                for (int j = 0; j < peticionesLectores.size(); j++) {
                                    arrayLector[j] = peticionesLectores.get(j);
                                }
                            }else{  //Si soy escritor me agrego a la lista de peticiones de escritores.
                                peticionesEscritores.add(id);
                                arrayEscritor = new String[peticionesEscritores.size()];
                                for (int j = 0; j < peticionesEscritores.size(); j++) {
                                    arrayEscritor[j] = peticionesEscritores.get(j);
                                }
                            }
                            //En ambos casos se agrega su peticion tanto al Array como al ArrayList.
                            jTextArea1.setText("\n--He solicitado un acceso al recurso 🙄👎");  //Muestra que ha solicitado un acceso.
                            jButton2.setVisible(false);  //Deshabilita el boton de "Salir"
                            //Finalmente mandamos la lista actualizada de las peticiones a todos los Peers en al red...
                            //... para que se actualicen con la nueva informacion.
                            for(int j = 0; j < refer.size(); j++) {
                                object = PeerHelper.narrow(ncRef.resolve_str(refer.get(j)));
                                object.actualizarPeticiones(arrayLector, arrayEscritor);
                            }
                        }
                        break;  //Rompe el ciclo for.
                    }
                }
                if(i==refer.size()){  //Si no se encontró ningun Peer utilizando el recurso, accede directamente a él.
                    working=true;  //Actualiza su variable indicando que esta en el recurso.
                    jTextArea1.setText("\n--Estoy dentro del recurso 😎🤙");  //Muestra esta accion en pantalla.
                    jButton2.setVisible(true);  //Habilita el boton de "Salir".
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "No seas gandalla cruck 😈😈\nYa haz solicitado tu acceso\no estas en el recurso");
            }
        } catch (NotFound | CannotProceed | org.omg.CosNaming.NamingContextPackage.InvalidName ex) {
                Logger.getLogger(LectorEscritor.class.getName()).log(Level.SEVERE, null, ex);
            }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
        //  -- Boton "SALIR" --
        
        // Funcionamiento general.
        // 1. Muestra en pantalla un msj de despedida, cambia el estado de su variable working a false y deshabilita el boton "Salir".
        // 2. Revisa las listas de peticiones de lectores y escritores, en este orden.
        //      2a. Si la lista de peticiones de lectores contiene peticiones.
        //          - Todos los lectores pasan a tomar el recurso.
        //      2b. Si no su cumplio 2a) revisa si la lista de peticiones de los escritores contiene peticiones.
        //          - Si tiene, pasa al primer escritor de la lista.
        // 3. Si ninguna lista contiene peticiones el recurso queda libre para el siguiente Peer que solicite su uso.
        // 4. Para ambos casos, hubo cambios en las listas puesto que se removieron peticiones, por tanto,...
        //    se actualizan los Arrays de las peticiones, mandamos las nuevas listas a todos los Peers de la red...
        //    y finaliza la ejecucion del botón.
        
        // ==NOTAS==
        // - Recordemos que los lectores siempre van a ser prioridad en este problema, es por ello que siempre se decide quien(es) entra(n) al recuso...
        //   en base a la lista de peticiones de los lectores.
        
        int i;
        jTextArea1.setText("\n--Hay la wachamos 🤠🤠");  //Muestra en pantalla que deja el recurso.
        working=false;  //Cambia el estado de su variable.
        jButton2.setVisible(false);  //Deshabilita el boton "Salir".
        if(!peticionesLectores.isEmpty()){  //Si la lista de peticiones de los lectores contiene peticiones...
            while(!peticionesLectores.isEmpty()){  //Mientras la lista no este vacia...
                try {
                    //Saca 1 a 1 los elementos de la lista y obtiene la referencia de su respectivo Peer.
                    Peer object = PeerHelper.narrow(ncRef.resolve_str(peticionesLectores.get(0)));
                    object.changeWorking();  //Cambia su estado de esperando a trabajando.
                } catch (NotFound | CannotProceed | org.omg.CosNaming.NamingContextPackage.InvalidName ex) {
                    Logger.getLogger(LectorEscritor.class.getName()).log(Level.SEVERE, null, ex);
                }
                peticionesLectores.remove(0);  //Va eliminando los elementos de la lista 1 a 1.
            }
        }
        else{  //Si la lista anterior no tenia peticiones.
            for(i = 0; i < refer.size(); i++) {  //Se mueve Peer a Peer...
                try {
                    Peer object;  //Objeto Peer CORBA sin referenciar.
                    object = PeerHelper.narrow(ncRef.resolve_str(refer.get(i)));  //... obteniendo su referencia...
                    if (object.working()){  //... y verifica si se encuentra trabajando en el recurso.
                        break;  //Rompe el ciclo en caso afirmativo.
                    }
                } catch (NotFound | CannotProceed | org.omg.CosNaming.NamingContextPackage.InvalidName ex) {
                    Logger.getLogger(LectorEscritor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(i == refer.size()){  //Si encontró que si hay otro(s) Peer(s) usando el recurso no entra al if.
                if(!peticionesEscritores.isEmpty()){  //Caso contrario, si la lista de peticiones de escritores contiene peticiones.
                    Peer object;  //Objeto Peer CORBA sin referenciar.
                    try {
                        object = PeerHelper.narrow(ncRef.resolve_str(peticionesEscritores.get(0)));  //Obtiene la referencia del primer escritor en la lista...
                        object.changeWorking();  //... y cambia su estado de esperando a trabajando.
                    } catch (NotFound | CannotProceed | org.omg.CosNaming.NamingContextPackage.InvalidName ex) {
                        Logger.getLogger(LectorEscritor.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    peticionesEscritores.remove(0);  //Elimina aquela peticion de la lista.
                }
            }
        }
        //En este punto se actualizan las listas de peticiones por los cambios hechos anteriormente.
        Peer object;
        arrayLector = new String[peticionesLectores.size()];
        for (int j = 0; j < peticionesLectores.size(); j++) {
            arrayLector[j] = peticionesLectores.get(j);
        }
        arrayEscritor = new String[peticionesEscritores.size()];
        for (int j = 0; j < peticionesEscritores.size(); j++) {
            arrayEscritor[j] = peticionesEscritores.get(j);
        }
        //Finalmente...
        for(int j = 0; j < refer.size(); j++) {  //Nos movemos Peer a Peer...
            try {
                object = PeerHelper.narrow(ncRef.resolve_str(refer.get(j)));  //... obteniendo su referencia...
                object.actualizarPeticiones(arrayLector, arrayEscritor);  //...y actualizando sus listas de peticiones con las nuevas.
            } catch (NotFound | CannotProceed | org.omg.CosNaming.NamingContextPackage.InvalidName ex) {
                Logger.getLogger(LectorEscritor.class.getName()).log(Level.SEVERE, null, ex);
            }    
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LectorEscritor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LectorEscritor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LectorEscritor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LectorEscritor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        
        try{
            
            // Crea e inicializa el ORB de CORBA
            ORB orb = ORB.init(args, null);
            // Referencia al POA raiz y activa el manejador de POA
            POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootpoa.the_POAManager().activate();
            
            PeerImpl peer = new PeerImpl();
            // Obtiene la referencia al objeto del servidor
            
            org.omg.CORBA.Object ref = rootpoa.servant_to_reference(peer);
            Peer href = PeerHelper.narrow(ref);
            
            // Obtiene el naming context de la raiz
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            // Usa el NamingContextExt en lugar de NamingContext. 
            // Esto es parte de Interoperable naming Service.  
            ncRef = NamingContextExtHelper.narrow(objRef);
            
            //Pedimos el ID al usuario.
            id = JOptionPane.showInputDialog("Introduce tu ID");
            LectorEscritor.id = id;  //Asignamos dicho ID al titulo del JFrame.
            //Guardamos la referencia a este objeto en el servicio de nombres.
            NameComponent path[] = ncRef.to_name( id );
            ncRef.bind(path, href);
            
            //Actualizamos el ArrayList que contiene los nombres de los Peers.
            refer = printContext(ncRef, "");
            
            //En caso de que no se cierre un Peer como debe ser (desde la ventana), pj. por consola ...
            //... Descomentar el for justo debajo, correr el programa y volver a comentarlo. Su funcion es...
            //... limpiar completamente nuestro servicio de nombres con el fin de borrar la referencia de...
            //... objeto de dicho Peer que ya no existe.
            
            /*
            for (int i = 0; i<refer.size() ; i++) {
                NameComponent pathaux[] = ncRef.to_name(refer.get(i));
                ncRef.unbind(pathaux);
            }
            */
            
            //Arrays que contienen las opciones con las que podremos entrar a la red.
            //1. En caso de que no exista un Peer "coordinador" le habilita la opcion al Peer de serlo.
            //2. Si ya exise, unicamente le mostrará que puede acceder como escritor o lector.
            String[] options1 = { "Lector", "Escritor", "Coordinador"};
            String[] options2 = { "Lector", "Escritor"};
            boolean flag = false;  //Bandera para controlar que opciones le mostramos a los Peers.
            
            //Para ello cada que un nuevo Peer empieza a conectarse, revisamos de entre todos los activos...
            //... de la red si alguno ya es coordinador, que es donde cambiamos el valor del booleano anterior.
            if(refer.size()>1){
                for (int i = 0; i < refer.size(); i++) {
                    if(!refer.get(i).equals(id)){
                        Peer object = PeerHelper.narrow(ncRef.resolve_str(refer.get(i)));
                        if(object.coordinador()){
                            flag = true;
                            break;
                        }
                    }
                }
            }
            
            int rol;  //Guarda la opcion clickeada del OptionPane.
            if(flag){  //Si ya existe coordinador.
                rol = JOptionPane.showOptionDialog(null, "Selecciona tu rol porfis",
                "Presiona un botón",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options2, options2[0]);
            }   
            else{  //Si no existe coordinador.
                rol = JOptionPane.showOptionDialog(null, "Selecciona tu rol porfis",
                "Presiona un botón",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options1, options1[0]);
            }
            
            //Asignamos dicho rol en base a la opcion elegida.
            switch(rol){
                case 0:
                    lector = true;
                    break;
                case 1:
                    escritor = true;
                    break;
                case 2:
                    coordinador = true;
                    break;
            }
            
            //Muestra en consola la lista Peers activos en el sistema.
            for (int i = 0; i < refer.size(); i++) {
                System.out.println(refer.get(i));
            }
            
            //Activa la interfaz grafica
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    new LectorEscritor().setVisible(true);
                }
            });
            //Inicializamos el objeto CORBA.
            orb.run();
        }catch(InvalidName | AdapterInactive | ServantNotActive | WrongPolicy | HeadlessException | org.omg.CosNaming.NamingContextPackage.InvalidName | NotFound | CannotProceed | AlreadyBound e){
            
        }
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private static javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
    static class PeerImpl extends PeerPOA {

        @Override
        public boolean coordinador() {  //DEVUELVE LA VARIABLE COORDINADOR.
            return coordinador;
        }
        @Override
        public boolean working() {  //DEVUELVE LA VARIABLE WORKING.
            return working;
        }

        @Override
        public boolean lector() {  //DEVUELVE LA VARIABLE LECTOR.
            return lector;
        }
        
        @Override
        public String[] obtenerPeticioneslector() {  //DEVUELVE EL ARRAY DE LAS PETICIONES DE LOS LECTORES.
            return arrayLector;
        }

        @Override
        public String[] obtenerPeticionesescritor() {  //DEVUELVE EL ARRAY DE LAS PETICIONES DE LOS LECTORES.
            return arrayEscritor;
        }

        @Override
        public void actualizarPeticiones(String[] lectores, String[] escritores) {  //ACTUALIZA LOS ARRAYS Y ARRAYLISTS DE LAS PETICIONES.
            peticionesLectores.clear();
            peticionesEscritores.clear();
            for (int i = 0; i < lectores.length; i++) {
                peticionesLectores.add(lectores[i]);
            }
            for (int i = 0; i < escritores.length; i++) {
                peticionesEscritores.add(escritores[i]);
            }
            arrayLector = lectores.clone();
            arrayEscritor = escritores.clone();
        }

        @Override
        public void changeWorking() {  //Metodo cuando un Peer de la lista de peticiones pasa a hacer uso del recurso.
            jButton2.setVisible(true);  //Habilita el boton "Salir".
            working = true;   //Cambia el estado de su variable working.
            jTextArea1.setText("\n--Estoy dentro del recurso 😎🤙");  //Muestra en pantalla que esta en el recurso.
        }
    }
}
