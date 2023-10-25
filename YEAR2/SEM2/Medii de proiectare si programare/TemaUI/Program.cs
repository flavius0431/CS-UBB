using log4net.Config;
using System.Configuration;
using TemaUI.domain;
using TemaUI.repository0;
using TemaUI.service;

namespace TemaUI
{
    internal class Program
    {
        private static readonly log4net.ILog log = log4net.LogManager.GetLogger(System.Reflection.MethodBase.GetCurrentMethod().DeclaringType);
        static void Main(string[] args)
        {
            log4net.Config.XmlConfigurator.Configure();
            
            log.Info("mergee ba");
            Console.WriteLine("Configuration Settings for concursDB {0}", GetConnectionStringByName("concursDB"));
            IDictionary<String, string> props = new SortedList<String, String>();
            props.Add("ConnectionString", GetConnectionStringByName("concursDB"));

            OficiuPersoanaDBRepo oficiuPersoanaDBRepo = new OficiuPersoanaDBRepo(props);
            ProbaDBRepo probaDBRepo = new ProbaDBRepo(props);
            ParticipantDBRepo participantDBRepo = new ParticipantDBRepo(props, probaDBRepo);

            Service service = new Service(oficiuPersoanaDBRepo, participantDBRepo, probaDBRepo);

            ApplicationConfiguration.Initialize();
            Form1 login = new Form1();
            login.setSevice(service);
            Application.Run(login);

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