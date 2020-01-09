using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace seminar9
{
    class Message
    {
        public Message()
        {
        }

        public Message(string content, string from, string to)
        {
            this.Content = content;
            this.From = from;
            this.To = to;
        }

        public string From { get; set; }

        public string To { get; set; }

        public string Content { get; set; }

        public override string ToString()
        {
            return "Message: to=" + To + ", from=" + From + ", content=" + Content;
        }
    }
}
