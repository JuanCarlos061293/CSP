package view;

import controller.PrincipalC;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;

public class PrincipalV extends JFrame {
    private JPanel pnlParametros;
    private JPanel pnlResultados;
    private JTextField txtVehiculos;
    private JTextField txtRatios;
    private JTextField txtPaintLimit;
    private JTextField txtObjectives;
    private JButton btnVehiculos;
    private JButton btnRatios;
    private JButton btnPaintLimit;
    private JButton btnObjectives;
    private JButton btnEjecutar;
    private JButton btnPredeterminado;
    private JScrollPane scpArea;
    private JTextArea txaArea;

    // Constructor
    /**
     * Creates a new, initially invisible <code>Frame</code> with the
     * specified title.
     * <p>
     * This constructor sets the component's locale property to the value
     * returned by <code>JComponent.getDefaultLocale</code>.
     *
     * @param title the title for the frame
     * @throws HeadlessException if GraphicsEnvironment.isHeadless()
     *                           returns true.
     * @see GraphicsEnvironment#isHeadless
     * @see Component#setSize
     * @see Component#setVisible
     * @see JComponent#getDefaultLocale
     */
    public PrincipalV(String title, PrincipalC principalC) throws HeadlessException {
        super(title);
        initComponent(principalC);
    }

    private void initComponent(PrincipalC pc) {
        setSize(500,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        setLayout(null);

        pnlParametros = new JPanel(null);
        pnlParametros.setBorder(new TitledBorder("Load Files:"));

        txtVehiculos = new JTextField("Path archivo vehiculos...");
        txtVehiculos.setEditable(false);
        pnlParametros.add(txtVehiculos);
        txtVehiculos.setBounds(10,20,390, 20);

        btnVehiculos = new JButton("...");
        btnVehiculos.setActionCommand("vehicles");
        btnVehiculos.addActionListener(pc);
        pnlParametros.add(btnVehiculos);
        btnVehiculos.setBounds(410,20,30,20);

        txtRatios = new JTextField("Path archivo ratios...");
        txtRatios.setEditable(false);
        pnlParametros.add(txtRatios);
        txtRatios.setBounds(10,50,390, 20);

        btnRatios = new JButton("...");
        btnRatios.setActionCommand("ratios");
        btnRatios.addActionListener(pc);
        pnlParametros.add(btnRatios);
        btnRatios.setBounds(410,50,30,20);

        txtPaintLimit = new JTextField("Path archivo Paint Limit...");
        txtPaintLimit.setEditable(false);
        pnlParametros.add(txtPaintLimit);
        txtPaintLimit.setBounds(10,80,390, 20);

        btnPaintLimit = new JButton("...");
        btnPaintLimit.setActionCommand("paintLimit");
        btnPaintLimit.addActionListener(pc);
        pnlParametros.add(btnPaintLimit);
        btnPaintLimit.setBounds(410,80,30,20);

        txtObjectives = new JTextField("Path archivo Objectives...");
        txtObjectives.setEditable(false);
        pnlParametros.add(txtObjectives);
        txtObjectives.setBounds(10,110,390, 20);

        btnObjectives = new JButton("...");
        btnObjectives.setActionCommand("objectives");
        btnObjectives.addActionListener(pc);
        pnlParametros.add(btnObjectives);
        btnObjectives.setBounds(410,110,30,20);

        getContentPane().add(pnlParametros);
        pnlParametros.setBounds(15,15, 450,140);

        btnPredeterminado = new JButton("Default");
        btnPredeterminado.addActionListener(pc);
        getContentPane().add(btnPredeterminado);
        btnPredeterminado.setBounds(275,159,85,20);

        btnEjecutar = new JButton("Ejecutar");
        btnEjecutar.addActionListener(pc);
        getContentPane().add(btnEjecutar);
        btnEjecutar.setBounds(375,159,85,20);

        pnlResultados = new JPanel(new BorderLayout(10,10));
        pnlResultados.setBorder(new LineBorder(Color.BLACK));

        txaArea = new JTextArea(5,20);
        scpArea = new JScrollPane(txaArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pnlResultados.add(scpArea, BorderLayout.CENTER);

        getContentPane().add(pnlResultados);
        pnlResultados.setBounds(15,185, 450, 270);
    }

    /**
     *
     * @param desc
     * @param extMin
     * @param extMay
     * @return
     */
    public File chooseFile(String desc, String extMin, String extMay, JTextField text){
        JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
        chooser.setFileFilter(new FileNameExtensionFilter(desc, extMin, extMay));

        int iStatus = chooser.showOpenDialog(getParent());

        if (iStatus == JFileChooser.APPROVE_OPTION){
            text.setText(chooser.getSelectedFile().getAbsolutePath());
            return chooser.getSelectedFile();
        } else {
            JOptionPane.showMessageDialog(this, "No se selecciono archivo", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }// Fin if
    }

    /* Gettes and Settes */

    public JTextField getTxtVehiculos() {
        return txtVehiculos;
    }

    public JTextField getTxtRatios() {
        return txtRatios;
    }

    public JTextField getTxtPaintLimit() {
        return txtPaintLimit;
    }

    public JTextField getTxtObjectives() {
        return txtObjectives;
    }

    public JTextArea getTxaArea() {
        return txaArea;
    }
}//-->
