# Dudol

O Dudol é um barramento de serviços para comunicação entre sistemas de uma mesma organização, incluindo funções para envio de emails e agendamento de requisições HTTP.

Ele é desenvolvido com o framework [Grails](http://grails.org) versão 4 e é reconhecidamente compatível com Java 8 e 11.

## Índice

* [Objetivos](#objetivos)
* [Emails](#emails)
* [Agendamentos](#agendamentos)
* [Gerenciamento](#gerenciamento)
* [Docker](#docker)
* [Contato](#contato)

## Objetivos

* Centralizar as configurações de servidores de emails, evitando o trabalho de reconfigurar todas as aplicações no caso de mudanças nas configurações ou no provedor de emails usado pela organização.
* Facilitar agendamento de tarefas: o Dudol realizar requisições HTTP na frequência configurada.


## Emails

Para enviar emails, deve-se fazer uma requisição HTTP POST para o endereço `<url_base_dudol>/email/enviar`, passando os seguintes parâmetros:

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

## Agendamentos

Com os agendamentos, define-se que chamadas HTTP são realizadas com as frequências definidas. A figura abaixo, apresenta a tela para agendamentos, que é autoexplicativa. O campo **key** serve apenas como documentação.

![Configurações de agendamento](/docs/images/agendamento.png "Configurações de agendamento")


## Gerenciamento

A área de gerenciamento é onde são cadastrados os dados do servidor de emails e os agendamentos. Essa área pode ou não requerer autenticação, a depender do parâmetro `autenticarAdmin` definido no arquivo `config/application.yml`, que pode receber os valores "true" ou "false". Caso o valor definido seja "true", o Dudol solicitará as credenciais e se comunicará com um sistema do TRE-TO chamado Autorizador para validá-las.

## Docker

As imagens Docker estão publicamente disponíveis no [Docker Hub](https://hub.docker.com/r/treto/dudol).

Além disso, os arquivos `Dockerfile` e `docker-compose.yml` estão no projeto. Vale explicar melhor os volumes definidos no `docker-compose.yml`:

* `./db:/var/lib/h2`: no diretório externo `db` será criado o arquivo usado pelo banco de dados H2 para a persistência dos dados.
* `./config:/opt/config`: o diretório externo `config` contém o arquivo `application.yml`, que mantém as parâmetros que podem ser alterados conforme a instalação. Caso o parâmetro `autenticarAdmin` tenha o valor "false", os demais parâmetros não precisam ser definidos.

## Contato

No caso de eventuais dúvidas, pode-se entrar em contato com os mantenedores pelo email <sesaw@tre-to.jus.br>.