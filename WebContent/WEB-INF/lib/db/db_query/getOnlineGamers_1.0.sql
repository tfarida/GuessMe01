SELECT L.id, L.sessionId, L.loginAt, L.logoutAt, L.activeStatus
, L.userId, U.gamerName, U.emailAddress, U.password, U.fullName, U.gender, U.createdAt  
FROM tb_user_login L 
left join tb_user U on L.userId = U.id
where L.activeStatus = 1
group by L.userId