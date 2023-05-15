using AutoMapper;
using Portfolio.Backend.Csharp.Models.User;
using Portfolio.Backend.Csharp.Models.User.Requests;

namespace Portfolio.Backend.Csharp.Mapper
{
    public class AutoMapperProfile : Profile
    {
        public AutoMapperProfile()
        {
            CreateMap<UserRequest, User>().ReverseMap();
        }
    }
}
