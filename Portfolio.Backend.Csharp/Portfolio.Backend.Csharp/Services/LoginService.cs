using Portfolio.Backend.Csharp.Interfaces;
using Portfolio.Backend.Csharp.Models.Entities;
using Portfolio.Backend.Csharp.Models.Enums;
using Portfolio.Backend.Csharp.Models.Requests;
using Portfolio.Backend.Csharp.Models.Responses;
using System.IdentityModel.Tokens.Jwt;
using System.Security.Claims;
using Microsoft.IdentityModel.Tokens;

namespace Portfolio.Backend.Csharp.Services
{
    public class LoginService : ILoginService
    {
        private readonly ILoginRepository _loginRepository;
        private readonly IUserService _userService;
        private readonly IConfiguration _configuration;

        public LoginService(ILoginRepository loginRepository, IUserService userService, IConfiguration configuration)
        {
            _loginRepository = loginRepository;
            _userService = userService;
            _configuration = configuration;
        }

        public async Task<LoginResponse> AuthenticateUser(LoginRequest authenticationRequest)
        {
            User foundUser = await _userService.GetUser(authenticationRequest.UserId, authenticationRequest.UserId);
            Login loginDetails = await _loginRepository.GetUserByIdAsync(foundUser.UserId);

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

            LoginResponse authenticationResponse = new LoginResponse(CreateToken(foundUser));

            return authenticationResponse;
        }

        public async Task<Login> RegisterUser(LoginRequest authenticationRequest)
        {
            User foundUser = await _userService.GetUser(authenticationRequest.UserId, authenticationRequest.UserId);
            Login loginDetails = await _loginRepository.GetUserByIdAsync(foundUser.UserId);

            bool userFound = DoesUserExist(foundUser, loginDetails);
            Console.WriteLine("User found: {0}", userFound);

            if (!userFound)
            {
                return null;
            }

            Console.WriteLine("Generate hashed password");
            loginDetails.Password = GenerateSaltAndHash(authenticationRequest.Password);
            loginDetails.AccountStatus = AccountStatus.Active;
            Console.WriteLine("Setting status to {0}", loginDetails.AccountStatus);

            return await _loginRepository.UpdateUserAsync(loginDetails);
        }

        private string GenerateSaltAndHash(string password)
        {
            string salt = BCrypt.Net.BCrypt.GenerateSalt();
            return BCrypt.Net.BCrypt.HashPassword(password, salt);
        }

        private bool DoesUserExist(User user, Login authentication)
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

        private string CreateToken (User user)
        {
            List<Claim> claims = new List<Claim>
            {
                new Claim(ClaimTypes.UserData, user.UserId),
                new Claim(ClaimTypes.Role, user.Role.ToString())
            };

            var key = new SymmetricSecurityKey(System.Text.Encoding.UTF8.GetBytes(_configuration.GetSection("AppSettings:Key").Value));

            var creds = new SigningCredentials(key, SecurityAlgorithms.HmacSha512);

            var token = new JwtSecurityToken(
                claims: claims,
                expires: DateTime.Now.AddDays(1),
                signingCredentials: creds);

            var jwt = new JwtSecurityTokenHandler().WriteToken(token);

            return jwt;
        }
    }
}
