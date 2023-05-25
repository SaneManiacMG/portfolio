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

        public async Task<Authentication> AuthenticateUser(AuthenticationRequest authenticationRequest)
        {
            User foundUser = await _userService.GetUser(authenticationRequest.UserId, authenticationRequest.UserId);
            Authentication loginDetails = await _authenticationRepository.GetUserByIdAsync(foundUser.UserId);

            bool userFound = DoesUserExist(foundUser, loginDetails);

            if (!userFound)
            {
                return null;
            }

            bool passwordMatch = BCrypt.Net.BCrypt.Verify(authenticationRequest.Password, loginDetails.Password);

            Console.WriteLine("Password: {0}", passwordMatch);

            if (!passwordMatch)
            {
                return null;
            }

            return loginDetails;
        }

        public async Task<Authentication> RegisterUser(AuthenticationRequest authenticationRequest)
        {
            User foundUser = await _userService.GetUser(authenticationRequest.UserId, authenticationRequest.UserId);
            Authentication loginDetails = await _authenticationRepository.GetUserByIdAsync(foundUser.UserId);

            bool userFound = DoesUserExist(foundUser, loginDetails);

            if (userFound)
            {
                return null;
            }

            loginDetails.Password = GenerateSaltAndHash(authenticationRequest.Password);
            loginDetails.AccountStatus = AccountStatus.Active;

            return await _authenticationRepository.UpdateUserAsync(loginDetails);
        }

        private string GenerateSaltAndHash(string password)
        {
            string salt = BCrypt.Net.BCrypt.GenerateSalt();
            return BCrypt.Net.BCrypt.HashPassword(password, salt);
        }

        private bool DoesUserExist(User user, Authentication authentication)
        {
            if (user == null)
            {
                return false;
            }

            if (authentication == null)
            {
                return false;
            }

            return true;
        }
    }
}
