using Portfolio.Backend.Csharp.Models.Enums;
using System.ComponentModel.DataAnnotations;

namespace Portfolio.Backend.Csharp.Models.Entities
{
    public class Authentication
    {

        public Authentication(string userId, string password, AccountStatus accountStatus)
        {
            UserId = userId;
            Password = password;
            AccountStatus = accountStatus;
        }

        public Authentication()
        {
        }

        [Key]
        public string UserId { get; set; }
        public string Password { get; set; }
        public AccountStatus AccountStatus { get; set; }
    }
}
