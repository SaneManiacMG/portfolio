using Portfolio.Backend.Csharp.Models.Entities;
using Portfolio.Backend.Csharp.Models.Requests;

namespace Portfolio.Backend.Csharp.Interfaces
{
    public interface IUserService
    {
        public Task<List<User>> GetUsers();
        public Task<User> GetUser(UserRequest userRequest);
        public Task<User> AddUser(UserRequest userRequest);
        public Task<User> UpdateUser(UserRequest userRequest);
        public Task<User> DeleteUser(string userId);

    }
}
