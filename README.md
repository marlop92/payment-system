Money transfer application using Java 8.

To start: just run Main.main() method.

Endpoints:

Customers' bank accounts: http://localhost:8080/api/accounts/internal
External bank accounts: http://localhost:8080/api/accounts/external
Money transfers: http://localhost:8080/api/transfers

To perform money transfer:
POST http://localhost:8080/api/transfers/transfer
Working json:
{
    "cid": 1,
    "receiverAccount": "2200000077777777",
    "title": "title",
    "amount": 600,
    "currency": "PLN"
}

Implementation decisions:
-I added some initial data to DB
-I would imagine that in real life scenario external accounts data could be taken from some third party
service but for simplicity I just create separated entity for them; to update external account balance
to which I am not supposed to have access I just call empty method which represents calling third party
-transfer can only pass when using proper currency combined with existing, valid account number
(which also handle the same currency as transfer).