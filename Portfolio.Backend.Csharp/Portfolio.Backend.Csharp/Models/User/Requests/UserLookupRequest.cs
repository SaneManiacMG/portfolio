namespace Portfolio.Backend.Csharp.Models.User.Requests
{
    public class UserLookupRequest
    {
        public string? Username { get; set; }
        public string? Email { get; set; }

        public UserLookupRequest(string? username, string? email)
        {
            Username = username;
            Email = email;
        }
    }
}
