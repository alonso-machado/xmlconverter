# XML to JSON Processor
##  Java 11 and Spring 2.7
#### This project is  done in AWS Lambda or Azure Function

### Read XML File and do the Following Transformations:

XML - "TIPO OPERACAO": P = PAGAMENTO | R = REEMBOLSO | L = LIQUIDAR <br>
XML - "FORMA DE PAGAMENTO": B = BOLETO | D = DEBITO EM CONTA | C = CARTAO | P = PIX <br>

JSON - "operationType": P = Payment | R = Reimbursement | S = Settle <br>
JSON - "paymentType": T = Ticket | D = Debit | C = Credit Card | P = PIX <br>

### After the Transformations WRITE the JSON into File

### Reference Documentation
Sample for XML Input [inputExample.xml](src/test/resources/inputExample.xml) <br>
Sample for JSON Output [outputExample.json](src/test/resources/outputExample.json) <br>