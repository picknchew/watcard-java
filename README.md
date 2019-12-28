# watcard-java

## Usage
To begin, create an instance of WatCardClient:

```
WatCardClient client = new WatCardClient.Builder().account("account").pin("pin").build();
```

Within WatCardClient, WatCardClient#hasValidCredentials, WatCardClient#getBalances and WatCardClient#getPersonalInfo
can be used to retrieve information about the user.

New sessions are automatically retrieved by the library when the last one expires.