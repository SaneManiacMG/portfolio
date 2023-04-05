namespace backend_portfolio_csharp.Models
{
    public class User
    {
        public string UserId { get; set; }
        public string UserName { get; set; }
        public string FirstName { get; set; }
        public string LastName { get; set; }
        public string Email { get; set; }
        public string PhoneNr { get; set; }
        public Enum Role { get; set; }
        public bool IsActive { get; set; }
    }
}
