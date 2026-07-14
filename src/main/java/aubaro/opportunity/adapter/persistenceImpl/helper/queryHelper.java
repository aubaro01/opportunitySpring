package aubaro.opportunity.adapter.persistenceImpl.helper;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component

public class queryHelper {

    private queryHelper(){}


    public void appendFilters(String filterBy, StringBuilder whereClause, MapSqlParameterSource params) {

        final Map<String, String> filterTranslations = Map.ofEntries(
                Map.entry("status", "so.FK_StateId"),
                Map.entry("amount", "so.Amount"),
                Map.entry("clientId", "so.FK_ClientId")
        );

        if (filterBy == null || filterBy.isBlank()) {
            return;
        }

        String[] filters = filterBy.split(",");
        int idx = 0;
        for (String filter : filters) {
            String[] parts = filter.split(":");
            if (parts.length != 3) {
                throw new IllegalArgumentException("Invalid format for filter: " + filter);
            }

            String field = parts[0].trim();
            String operator = parts[1].trim();
            String value = parts[2].trim();

            String column = filterTranslations.get(field);
            if (column == null) {
                throw new IllegalArgumentException("Invalid filter field: " + field);
            }

            String sqlOperator = switch (operator) {
                case "eq" -> "=";
                case "ne" -> "<>";
                case "gt" -> ">";
                case "gte" -> ">=";
                case "lt" -> "<";
                case "lte" -> "<=";
                case "like" -> "LIKE";
                default -> throw new IllegalArgumentException("Operator not allowed: " + operator);
            };

            String paramName = "filter" + idx;
            whereClause.append(" AND ").append(column).append(" ").append(sqlOperator).append(" :").append(paramName);
            params.addValue(paramName, operator.equals("like") ? "%" + value + "%" : value);

            idx++;
        }
    }

    /**
     * Traduz o parâmetro sortBy (formato "campo::direcao,campo::direcao")
     * e devolve a cláusula ORDER BY pronta a usar.
     */
    public String buildOrderByClause(String sortBy) {

        final Map<String, String> sortTranslations = Map.ofEntries(
                Map.entry("startDate", "so.StartDate"),
                Map.entry("endDate", "so.EndDate"),
                Map.entry("amount", "so.Amount"),
                Map.entry("status", "so.FK_StateId")
        );

        if (sortBy == null || sortBy.isBlank()) {
            return " ORDER BY so.StartDate ASC";
        }

        String[] sorts = sortBy.split(",");
        List<String> orderParts = new ArrayList<>();

        for (String sort : sorts) {
            String[] parts = sort.split("::");
            if (parts.length != 2) {
                throw new IllegalArgumentException("Invalid format for sort: " + sort);
            }

            String field = parts[0].trim();
            String direction = parts[1].trim().toLowerCase();

            String column = sortTranslations.get(field);
            if (column == null) {
                throw new IllegalArgumentException("Invalid sort field: " + field);
            }
            if (!direction.equals("asc") && !direction.equals("desc")) {
                throw new IllegalArgumentException("Invalid sort direction: " + direction);
            }

            orderParts.add(column + " " + direction.toUpperCase());
        }

        return " ORDER BY " + String.join(", ", orderParts);
    }
}
