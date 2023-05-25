namespace Portfolio.Backend.Csharp.Models.Responses
{
    public class AuthenticationResponse
    {
        public AuthenticationResponse(string userId, string token)
        {
            UserId = userId;
            Token = token;
        }

        public string UserId { get; set; }
        public string Token { get; set; }
    }
}
