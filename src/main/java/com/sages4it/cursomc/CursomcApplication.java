package com.sages4it.cursomc;

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
import com.sages4it.cursomc.domain.Produto;
import com.sages4it.cursomc.domain.enums.TipoCliente;
import com.sages4it.cursomc.repositories.CategoriaRepository;
import com.sages4it.cursomc.repositories.CidadeRepository;
import com.sages4it.cursomc.repositories.ClienteRepository;
import com.sages4it.cursomc.repositories.EnderecoRepository;
import com.sages4it.cursomc.repositories.EstadoRepository;
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

	@Override
	public void run(String... args) throws Exception {

		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));

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
		
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		Cliente cli1 = new Cliente(null,"Sergio Cruz", "sergio.cruz@sages4it.com", "18419691844", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("55394219", "971719217"));
		
		Endereco e1 = new Endereco(null, "Av. Maestro Vilas Lobos", "805", "A", "Tucuruvi", "02260060", cli1, c2);
		Endereco e2 = new Endereco(null, "Rua Estela", "515", "Bloco F, Conj. 182", "Paraiso", "04091000", cli1, c2);
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1,e2));	
	}

}
