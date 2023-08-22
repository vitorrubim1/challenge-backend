package model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.sql.Date;
import java.util.Objects;

/**
 * Classe responsável por representar uma movimentação de produto.
 *
 * A classe Movimentacao contém informações sobre uma movimentação, como seu identificador, data, usuário responsável,
 * produto envolvido e quantidade movimentada.
 *
 * @since 1.0
 * @version 1.0
 * @see services.MovimentacaoService
 * @see dao.MovimentacaoDao
 * @see controller.MovimentacaoResource
 * @see model.Usuario
 * @see model.Produto
 * @author Stockwave
 */
public class Movimentacao {

    /**
     * Identificador único da movimentação.
     */
    private int id_movimentacao;

    /**
     * Data da movimentação.
     */
    @NotNull(message = "A data da movimentação não pode ser nula.")
    private Date dt_movimentacao;

    /**
     * Usuário responsável pela movimentação.
     */
    @NotNull(message = "O usuário da movimentação não pode ser nulo.")
    private Usuario usuario_movimentacao;

    /**
     * Produto envolvido na movimentação.
     */
    @NotNull(message = "O produto da movimentação não pode ser nulo.")
    private Produto produto_movimentacao;

    /**
     * Quantidade movimentada.
     */
    @NotNull(message = "A quantidade da movimentação não pode ser nula.")
    @Positive(message = "A quantidade da movimentação deve ser maior que zero.")
    private int qtd_movimentacao;

    /**
     * Obtém o identificador da movimentação.
     * @return o identificador da movimentação.
     */
    public int getId_movimentacao() {
        return id_movimentacao;
    }

    /**
     * Define o identificador da movimentação.
     * @param id_movimentacao o identificador da movimentação.
     */
    public void setId_movimentacao(int id_movimentacao) {
        this.id_movimentacao = id_movimentacao;
    }

    /**
     * Obtém a data da movimentação.
     * @return a data da movimentação.
     */
    public Date getDt_movimentacao() {
        return dt_movimentacao;
    }

    /**
     * Define a data da movimentação.
     * @param dt_movimentacao a data da movimentação.
     */
    public void setDt_movimentacao(Date dt_movimentacao) {
        this.dt_movimentacao = dt_movimentacao;
    }

    /**
     * Obtém o usuário responsável pela movimentação.
     * @return o usuário responsável pela movimentação.
     */
    public Usuario getUsuario_movimentacao() {
        return usuario_movimentacao;
    }

    /**
     * Define o usuário responsável pela movimentação.
     * @param usuario_movimentacao o usuário responsável pela movimentação.
     */
    public void setUsuario_movimentacao(Usuario usuario_movimentacao) {
        this.usuario_movimentacao = usuario_movimentacao;
    }

    /**
     * Obtém o produto envolvido na movimentação.
     * @return o produto envolvido na movimentação.
     */
    public Produto getProduto_movimentacao() {
        return produto_movimentacao;
    }

    /**
     * Define o produto envolvido na movimentação.
     * @param produto_movimentacao o produto envolvido na movimentação.
     */
    public void setProduto_movimentacao(Produto produto_movimentacao) {
        this.produto_movimentacao = produto_movimentacao;
    }

    /**
     * Obtém a quantidade movimentada.
     * @return a quantidade movimentada.
     */
    public int getQtd_movimentacao() {
        return qtd_movimentacao;
    }

    /**
     * Define a quantidade movimentada.
     * @param qtd_movimentacao a quantidade movimentada.
     */
    public void setQtd_movimentacao(int qtd_movimentacao) {
        this.qtd_movimentacao = qtd_movimentacao;
    }

    /**
     * Construtor padrão da classe Movimentacao.
     */
    public Movimentacao() {
        super();
    }

    /**
     * Construtor não padrão da classe Movimentacao.
     * @param id_movimentacao o identificador da movimentação.
     * @param dt_movimentacao a data da movimentação.
     * @param usuario_movimentacao o usuário responsável pela movimentação.
     * @param produto_movimentacao o produto envolvido na movimentação.
     * @param qtd_movimentacao a quantidade movimentada.
     */
    public Movimentacao(int id_movimentacao,
                        @NotNull(message = "A data da movimentação não pode ser nula.") Date dt_movimentacao,
                        @NotNull(message = "O usuário da movimentação não pode ser nulo.") Usuario usuario_movimentacao,
                        @NotNull(message = "O produto da movimentação não pode ser nulo.") Produto produto_movimentacao,
                        @NotNull(message = "A quantidade da movimentação não pode ser nula.")
                        @Positive(message = "A quantidade da movimentação deve ser maior que zero.") int qtd_movimentacao) {
        super();
        this.id_movimentacao = id_movimentacao;
        this.dt_movimentacao = dt_movimentacao;
        this.usuario_movimentacao = usuario_movimentacao;
        this.produto_movimentacao = produto_movimentacao;
        this.qtd_movimentacao = qtd_movimentacao;
    }

    /**
     * Retorna o hash code do objeto Movimentacao.
     * @return o hash code do objeto Movimentacao.
     */
    @Override
    public int hashCode() {
        return Objects.hash(dt_movimentacao, id_movimentacao, produto_movimentacao, qtd_movimentacao,
                usuario_movimentacao);
    }

    /**
     * Verifica se o objeto Movimentacao é igual a outro objeto.
     * @param obj o objeto a ser comparado.
     * @return true se os objetos são iguais, false caso contrário.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Movimentacao other = (Movimentacao) obj;
        return id_movimentacao == other.id_movimentacao && qtd_movimentacao == other.qtd_movimentacao
                && Objects.equals(dt_movimentacao, other.dt_movimentacao)
                && Objects.equals(usuario_movimentacao, other.usuario_movimentacao)
                && Objects.equals(produto_movimentacao, other.produto_movimentacao);
    }

    /**
     * Retorna uma representação em formato de string do objeto Movimentacao.
     * @return uma representação em formato de string do objeto Movimentacao.
     */
    @Override
    public String toString() {
        return "Movimentacao [id_movimentacao=" + id_movimentacao + ", dt_movimentacao=" + dt_movimentacao
                + ", usuario_movimentacao=" + usuario_movimentacao + ", produto_movimentacao=" + produto_movimentacao
                + ", qtd_movimentacao=" + qtd_movimentacao + "]";
    }
}
