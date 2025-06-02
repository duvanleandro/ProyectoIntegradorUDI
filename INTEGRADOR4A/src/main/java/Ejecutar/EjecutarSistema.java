package Ejecutar;

import com.mycompany.integrador4a.igu.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EjecutarSistema {

    static MenuUsuario menu;
    static MenuAdmin menuAd;

    static RealizarSolicitud RSolicitud;
    static RealizarDevolucion RDevolucion;
    static PedirSoporte PSoporte;
    static InformacionApp IApp;
    static PedirPrestamo PPrestamo;
    static VerMisPrestamos VMPrestamo;

    static GestionarSolicitudes GSolicitudes;
    static RealizarSancion RSancion;
    static GestionarUsuarios GUsuarios;
    static GestionarDevoluciones GDevoluciones;
    static GestionarSanciones GSanciones;

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            Login login = new Login();
            login.setVisible(true);
            login.setLocationRelativeTo(null);

            login.getBtnLogin().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    login.setVisible(false);

                    if (menu == null) menu = new MenuUsuario();
                    if (menuAd == null) menuAd = new MenuAdmin();

                    if (RSolicitud == null) RSolicitud = new RealizarSolicitud();
                    if (RDevolucion == null) RDevolucion = new RealizarDevolucion();
                    if (PSoporte == null) PSoporte = new PedirSoporte();
                    if (IApp == null) IApp = new InformacionApp();
                    if (PPrestamo == null) PPrestamo = new PedirPrestamo();
                    if (VMPrestamo == null) VMPrestamo = new VerMisPrestamos();

                    if (GSolicitudes == null) GSolicitudes = new GestionarSolicitudes();
                    if (RSancion == null) RSancion = new RealizarSancion();
                    if (GUsuarios == null) GUsuarios = new GestionarUsuarios();
                    if (GDevoluciones == null) GDevoluciones = new GestionarDevoluciones();
                    if (GSanciones == null) GSanciones = new GestionarSanciones();

                    menu.setVisible(true);
                    menu.setLocationRelativeTo(null);
                    menuAd.setVisible(true);
                    menuAd.setLocationRelativeTo(null);

                    // MENU USUARIO
                    menu.getLblDevolucion().addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent e) {
                            menu.setVisible(false);
                            RDevolucion.setVisible(true);
                            RDevolucion.setLocationRelativeTo(null);
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
                            menu.setVisible(false);
                            RSolicitud.setVisible(true);
                            RSolicitud.setLocationRelativeTo(null);
                        }
                    });

                    menu.getLblSoporte().addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent e) {
                            menu.setVisible(false);
                            PSoporte.setVisible(true);
                            PSoporte.setLocationRelativeTo(null);
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

                    // RDevolucion
                    RDevolucion.getBtnMenuPrincipal().addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent e) {
                            RDevolucion.setVisible(false);
                            menu.setVisible(true);
                        }
                    });

                    RDevolucion.getBtnSalir().addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent e) {
                            RDevolucion.setVisible(false);
                            login.setVisible(true);
                        }
                    });

                    // ADMIN
                    menuAd.getLblDevoluciones().addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent e) {
                            menuAd.setVisible(false);
                            GDevoluciones.setVisible(true);
                            GDevoluciones.setLocationRelativeTo(null);
                        }
                    });

                    menuAd.getLblSalir().addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent e) {
                            menuAd.setVisible(false);
                            login.setVisible(true);
                        }
                    });

                    menuAd.getLblSancionar().addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent e) {
                            menuAd.setVisible(false);
                            RSancion.setVisible(true);
                            RSancion.setLocationRelativeTo(null);
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
                            login.setVisible(true);
                        }
                    });

                    GUsuarios.getBtnMenuPrincipal().addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent e) {
                            GUsuarios.setVisible(false);
                            menuAd.setVisible(true);
                        }
                    });

                    GUsuarios.getBtnSalir().addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent e) {
                            GUsuarios.setVisible(false);
                            login.setVisible(true);
                        }
                    });

                    GDevoluciones.getBtnMenuPrincipal().addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent e) {
                            GDevoluciones.setVisible(false);
                            menuAd.setVisible(true);
                        }
                    });

                    GDevoluciones.getBtnSalir().addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent e) {
                            GDevoluciones.setVisible(false);
                            login.setVisible(true);
                        }
                    });

                    GSanciones.getBtnMenuPrincipal().addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent e) {
                            GSanciones.setVisible(false);
                            menuAd.setVisible(true);
                        }
                    });

                    GSanciones.getBtnSalir().addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent e) {
                            GSanciones.setVisible(false);
                            login.setVisible(true);
                        }
                    });
                }
            });
        });
    }
}