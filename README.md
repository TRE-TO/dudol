# Dudol

O Dudol é um barramento de serviços para comunicação entre sistemas de uma mesma organização, incluindo funções para envio de emails e agendamento de requisições HTTP.

Ele é desenvolvido com o framework [Grails](http://grails.org) versão 4 e é reconhecidamente compatível com Java 8 e 11.

## Índice

* [Objetivos](#objetivos)
* [Emails](#emails)


## Objetivos
* Centralizar as configurações de servidores de emails, evitando o trabalho de reconfigurar todas as aplicações no caso de mudanças nas configurações ou no provedor de emails usado pela organização.
* Facilitar agendamento de tarefas: o Dudol realizar requisições HTTP na frequência configurada. Este mecanismo é chamado **Comunicação Passiva**.


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

## Comunicação Passiva

A Comunicação Passiva refere-se a um agendamento, em que uma chamada HTTP é realizada com a frequência definida. A figura abaixo, apresenta a tela para agendamentos, que é autoexplicativa. O campo **key** serve apenas como documentação.

![Configurações de agendamento](/docs/images/agendamento.png "Configurações de agendamento")


## Imagem Docker
