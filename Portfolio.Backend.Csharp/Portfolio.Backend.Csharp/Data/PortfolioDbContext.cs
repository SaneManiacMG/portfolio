using Microsoft.EntityFrameworkCore;
using Portfolio.Backend.Csharp.Models.User;

namespace Portfolio.Backend.Csharp.Data
{
    public class PortfolioDbContext : DbContext
    {
        public PortfolioDbContext(DbContextOptions options) : base(options)
        {
        }

        public DbSet<User> Users { get; set; }
    }
}
