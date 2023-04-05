using backend_portfolio_csharp.Models;

namespace backend_portfolio_csharp.Interfaces
{
    public interface IUserService
    {
        public List<User> GetAllUsers();
        public User GetUserById(string userId);
        public User AddUser(User user);
        public User UpdateUser(User user);
        public User DeleteUser(string userId);

        public string GenerateUserSequence();

    }
}
