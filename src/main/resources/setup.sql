INSERT INTO `Role` (`roleID`, `name`) VALUES ('1', 'ADMIN');

INSERT INTO `User` (`userID`, `active`, `email`, `firstname`, `note`, `password`, `surname`, `username`) VALUES ('1', '1', 'admin@admin.com', 'admin', NULL, 'admin', 'admin', 'admin');

INSERT INTO `User_Role` (`User_userID`, `roles_roleID`) VALUES ('1', '1');

INSERT INTO `Role` (`roleID`, `name`) VALUES ('2', 'USER');
