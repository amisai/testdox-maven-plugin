Testdox-maven-plugin
====================

This maven plugin creates a report with a list of objects and their behaviours extracted from test cases, based on [agiledox](http://agiledox.sourceforge.net/). 

So, if you have a test case such as:

```java
public class CustomerLookupTest extends TestCase {
    testFindsCustomerById() {
        ...
    }
    testFailsForDuplicateCustomers() {
        ...
    }
    shouldFindCustomersByInterests() {
        ...
    }
    ...
}
```

something like this will be shown in report:

```
CustomerLookup
- finds customer by id
- fails for duplicate customers
- should find customers by interests
- ...  
```

Currently, this plugin identifies 'test' and 'should prefixes, removing 'test' prefix but leaving 'should' prefix in the report.

