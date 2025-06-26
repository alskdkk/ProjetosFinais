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
}

class Carrinho {
  constructor() {
    this.itens = [];
  }

  adicionarItem(produto, quantidade) {
    this.itens.push({ produto, quantidade });
  }
}

class Pedido {
  constructor(cliente, carrinho) {
    this.cliente = cliente;
    this.carrinho = carrinho;
  }
}

class Pagamento {
  constructor(valor, metodo) {
    this.valor = valor;
    this.metodo = metodo;
  }
}

class Supermercado {
  constructor(nome) {
    this.nome = nome;
  }
}
