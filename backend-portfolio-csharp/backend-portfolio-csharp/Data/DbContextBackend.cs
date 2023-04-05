using backend_portfolio_csharp.Models;
using Microsoft.EntityFrameworkCore;

namespace backend_portfolio_csharp.Data
{
    public class DbContextBackend : DbContext
    {
        public DbContextBackend(DbContextOptions options) : base(options) { }

        public DbSet<User> Users { get; set; }
    }
}
