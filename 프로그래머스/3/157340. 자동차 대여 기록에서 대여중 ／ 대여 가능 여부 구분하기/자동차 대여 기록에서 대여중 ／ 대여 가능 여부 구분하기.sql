-- 10월 16일 이후에 빌린 사람도 있을 것
-- start, end 사이에 10월 16일이 있으면 된다
-- 일단 car_id 별로 그룹핑
-- start date 중 10월 16일보다 작으면서 end-date이 10월 16일과 같거나 큰 곳이 있다면 대여중, 아니라면 대여 가능

SELECT CAR_ID, 
    MAX(CASE WHEN TO_DATE('2022-10-16', 'YYYY-MM-DD') BETWEEN START_DATE AND END_DATE
        THEN '대여중'
        ELSE '대여 가능'
    END) AS AVAILABILITY
FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
GROUP BY CAR_ID
ORDER BY CAR_ID DESC;