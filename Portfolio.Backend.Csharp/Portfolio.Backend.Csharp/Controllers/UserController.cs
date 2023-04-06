using Microsoft.AspNetCore.Mvc;
using Portfolio.Backend.Csharp.Models.Entities;

namespace Portfolio.Backend.Csharp.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class UserController : Controller
    {
        [HttpGet]
        [Route("/GetUsers")]
        public async Task<IActionResult> GetAllUsers()
        {
            return Ok();
        }

        [HttpGet]
        [Route("/GetUser/{userId}")]
        public async Task<IActionResult> GetUser([FromRoute] string userId)
        {
            return Ok();
        }

        [HttpPost]
        [Route("/AddUser")]
        public async Task<IActionResult> AddUser([FromBody] User user)
        {
            return Ok();
        }

        [HttpPut]
        [Route("/UpdateUser")]
        public async Task<IActionResult> UpdateUser([FromBody] User user)
        {
            return Ok();
        }

        [HttpDelete]
        [Route("/DeleteUser")]
        public async Task<IActionResult> DeleteUser([FromBody] User user)
        {
            return Ok();
        }
    }
}
