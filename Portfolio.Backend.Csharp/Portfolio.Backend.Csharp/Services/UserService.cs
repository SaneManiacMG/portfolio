using Portfolio.Backend.Csharp.Interfaces;
using Portfolio.Backend.Csharp.Models.Entities;
using Portfolio.Backend.Csharp.Models.Requests;

namespace Portfolio.Backend.Csharp.Services
{
    public class UserService : IUserService
    {
        private readonly IUserRepository _userRepository;
        private readonly ISequenceGenerator _sequenceGenerator;

        public UserService(IUserRepository userRepository)
        {
            _userRepository = userRepository;
            _sequenceGenerator = new SequenceGenerator();
        }

        public Task<User> AddUser(UserRequest userRequest)
        {
            throw new NotImplementedException();
        }

        public Task<User> DeleteUser(string userId)
        {
            throw new NotImplementedException();
        }

        public Task<User> GetUser(UserRequest userRequest)
        {
            throw new NotImplementedException();
        }

        public Task<User> GetUserByEmail(string email)
        {
            throw new NotImplementedException();
        }

        private Task<User> GetUserById(string userId)
        {
            throw new NotImplementedException();
        }

        public Task<User> GetUserByUsername(string username)
        {
            throw new NotImplementedException();
        }

        public Task<List<User>> GetUsers()
        {
            throw new NotImplementedException();
        }

        public Task<User> UpdateUser(UserRequest userRequest)
        {
            throw new NotImplementedException();
        }
    }
}
