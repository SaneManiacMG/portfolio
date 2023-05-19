﻿using AutoMapper;
using Portfolio.Backend.Csharp.Models.Entities;
using Portfolio.Backend.Csharp.Models.Requests;
using Portfolio.Backend.Csharp.Models.Responses;
using Portfolio.Backend.Csharp.Models.User.Requests;

namespace Portfolio.Backend.Csharp.Mapper
{
    public class AutoMapperProfile : Profile
    {
        public AutoMapperProfile()
        {
            CreateMap<UserRequest, User>();
            CreateMap<User, UserResponse>();
            //CreateMap<List<User>, List<UserResponse>>();
            CreateMap<AuthenticationRequest, Authentication>();
            CreateMap<Authentication, AuthenticationResponse>();
        }
    }
}
