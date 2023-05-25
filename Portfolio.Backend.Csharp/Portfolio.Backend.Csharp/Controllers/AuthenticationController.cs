using Microsoft.AspNetCore.Mvc;
using Portfolio.Backend.Csharp.Interfaces;
using Portfolio.Backend.Csharp.Models.Requests;
using Portfolio.Backend.Csharp.Services;

namespace Portfolio.Backend.Csharp.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class AuthenticationController : Controller
    {
        private readonly IAuthenticationService _authenticationService;

        public AuthenticationController(IAuthenticationService authenticationService)
        {
            _authenticationService = authenticationService;
        }

        [HttpPost]
        [Route("/Login")]
        public async Task<IActionResult> Authenticate([FromBody] AuthenticationRequest authenticationRequest)
        {
            return Ok();
        }

        [HttpPost]
        [Route("/Register")]
        public async Task<IActionResult> RegisterUser([FromBody] AuthenticationRequest authenticationRequest)
        {
            return Ok(await _authenticationService.RegisterUser(authenticationRequest));
        }
    }
}
