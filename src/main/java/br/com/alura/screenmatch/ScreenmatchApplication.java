package br.com.alura.screenmatch;

import br.com.alura.screenmatch.model.DadosEpisodio;
import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.service.ConsumoAPI;
import br.com.alura.screenmatch.service.ConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var consumoAPI = new ConsumoAPI();
		var consulta = consumoAPI.obterDados("https://www.omdbapi.com/?t=gilmore+girls&apikey=9dbf077e");
//		System.out.println(consulta);
//		consulta = consumoAPI.obterDados("https://coffee.alexflipnote.dev/random.json");
		System.out.println(consulta);
		ConverteDados converteDados = new ConverteDados();
		DadosSerie dadosSerie = converteDados.obterDados(consulta, DadosSerie.class);
		System.out.println(dadosSerie);
		consulta = consumoAPI.obterDados("https://omdbapi.com/?t=gilmore+girls&season=1&episode=2&apikey=9dbf077e");
		DadosEpisodio dadosEpisodio = converteDados.obterDados(consulta, DadosEpisodio.class);
		System.out.println(dadosEpisodio);
	}
}
