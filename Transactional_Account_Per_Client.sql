SELECT RPAD(trans.client_id, 10, ' ')                 AS "Client Id",
       RPAD(trans.surname, 100, ' ')                  AS "Client Surname",
       RPAD(trans.client_account_number, 10, ' ')     AS "Client Account Number",
       RPAD(trans.description, 50, ' ')               AS "Account Description",
       RPAD(trans.display_balance, 22, ' ')           AS "Display Balance"
  FROM 
       (
        SELECT cli.client_id,
               cli.surname,
               cla.client_account_number,
               act.description,
               cla.display_balance,
               RANK() OVER (PARTITION BY cli.client_id ORDER BY cla.display_balance DESC) balance_rank
          FROM client cli,
               client_account cla,
               account_type act
         WHERE cli.client_id         = cla.client_id
           AND act.account_type_code = cla.account_type_code
           AND act.transactional     = TRUE
       ) trans
 WHERE trans.balance_rank = 1
 ORDER BY trans.client_id;
