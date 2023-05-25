using Portfolio.Backend.Csharp.Data;
using Portfolio.Backend.Csharp.Interfaces;
using Portfolio.Backend.Csharp.Models.Entities;

namespace Portfolio.Backend.Csharp.Repositories
{
    public class AuthenticationRepository : IAuthenticationRepository
    {
        private readonly PortfolioDbContext _dbContext;
        public AuthenticationRepository(PortfolioDbContext portfolioDbContext) {
            _dbContext = portfolioDbContext;        
        }

        public async Task<Authentication> CreateNewUserAsync(Authentication authentication)
        {
            _dbContext.Add(authentication);
            await _dbContext.SaveChangesAsync();
            return authentication;
        }

        public async Task<Authentication> DeleteUserAsync(Authentication authentication)
        {
            _dbContext.LoginDetails.Remove(authentication);
            await _dbContext.SaveChangesAsync();

            return authentication;
        }

        public async Task<Authentication> GetUserByIdAsync(string userId)
        {
            return await _dbContext.LoginDetails.FindAsync(userId);
        }

        public async Task<Authentication> UpdateUserAsync(Authentication authentication)
        {
            _dbContext.Update(authentication);
            await _dbContext.SaveChangesAsync();

            return authentication;
        }
    }
}
