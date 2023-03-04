
using Sem11_12.Model;
using Sem11_12.Model.Validator;
using Sem11_12.Repository;
using Sem11_12.Service;
using System;
using System.Collections.Generic;
using System.ComponentModel.Design;
using System.Configuration;
using System.Linq;
using System.Runtime.CompilerServices;
using System.Security.Cryptography;

namespace Sem11_12
{
    class Program
    {

        static void Main(string[] args)
        {


            List<Angajat> angajati = GetAngajatService().FindAllAngajati();

            List<int> l = new List<int>() { 1,2,3,4,5,6,7,8,9,10};          
            var res=l.Where(x => x % 2 == 0);
       
            res.ToList().ForEach(Console.WriteLine);

            DateTime date = DateTime.Now;
            Console.WriteLine(date.ToString("dd/mm/yy hh:MM"));


            
            // 1. afisati doar angajatii care au nivelul junior - extension methods "Where"
            Console.WriteLine("1. afisati doar angajatii care au nivelul junior\n");
            var junior =angajati.Where(x=>x.Nivel ==KnowledgeLevel.Junior);

            junior.ToList().ForEach(Console.WriteLine);
            //2  - cerinta 1 din pdf sem11-12  extension methods 

            Console.WriteLine("2 - cerinta 1 din pdf sem11\n");
            var angajati_sortati = angajati.OrderBy(x=>x.Nivel)
                .ThenByDescending(x=>x.VenitPeOra);
            
            angajati_sortati.ToList().ForEach(Console.WriteLine);

            //2  - cerinta 1 din pdf sem11-12  sql like 
            Console.WriteLine("2  - cerinta 1 din pdf sem11-12  sql like\n");
            IEnumerable<Angajat> angajatiSql = 
                from angajat in angajati 
                orderby angajat.Nivel ascending,
                angajat.VenitPeOra descending
                select angajat;
                 
            angajatiSql.ToList().ForEach(Console.WriteLine);

            //2 cate ore dureaza in medie fiecare tip de sarcina
            Console.WriteLine("2 cate ore dureaza in medie fiecare tip de sarcina\n");
            var sarcini = GetSarcinaService().FindAllSarcini(); 

            var ore = sarcini.GroupBy(x=>x.TipDificultate).Select(
                y => new {dificultate = y.Key,media= y.Average(g=>g.NrOreEstimate) });
            
            ore.ToList().ForEach(Console.WriteLine);

            //3 primii doi cei mai harnici angajati
            Console.WriteLine("3 primii doi cei mai harnici angajati");
            var pontaje = GetPontajService().FindAllPontaje();
            var harnici = angajati.OrderByDescending(y => y.VenitPeOra * 
            pontaje.Where(x => x.Angajat.ID == y.ID).Sum(xl=>xl.Sarcina.NrOreEstimate));

            //venit totaal
            foreach( Angajat a in harnici)
            {
                var pontajeAngajat = pontaje.Where(x => x.Angajat.ID == a.ID).Sum(xl => xl.Sarcina.NrOreEstimate);
                var suma = pontajeAngajat*a.VenitPeOra;
                Console.WriteLine("Angajat"+ a.Nume+"suma:"+ suma );
            }

            harnici.ToList().ForEach(Console.WriteLine);    

        }

        //citim luna de la tastatura
        Console.WriteLine("\n5)Introduceti luan dorita");
        String luna = Console.ReadLine;

        

        private static void Task2()
        {
            

        }


        private static AngajatService GetAngajatService()
        {
            //string fileName2 = ConfigurationManager.AppSettings["angajatiFileName"];
            string fileName = "..\\..\\..\\data\\angajati.txt";
            IValidator<Angajat> vali = new AngajatValidator();

            IRepository<string, Angajat> repo1 = new AngajatInFileRepository(vali, fileName);
            AngajatService service = new AngajatService(repo1);
            return service;
        }

        private static SarcinaService GetSarcinaService()
        {
            //string fileName2 = ConfigurationManager.AppSettings["sarciniFileName"];
            string fileName2 = "..\\..\\..\\data\\sarcini.txt";
            IValidator<Sarcina> vali = new SarcinaValidator();

            IRepository<string, Sarcina> repo1 = new SarcinaInFileRepository(vali, fileName2);
            SarcinaService service = new SarcinaService(repo1);
            return service;
        }

        private static PontajService GetPontajService()
        {
            //string fileName2 = ConfigurationManager.AppSettings["pontajeFileName"];
            string fileName2 = "..\\..\\..\\data\\pontaje.txt";
            IValidator<Pontaj> vali = new PontajValidator();

            IRepository<string, Pontaj> repo1 = new PontajInFileRepository(vali, fileName2);
            PontajService service = new PontajService(repo1);
            return service;
        }

    }
}
