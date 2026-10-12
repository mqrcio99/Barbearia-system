package barbearia.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import barbearia.domain.Servico;

/**
 * Serviço responsável por salvar e recuperar a lista de serviços em um arquivo JSON.
 */
public class PersistenciaService {

    private final ObjectMapper objectMapper;

    public PersistenciaService() {
        this.objectMapper = new ObjectMapper();
    }

    /**
     * Salva a lista de serviços em um arquivo JSON informado.
     *
     * @param servicos lista de serviços a ser persistida
     * @param caminho caminho do arquivo de dados
     * @throws IOException caso ocorra erro de leitura ou escrita do arquivo
     */
    public void salvar(List<Servico> servicos, String caminho) throws IOException {
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(caminho), servicos);
    }

    /**
     * Carrega a lista de serviços de um arquivo JSON informado.
     *
     * @param caminho caminho do arquivo de dados
     * @return lista de serviços recuperada do arquivo, ou lista vazia quando o arquivo não existir
     * @throws IOException caso ocorra erro de leitura do arquivo
     */
    public List<Servico> carregar(String caminho) throws IOException {
        File arquivo = new File(caminho);
        if (!arquivo.exists()) {
            return new ArrayList<>();
        }

        try {
            return objectMapper.readValue(arquivo, new TypeReference<List<Servico>>() {
            });
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }
}
