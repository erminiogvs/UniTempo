# UniTempo - Aplicativo de Previsão do Tempo

UniTempo é um aplicativo Android desenvolvido para fornecer previsões de tempo precisas e atualizadas. O aplicativo permite que os usuários vejam as condições climáticas em tempo real, com detalhes sobre temperatura, umidade, vento e outras informações meteorológicas, baseadas no **WOEID** de uma cidade, obtido através da leitura de um **QR Code**.

## Funcionalidades

- **Previsão do tempo:** Mostra a previsão do tempo atual e para os próximos dias.
- **Leitura de QR Code:** O usuário escaneia um QR Code que contém o WOEID da cidade desejada, e o aplicativo exibe a previsão do tempo para essa cidade.
- **Modo escuro:** O aplicativo suporta o modo escuro para uma experiência mais agradável em ambientes com pouca luz.
- **Interface simples e intuitiva:** Navegação fácil e rápida entre as telas de previsão do tempo e mapa.

## Como usar

1. **Baixe e instale o aplicativo:** Você pode baixar o aplicativo diretamente na Play Store (se já tiver sido publicado) ou compilar e rodar localmente.
2. **Escaneie um QR Code:** Abra o aplicativo e escaneie o QR Code que contém o **WOEID** de uma cidade. O código pode ser gerado através de um serviço ou fornecido diretamente.
3. **Visualizar a previsão:** Após escanear o QR Code, o aplicativo mostrará a previsão do tempo para a cidade correspondente ao WOEID.
4. **Navegar entre as abas:** O aplicativo possui duas principais seções: previsão do tempo e mapa. Você pode alternar entre elas facilmente usando o menu na parte superior.

## Como rodar o projeto localmente

1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/unitempo.git
Abra o projeto no Android Studio.

Execute o aplicativo em um dispositivo Android ou emulador.

O aplicativo solicitará permissão para acessar a câmera, que deve ser concedida para escanear o QR Code.

Tecnologias usadas
Android Studio para o desenvolvimento do aplicativo.
API HG Brasil para obter os dados meteorológicos.
Google Maps API para integração com mapas e localização.
ZXing para a leitura de QR Codes.
Retrofit para a comunicação com a API e obtenção de dados em formato JSON.
Contribuições
Contribuições são bem-vindas! Se você quiser ajudar a melhorar o UniTempo, fique à vontade para abrir um pull request.

Faça um fork do projeto.
Crie uma branch para sua feature (git checkout -b feature/nome-da-feature).
Comite suas alterações (git commit -am 'Adiciona nova feature').
Envie para o branch (git push origin feature/nome-da-feature).
Abra um pull request.

Licença

Para uso pessoal.
