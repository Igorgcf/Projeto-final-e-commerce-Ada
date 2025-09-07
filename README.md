# 🛍️ Ada Commerce

## E-Commerce em Java (sem banco de dados)

Este projeto foi desenvolvido como parte de um desafio da Ada Tech, com o objetivo de implementar um sistema de E-Commerce aplicando princípios de POO e SOLID, utilizando apenas Java puro e persistência em memória/arquivos (.dat).

📋 Funcionalidades

O sistema oferece um fluxo completo de um e-commerce:

👤 Clientes

Cadastrar cliente (nome + documento obrigatório).

Listar clientes cadastrados.

Atualizar dados de cliente.

Não há exclusão (mantém histórico).

📦 Produtos

Cadastrar produto (nome + preço padrão).

Listar produtos disponíveis.

Atualizar dados de produto.

Não há exclusão (mantém histórico).

🛒 Pedidos

Criar pedido para um cliente.

Adicionar item (produto, quantidade e preço de venda).

Alterar quantidade de item.

Remover item.

Finalizar pedido (mínimo 1 item, valor > 0).

Pagar pedido.

Entregar pedido.

⚙️ Regras de Negócio

Todo pedido inicia com status ABERTO.

Pedidos só podem ser finalizados se tiverem itens e valor > 0.

Finalização altera status para AGUARDANDO PAGAMENTO.

Pagamento muda status para PAGO.

Após pago, pode ser ENTREGUE → status FINALIZADO.

Sempre que há mudança importante, o cliente é notificado via e-mail (simulado no console).

🏗️ Arquitetura

UI: Interface de linha de comando interativa com Scanner.

Services: Contêm a lógica de negócio (Clientes, Produtos, Pedidos).

Repositories: Genéricos, com persistência em memória e arquivos (.dat).

Entities: Representam Cliente, Produto, Pedido, ItemPedido.

📂 Estrutura de pacotes sugerida:

src/

 ├── Main.java
 
 ├── ui/
 
 │    ├── ClienteUI.java
 
 │    ├── ProdutoUI.java
 
 │    ├── PedidoUI.java
 
 ├── service/
 
 │    ├── ClienteService.java
 
 │    ├── ProdutoService.java
 
 │    ├── PedidoService.java
 
 ├── repository/
 
 │    ├── Repository.java
 
 │    ├── InMemoryRepository.java
 
 │    ├── ClienteRepository.java
 
 │    ├── ProdutoRepository.java
 
 │    ├── PedidoRepository.java
 
 ├── entities/
 
 │   ├── Cliente.java
      
 │   ├── Produto.java
      
 │   ├── Pedido.java
      
 │  ├── ItemPedido.java
