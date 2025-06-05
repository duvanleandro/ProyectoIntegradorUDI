package Ejecutar;

import Entidad.Usuario;
import Logica.GestionarSanciones;
import com.mycompany.integrador4a.igu.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import Logica.IniciarSesion;

public class EjecutarSistema {

    static MenuUsuario menu;
    static MenuAdmin menuAd;

    static RealizarSolicitud RSolicitud;
    static PedirSoporte PSoporte;
    static InformacionApp IApp;
    static PedirPrestamo PPrestamo;
    static VerMisPrestamos VMPrestamo;
    static RealizarEncuestaGUI REncuesta;
    static GestionarSolicitudes GSolicitudes;
    static RealizarSancion RSancion;
    static GestionarUsuariosGUI GUsuarios;
    static AgregarUsuariosGUI AUsuarios;
    static GestionarSoporteGUI GSoporte;
    static GestionarSancionesGUI GSanciones;
    static VerMisSanciones VMSanciones;
    static AdministrarBD ABD;
    static GestionarEncuestaGUI GEncuesta;

    public static void main(String[] args) {
        

        java.awt.EventQueue.invokeLater(() -> {
            Login login = new Login();
            login.setVisible(true);
            login.setLocationRelativeTo(null);

            // Registro del listener del botón “Login”
            login.getBtnLogin().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // 1) Leer campos
                    String correoIngresado = login.getUsuario();
                    String claveIngresada = login.getPassword();

                    // 2) Validar que no estén vacíos
                    if (correoIngresado.isEmpty() || claveIngresada.isEmpty()) {
                        JOptionPane.showMessageDialog(
                            login,
                            "Debes ingresar usuario y contraseña.",
                            "Error",
                            JOptionPane.WARNING_MESSAGE
                        );
                        return;
                    }

                    // 3) Validar credenciales con la BD
                    IniciarSesion ls = new IniciarSesion();
                    Usuario usuario = ls.validarCredenciales(correoIngresado, claveIngresada);

                    if (usuario == null) {
                        JOptionPane.showMessageDialog(
                            login,
                            "Usuario o contraseña incorrectos.",
                            "Error de autenticación",
                            JOptionPane.ERROR_MESSAGE
                        );
                        return;
                    }

                    String rol = usuario.getNivel();

                    // 4) Credenciales válidas
                    login.setVisible(false);

                    // 5) Inicializo ventanas generales si aún no existen
                    
                    if (menuAd == null)      menuAd      = new MenuAdmin();
                    if (RSolicitud == null)  RSolicitud  = new RealizarSolicitud();
                    if (IApp == null)        IApp        = new InformacionApp();
                    if (GSoporte == null)  GSoporte  = new GestionarSoporteGUI();
                    if (GSanciones == null)     GSanciones     = new GestionarSancionesGUI();
                    if (ABD == null) ABD = new AdministrarBD();
                    if (GEncuesta == null) GEncuesta = new GestionarEncuestaGUI();

                    // Crear NUEVAS instancias para ventanas que dependen del usuario activo
                    menu = new MenuUsuario(usuario);
                    VMPrestamo = new VerMisPrestamos(usuario);
                    PPrestamo = new PedirPrestamo(usuario, VMPrestamo);
                    GSolicitudes = new GestionarSolicitudes(usuario);
                    GUsuarios = new GestionarUsuariosGUI(GSolicitudes);
                    AUsuarios = new AgregarUsuariosGUI(GUsuarios);
                    PSoporte = new PedirSoporte(usuario, GSoporte);
                    RSancion = new RealizarSancion(GSanciones);
                    VMSanciones = new VerMisSanciones(usuario);
                    REncuesta = new RealizarEncuestaGUI(usuario);
                    


                    // 6) Mostrar solo el menú que corresponda según el rol
                    if (rol.equalsIgnoreCase("ADMIN")) {
                        menuAd.setVisible(true);
                        menuAd.setLocationRelativeTo(null);
                    } else {
                        menu.setVisible(true);
                        menu.setLocationRelativeTo(null);
                    }
                    // MENU USUARIO
           
                    
                    menu.getLblSalir().addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent e) {
                            menu.setVisible(false);
                            limpiarVentanasUsuario();
                            login.setVisible(true);
                            
                        }
                    });

                    menu.getLblInformacion().addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent e) {
                            menu.setVisible(false);
                            IApp.setVisible(true);
                            IApp.setLocationRelativeTo(null);
                        }
                    });

                menu.getLblSolicitud().addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        GestionarSanciones gestorSanciones = new GestionarSanciones();

                        if (gestorSanciones.tieneSanciones(menu.getIdUsuario())) {
                            JOptionPane.showMessageDialog(menu, 
                                "No puedes hacer solicitudes porque tienes sanciones activas.", 
                                "Acceso denegado", 
                                JOptionPane.WARNING_MESSAGE);
                            return; // no abrir ventana
                        }

                        menu.setVisible(false);
                        RSolicitud.setVisible(true);
                        RSolicitud.setLocationRelativeTo(null);
                    }
                });
                
                    menu.getLblEncuesta().addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent e) {
                            menu.setVisible(false);
                            REncuesta.setVisible(true);
                            REncuesta.setLocationRelativeTo(null);
                        }
                    });

                    menu.getLblMisSanciones().addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent e) {
                            menu.setVisible(false);
                            VMSanciones.setVisible(true);
                            VMSanciones.setLocationRelativeTo(null);
                        }
                    });
                    
                     VMSanciones.getLblMenuPrincipal().addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent e) {
                            VMSanciones.setVisible(false);
                            menu.setVisible(true);
                            menu.setLocationRelativeTo(null);
                        }
                     });
                        
                        VMSanciones.getLblSalir().addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent e) {
                            VMSanciones.setVisible(false);
                            menu.setVisible(true);
                            menu.setLocationRelativeTo(null);
                        }
                        });
                  
                    VMSanciones.getLblSalir().addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent e) {
                            VMSanciones.setVisible(false);
                            limpiarVentanasUsuario();
                            login.setVisible(true);
                        }
                    });
                    // RSolicitud
                    RSolicitud.getBtnMenuPrincipal().addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent e) {
                            RSolicitud.setVisible(false);
                            menu.setVisible(true);
                        }
                    });

                    RSolicitud.getBtnSalir().addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent e) {
                            RSolicitud.setVisible(false);
                            limpiarVentanasUsuario();
                            login.setVisible(true);
                        }
                    });

                    RSolicitud.getLblPrestarAudio().addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent e) {
                            RSolicitud.setVisible(false);
                            PPrestamo.setVisible(true);
                            PPrestamo.setLocationRelativeTo(null);
                        }
                    });

                    RSolicitud.getLblPrestarSala().addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent e) {
                            RSolicitud.setVisible(false);
                            PPrestamo.setVisible(true);
                            PPrestamo.setLocationRelativeTo(null);
                        }
                    });

                    RSolicitud.getLblVerPrestamos().addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent e) {
                            RSolicitud.setVisible(false);
                            VMPrestamo.setVisible(true);
                            VMPrestamo.setLocationRelativeTo(null);
                        }
                    });

                    // PPrestamo
                    PPrestamo.getBtnMenuPrincipal().addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent e) {
                            PPrestamo.setVisible(false);
                            menu.setVisible(true);
                        }
                    });

                    PPrestamo.getBtnSalir().addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent e) {
                            PPrestamo.setVisible(false);
                            limpiarVentanasUsuario();
                            login.setVisible(true);
                        }
                    });

                    PPrestamo.getBtnIrAudiovisual().addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent e) {
                            PPrestamo.setVisible(false);
                            VMPrestamo.setVisible(true);
                            VMPrestamo.setLocationRelativeTo(null);
                        }
                    });

                    // VMPrestamo
                    VMPrestamo.getRealizarPrestamo().addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent e) {
                            VMPrestamo.setVisible(false);
                            PPrestamo.setVisible(true);
                            PPrestamo.setLocationRelativeTo(null);
                        }
                    });

                    VMPrestamo.getBtnMenuPrincipal().addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent e) {
                            VMPrestamo.setVisible(false);
                            menu.setVisible(true);
                        }
                    });

                    VMPrestamo.getBtnSalir().addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent e) {
                            VMPrestamo.setVisible(false);
                            limpiarVentanasUsuario();
                            login.setVisible(true);
                        }
                    });

                    // PSoporte
                    PSoporte.getBtnMenuPrincipal().addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent e) {
                            PSoporte.setVisible(false);
                            menu.setVisible(true);
                        }
                    });

                    PSoporte.getBtnSalir().addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent e) {
                            PSoporte.setVisible(false);
                            limpiarVentanasUsuario();
                            login.setVisible(true);
                        }
                    });

                    // IApp
                    IApp.getLblMenuPrincipal().addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent e) {
                            IApp.setVisible(false);
                            menu.setVisible(true);
                        }
                    });

                    IApp.getLblSalir().addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent e) {
                            IApp.setVisible(false);
                            limpiarVentanasUsuario();
                            login.setVisible(true);
                        }
                    });

                    IApp.getLblSolicitud().addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent e) {
                            IApp.setVisible(false);
                            RSolicitud.setVisible(true);
                            RSolicitud.setLocationRelativeTo(null);
                        }
                    });

            
     

                    // ADMIN
                    menuAd.getLblDevoluciones().addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent e) {
                            menuAd.setVisible(false);
                            GSoporte.setVisible(true);
                            GSoporte.setLocationRelativeTo(null);
                        }
                    });

                    menuAd.getLblSalir().addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent e) {
                            menuAd.setVisible(false);
                            limpiarVentanasUsuario();
                            login.setVisible(true);
                        }
                    });



                    menuAd.getLblSolicitudes().addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent e) {
                            menuAd.setVisible(false);
                            GSolicitudes.setVisible(true);
                            GSolicitudes.setLocationRelativeTo(null);
                        }
                    });

                    menuAd.getLblUsuarios().addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent e) {
                            menuAd.setVisible(false);
                            GUsuarios.setVisible(true);
                            GUsuarios.setLocationRelativeTo(null);
                        }
                    });

                    menuAd.getLblGestionSanciones().addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent e) {
                            menuAd.setVisible(false);
                            GSanciones.setVisible(true);
                            GSanciones.setLocationRelativeTo(null);
                        }
                    });
                    
                    
                    
                    menuAd.getLblBaseDeDatos().addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent e) {
                            menuAd.setVisible(false);
                            ABD.setVisible(true);
                            ABD.setLocationRelativeTo(null);
                        }
                    });

                    menuAd.getLblGestionarEncuesta().addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent e) {
                            menuAd.setVisible(false);
                            GEncuesta.setVisible(true);
                            GEncuesta.setLocationRelativeTo(null);
                        }
                    });
                    
                    // ADMIN interno
                    GSolicitudes.getBtnMenuPrincipal().addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent e) {
                            GSolicitudes.setVisible(false);
                            menuAd.setVisible(true);
                        }
                    });

                    GSolicitudes.getBtnSalir().addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent e) {
                            GSolicitudes.setVisible(false);
                            limpiarVentanasUsuario();
                            login.setVisible(true);
                        }
                    });

                    RSancion.getBtnMenuPrincipal().addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent e) {
                            RSancion.setVisible(false);
                            menuAd.setVisible(true);
                        }
                    });

                    RSancion.getBtnSalir().addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent e) {
                            RSancion.setVisible(false);
                            limpiarVentanasUsuario();
                            login.setVisible(true);
                        }
                    });

                    GUsuarios.getBtnMenuPrincipal().addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent e) {
                            GUsuarios.setVisible(false);
                            menuAd.setVisible(true);
                        }
                    });
                    
                    GUsuarios.getBtnAgregar().addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent e) {
                            GUsuarios.setVisible(false);
                            AUsuarios.setVisible(true);
                        }
                    });
                    
                    AUsuarios.getBtnSalir().addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent e) {
                            AUsuarios.setVisible(false);
                            GUsuarios.setVisible(true);
                        }
                    });

                    GUsuarios.getBtnSalir().addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent e) {
                            GUsuarios.setVisible(false);
                            limpiarVentanasUsuario();
                            login.setVisible(true);
                        }
                    });

                    GSoporte.getBtnMenuPrincipal().addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent e) {
                            GSoporte.setVisible(false);
                            menuAd.setVisible(true);
                        }
                    });

                    GSoporte.getBtnSalir().addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent e) {
                            GSoporte.setVisible(false);
                            limpiarVentanasUsuario();
                            login.setVisible(true);
                        }
                    });

                    GSanciones.getBtnMenuPrincipal().addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent e) {
                            GSanciones.setVisible(false);
                            menuAd.setVisible(true);
                        }
                    });
                    
                    GSanciones.getBtnSancionar().addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent e) {
                            GSanciones.setVisible(false);
                            RSancion.setVisible(true);
                            RSancion.setLocationRelativeTo(null);
                        }
                    });
                    
                    RSancion.getBtnMenuPrincipal().addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent e) {
                            RSancion.setVisible(false);
                            menuAd.setVisible(true);
                            menuAd.setLocationRelativeTo(null);
                        }
                    });
                    
                    RSancion.getBtnSalir().addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent e) {
                            RSancion.setVisible(false);
                            limpiarVentanasUsuario();
                            login.setVisible(true);
                            
                        }
                    });

                    GSanciones.getBtnSalir().addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent e) {
                            GSanciones.setVisible(false);
                            limpiarVentanasUsuario();
                            login.setVisible(true);
                        }
                    });
                }
            });
        });
        
    }
    private static void limpiarVentanasUsuario() {
    PPrestamo = null;
    VMPrestamo = null;
    GSolicitudes = null;
    // Agrega aquí otras ventanas que dependan del usuario
}

    
}   