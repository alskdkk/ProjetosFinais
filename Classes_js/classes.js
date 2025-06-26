class Produto {
  constructor(nome, preco) {
    this.nome = nome;
    this.preco = preco;
  }
}

class Categoria {
  constructor(nome) {
    this.nome = nome;
  }
}

class Cliente {
  constructor(nome) {
    this.nome = nome;
  }
}

class Funcionario {
  constructor(nome) {
    this.nome = nome;
  }
}

class Gerente extends Funcionario {
  constructor(nome) {
    super(nome);
  }
}

class Estoque {
  constructor() {
    this.produtos = [];
  }

  adicionarProduto(produto) {
    this.produtos.push(produto);
  }

  listarProdutos() {
    console.log("Produtos no estoque:");
    this.produtos.forEach((p) => {
      console.log(p.nome + " - R$" + p.preco);
    });
  }
}

class Carrinho {
  constructor() {
    this.itens = [];
  }

  adicionarItem(produto, quantidade) {
    this.itens.push({ produto, quantidade });
  }

  calcularTotal() {
    let total = 0;
    this.itens.forEach((item) => {
      total += item.produto.preco * item.quantidade;
    });
    return total;
  }

  listarItens() {
    console.log("Itens no carrinho:");
    this.itens.forEach((item) => {
      console.log(item.quantidade + "x " + item.produto.nome);
    });
  }
}

class Pedido {
  constructor(cliente, carrinho) {
    this.cliente = cliente;
    this.carrinho = carrinho;
    this.total = carrinho.calcularTotal();
  }

  mostrarResumo() {
    console.log("Cliente: " + this.cliente.nome);
    this.carrinho.listarItens();
    console.log("Total: R$" + this.total);
  }
}

class Pagamento {
  constructor(valor, metodo) {
    this.valor = valor;
    this.metodo = metodo;
  }

  confirmarPagamento() {
    console.log("Pagamento de R$" + this.valor + " feito com " + this.metodo);
  }
}

class Supermercado {
  constructor(nome) {
    this.nome = nome;
  }
}

// Simulação com dados

const mercado = new Supermercado("Mercado Bom");

const produto1 = new Produto("Arroz", 20);
const produto2 = new Produto("Feijão", 8);

const estoque = new Estoque();
estoque.adicionarProduto(produto1);
estoque.adicionarProduto(produto2);
estoque.listarProdutos();

const cliente = new Cliente("João");
const carrinho = new Carrinho();
carrinho.adicionarItem(produto1, 2);
carrinho.adicionarItem(produto2, 1);

const pedido = new Pedido(cliente, carrinho);
pedido.mostrarResumo();

const pagamento = new Pagamento(pedido.total, "Cartão");
pagamento.confirmarPagamento();
