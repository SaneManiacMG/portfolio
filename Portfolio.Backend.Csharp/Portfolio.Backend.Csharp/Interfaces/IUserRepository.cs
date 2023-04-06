using Portfolio.Backend.Csharp.Models.Entities;

namespace Portfolio.Backend.Csharp.Interfaces
{
    public interface IUserRepository
    {
        Task<List<User>> GetUsersAsync();
        Task<User> GetUserByIdAsync(string userId);
        Task<User> AddUserAsync(User user);
        Task<User> UpdateUserAsync(User user);
        Task<User> DeleteUserAsync(string userId);
    }
}
