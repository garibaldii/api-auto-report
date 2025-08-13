# API Auto Report

A **API Auto Report** foi desenvolvida para automatizar a geração de relatórios de vendas de múltiplas empresas, eliminando a morosidade de trabalhar manualmente com planilhas Excel. O sistema centraliza dados de diferentes marketplaces e permite o cálculo rápido do valor total das operações.

---

## Caso de Uso

<img width="300" height="300" alt="image" src="https://github.com/user-attachments/assets/e1079c1b-edc5-43e3-9eda-106c32b53159" />

Todo mês, cada empresa precisa exportar um relatório de vendas. Para cada relatório:

1. Obter todos os pedidos (`orders.all`);
2. Somar a coluna `t` (total);
3. Somar as colunas de taxas;
4. Calcular a diferença entre a coluna `t` e a soma das taxas.

Lembrando que este formato é para a planilha de vendas modelo Shopee, a ideia é fazer com que o usuário escolha qual coluna deseja adicionar à fórmula.
Como cada empresa pode ter múltiplos arquivos, o processo manual se torna moroso. A API automatiza esse fluxo, padronizando a geração de relatórios para todas as empresas.

---

## Funcionalidades da API

- **Autenticação**: Controle de acesso via Spring Security, com suporte a HTTP Basic Auth.
- **CRUD de Empresas**: Criar, editar, visualizar e remover empresas.
- **Gestão de Dados de Empresas**: Atribuir relatórios de marketplaces (Shopee, etc.) às empresas.
- **Dashboard/Endpoints de Consulta**: Obter dados consolidados de cada empresa, incluindo valor total da operação e taxas.

---

## Tecnologias Utilizadas

- **Backend**: Java + Spring Boot
- **Banco de Dados**: MySQL 
- **Autenticação**: Spring Security com BCrypt
- **Documentação de API**: Swagger/OpenAPI (opcional)
- **Versionamento e CI/CD**: GitHub Actions

---


## Estrutura de Endpoints (Exemplo)

| Endpoint | Método | Descrição |
|----------|--------|-----------|
| `/api/companies` | GET | Lista todas as empresas |
| `/api/companies` | POST | Cria uma nova empresa |
| `/api/companies/{id}` | PUT | Atualiza dados da empresa |
| `/api/companies/{id}` | DELETE | Remove empresa |
| `/api/reports/{companyId}` | GET | Retorna relatório consolidado da empresa |
dsdsdsdsdsd> Obs.: Todos os endpoints estão protegidos via autenticação HTTP Basic.

---

### Fases de Desenvolvimento
[⌛ CRUD de Usuários] --> [🔲 CRUD de Empresas] --> [🔲 CRUD de Relatórios / Dados de Marketplace] --> [🔲 Lógica de Excel / Consolidação de Relatórios]
- ⌛ **CRUD de Usuários**: Implementando, permite criar, editar, visualizar e remover usuários internos da plataforma.
- 🔲 **CRUD de Grupos Empresariais**: Próximo passo, será responsável por gerenciar Grupos Empresariais cadastrados.
- 🔲 **CRUD de Empresas**: Próximo passo, será responsável por gerenciar empresas cadastradas.
- 🔲 **CRUD de Relatórios / Dados de Marketplace**: Após as empresas, será possível vincular relatórios e dados específicos de marketplaces.
- 🔲 **Lógica de Excel / Consolidação de Relatórios**: Última etapa, onde a API automatiza a soma de valores e taxas, gerando os relatórios consolidados.

---

## Setup do Ambiente

```bash
git clone https://github.com/garibaldii/api-auto-report.git
cd api-auto-report
./mvnw clean install
```
---

## Estrutura do Projeto

- `ApiAutoReportApplication`: Classe principal que inicializa a aplicação Spring Boot.
- `controller/`: Controladores responsáveis pelos endpoints da API.
- `service/`: Serviços com a lógica de negócio, incluindo processamento de relatórios.
- `persistence/`: DAO e entidades para persistência em banco de dados.
- `dto/`: Objetos de transferência de dados entre camadas.
- `config/`: Configurações de segurança e beans globais, incluindo Spring Security.
- `application.properties`: Configurações da aplicação, banco de dados, autenticação e variáveis de ambiente. 

---





