namespace Portfolio.Backend.Csharp.Data
{
    public class JwtSettings
    {
        public JwtSettings()
        {
            SecretKey = "TestSecretKey";
            ExpirationMinutes = 100;
        }

        public string SecretKey { get; set; }
        public string Issuer { get; set; }
        public string Audience { get; set; }
        public int ExpirationMinutes { get; set; }
    }


}
