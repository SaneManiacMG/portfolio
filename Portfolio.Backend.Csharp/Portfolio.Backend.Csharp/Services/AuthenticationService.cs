using Portfolio.Backend.Csharp.Data;
using Portfolio.Backend.Csharp.Interfaces;
using Portfolio.Backend.Csharp.Models.Entities;
using Portfolio.Backend.Csharp.Models.Enums;
using Portfolio.Backend.Csharp.Models.Requests;
using Portfolio.Backend.Csharp.Models.Responses;
using System.IdentityModel.Tokens.Jwt;
using System.Security.Claims;
using System.Text;
using Microsoft.Extensions.Options;
using Microsoft.IdentityModel.Tokens;

namespace Portfolio.Backend.Csharp.Services
{
    public class AuthenticationService : IAuthenticationService
    {
        private readonly IAuthenticationRepository _authenticationRepository;
        private readonly IUserService _userService;
        private readonly JwtSettings _jwtSettings;

        public AuthenticationService(IAuthenticationRepository authenticationRepository, IUserService userService,
            IOptions<JwtSettings> jwtSettings)
        {
            _authenticationRepository = authenticationRepository;
            _userService = userService;
            _jwtSettings = jwtSettings.Value;
        }

        public async Task<AuthenticationResponse> AuthenticateUser(AuthenticationRequest authenticationRequest)
        {
            User foundUser = await _userService.GetUser(authenticationRequest.UserId, authenticationRequest.UserId);
            Authentication loginDetails = await _authenticationRepository.GetUserByIdAsync(foundUser.UserId);

            bool userFound = DoesUserExist(foundUser, loginDetails);

            if (!userFound)
            {
                return null;
            }

            bool passwordMatch = BCrypt.Net.BCrypt.Verify(authenticationRequest.Password, loginDetails.Password);

            if (!passwordMatch)
            {
                return null;
            }

            AuthenticationResponse authenticationResponse = new AuthenticationResponse(authenticationRequest.UserId, GenerateJwtToken(authenticationRequest.UserId));

            return authenticationResponse;
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

        private string GenerateJwtToken(string userId)
        {
            var tokenHandler = new JwtSecurityTokenHandler();
            var key = Encoding.ASCII.GetBytes(_jwtSettings.SecretKey);
            var tokenDescriptor = new SecurityTokenDescriptor
            {
                Subject = new ClaimsIdentity(new[]
                {
                    new Claim(ClaimTypes.NameIdentifier, userId),
                }),
                Expires = DateTime.UtcNow.AddMinutes(_jwtSettings.ExpirationMinutes),
                SigningCredentials = new SigningCredentials(new SymmetricSecurityKey(key), SecurityAlgorithms.HmacSha256Signature),
            };
            var token = tokenHandler.CreateToken(tokenDescriptor);
            return tokenHandler.WriteToken(token);
        }
    }
}
