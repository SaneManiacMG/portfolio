using Microsoft.EntityFrameworkCore;
using Portfolio.Backend.Csharp.Data;
using Portfolio.Backend.Csharp.Interfaces;
using Portfolio.Backend.Csharp.Services;
using Portfolio.Backend.Csharp.Repositories;
using AutoMapper;
using Portfolio.Backend.Csharp.Mapper;

var builder = WebApplication.CreateBuilder(args);

// Add services to the container.

builder.Services.AddControllers();
// Learn more about configuring Swagger/OpenAPI at https://aka.ms/aspnetcore/swashbuckle
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();

builder.Services.AddScoped<IUserRepository, UserRepository>();
builder.Services.AddScoped<ISequenceGenerator, SequenceGenerator>();
builder.Services.AddScoped<IUserService, UserService>();
builder.Services.AddScoped<IAuthenticationRepository, AuthenticationRepository>();
builder.Services.AddScoped<IAuthenticationService, AuthenticationService>();
builder.Services.AddAutoMapper(typeof(Program).Assembly);

builder.Services.AddDbContext<PortfolioDbContext>(
    options => options.UseSqlServer(
        builder.Configuration.GetConnectionString("PortfolioApiConnectionString")));

var app = builder.Build();

// Configure the HTTP request pipeline.
if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI();
}

app.UseHttpsRedirection();

app.UseAuthorization();

app.MapControllers();

app.Run();
