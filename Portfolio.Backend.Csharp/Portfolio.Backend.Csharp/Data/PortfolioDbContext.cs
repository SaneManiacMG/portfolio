using Microsoft.EntityFrameworkCore;
using Portfolio.Backend.Csharp.Models.Entities;

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
