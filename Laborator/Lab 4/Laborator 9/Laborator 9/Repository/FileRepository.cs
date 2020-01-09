using Laborator_9.Domain;
using Laborator_9.Domain.Validators;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Laborator_9.Repository
{ 
    public delegate E Parser<E>(string line);
    public delegate String Unparser<E>(E entity);

    abstract class FileRepository<ID, E> : InMemoryRepository<ID, E> where E : Entity<ID>
    {
        protected string Filename { get; set; }
        protected Parser<E> parser;
        protected Unparser<E> unparser;

        public FileRepository(IValidator<E> validator, string filename, Parser<E> parser, Unparser<E> unparser) : base(validator)
        {
            Filename = filename;
            this.parser = parser;
            this.unparser = unparser;
            if (parser != null)
                LoadFromFile();
        }

        protected virtual void LoadFromFile()
        {
            List<E> list = DataReader.ReadData(Filename, parser);
            list.ForEach(x => elements[x.ID] = x);
        }

        protected virtual void SaveToFile()
        {
            ;
        }

        public override E Delete(ID id)
        {
            E e = base.Delete(id);
            SaveToFile();
            return e;
        }

        public override E Save(E entity)
        {
            E e = base.Save(entity);
            SaveToFile();
            return e;
        }

        public override E Update(E entity)
        {
            E e = base.Update(entity);
            SaveToFile();
            return e;
        }
    }
}
