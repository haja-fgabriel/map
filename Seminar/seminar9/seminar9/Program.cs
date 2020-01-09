using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace seminar9
{
    class Program
    {
        static bool LessThan2(int n)
        {
            return n <= 2;
        }

        static void Main(string[] args)
        {
            int[] numbers = { 1, 2, 0, 5, 6, 3, 2, 2 };

            Predicate<int> predicateLambda = n => n <= 2;
            Predicate<int> predicateMethod = LessThan2;

            var res = numbers.Where(predicateLambda.Invoke);
            var r2 = from n in numbers
                     where predicateLambda.Invoke(n)
                     select n;

            var freq = numbers
                .GroupBy(x => x)
                .Select(n => new { Key = n.Key, Freq = n.Count() });

            numbers[0] = 5;

            Console.WriteLine("'Group by' query");
            freq.ToList().ForEach(Console.WriteLine);

            var freqSql = from n in numbers
                          group n by n into g
                          select new
                          {
                              Element = g.Key,
                              Count = g.Count()
                          };

            var resSql = from n in numbers
                         where n <= 2
                         select new { Value = n, Patrat = n * n };  

            //Console.WriteLine("Using method reference");
            //res.ToList().ForEach(Console.WriteLine);
            //Console.WriteLine("Using lambda function");
            //res.ToList().ForEach(x => Console.WriteLine(x));
            //Console.WriteLine("Using SQL-like queries");
            //r2.ToList().ForEach(Console.WriteLine);

            numbers
                .ToList()
                .Sort((x, y) => (y - x));
            numbers
                .ToList()
                .ForEach(Console.WriteLine);
        }
        
    }
}
