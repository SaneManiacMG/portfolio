using Microsoft.AspNetCore.Mvc;
using Portfolio.Backend.Csharp.Interfaces;
using Portfolio.Backend.Csharp.Models.Entities;
using Portfolio.Backend.Csharp.Models.Requests;

namespace Portfolio.Backend.Csharp.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class UserController : Controller
    {
        private readonly IUserService _userService;

        public UserController(IUserService userService)
        {
            _userService = userService;
        }

        [HttpGet]
        [Route("/GetUsers")]
        public async Task<IActionResult> GetAllUsers()
        {
            return Ok();
        }

        [HttpPost]
        [Route("/GetUser")]
        public async Task<IActionResult> GetUser([FromBody] UserRequest userRequest)
        {
            return Ok();
        }

        [HttpPost]
        [Route("/AddUser")]
        public async Task<IActionResult> AddUser([FromBody] UserRequest userRequest)
        {
            return Ok();
        }

        [HttpPut]
        [Route("/UpdateUser")]
        public async Task<IActionResult> UpdateUser([FromBody] UserRequest userRequest)
        {
            return Ok();
        }

        [HttpDelete]
        [Route("/DeleteUser/{userId}")]
        public async Task<IActionResult> DeleteUser([FromRoute] UserRequest userRequest)
        {
            return Ok();
        }
    }
}
