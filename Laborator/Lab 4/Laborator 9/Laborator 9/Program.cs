using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Laborator_9.Repository;
using Laborator_9.Domain;
using Laborator_9.Domain.Validators;
using Laborator_9.Service;
using Laborator_9.UI;

namespace Laborator_9
{
    class Program
    {
        static void Main(string[] args)
        {
            ActivePlayerFileRepository activePlayerFile = new ActivePlayerFileRepository(new ActivePlayerValidator(), "..\\..\\Data\\ActivePlayers.csv");
            MatchFileRepository matchFile = new MatchFileRepository(new MatchValidator(), "..\\..\\Data\\Matches.csv");
            TeamFileRepository teamFile = new TeamFileRepository(new TeamValidator(), "..\\..\\Data\\Teams.csv");
            PlayerFileRepository playerFile = new PlayerFileRepository(new PlayerValidator(), "..\\..\\Data\\Players.csv");
            StudentFileRepository repository = new StudentFileRepository(new StudentValidator(), "..\\..\\Data\\Students.csv");

            ActivePlayerService activePlayerService = new ActivePlayerService(activePlayerFile, playerFile);
            PlayerService service = new PlayerService(playerFile, repository);
            MatchService matchService = new MatchService(matchFile, activePlayerFile, playerFile);

            ConsoleUI ui = new ConsoleUI(activePlayerService, service, matchService);

            ui.run();

            matchService.GetMatches(DateTime.Parse("2019.12.30"), DateTime.Parse("2020.01.02")).ForEach(Console.WriteLine);
            Console.WriteLine(matchService.GetMatchResult(1));
            activePlayerService.GetActivePlayers(2, 1).ForEach(Console.WriteLine);
            service.GetPlayersByTeam(3).ForEach(Console.WriteLine);
        }
    }
}
