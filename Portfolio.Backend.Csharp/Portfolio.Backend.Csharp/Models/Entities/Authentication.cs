using Portfolio.Backend.Csharp.Models.Enums;

namespace Portfolio.Backend.Csharp.Models.Entities
{
    public class Authentication
    {
        public string UserId { get; set; }
        public string Password { get; set; }
        public AccountStatus AccountStatus { get; set; }
    }
}
