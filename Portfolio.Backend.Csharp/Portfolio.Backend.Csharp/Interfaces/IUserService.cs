using Portfolio.Backend.Csharp.Models.User;
using Portfolio.Backend.Csharp.Models.User.Requests;

namespace Portfolio.Backend.Csharp.Interfaces
{
    public interface IUserService
    {
        public Task<List<User>> GetUsers();
        public Task<User> GetUser(string username, string email);
        public Task<User> AddUser(UserRequest userRequest);
        public Task<User> UpdateUser(UserRequest userRequest);
        public Task<User> DeleteUser(string userId);

    }
}
