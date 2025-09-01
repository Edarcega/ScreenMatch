package br.com.alura.screenmatch.pincipal;

import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.model.DadosTemporada;
import br.com.alura.screenmatch.service.ConsumoAPI;
import br.com.alura.screenmatch.service.ConverteDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {

    private Scanner leitura = new Scanner(System.in);
    private ConsumoAPI consumo = new ConsumoAPI();
    private ConverteDados conversor = new ConverteDados();

    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=9dbf077e";

    public void exibeMenu() {
        System.out.println("Digite o nome da série para busca");
        var nomeSerie = leitura.nextLine();
        var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
        System.out.println(dados);

        List<DadosTemporada> temporadas = new ArrayList<>();

        for (int i = 1; i<=dados.totalTemporadas(); i++) {
            json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ","+") + "&season="+i+API_KEY);
            DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
            temporadas.add(dadosTemporada);
        }
//       A linha temporadas.forEach(System.out::println); utiliza o metodo forEach da interface List
//		 para iterar sobre todos os elementos da lista temporadas e imprimir cada um deles.
//       O operador :: é chamado de method reference (referência de metodo) em Java. Ele permite
//       referenciar diretamente um metodo existente, sem precisar escrever uma expressão lambda completa.
//       Neste caso, System.out::println refere-se ao metodo println do objeto System.out, que imprime
//       o elemento passado como argumento.
//       Portanto, para cada elemento da lista temporadas, o metodo println será chamado, imprimindo
//       o conteúdo de cada temporada no console. É uma forma mais concisa e legível de escrever:
//       temporadas.forEach(x -> System.out.println(x));
        temporadas.forEach(System.out::println);
    }

}
