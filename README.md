# Dudol

O Dudol é um barramento de serviços para comunicação entre sistemas de uma mesma organização. Ele assume que a comunicação entre os sistemas será feita com o protocolo HTTP e seus métodos GET e POST.

## Objetivos
* Para uma aplicação **cliente**, que utilize operações providas por aplicações provedoras (chamadas **serviços** no contexto do Dudol), um importante benefício é a redução da dependência dos endereços destes serviços. Com isto, é possível alterar os endereços dos serviços, sem a necessidade de alteração das aplicações clientes. A comunicação partindo do cliente para o serviço é chamada **Comunicação Ativa**.
* Uma vez que os serviços podem estar ocupadas quando receberem novas mensagens, o Dudol aumenta a confiabilidade da entrega, ao utilizar mensageria (com o [RabbitMQ](http://rabbitmq.com)).
* Padronização da comunicação entre sistemas, uma vez que os sistemas comunicantes precisam aderir a convenções (mínimas) definidas pelo Dudol.
* Facilidades para agendamento: o Dudol pode buscar informações dos serviços e enviar para os clientes de tempos em tempos, na frequência configurada. Este mecanismo é chamado **Comunicação Passiva**.
 

## Comunicação Ativa
Os elementos presentes na Comunicação Ativa são os seguintes:
[Comunicação Ativa](/docs/images/com-ativa.png "Comunicação Ativa")


## Comunicação Passiva
Os elementos presentes na Comunicação Passiva são os seguintes:
[Comunicação Passiva](/docs/images/com-passiva.png "Comunicação Passiva")


## Tecnologias Utilizadas

* [Grails](http://grails.org): para a camada Web, que inclui a área de gerenciamento e os endpoints (URL's) com os quais as aplicações clientes se comunicam.
* [RabbitMQ](http://www.rabbitmq.com/): para mensageria.
* [Scala](http://scala-lang.org): utilizada para criar os consumidores da mensageria.
 

## Instalação e Execução

### Baixar e Executar o RabbitMQ

As instruções sobre como baixar e executar o RabbitMQ encontram-se [aqui](http://www.rabbitmq.com/download.html). Geralmente basta executá-lo, sem nenhuma configuração adicional.

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
    
### Build dos Consumidores da Mensageria

TODO
