# Comente-Sobre

Este é desafio de programação proposto pela Bluesoft. A ideia é basicamente a seguinte:

Ao entrar na página inicial em [http://localhost:8080/comente-sobre](http://localhost:8080/comente-sobre), haverá alguma forma de perguntar para o usuário sobre o que ele quer comentar. Digamos que o usuário digite "métodos ágeis". Você então deverá direcioná-lo para a url [http://localhost:8080/comente-sobre/metodos-ageis]([http://localhost:8080/comente-sobre/metodos-ageis) e deverá exibir dois campos: um para o e-mail dele e outro para o comentário (como se fosse um comentário de blog mesmo). Ao confirmar, o comentário do usuário deverá ser exibido na tela, junto de todos os outros já feitos sobre o mesmo assunto. Uma mesma pessoa pode deixar mais de um comentário.

## Pré-requisitos

O único pré-requisito do projeto é que a instalação do maven. Ele é a ferramenta usada para fazer o build e gerência de dependências do projeto. Você pode obter informações sobre sua instalação clicando [aqui](http://maven.apache.org/download.cgi#Installation).

## Build

Para fazer o processo de build é necessário que todos os pré-requisitos da seção instalação tenham sido satisfeitos.

Há duas opções para fazer o build projeto. Uma para gerar apenas o pacote (arquivo .war) e outra com o servlet container incorporado ao processo de build. Para executar os comandos descritos abra um terminal de comando e navegue até o diretório do projeto.

### Processo simples

Neste processo basta executar o comando `mvn package` que será gerado um pacote com o nome **comente-sobre.war** no diretorio */target*. Após a geração do pacote você pode usar qualquer servlet container, compatível com a especificação JEE6, para rodar o projeto.

### Processo com o web container integrado

Este processo utiliza o plugin do jetty para o maven. Nesta opção será feito o build da aplicação e automaticamente será iniciado um web container (o jetty na versão 8). Para interromper o servidor basta pressionar `CRTL + C`.

Para rodar o processo de build via jetty 8 use o comando:

    mvn jetty:run

Caso você queira que o processo de build rode os testes também gere um arquivo **comente-sobre.war** no diretório */target*, use o comando:

    mvn jetty:run-war

Na segunda opção, após o processo de build serão executados os testes do projeto. Se você desejar rodar apenas os testes do projeto utilize o comando:

    mvn test

### Acessando a aplcação

Após subir o aplicativo em um web container, você pode acessar a aplicação na url abaixo:

    http://localhost:8080/comente-sobre.
