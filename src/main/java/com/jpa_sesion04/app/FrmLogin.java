package com.jpa_sesion04.app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.Persistence;
import jakarta.persistence.StoredProcedureQuery;

public class FrmLogin extends JFrame {

	private static final EntityManagerFactory emf =
			Persistence.createEntityManagerFactory("jpa_sesion04_pu");

	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField txtClave;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmLogin frame = new FrmLogin();
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
	public FrmLogin() {
		setTitle("Inicio de Sesión");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 340, 200);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitulo = new JLabel("Sistema de Productos");
		lblTitulo.setBounds(90, 11, 160, 20);
		contentPane.add(lblTitulo);

		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(20, 50, 80, 14);
		contentPane.add(lblUsuario);

		txtUsuario = new JTextField();
		txtUsuario.setBounds(110, 47, 180, 20);
		txtUsuario.setColumns(10);
		contentPane.add(txtUsuario);

		JLabel lblClave = new JLabel("Contraseña:");
		lblClave.setBounds(20, 85, 80, 14);
		contentPane.add(lblClave);

		txtClave = new JPasswordField();
		txtClave.setBounds(110, 82, 180, 20);
		contentPane.add(txtClave);

		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validarLogin();
			}
		});
		btnIngresar.setBounds(80, 120, 89, 23);
		contentPane.add(btnIngresar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnCancelar.setBounds(185, 120, 89, 23);
		contentPane.add(btnCancelar);

		// Permitir login con la tecla Enter en el campo contraseña
		txtClave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validarLogin();
			}
		});
	}

	// ─── Llama al stored procedure sp_validar_usuario ─────────────────────────
	void validarLogin() {
		String usuario = txtUsuario.getText().trim();
		String clave   = new String(txtClave.getPassword()).trim();

		if (usuario.isEmpty() || clave.isEmpty()) {
			JOptionPane.showMessageDialog(this,
					"Ingrese usuario y contraseña.", "Aviso", JOptionPane.WARNING_MESSAGE);
			return;
		}

		EntityManager em = emf.createEntityManager();
		try {
			StoredProcedureQuery spq = em.createStoredProcedureQuery("sp_validar_usuario");
			spq.registerStoredProcedureParameter("p_usuario",   String.class,  ParameterMode.IN);
			spq.registerStoredProcedureParameter("p_clave",     String.class,  ParameterMode.IN);
			spq.registerStoredProcedureParameter("p_resultado", Integer.class, ParameterMode.OUT);

			spq.setParameter("p_usuario", usuario);
			spq.setParameter("p_clave",   clave);
			spq.execute();

			int resultado = (Integer) spq.getOutputParameterValue("p_resultado");

			if (resultado > 0) {
				JOptionPane.showMessageDialog(this,
						"Usuario o contraseña fueron validados correctamente.", "Acceso correcto", JOptionPane.INFORMATION_MESSAGE);
				txtClave.setText("");
				txtClave.requestFocus();
			} else {
				JOptionPane.showMessageDialog(this,
						"Usuario o contraseña incorrectos.", "Acceso denegado", JOptionPane.ERROR_MESSAGE);
				txtClave.setText("");
				txtClave.requestFocus();
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this,
					"Error al conectar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			ex.printStackTrace();
		} finally {
			em.close();
		}
	}
}
