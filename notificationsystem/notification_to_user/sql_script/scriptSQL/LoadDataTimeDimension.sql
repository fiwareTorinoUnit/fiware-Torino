SELECT DISTINCT month(date) as month,  year(date) as year, ((month(date) - 1) / 3)+1 AS quarter,((month(date) - 1) / 4)+1 AS fourMonth_Period 
FROM rental;