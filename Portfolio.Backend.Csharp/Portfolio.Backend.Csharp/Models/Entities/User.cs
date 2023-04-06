using System.ComponentModel.DataAnnotations;

namespace Portfolio.Backend.Csharp.Models.Entities
{
    public class User
    {
        public string UserId { get; set; }
        public string UserName { get; set; }
        public string FirstName { get; set; }
        public string LastName { get; set; }
        public string Email { get; set; }
        public string PhoneNr { get; set; }
        public bool IsOwner { get; set; }
    }
}
