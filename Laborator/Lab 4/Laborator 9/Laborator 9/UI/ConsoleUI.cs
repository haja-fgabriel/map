using Laborator_9.Service;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Laborator_9.UI
{
    class ConsoleUI
    {
        private ActivePlayerService activePlayerService;
        private PlayerService playerService;
        private MatchService MatchService;

        private string options = "Optiuni:" +
            "\n1. Adauga jucator" + 
            "\n2. Adauga jucator activ" +
            "\n3. Adauga meci" +
            "\n4. Adauga echipa" +
            "\n5. Afiseaza jucatorii unei echipe" +
            "\n6. Afiseaza jucatorii activi unei echipe la un meci dat" +
            "\n7. Afiseaza toate meciurile dintr-o perioada calendaristica" +
            "\n8. Afiseaza scorul de la un un meci" +
            "\n0. Iesi din aplicatie";


        public ConsoleUI(ActivePlayerService activePlayerService, PlayerService playerService, MatchService matchService)
        {
            this.activePlayerService = activePlayerService;
            this.playerService = playerService;
            MatchService = matchService;
        }

        public void run()
        {
            while (true)
            {
                Console.WriteLine(options);
                string read = Console.ReadLine();
                if (read.StartsWith("0"))
                    break;
            }
        }

        public void AddPlayer()
        {

        }

        public void AddActivePlayer()
        {
            
        }

        public void AddMatch()
        {

        }

        public void AddTeam()
        {

        }
    }
}
