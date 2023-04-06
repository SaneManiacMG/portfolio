using Portfolio.Backend.Csharp.Models.Entities;

namespace Portfolio.Backend.Csharp.Interfaces
{
    public interface IUserService
    {
        public Task<List<User>> GetUsers();
        public Task<User> GetUser(User user);
        public Task<User> GetUserById(string userId);
        public Task<User> GetUserByUsername(string username);
        public Task<User> GetUserByEmail(string email);

        public Task<User> CreateUser(User user);

        public Task<User> UpdateUser(string password);

        public Task<User> DeleteUser(User user);

    }
}
