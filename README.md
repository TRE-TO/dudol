# Dudol

O Dudol é um barramento de serviços para comunicação entre sistemas de uma mesma organização. Ele assume que a comunicação entre os sistemas será feita com o protocolo HTTP e seus métodos GET e POST.

## Objetivos
* Para uma aplicação **cliente**, que utilize operações providas por aplicações provedoras (chamadas **Serviços HTTP** no contexto do Dudol), um importante benefício é a redução da dependência dos endereços destes serviços. Com isto, é possível alterar os endereços dos serviços, sem a necessidade de alteração das aplicações clientes. A comunicação partindo do cliente para o serviço é chamada **Comunicação Ativa**.
* Padronização da comunicação entre sistemas, uma vez que os sistemas comunicantes precisam aderir a convenções (mínimas) definidas pelo Dudol.
* Facilidades para agendamento: o Dudol pode buscar informações dos serviços e enviar para os clientes de tempos em tempos, na frequência configurada. Este mecanismo é chamado **Comunicação Passiva**.
* Como um adicional, o Dudol também provê um serviço para envio de emails, permitindo centralizar as configurações de servidores de emails, evitando o retrabalho no caso de mudanças nestas configurações.
 

## Comunicação Ativa
Para utilizar a Comunicação Ativa, primeiro é necessário cadastrar o Serviço HTTP, fornecendo sua URL base e uma chave, que será utilizada para identificar o serviço. Depois, no sistema cliente, basta utilizar a URL do Dudol, ao invés da URL base do serviço HTTP. Os dados abaixo exemplificam isto:

1.  URL base: http://qualquer1.empresa.com/servicoX
2.  Chave: s1
3.  URL que seria utilizada pela aplicação cliente sem usar o Dudol: http://host1.empresa.com/servicoX/caminho/qualquer?par1=val1&par2=val2
4.  URL a ser utilizada pela aplicação cliente com o Dudol: http://qualquer2.empresa.com/dudol/s1/caminho/qualquer?par1=val1&par2=val2

Observe que a chave da linha 2 deve ser igual à chave da linha 4 (após o nome "dudol"). O trabalho do Dudol será trocar a chave pela URL base do serviço identificado por aquela chave.

Deve-se atentar ao fato de que a URL base do Dudol (no exemplo, http://qualquer2.empresa.com/dudol) não deve mudar ao longo do tempo, daí a importância de ser bem escolhida, e consequentemente, desta forma é que se tem o benefício de usar o Dudol na Comunicação Ativa.

O Dudol fará requisições ao serviços HTTP utilizando o mesmo verbo HTTP (GET ou POST) usado pelo cliente para se comunicar com ele.


## Comunicação Passiva

A Comunicação Passiva, na verdade, refere-se a um agendamento, em que um programa ou script pode ser executado com a frequência definida. A figura abaixo, apresenta a tela para agendamentos, que é autoexplicativa. O campo **key** serve apenas como documentação.

![Configurações de agendamento](/docs/images/agendamento.png "Configurações de agendamento")



## Emails
Para enviar emails, deve-se fazer uma requisição HTTP POST para o endereço <url_base_dudol>/email/enviar, passando os seguintes parâmetros:
* **from**: email de origem (obrigatório). Caso o servidor de emails requeira autenticação, ele poderá não ser respeitado, sendo apresentado como remetente o usuário que possui as credenciais utilizadas na autenticação.
* **fromname**: nome da pessoa ou entidade que está enviando o email (obrigatório).
* **subject**: assunto (obrigatório).
* **message**: corpo do email (obrigatório).
* **to**: lista de emails de destino (obrigatório).
* **cc**: lista de emails para os quais o email deve ir como cópia (opcional).
* **bcc**: lista de emails para os quais o email deve ir como cópia oculta (opcional).
* **html**: flag que indica que o email contém conteúdo HTML (opcional - se não informado, o email não formatará o conteúdo HTML).

As configurações de emails requerem apenas cinco valores, como mostra a figura abaixo, que é autoexplicativa, sendo que apenas **host** e **port** são obrigatórios.

![Configurações de email](/docs/images/email.png "Configurações de email")

## Tecnologias Utilizadas

* [Grails](http://grails.org): para a camada Web, que inclui a área de gerenciamento e os endpoints (URL's) com os quais as aplicações clientes se comunicam.
* [Apache Commons Email](http://commons.apache.org/proper/commons-email/): para envio de emails.


## Instalação e Execução

### Executando a Camada Web

*    **Instalar o Grails:**
    
    Primeiramente, deve-se verificar a versão do Grails usada no arquivo [application.properties](/source/dudol/application.properties). (Esta versão não foi colocada aqui explicitamente pois ela pode mudar em futuros commits, gerando o risco desta documentação ficar desatualizada.)
     
    Para instalar o Grails, uma opção prática é utilizar o [GVM](http://gvmtool.net). Com o GVM instalado, basta executar:
    > gvm install grails \<versão no application.properties\>

*    **Alterar as configurações de acesso a banco de dados:**

    Alterar o arquivo [DataSource.groovy](/source/dudol/grails-app/conf/DataSource.groovy) para que ele aponte para o banco de dados desejado. Instruções de como configurar o banco de dados podem ser encontradas [aqui](http://grails.org/doc/latest/guide/conf.html#dataSource).
    
    Se for utilizado um banco de dados diferente do H2 (padrão), também será necessário adicionar o respectivo driver JDBC à aplicação. Isto pode ser feito adicionando-se a respectiva dependência na cláusula *dependencies* no arquivo [BuildConfig.groovy](/source/dudol/grails-app/conf/BuildConfig.groovy). Já existe um exemplo comentado para o caso do driver JDBC para o MySQL. A definição destas dependêncies segue as mesmas regras de arquivos pom.xml do Maven, com a diferença de que o groupId, artifactId e versão são separadas por :.
    
*    **Executar a aplicação Grails**
    
    Para executar em modo de desenvolvimento, entre no diretório *dudol/source/dudol* e execute o comando **grails run-app**. Pronto!

    Para funcionar em produção você irá preferir executar o comanto **grails war**. Será gerado um arquivo .war, no diretório *dudol/source/dudol/target*.
