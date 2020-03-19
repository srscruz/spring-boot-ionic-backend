package com.sages4it.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sages4it.cursomc.domain.Categoria;
import com.sages4it.cursomc.domain.Cidade;
import com.sages4it.cursomc.domain.Cliente;
import com.sages4it.cursomc.domain.Endereco;
import com.sages4it.cursomc.domain.Estado;
import com.sages4it.cursomc.domain.ItemPedido;
import com.sages4it.cursomc.domain.Pagamento;
import com.sages4it.cursomc.domain.PagamentoComBoleto;
import com.sages4it.cursomc.domain.PagamentoComCartao;
import com.sages4it.cursomc.domain.Pedido;
import com.sages4it.cursomc.domain.Produto;
import com.sages4it.cursomc.domain.enums.EstadoPagamento;
import com.sages4it.cursomc.domain.enums.TipoCliente;
import com.sages4it.cursomc.repositories.CategoriaRepository;
import com.sages4it.cursomc.repositories.CidadeRepository;
import com.sages4it.cursomc.repositories.ClienteRepository;
import com.sages4it.cursomc.repositories.EnderecoRepository;
import com.sages4it.cursomc.repositories.EstadoRepository;
import com.sages4it.cursomc.repositories.ItemPedidoRepository;
import com.sages4it.cursomc.repositories.PagamentoRepository;
import com.sages4it.cursomc.repositories.PedidoRepository;
import com.sages4it.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	ItemPedidoRepository itemPedidoRepository;
	@Override
	public void run(String... args) throws Exception {

		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");
		Categoria cat3 = new Categoria(null, "Cama Mesa e Banho");
		Categoria cat4 = new Categoria(null, "Perfumaria");
		Categoria cat5 = new Categoria(null, "Farmacia");
		Categoria cat6 = new Categoria(null, "Vestuario");
		Categoria cat7 = new Categoria(null, "Cosmetico");
		Categoria cat8 = new Categoria(null, "Eletroeletronicos");
		Categoria cat9 = new Categoria(null, "Moveis");
		Categoria cat10 = new Categoria(null, "Alimentos");
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2,cat3, cat4, cat5, cat6, cat7, cat8, cat9, cat10));

		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");

		Cidade c1 = new Cidade(null, "Uberlandia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);

		est1.getCidades().addAll(Arrays.asList(c1));
		est1.getCidades().addAll(Arrays.asList(c2, c3));

		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));

		Cliente cli1 = new Cliente(null, "Sergio Cruz", "sergio.cruz@sages4it.com", "18419691844",
				TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("55394219", "971719217"));

		Endereco e1 = new Endereco(null, "Av. Maestro Vilas Lobos", "805", "A", "Tucuruvi", "02260060", cli1, c2);
		Endereco e2 = new Endereco(null, "Rua Estela", "515", "Bloco F, Conj. 182", "Paraiso", "04091000", cli1, c2);
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2019 10:22"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("22/02/2020 09:35"), cli1, e2);

		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, null,
				sdf.parse("23/03/2020 00:00"));
		ped2.setPagamento(pagto2);

		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.0, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.0, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.0, 1, 800.00);
		
		ped1.getItems().addAll(Arrays.asList(ip1, ip2));
		ped2.getItems().addAll(Arrays.asList(ip3));
		
		p1.getItems().addAll(Arrays.asList(ip1));
		p2.getItems().addAll(Arrays.asList(ip3));
		p3.getItems().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2,ip3));
	}

}
