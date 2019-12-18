# watcard-java

## Usage
To begin, create an instance of WatCard API. You will need a separate
instance for each individual WatCard as session cookies are stored.

The session expires fairly quickly, so WatCardService#authenticate will need to be called fairly
often in order for the other methods to work.

```
WatCardAPI api = new WatCardAPI();
WatCardService service = api.createService();
```