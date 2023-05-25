using Portfolio.Backend.Csharp.Models.Entities;

namespace Portfolio.Backend.Csharp.Interfaces
{
    public interface IAuthenticationRepository
    {
        Task<Authentication> GetUserByIdAsync(string userId);
        Task<Authentication> CreateNewUserAsync(Authentication authentication);
        Task<Authentication> UpdateUserAsync(Authentication authentication);
        Task<Authentication> DeleteUserAsync(Authentication authentication);
    }
}
