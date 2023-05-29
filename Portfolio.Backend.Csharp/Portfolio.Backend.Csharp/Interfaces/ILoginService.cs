using Portfolio.Backend.Csharp.Models.Entities;
using Portfolio.Backend.Csharp.Models.Requests;
using Portfolio.Backend.Csharp.Models.Responses;

namespace Portfolio.Backend.Csharp.Interfaces
{
    public interface ILoginService
    {
        Task<LoginResponse> AuthenticateUser(LoginRequest authenticationRequest);
        Task<Login> RegisterUser(LoginRequest authenticationRequest);
    }
}
