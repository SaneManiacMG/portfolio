using Microsoft.EntityFrameworkCore;
using Portfolio.Backend.Csharp.Data;
using Portfolio.Backend.Csharp.Interfaces;
using Portfolio.Backend.Csharp.Models.User;

namespace Portfolio.Backend.Csharp.Repositories
{
    public class UserRepository : IUserRepository
    {
        private readonly PortfolioDbContext _dbContext;

        public UserRepository(PortfolioDbContext dbContext)
        {
            _dbContext = dbContext;
        }

        public async Task<User> AddUserAsync(User user)
        {
            _dbContext.Users.Add(user);
            await _dbContext.SaveChangesAsync();

            return user;
        }

        public async Task<User> DeleteUserAsync(User user)
        {
            _dbContext.Users.Remove(user);
            await _dbContext.SaveChangesAsync();

            return user;
        }

        public async Task<User> GetUserByEmailAsync(string email)
        {
#pragma warning disable CS8603 // Possible null reference return.
            return await _dbContext.Users.FirstOrDefaultAsync(u => u.Email == email);
#pragma warning restore CS8603 // Possible null reference return.
        }

        public async Task<User> GetUserByIdAsync(string userId)
        {
#pragma warning disable CS8603 // Possible null reference return.
            return await _dbContext.Users.FindAsync(userId);
#pragma warning restore CS8603 // Possible null reference return.
        }

        public Task<User> GetUserByUsernameAsync(string username)
        {
            return _dbContext.Users.FirstOrDefaultAsync(u => u.Username == username)!;
        }

        public async Task<List<User>> GetUsersAsync()
        {
            return await _dbContext.Users.ToListAsync();
        }

        public async Task<User> UpdateUserAsync(User user)
        {
            _dbContext.Update(user);
            await _dbContext.SaveChangesAsync();

            return user;
        }
    }
}
    