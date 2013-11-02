# Dudol

O Dudol é um barramento de serviços para comunicação entre sistemas de uma mesma organização. Ele assume que a comunicação entre os sistemas será feita com o protocolo HTTP e seus métodos GET e POST.

## Objetivos
* Para uma aplicação **cliente**, que utilize operações providas por aplicações provedoras (chamadas **serviços** no contexto do Dudol), um importante benefício é a redução da dependência dos endereços destes serviços. Com isto, é possível alterar os endereços dos serviços, sem a necessidade de alteração das aplicações clientes. A comunicação partindo do cliente para o serviço é chamada **Comunicação Ativa**.
* Uma vez que os serviços podem estar ocupadas quando receberem novas mensagens, o Dudol aumenta a confiabilidade da entrega, ao utilizar mensageria (com o [RabbitMQ](http://rabbitmq.com)).
* Padronização da comunicação entre sistemas, uma vez que os sistemas comunicantes precisam aderir a convenções (mínimas) definidas pelo Dudol.
* Facilidades para agendamento: o Dudol pode buscar informações dos serviços e enviar para os clientes de tempos em tempos, na frequência configurada. Este mecanismo é chamado **Comunicação Passiva**.
 

## Comunicação Ativa
Os elementos presentes na Comunicação Ativa são os seguintes:
[Comunicação Ativa](/imagens/com-ativa.png "Comunicação Ativa")


## Comunicação Passiva
Os elementos presentes na Comunicação Passiva são os seguintes:
[Comunicação Passiva](/imagens/com-passiva.png "Comunicação Passiva")


## Tecnologias Utilizadas

* [Grails](http://grails.org): para a camada Web, que inclui a área de gerenciamento e os endpoints (URL's) com os quais as aplicações clientes se comunicam.
* [RabbitMQ](http://www.rabbitmq.com/): para mensageria.
* [Scala](http://scala-lang.org): utilizada para criar os consumidores da mensageria.
 

## Instalação

### Baixar e Instalar o RabbitMQ


### 
- 

