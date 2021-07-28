SELECT RPAD(TRIM(CONCAT(cli.title, ' ', cli.name, ' ', cli.surname)), 50, ' ')  AS "Client" , 
       RPAD(TO_CHAR(NVL(loan.balance, 0), 'FM999999999999999999.00'), 30, ' ')  AS "Loan Balance", 
       RPAD(TO_CHAR(NVL(trans.balance, 0), 'FM999999999999999999.00'), 30, ' ') AS "Transactional Balance",
       RPAD(TO_CHAR(NVL(net.balance, 0), 'FM999999999999999999.00'), 30, ' ')   AS "Net Position"
FROM 
	client cli
LEFT OUTER JOIN
                (SELECT cla.client_id, 
                        SUM(cla.display_balance) balance
                   FROM client_account cla
                  WHERE cla.account_type_code in ('PLOAN', 'HLOAN')
                  GROUP BY cla.client_id
                 ) loan
ON
        cli.client_id = loan.client_id
LEFT OUTER JOIN
                (SELECT cla.client_id, 
                        SUM(cla.display_balance) balance
                   FROM client_account cla,
                        account_type act
                  WHERE cla.account_type_code = act.account_type_code
                    AND act.transactional     = TRUE
                  GROUP BY cla.client_id
                 ) trans
ON
	cli.client_id  = trans.client_id
LEFT OUTER JOIN
                (SELECT cla.client_id, 
			SUM(CASE 
				WHEN conversion_indicator = '*' THEN (cla.display_balance * rate)
				WHEN conversion_indicator = '/' THEN (cla.display_balance / rate)
				ELSE cla.display_balance 
			     END) AS balance
		   FROM client_account cla,
                        currency_conversion_rate  ccr
                  WHERE ccr.currency_code = cla.currency_code
                  GROUP BY cla.client_id) net
ON
	cli.client_id = net.client_id;

