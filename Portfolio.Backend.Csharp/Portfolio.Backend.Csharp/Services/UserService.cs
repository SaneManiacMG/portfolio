using Portfolio.Backend.Csharp.Interfaces;
using Portfolio.Backend.Csharp.Models.User;
using Portfolio.Backend.Csharp.Models.User.Requests;

namespace Portfolio.Backend.Csharp.Services
{
    public class UserService : IUserService
    {
        private readonly IUserRepository _userRepository;
        private readonly ISequenceGenerator _sequenceGenerator;

        public UserService(IUserRepository userRepository, ISequenceGenerator sequenceGenerator)
        {
            _userRepository = userRepository;
            _sequenceGenerator = sequenceGenerator;
        }

        public async Task<User> AddUser(UserRequest userRequest)
        {
            var userExists = await GetUser(userRequest.Username, userRequest.Email)!;
            if(userExists != null)
            {
                return userExists;
            }

            string generatedUserId = _sequenceGenerator.UserIdSequenceGenerator();
            User newUser = new User(generatedUserId, userRequest);
            return await _userRepository.AddUserAsync(newUser);
        }

        public async Task<User> DeleteUser(string userId)
        {
            var userExists = await GetUserById(userId);
            return await _userRepository.DeleteUserAsync(userExists)!;
        }

        public async Task<User> GetUser(string username, string email)
        {
            var usernameExists = await GetUserByEmail(email);
            if (usernameExists != null)
            {
                return usernameExists;
            }

            var emailExists = await GetUserByUsername(username);
            if (emailExists != null)
            {
                return emailExists;
            }
            return null;
        }

        private async Task<User> GetUserByEmail(string email)
        {
            return await _userRepository.GetUserByEmailAsync(email);
        }

        private async Task<User> GetUserById(string userId)
        {
            return await _userRepository.GetUserByIdAsync(userId);
        }

        private async Task<User> GetUserByUsername(string username)
        {
            return await _userRepository.GetUserByUsernameAsync(username);
        }

        public async Task<List<User>> GetUsers()
        {
            return await _userRepository.GetUsersAsync();
        }

        public async Task<User> UpdateUser(UserRequest userRequest)
        {
            var userExists = await GetUser(userRequest.Username, userRequest.Email);
            if (userExists != null)
            {
                userExists.Username = userRequest.Username;
                userExists.FirstName = userRequest.FirstName;
                userExists.LastName = userRequest.LastName;
                userExists.Email = userRequest.Email;
                userExists.PhoneNr = userRequest.PhoneNr;

                return await _userRepository.UpdateUserAsync(userExists);
            }
            return null;
        }
    }
}
