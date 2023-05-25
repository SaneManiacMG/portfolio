using Portfolio.Backend.Csharp.Models.Entities;
using Portfolio.Backend.Csharp.Models.Requests;
using Portfolio.Backend.Csharp.Models.Responses;

namespace Portfolio.Backend.Csharp.Interfaces
{
    public interface IAuthenticationService
    {
        Task<AuthenticationResponse> AuthenticateUser(AuthenticationRequest authenticationRequest);
        Task<Authentication> RegisterUser(AuthenticationRequest authenticationRequest);
    }
}
