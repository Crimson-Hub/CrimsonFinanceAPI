package com.crimsonhub.CrimsonFinanceAPI.domain.type;

/**
 * Enumeração que representa os diferentes tipos de papéis (roles) de usuários no sistema.
 * <p>
 *     Esta enum é utilizada para definir os níveis de permissão e autoridade de um usuário dentro do sistema.
 * </p>
 *
 * <p><b>Tipos de papéis disponíveis:</b></p>
 * <ul>
 *     <li>{@link #USER} - Usuário padrão com permissões básicas.</li>
 *     <li>{@link #MOD} - Moderador com permissões adicionais para gerenciar conteúdos e usuários.</li>
 *     <li>{@link #ADMIN} - Administrador com acesso total ao sistema.</li>
 * </ul>
 *
 * @author Crimson Solutions
 * @version 1.0
 * @since 2024-01-01
 */
public enum RoleType {

    /**
     * Usuário padrão.
     * <p>
     * Representa o nível básico de acesso ao sistema, com permissões limitadas
     * para operações como visualização e interações básicas.
     * </p>
     */
    USER,

    /**
     * Moderador.
     * <p>
     * Este papel possui permissões adicionais para gerenciar conteúdos e usuários,
     * como a edição ou exclusão de publicações.
     * </p>
     */
    MOD,

    /**
     * Administrador.
     * <p>
     * Representa o nível mais alto de permissão, com acesso completo a todas as
     * funcionalidades e configurações do sistema.
     * </p>
     */
    ADMIN
}
