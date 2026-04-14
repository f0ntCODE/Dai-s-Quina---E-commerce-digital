## Observações do projeto

O projeto presente me apresentou algumas característica no desenvolvimento.

Percebi que nas classes de objetos é importante atribuir validações internas dentro do construtor e métodos úteis, privadas, para benefício da própria classe. O modelo chamado de [Cliente](src/main/java/edu/daisquina/dominio/Cliente.java) é um bom exemplo disso, já que ele é compost por um construtor que faz validações e um método de atualização próprio:

- Construtor:

    public Cliente(int id, String nome, String email, String senha) {

        Pattern formatoNome = Pattern.compile(".+@email\\.com$");
        Matcher matcher = formatoNome.matcher(email.trim());

        if(matcher.find()){
            this.email = email;
        }
        else{ throw new IllegalArgumentException("Email inválido. Não segue os padrões");}

        this.id = id;
        this.nome = nome.trim();
        this.senha = senha;
    }`

- Método de atualização: 

        public void atualizar(String novoNome, String novoEmail, String novaSenha){

        if(novoNome == null || novoNome.isBlank()) throw new IllegalArgumentException("Nome inválido");
        if(novoEmail == null || novoEmail.isBlank()) throw new IllegalArgumentException("Email inválido");
        if(novaSenha == null || novaSenha.isBlank()) throw new IllegalArgumentException("Senha inválida");  

        this.nome  = novoNome;
        this.email = novoEmail;
        this.senha = novaSenha;

    }` 

Essa é uma boa prática no desenvolvimento de software porque segue os princípios SOLID: a própria classe é responsável por si própria e consegue mudar seu próprio estado. Ela não conhece outras classes senão ela mesma.

