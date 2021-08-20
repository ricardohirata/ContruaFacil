#language: pt
Funcionalidade: Comprar curso CS
  Cenario: Pesquisar cenario e incluir no carrinho
    Dado que acesso o site da iterasys
    Quando pesquiso por "Mantis"
    E clico na lupa
    Entao vejo a lista de resultado para o curso "Mantis"
    Quando clico em Matricule-se
    Entao confirmo o nome do curso como "Mantis" e o preco de "R$ 49,99"

  Cenario: Pesquisar cenario e incluir no carrinho
    Dado que acesso o site da iterasys
    Quando pesquiso por "Mantis"
    E pressiono Enter
    Entao vejo a lista de resultado para o curso "Mantis"
    Quando clico em Matricule-se
    Entao confirmo o nome do curso como "Mantis" e o preco de "R$ 49,99"

  Esquema do Cenario: Pesquisar cenario e incluir no carrinho EC
    Dado que acesso o site da iterasys
    Quando pesquiso por <curso>
    E clico na lupa
    Entao vejo a lista de resultado para o curso <curso>
    Quando clico em Matricule-se
    Entao confirmo o nome do curso como <curso> e o preco de <preco>
    Exemplos:
      | curso               | preco |
      |"Mantis"             | "R$ 49,99"|
      |"Preparatório CTFL"  | "R$ 169,00" |

  Cenario: Ver detalhes de um curso
    Dado que acesso o site da iterasys
    E clico no botao Ok do popup da LGPD
    Quando clico na imagem de um curso
    Entao vejo apagina com detalhes do curso

  Cenario: Ver detalhes de um curso movendo o mouse
    Dado que acesso o site da iterasys
    E clico no botao Ok do popup da LGPD
    Quando movo o mouse ate a imagem do curso
    E clico na imagem saiba mais de um curso
    Entao vejo apagina com detalhes do curso