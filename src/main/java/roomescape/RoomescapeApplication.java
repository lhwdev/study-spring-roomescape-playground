package roomescape;

import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import roomescape.global.DatabaseInitialization;

import java.sql.SQLException;

@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
@SpringBootApplication
public class RoomescapeApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(RoomescapeApplication.class, args);
    }

    
    @Autowired
    private DatabaseInitialization databaseInitialization;
    
    @Override
    public void run(String... args) {
        databaseInitialization.initializeTables();
    }
    
    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server h2TcpServer() throws SQLException {
        return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "8082");
    }
}
