using Portfolio.Backend.Csharp.Models.User;
using Portfolio.Backend.Csharp.Models.User.Requests;
using Portfolio.Backend.Csharp.Models.User.Responses;

namespace Portfolio.Backend.Csharp.Interfaces
{
    public interface IUserService
    {
/*        public Task<List<User>> GetUsers();
        public Task<User> GetUser(string username, string email);*/
        public Task<UserResponse> AddUser(UserRequest userRequest);
        public Task<UserResponse> UpdateUser(UserRequest userRequest);
        public Task<UserResponse> DeleteUser(string userId);
        public Task<UserResponse> GetUserResponse(UserRequest userRequest);
        public Task<List<UserResponse>> GetAllUsersResponse();

    }
}
