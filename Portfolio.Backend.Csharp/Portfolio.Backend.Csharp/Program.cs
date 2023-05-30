using Microsoft.EntityFrameworkCore;
using Portfolio.Backend.Csharp.Configs;
using Portfolio.Backend.Csharp.Interfaces;
using Portfolio.Backend.Csharp.Services;
using Portfolio.Backend.Csharp.Repositories;
using Microsoft.AspNetCore.Authentication.JwtBearer;
using Microsoft.IdentityModel.Tokens;
using System.Text;

var builder = WebApplication.CreateBuilder(args);

// Add services to the container.

builder.Services.AddControllers();
// Learn more about configuring Swagger/OpenAPI at https://aka.ms/aspnetcore/swashbuckle
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();

builder.Services.AddScoped<IUserRepository, UserRepository>();
builder.Services.AddScoped<ISequenceGenerator, SequenceGenerator>();
builder.Services.AddScoped<IUserService, UserService>();
builder.Services.AddScoped<ILoginRepository, LoginRepository>();
builder.Services.AddScoped<ILoginService, LoginService>();

builder.Services.AddAutoMapper(typeof(Program).Assembly);

builder.Services.AddDbContext<PortfolioDbContext>(
    options => options.UseSqlServer(
        builder.Configuration.GetConnectionString("PortfolioApiConnectionString")));

builder.Services.AddAuthentication(JwtBearerDefaults.AuthenticationScheme)
    .AddJwtBearer(options =>
    {
        options.TokenValidationParameters = new TokenValidationParameters
        {
            ValidateIssuer = true,
            ValidateAudience = false,
            ValidateLifetime = true,
            ValidateIssuerSigningKey = true,
            ValidIssuer = "SaneManiacWorks",
            IssuerSigningKey = new SymmetricSecurityKey(Encoding.UTF8
            .GetBytes("2dae84f846e4f4b158a8d26681707f4338495bc7ab68151d7f7679cc5e56202dd3da0d356da007a7c28cb0b780418f4f3246769972d6feaa8f610c7d1e7ecf6a"))
        };
    });

var app = builder.Build();

// Configure the HTTP request pipeline.
if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI();
}

app.UseHttpsRedirection();

app.UseAuthentication();
app.UseAuthorization();

app.MapControllers();

app.Run();
