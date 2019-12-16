package krystiankatafoni.retailrecommendationsystem;

import org.neo4j.driver.v1.*;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Map;


@Component
public class AppStartup implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {

        try (Driver driver = GraphDatabase.driver("bolt://localhost:7687",
                AuthTokens.basic("rec", "rec"));
                Session session = driver.session()) {

            StatementResult result = session.run("CREATE (a:Greeting) " +
                    "SET a.message = 'message' " +
                    "RETURN a.message + ', from node ' + id(a)");

            while(result.hasNext()) {
                String rows = "";
                Record record = result.next();
                Map<String,Object> row = record.asMap();
                for ( Map.Entry<String,Object> column : row.entrySet() )
                {
                    rows += column.getKey() + ": " + column.getValue() + "; ";
                }
                rows += "\n";
                System.out.println(rows);

            }
        }
    }
}
