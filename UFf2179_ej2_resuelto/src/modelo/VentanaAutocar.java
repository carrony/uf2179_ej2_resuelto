package modelo;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class VentanaAutocar extends JFrame {

	private JPanel contentPane;
	private JTextField txtMatricula;
	private JTextField txtMarca;
	private JTextField txtKm;
	private JTextField txtModelo;

	private List<Autocar> listaAutocares;
	private JSpinner spinnerPlazas;
	private JTable table;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAutocar frame = new VentanaAutocar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaAutocar() {
		this.listaAutocares = new ArrayList<Autocar>();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][grow][grow][][grow]", "[30.00][][][][][grow]"));
		
		JLabel lblNewLabel_5 = new JLabel("Gestión Autocares");
		lblNewLabel_5.setBackground(Color.CYAN);
		lblNewLabel_5.setOpaque(true);
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 18));
		contentPane.add(lblNewLabel_5, "cell 0 0 5 1,grow");
		
		JLabel lblNewLabel = new JLabel("Matrícula:");
		contentPane.add(lblNewLabel, "cell 1 1,alignx trailing");
		
		txtMatricula = new JTextField();
		contentPane.add(txtMatricula, "cell 2 1,growx");
		txtMatricula.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Marca:");
		contentPane.add(lblNewLabel_1, "cell 1 2,alignx trailing");
		
		txtMarca = new JTextField();
		contentPane.add(txtMarca, "cell 2 2,growx");
		txtMarca.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Modelo:");
		contentPane.add(lblNewLabel_3, "cell 3 2,alignx trailing");
		
		txtModelo = new JTextField();
		contentPane.add(txtModelo, "cell 4 2,growx");
		txtModelo.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Kilómetros:");
		contentPane.add(lblNewLabel_2, "cell 1 3,alignx trailing");
		
		txtKm = new JTextField();
		contentPane.add(txtKm, "cell 2 3,growx");
		txtKm.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Plazas:");
		contentPane.add(lblNewLabel_4, "cell 3 3,alignx left,aligny baseline");
		
		spinnerPlazas = new JSpinner();
		spinnerPlazas.setModel(new SpinnerNumberModel(30, 1, 80, 1));
		contentPane.add(spinnerPlazas, "cell 4 3");
		
		JPanel panel = new JPanel();
		contentPane.add(panel, "cell 1 4 4 1,alignx center,growy");
		panel.setLayout(new MigLayout("", "[grow]", "[]"));
		
		JButton btnInsertar = new JButton("Insertar");
		btnInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertar();
			}
		});
		panel.add(btnInsertar, "flowx,cell 0 0");
		
		JButton btnMostrar = new JButton("Mostrar Datos");
		btnMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarDatos();
			}
		});
		panel.add(btnMostrar, "cell 0 0");
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "cell 1 5 4 1,grow");
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Marca", "Modelo", "Kil\u00F3metros", "Num. plazas", "Matr\u00EDcula"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, Integer.class, Integer.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane.setViewportView(table);
	}

	protected void mostrarDatos() {
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		
		modelo.setRowCount(0);
		for (Autocar autocar : listaAutocares) {
			Object fila [] = {
					autocar.getMarca(), autocar.getModelo(),
					autocar.getKilometros(), 
					Integer.valueOf(autocar.getNum_plazas()),
					autocar.getMatricula()
			};
			modelo.addRow(fila);
		}
		
	}

	protected void insertar() {
		try {
			String matricula = txtMatricula.getText();
			String marca = txtMarca.getText();
			String modelo = txtModelo.getText();
			int kms = Integer.parseInt(txtKm.getText());
			int plazas =(int) spinnerPlazas.getValue();
			
			if (matricula==null || matricula.isBlank()||
				marca==null || marca.isBlank()||
				modelo==null || modelo.isBlank()) {
				JOptionPane.showMessageDialog(this, "Debe introducir todos los datos",
						"Introduce datos", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			Autocar a = new Autocar(matricula, marca, modelo, kms, plazas);
			
			if (listaAutocares.contains(a)) {
				JOptionPane.showMessageDialog(this, 
						"No se puede insertar .\nEl autocar ya existe",
						"Error", JOptionPane.ERROR_MESSAGE);
			} else {
				listaAutocares.add(a);
			}
			
		}catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(this, 
					"Debe introducir un número de KM correcto",
					"Introduce datos", JOptionPane.ERROR_MESSAGE);
		}
		
		
		
	}

}
