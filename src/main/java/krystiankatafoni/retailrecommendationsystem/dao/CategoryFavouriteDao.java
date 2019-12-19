package krystiankatafoni.retailrecommendationsystem.dao;


import krystiankatafoni.retailrecommendationsystem.dao.generic.FavouriteDao;
import krystiankatafoni.retailrecommendationsystem.domain.CategoryWithAmount;
import krystiankatafoni.retailrecommendationsystem.domain.nodes.Category;
import org.neo4j.driver.v1.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
@Qualifier("categoryFavourite")
public class CategoryFavouriteDao implements FavouriteDao<CategoryWithAmount, Integer> {

    private final String GET_FAVOURITES = "MATCH (u:User {id: $id})-[pu:purchased]->(p:Product)-" +
            "[:BELONG_TO]->(c:Category) WITH c as category, " +
            "count(pu) as amount return category,amount order by amount desc limit 1";


    @Override
    public CategoryWithAmount getFavourite(Integer id) {
        CategoryWithAmount categoryWithAmount = new CategoryWithAmount();
        Map<String, Object> params = new HashMap<>();
        params.put("id", id.toString());
        try (Driver driver = GraphDatabase.driver(DatabaseConnection.URI,
                AuthTokens.basic(DatabaseConnection.USERNAME, DatabaseConnection.PASSWORD));
                Session session = driver.session()) {
            StatementResult result = session.run(GET_FAVOURITES, params);

            while (result.hasNext()) {
                Record record = result.next();
                categoryWithAmount.setCategory(new Category(record.get("category").asNode().get("id").asString(),
                        record.get("category").asNode().get("name").asString()));
                categoryWithAmount.setAmount(record.get("amount").asInt());

            }
        }
        return categoryWithAmount;
    }
}
