# XML to JSON Processor Function
##  Java 11 and Spring 2.7
#### This project is  done in AWS Lambda

### Read XML input and do the Following Transformations:

XML - "TIPO OPERACAO": P = PAGAMENTO | R = REEMBOLSO | L = LIQUIDAR <br>
XML - "FORMA DE PAGAMENTO": B = BOLETO | D = DEBITO EM CONTA | C = CARTAO | P = PIX <br>

JSON - "operationType": P = Payment | R = Reimbursement | S = Settle <br>
JSON - "paymentType": T = Ticket | D = Debit | C = Credit Card | P = PIX <br>

### Design Options:
#### Option 1: Dead Letter Queue (For Input Messages that got Errors)
#### Option 2: Gracefully Accept the XML without some components and map to NULL

### After the Transformations the RESPONSE is JSON 

### Reference Documentation
Sample for XML Input [inputExample.xml](src/test/resources/inputExample.xml) <br>
Sample for JSON Output [outputExample.json](src/test/resources/outputExample.json) <br>