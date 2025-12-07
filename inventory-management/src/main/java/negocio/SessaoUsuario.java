package negocio; // ou apresentacao, mas escolha um e mantém

public class SessaoUsuario {

    private static Funcionario funcionarioLogado;

    public static void setFuncionarioLogado(Funcionario f) {
        funcionarioLogado = f;
    }

    public static Funcionario getFuncionarioLogado() {
        return funcionarioLogado;
    }
}
