import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Test;

public class Test1 {

    @Test
    public void c(){
        // 验证token
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwibmFtZSI6IlRPTSIsImFnZSI6MTh9.Y0ggmgIyuHMVFFHwsN2wp4xaFmue2FFNhhVYIwRq514";

        // ip
        String ip = "127.0.0.4";

        // 服务器密钥
        String serviceKey = "atguigu12345";

        // 解析
        Jwt parse = Jwts.parser().setSigningKey(MD5.encrypt(serviceKey + ip)).parse(token);
        System.out.println(parse);

    }

    @Test
    public void b(){
        // 验证token
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwibmFtZSI6IlRPTSIsImFnZSI6MTh9.Z1oItKgr3K1s5TH6PIJaZIl4_d1evH5er-flp4YQGhw";
        
        // ip
        String ip = "127.0.0.2";

        // 服务器密钥
        String serviceKey = "atguigu123";

        // 解析
        Jwt parse = Jwts.parser().setSigningKey(MD5.encrypt(serviceKey + ip)).parse(token);
        System.out.println(parse);

    }


    @Test
    public void a(){

        // 服务器密钥
        String serviceKey = "atguigu12345";

        // 自包含信息
        User user = new User();
        user.setName("TOM");
        user.setAge(18);
        user.setId(1L);

        // 其他盐(值)
        String ip = "127.0.0.1";

        // 生成token
        String token = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")

                .claim("id", user.getId())
                .claim("name", user.getName())
                .claim("age", user.getAge())

                .signWith(SignatureAlgorithm.HS256, MD5.encrypt(serviceKey + ip))
                .compact();

        System.out.println(token);

    }
}
