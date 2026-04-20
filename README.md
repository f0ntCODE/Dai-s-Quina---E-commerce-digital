# Dai's Quina
## E-commerce Digital

## O que é este projeto?
Projeto pessoal feito para refinar e aplicar meus conhecimentos em desenvolvimento e engenharia de software com foco em design de software, clean code, Arquitetura de Software, Estrutura de dados e QA (Quality Assurance).

O padrão de desenvolvimento que está sendo usado é o TDD (Test Driven Domain), que garante com que as funcionalidades do software estejam, funcionando corretamente - todos testados e validados.

## Características

- linguagem de programação/framework: Java 21.0.9 + Spring Boot;
- Gerenciador de dependências: Maven;
- Estrutura do projeto: *MVC* e *Camadas*;
- Estrutura de implantação: Monolítica;
- Framework de teste: JUnit 5 + Mockito;

## Características técnicas (Desta branch)

### Estrutura de dados
- Uma lista de mercadorias em um carrinho de compras e nos produtos do pedido, por exemplo, não podem haver duplicatas e não há necessidade de se priorizar a ordem de produtos na lista. Dessa maneira, a melhor escolha para uma lista de produtos nesse contexto é o uso de ***HashSet()*** (conjuntos), que ordena os itens sem priorizar ordem e não permite duplicatas naturalmente.

Trecho de código que utiliza um Set(), na entidade [Carrinho](src/main/java/edu/daisquina/dominio/Carrinho.java)`

    public class Carrinho {
    private final Cliente cliente;

    private Set<ItemCarrinho> itensCarrinho = new HashSet<>();
    ´


- Como esta versão do projeto foi feita sem framework precisei simular o comportamento de uma tabela de banco de dados não relacional em memória. Para esse feito, a melhor estrutura de dados é o ***HashMap<K, V>()***(Ou *dicionário*, em algumas linguagens). Trata-se de uma estrutura de dados que combina pares de *chave* (**K**ey) e *valor* (**V**alue) para identificar os objetos nele inseridos. Funciona de maneira similar a uma tabela de banco de dados: Usa-se o identificador para representar e referenciar um registro na tabela. Essa estrutura de dados é ótima para isso porque não permite Chaves duplicadas e é mais simples para buscas.

Trecho de código da classe [ClientePersistencia](src/main/java/edu/daisquina/banco/ClientePersistencia.java) com um método para salvar na memória:

`

    public class ClientePersistencia implements ClienteRepository{

    Map<Integer, Cliente> bancoCliente = new HashMap<>(); //banco de dados em memória

    @Override
    public Cliente salvar(Cliente cliente) {
        
        bancoCliente.put(cliente.getId(), cliente);

        return cliente;

    }
`

### Design Patterns

- Foi construído um **Simple Factory** para fazer o controle sobre os meios de pagamento possíveis ao fazer o *checkout* do pedido. Trata-se de uma implementação simplificada da **Factory Method**, que melhora a organização e a escalabilidade do software. Além disso, contribui para o reúso de código, pois possibilita a escolha correta do método de pagamento pelo próprio *service* de maneira descomplicada e simples. À medida em que novos métodos de pagamento chegam, bastam poucas implementações para incorporá-lo na aplicação. 

    Todavia, o padrão Simple Factory não cumpre plenamente as boas práticas SOLID, especialmente o "O" (Open-Closed Principle), porque precisa-se alterar a classe de seleção de métodos de apagamento sempre que for necessário, tornando-a rígida à medida em que o sistema cresce.

    Acesse a classe e navege pelas implementações dela clicando [aqui](src/main/java/edu/daisquina/factories/PagamentoFactory.java)

### Testes
- Este projeto foi construído com o método *TDD*, ou seja, testar primeiro, implementar depois. Dessa forma, obrigatoriamente, precisei aprender a criar casos de teste com JUnit 5 para realizar testes unitários automatizados. Isso foi um grande salto de aprendizado, porque, à medida que o sistema ia sendo desenvolvido, testes e melhorias iam sendo realizados cada vez mais, garantindo a boa funcionalidade da lógica de negócios e da implementação como um todo. 
Hoje, valoriza-se softwares que estejam plenamente testados.

## Considerações Finais

Construir este software está sendo uma experiência realmente inspiradora. Tive que colocar a mão na massa para estudar e compreender não só como um sistema funciona "por trás dos panos", mas como as classes e funções devem estar funcionando em harmonia, cada uma responsável por uma parte no software; seguem um papel definido. Uma verdadeira hierarquia de papéis; uma arte.

Compreender a arquitetura que deve compor o software - a escolha de padrões a usar, que método de implantação é mais adequado e que dependências vale a pena incluir - garante com que tudo funcione em verdadeira harmonia e excelência. Dê valor a isso, não só à linguagem de programação, e veja tudo fluir. Bugs irão surgir - sempre vão surgir - , mas em um ambiente menos propício para sua proliferação.
Experimente estudar arquitetura e design de software e passe a olhar de maneira diferente para aquele sistema que lhe é exibido na tela.