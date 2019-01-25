package claculadoraswinglab;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.lang.Math;
public class Calculadora extends JFrame {
    
    public static void main(String[] args) {

        Calculadora exce= new Calculadora();
        exce.setVisible(true);
    }
	JTextField pa;
	double resultado;
        double resultado2;
        double res3;
	String operacion;
	JPanel paN, paO;
	boolean nuevaOperacion = true;
        
	public Calculadora() {
		super();
		setSize(500, 300);
		setTitle("Calculadora");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setResizable(true);

		JPanel panel = (JPanel) this.getContentPane();
		panel.setLayout(new BorderLayout());

		pa = new JTextField("0", 20);
		pa.setBorder(new EmptyBorder(4, 4, 4, 4));
		pa.setFont(new Font("Arial", Font.BOLD, 25));
		pa.setHorizontalAlignment(JTextField.RIGHT);
		pa.setEditable(false);
		pa.setBackground(Color.WHITE);
		panel.add("North", pa);
		paN = new JPanel();
		paN.setLayout(new GridLayout(4, 3));
		paN.setBorder(new EmptyBorder(4, 4, 4, 4));
                
		for (int n = 9; n >= 0; n--) {
			Bono("" + n);
		}
		panel.add("Center", paN);
		paO = new JPanel();
		paO.setLayout(new GridLayout(6, 1));
		paO.setBorder(new EmptyBorder(4, 4, 4, 4));

		Bo("+");
                Bo("Cos");
                Bo("Sin");
                Bo("Tan");
		Bo("-");
		Bo("*");
		Bo("/");
		Bo("=");
                Bo("^");
                Bo("√");
		Bo("CE");
		panel.add("East", paO);
		validate();
	}

	/**
	 * Crea un boton del teclado numérico y enlaza sus eventos con el listener
	 * correspondiente
	 * 
	 * @param digito
	 *            boton a crear
	 */
	private void Bono(String digito) {
		JButton btn = new JButton();
		btn.setText(digito);
                btn.setBackground(Color.cyan);
		btn.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent evt) {
				JButton btn = (JButton) evt.getSource();
				elnume(btn.getText());
			}
		});

		paN.add(btn);
	}

	/**
	 * Crea un botón de operacion y lo enlaza con sus eventos.
	 * 
	 * @param operacion
	 */
	private void Bo(String operacion) {
		JButton btn = new JButton(operacion);
		btn.setForeground(Color.BLACK);
                btn.setBackground(Color.lightGray);
		btn.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent evt) {
				JButton btn = (JButton) evt.getSource();
				opeo(btn.getText());
			}
		});

		paO.add(btn);
	}

	private void elnume(String digito) {
		if (pa.getText().equals("0") || nuevaOperacion) {
			pa.setText(digito);
		} else {
			pa.setText(pa.getText() + digito);
		}
		nuevaOperacion = false;
	}

	private void opeo(String tecla) {
		if (tecla.equals("=")) {
			calcularResultado();
		} else if (tecla.equals("CE")) {
			resultado = 0;
			pa.setText("");
			nuevaOperacion = true;
		} else {
			operacion = tecla;
			if ((resultado > 0) && !nuevaOperacion) {
				calcularResultado();
			} else {
				resultado = new Double(pa.getText());
			}
		}

		nuevaOperacion = true;
	}

	/**
	 * Calcula el resultado y lo muestra por pantalla
	 */
	private void calcularResultado() {
		if (operacion.equals("+")) {
			resultado += new Double(pa.getText());
		} else if (operacion.equals("-")) {
			resultado -= new Double(pa.getText());
		} else if (operacion.equals("/")) {
			resultado /= new Double(pa.getText());
		} else if (operacion.equals("*")) {
			resultado *= new Double(pa.getText());
		}else if (operacion.equals("^")){
                        resultado = new Double(pa.getText());
                }
                 else if (operacion.equals("Cos")) {
			resultado = Math.cos(new Double(pa.getText()));
		}else if (operacion.equals("Sin")) {
			resultado = Math.sin(new Double(pa.getText()));
		}else if (operacion.equals("Tan")) {
			res3 = Math.tan(new Double(pa.getText()));
                        resultado = Math.toDegrees(res3);
		}else if (operacion.equals("√")){
                        resultado = Math.sqrt(new Double(pa.getText()));
                }

		pa.setText("" + resultado);
		operacion = "";
	}
}

//Convertir los sen,cos,tan de Radianes a normal*
//Validar Falta de texto al presionar una operacion sin nada*
//Quitar el .0 de los resultados*
//validar entrada de numeros*
//agregar √ y ^ *