using Portfolio.Backend.Csharp.Interfaces;
using Portfolio.Backend.Csharp.Models.Entities;
using Portfolio.Backend.Csharp.Models.Requests;

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
            throw new NotImplementedException();
        }

        public async Task<User> DeleteUser(string userId)
        {
            throw new NotImplementedException();
        }

        public async Task<User> GetUser(UserRequest userRequest)
        {
            var usernameExists = await _userRepository.GetUserByUsernameAsync(userRequest.Username);
            if (usernameExists != null)
            {
                return usernameExists;
            }

            var emailExists = await _userRepository.GetUserByEmailAsync(userRequest.Email);
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
            throw new NotImplementedException();
        }
    }
}
