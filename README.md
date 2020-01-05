# watcard-java

## Usage
To begin, create an instance of WatCardClient:

```
WatCardClient client = new WatCardClient.Builder().account("account").pin("pin").build();
```

Within `WatCardClient`, various methods including _WatCardClient#hasValidCredentials_, _WatCardClient#getBalances_ and 
_WatCardClient#getPersonalInfo_ can be used to retrieve information about the client. These methods return `Single` 
which can be subscribed to by calling `Single#subscribeWith` and passing an appropriate `Observer`.

New sessions are automatically retrieved by the library when the last one expires.

## License
This project is licensed under the Apache License. See the `LICENSE` file for more details.