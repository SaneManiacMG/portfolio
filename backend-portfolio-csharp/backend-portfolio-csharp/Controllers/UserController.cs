using Microsoft.AspNetCore.Mvc;

namespace backend_portfolio_csharp.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class UserController : Controller
    {
        [HttpGet]
        public async Task<IActionResult> GetAllUsers()
        {
            return Ok();
        }

        [HttpGet]
        [Route("{UserId}")]
        public async Task<IActionResult> GetUserById([FromRoute] string userId)
        {
            return Ok();
        }

        [HttpPost]
        public async Task<IActionResult> AddUser([FromBody] UserController user)
        {
            return Ok();
        }

        [HttpPut]
        public async Task<IActionResult> UpdateUser([FromBody] UserController user)
        {
            return Ok();
        }

        [HttpDelete]
        public async Task<IActionResult> DeleteUser([FromBody] UserController user)
        {
            return Ok(); 
        }
    }
}
