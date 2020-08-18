/*
Cenário de Negócio:
Todo dia útil por volta das 6 horas da manhã um colaborador da retaguarda do Sicredi recebe e organiza as informações de contas para enviar ao Banco Central. Todas agencias e cooperativas enviam arquivos Excel à Retaguarda. Hoje o Sicredi já possiu mais de 4 milhões de contas ativas.
Esse usuário da retaguarda exporta manualmente os dados em um arquivo CSV para ser enviada para a Receita Federal, antes as 10:00 da manhã na abertura das agências.

Requisito:
Usar o "serviço da receita" (fake) para processamento automático do arquivo.

Funcionalidade:
0. Criar uma aplicação SprintBoot standalone. Exemplo: java -jar SincronizacaoReceita <input-file>
1. Processa um arquivo CSV de entrada com o formato abaixo.
2. Envia a atualização para a Receita através do serviço (SIMULADO pela classe ReceitaService).
3. Retorna um arquivo com o resultado do envio da atualização da Receita. Mesmo formato adicionando o resultado em uma nova coluna.


Formato CSV:
agencia;conta;saldo;status
0101;12225-6;100,00;A
0101;12226-8;3200,50;A
3202;40011-1;-35,12;I
3202;54001-2;0,00;P
3202;00321-2;34500,00;B
...

*/
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

package sincronizacaoreceita;

public class SincronizacaoReceita {
	//le arquivo
	public List leArquivo(contas.csv){
		Reader reader = FilesReader("contas.csv");
		List<String[]> linha = new ArryList<String>();
		//separar valores de cada variavel
		while(reader.nextLine!=null){			
				linha.add(reader.split(";"));
		}
		return linha;
	}

    public static void main(String[] args) {
        
        // Exemplo como chamar o "serviço" do Banco Central.
        // ReceitaService receitaService = new ReceitaService();
        // receitaService.atualizarConta("0101", "123456", 100.50, "A");  
			String ag;
			String cc;
			double saldo;
			String status;
			
			//lê arquivo
			List dados = leArquivo(contas.csv);
			//pega valor de cada variável
			for(int i =0;i<dados.length;i++){
				for(int j=0;j<dados[i].length;i++){
					if(ag==null){
						ag = dados[i][j];
					}else if(cc==null){
						cc = dados[i][j];
					}else if(saldo==null){
						saldo = Double.ValueOf(dados[i][j]);
					}else if(status==null){
						status = dados[i][j];
					}else {
						//testa se valores ok e atualiza tabela
						if(atualizarConta(ag, cc, saldo, status)){
							dados[i] = dados[i]+"true";
						}else{
							dados[i] = dados[i]+"false";
						}
					}
				}
				ag = null;
				cc = null;
				saldo = null;
				status = null;
			}
			
			//enviar arquivo
			FileWriter fw = new FileWriter("dados_Atualizados.CSV");
			PrintWriter gravarArq = new PrintWriter(fw);
			for(int i=0;i<dados.length;i++){
				String ln = dados[i]
				gravarArq.printf(ln);
			}
			fw.close();
			/*
			 -recebe aquico CSV
			 -Lê arquivo;
			 -separa os valores
			 -pega primeiros valores e avalia com receitaService
			 -se ok
			 -adiciona do arquivo resultado para reenvio
			 -se não avisa usuario
			 -faz isso até o fim do arquivo
			 -envia arquivo final
			 
			*/
			
    }
    
}
