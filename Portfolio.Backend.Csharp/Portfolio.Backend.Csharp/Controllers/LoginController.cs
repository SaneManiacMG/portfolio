using Microsoft.AspNetCore.Mvc;
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
        private readonly IConfiguration _configuration;

        public LoginController(ILoginService loginService, IConfiguration configuration)
        {
            _loginService = loginService;
            _configuration = configuration;
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
