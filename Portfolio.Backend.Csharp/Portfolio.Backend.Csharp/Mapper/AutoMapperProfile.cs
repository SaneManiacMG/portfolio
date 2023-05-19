using AutoMapper;
using Portfolio.Backend.Csharp.Models.User;
using Portfolio.Backend.Csharp.Models.User.Requests;
using Portfolio.Backend.Csharp.Models.User.Responses;

namespace Portfolio.Backend.Csharp.Mapper
{
    public class AutoMapperProfile : Profile
    {
        public AutoMapperProfile()
        {
            CreateMap<UserRequest, User>();
            CreateMap<User, UserResponse>();
            //CreateMap<List<User>, List<UserResponse>>();
        }
    }
}
