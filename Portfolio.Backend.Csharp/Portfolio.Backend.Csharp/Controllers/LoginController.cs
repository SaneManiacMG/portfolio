﻿using Microsoft.AspNetCore.Mvc;
using Portfolio.Backend.Csharp.Configs;
using Portfolio.Backend.Csharp.Interfaces;
using Portfolio.Backend.Csharp.Models.Requests;
using Portfolio.Backend.Csharp.Services;

namespace Portfolio.Backend.Csharp.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class LoginController : Controller
    {
        private readonly ILoginService _loginService;
        private readonly JwtAuthenticationManager _jwtAuthenticationManager;

        public LoginController(ILoginService loginService, JwtAuthenticationManager jwtAuthenticationManager)
        {
            _loginService = loginService;
            _jwtAuthenticationManager = jwtAuthenticationManager;
        }

        [HttpPost]
        [Route("/Login")]
        public async Task<IActionResult> LoginUser([FromBody] LoginRequest loginRequest)
        {
            return Ok(await _loginService.AuthenticateUser(loginRequest));
        }

        [HttpPost]
        [Route("/Register")]
        public async Task<IActionResult> RegisterUser([FromBody] LoginRequest loginRequest)
        {
            return Ok(await _loginService.RegisterUser(loginRequest));
        }
    }
}
