using Portfolio.Backend.Csharp.Interfaces;
using Portfolio.Backend.Csharp.Models.Entities;
using Portfolio.Backend.Csharp.Models.Enums;
using Portfolio.Backend.Csharp.Models.Requests;
using Portfolio.Backend.Csharp.Models.Responses;

namespace Portfolio.Backend.Csharp.Services
{
    public class AuthenticationService : IAuthenticationService
    {
        private readonly IAuthenticationRepository _authenticationRepository;
        private readonly IUserService _userService;

        public AuthenticationService(IAuthenticationRepository authenticationRepository, IUserService userService)
        {
            _authenticationRepository = authenticationRepository;
            _userService = userService;
        }

        public Task<AuthenticationResponse> AuthenticateUser(AuthenticationRequest authenticationRequest)
        {
            var foundUser = _userService.GetUser(authenticationRequest.UserId, authenticationRequest.UserId);
            if (foundUser == null)
            {
                return null;
            }


            return null;
        }

        public async Task<Authentication> RegisterUser(AuthenticationRequest authenticationRequest)
        {
            var foundUser = await _userService.GetUser(authenticationRequest.UserId, authenticationRequest.UserId);
            if (foundUser == null)
            {
                return null;
            }

            Authentication loginDetails = await _authenticationRepository.GetUserByIdAsync(foundUser.UserId);
            if (loginDetails == null)
            {
                return null;
            }

            if (foundUser.UserId != loginDetails.Password)
            {
                return null;
            }

            loginDetails.Password = GenerateSaltAndHash(authenticationRequest.Password);
            loginDetails.AccountStatus = AccountStatus.Active;
            Console.WriteLine("UserId: {0}\nPassword: {1}\nAccount Status: {2}", loginDetails.UserId, loginDetails.Password, loginDetails.AccountStatus);

            return await _authenticationRepository.UpdateUserAsync(loginDetails);
        }

        private string GenerateSaltAndHash(string password)
        {
            string salt = BCrypt.Net.BCrypt.GenerateSalt();
            return BCrypt.Net.BCrypt.HashPassword(password, salt);
        }
    }
}
