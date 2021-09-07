# ezypay-interview
Java technical assessment for ezypay

## Run
Can be accessed at localhost:8080

## Using the API

### Request
- METHOD: POST
- URL: localhost:8080/subscribe
- JSON :
```json
  amount: double,
  type: String ("DAILY/WEEKLY/MONTHLY")
  billable: String (if WEEKLY day of week i.e TUESDAY, if MONTHLY  then date, i.e 24)
  startDate: String (dd/MM/yyyy)
  endDate: String (dd/MM/yyyy)
```  
### Response
```json
  amount: double,
  type: String ("DAILY/WEEKLY/MONTHLY"),
  invoiceDates: Array of dates
```
## Example

### Request
```json
{
    "amount": 150.50,
    "type": "MONTHLY",
    "billable": "10",
    "startDate": "06/09/2021",
    "endDate": "06/12/2021"
}
```
 ### Response
 ```json
 {
    "amount": 150.5,
    "type": "MONTHLY",
    "invoiceDates": [
        "10/09/2021",
        "10/10/2021",
        "10/11/2021"
    ]
}
```
