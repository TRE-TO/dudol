=====
DUDOL
=====

Comunicação Ativa
----------------------

Uma comunicação ativa é quando um sistema cliente interage ativamente com o dudol, enviando e recebendo informações segundo sua própria conveniência.

Um sistema cliente chamará ativamente o dudol na seguinte forma:

htpp://link_dudol/[servico/]{default|provedor}/operacao/parametros

- servico: É uma informação opicional que identifica um serviço de comunicação externa, se for omitido o sistema cliente deseja enviar uma informação diretamente para o provedor, e não para um serviço de comunicação externa. Pode ter uma dos seguintes valores:
  - sms: para envio de SMS;
  - email: para envio de email;
  - twitter: para envio de twitte para conta específica;
  - facebook: para envio de postagem em página do facebook em conta específica;
- default: indica que o dudol irá selecionar o provedor do serviço, esta informação só é válida para os serviços, se cliente quiser enviar uma informação diretamente a um provedor deverá especificá-lo;
- provedor: indica um provedor do serviço configurado no dudol, e.g.:treto, conectta, ascom, SADP, PadLog;
- operacao: identifica uma das seguintes operações:
  - enviar: quando deseja enviar uma informação ao serviço;
  - verificar: quando deseja verificar o status de uma operação de envio;
  - receber: quando desejar receber uma informação do serviço (se houver algo disponível)[1];
- parametros: são as informações necessárias para que a operação seja concluída;

Exemplo:

1. Enviar e-mail:

htpp://link_dudol/email/default/enviar/?to=abruno@tre-to.jus.br&subject=Teste&body=Olá Mundo

htpp://link_dudol/email/treto/enviar/?to=contato@abruno.com&subject=Outro Teste&body=Mais um teste

2. Enviar SMS

htpp://link_dudol/sms/conectta/enviar/?to=6399990000&body=Olá Mundo

htpp://link_dudol/sms/default/enviar/?to=6300009999&body=Olá Mundo

3. Gravar uma tarefa no PadLog

htpp://link_dudol/padlog/enviar/?task_id=1432&status=concluida&data=10/01/2013

4. Ler uma informação do SistemaX[1]

htpp://link_dudol/SistemaX/receber/?my_id=sistemay

5. Verificar SMS

htpp://link_dudol/sms/default/verificar/?envio_id=12998

Comunicação Passiva
--------------------

Uma comunicação Passiva é quando o dudol está configurado para chamar o sistema cliente, notadamente quando o provedor tiver novas informações para o sistema cliente.

A configuração deverá ser feita da seguinte forma:
- identificação do ponto de entrada do sistema cliente (banco de dados ou webservice)
- identificação das informações de entrada no sistema cliente (no mesmo local que é usado ativamente) [1]
- periodicidade mínima e máxima para a busca por informação (opicional?)
- se o dudol deve informar que fez a consulta, mesmo se não houver nada novo

Provedores
----------

Existem dois tipos de provedores no dudol:

1. externo: que é um serviço de comunicação para fora do ambiente da JE do dudol;

2. interno: normalmente é algum sistema, ou outro serviço, interno ao ambiente da JE;

### Provedores Externos

Um provedor externo é chamado, também, de provedor de serviço, ou somente serviço. Um dos provedores de serviço será o provedor default, quando um cliente for utilizar uma comunicação ativa com o dudol é recomendado que o mesmo utilize o provedor default do dudol.

### Provedores Internos

São os sistemas que recebem informações vindos dos sistemas clientes. O dudol precisa conhecer a forma como essa informação deve ser inserida no sistema provedor, pois muitas vezes o sistema em questão não tem conhecimento sobre o dudol.

[1] deverá haver uma configuração de como o sistema cliente recebe a informação de retorno do dudol
