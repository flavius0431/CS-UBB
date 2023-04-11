using log4net.Config;
using System.Configuration;
using ConsoleApp1.domain;
using ConsoleApp1.repository0;

namespace Tema1csharp
{
    internal class Program
    {
        private static readonly log4net.ILog log = log4net.LogManager.GetLogger(System.Reflection.MethodBase.GetCurrentMethod().DeclaringType);
        static void Main(string[] args)
        {
            log4net.Config.XmlConfigurator.Configure();
            Console.WriteLine("Joc e logger:");
            log.Info("mergee ba");
            Console.WriteLine("Configuration Settings for concursDB {0}", GetConnectionStringByName("concursDB"));
            IDictionary<String, string> props = new SortedList<String, String>();
            props.Add("ConnectionString", GetConnectionStringByName("concursDB"));

            Console.WriteLine("OficiuPersoanaDBRepo  tema1v2");
            OficiuPersoanaDBRepo oficiuPersoanaDBRepo = new OficiuPersoanaDBRepo(props);

            Console.WriteLine("ProbaDBRepository");
            ProbaDBRepo probaDBRepo = new ProbaDBRepo(props);

            Console.WriteLine("ParticipantDBRepository");
            ParticipantDBRepo participantDBRepo = new ParticipantDBRepo(props, probaDBRepo);

            if (oficiuPersoanaDBRepo.LogIn("flavius", "1234") != null)
            {
                System.Console.WriteLine("e bine");
            }
            else
            {
                System.Console.WriteLine("nu face nimic");
            }

            foreach (Participant participant in participantDBRepo.FindAll())
            {
                System.Console.WriteLine(participant);
            }


        }

        static string GetConnectionStringByName(string name)
        {
            // Assume failure.
            string returnValue = null;

            // Look for the name in the connectionStrings section.
            ConnectionStringSettings settings = ConfigurationManager.ConnectionStrings[name];

            // If found, return the connection string.
            if (settings != null)
                returnValue = settings.ConnectionString;

            return returnValue;
        }
    }
}