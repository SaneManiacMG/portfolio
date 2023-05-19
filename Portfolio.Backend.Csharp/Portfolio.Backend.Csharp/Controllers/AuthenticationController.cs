using Microsoft.AspNetCore.Mvc;
using Portfolio.Backend.Csharp.Models.Requests;

namespace Portfolio.Backend.Csharp.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class AuthenticationController : Controller
    {
        public AuthenticationController()
        {
        }

        [HttpPost]
        [Route("/Login")]
        public IActionResult Authenticate([FromBody] AuthenticationRequest authenticationRequest)
        {
            return View();
        }
}
