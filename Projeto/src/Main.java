import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CadastroTela extends JFrame {

    private JTextField txtEmail;
    private JPasswordField txtSenha;
    private JButton btnCadastrar;

    private ArrayList<Usuario> usuarios = new ArrayList<>();

    public CadastroTela() {
        setTitle("Cadastro do Cliente");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel lblEmail = new JLabel("E-mail:");
        lblEmail.setBounds(20, 20, 100, 25);
        add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(100, 20, 200, 25);
        add(txtEmail);

        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setBounds(20, 60, 100, 25);
        add(lblSenha);

        txtSenha = new JPasswordField();
        txtSenha.setBounds(100, 60, 200, 25);
        add(txtSenha);

        btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setBounds(100, 110, 120, 30);
        add(btnCadastrar);

        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarUsuario();
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void cadastrarUsuario() {
        String email = txtEmail.getText();
        String senha = new String(txtSenha.getPassword());

        if (email.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Preencha todos os campos!");
            return;
        }

        usuarios.add(new Usuario(email, senha));

        JOptionPane.showMessageDialog(this,
                "Usuário cadastrado com sucesso!");

        txtEmail.setText("");
        txtSenha.setText("");
    }

    public static void main(String[] args) {
        new CadastroTela();
    }

    public class Usuario {
        private String email;
        private String senha;

        public Usuario(String email, String senha) {
            this.email = email;
            this.senha = senha;
        }

        public String getEmail() {
            return email;
        }

        public String getSenha() {
            return senha;
        }
    }
}