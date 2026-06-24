Projeto desenvolvido para a disciplina de **Redes de Computadores**, com o objetivo de demonstrar a comunicação entre processos utilizando **Sockets TCP** em Java.

---

##  Descrição

Este sistema simula um **chat em tempo real**, onde múltiplos clientes podem se conectar a um servidor e trocar mensagens simultaneamente.

A comunicação é feita via **Socket TCP**, garantindo entrega confiável das mensagens entre os usuários conectados.

---

##  Tecnologias utilizadas

- Java
- Sockets TCP (java.net.Socket / ServerSocket)
- Threads (para múltiplos clientes simultâneos)
- Console (CLI)

---

##  Conceitos aplicados

- Arquitetura Cliente-Servidor
- Comunicação via rede (TCP)
- Programação concorrente (Threads)
- Broadcast de mensagens
- Controle de múltiplos clientes

---

##  Estrutura do projeto


org.example
│
├── cliente
│ └── Cliente.java
│
└── servidor
├── Servidor.java
└── ClienteHandler.java


---

##  Como executar o projeto

### 1. Executar o servidor

Execute a classe:


Servidor.java


O servidor será iniciado na porta:


5000


---

### 2. Executar o cliente

Execute a classe:


Cliente.java


Você pode abrir **várias instâncias do cliente** para simular múltiplos usuários.

---

##  Funcionalidades

- Conexão de múltiplos clientes ao servidor
- Identificação de usuários por nome
- Envio de mensagens em tempo real
- Broadcast de mensagens para todos os clientes
- Mensagens diferenciadas:
  - `Você:` para o remetente
  - `Nome do usuário:` para outros clientes
- Horário das mensagens
- Comando `/sair` para encerrar conexão

---

##  Exemplo de uso


Servidor iniciado na porta 5000
Novo cliente conectado.

Digite seu nome:
Gabriel

[14:32:10] Você: Olá pessoal
[14:32:12] Pedro: Oi Gabriel


---

##  Comandos disponíveis


/sair → encerra a conexão com o servidor


---

##  Arquitetura

- O servidor mantém uma lista de clientes conectados
- Cada cliente roda em uma thread separada (ClienteHandler)
- Mensagens são enviadas via broadcast para todos os clientes
- O servidor diferencia o remetente dos demais usuários

---

##  Autores
- Gabriel Nicoletti Bocute
- Guilherme Lizzi Vieira
- Kaique Dias Galera
- Pedro de Carvalho Moraes

Projeto desenvolvido para fins acadêmicos – Redes de Computadores.