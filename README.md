**RXExtended**
-------------
Simple project to add some functionalities to the great RxJava library. Project is at its beginning and only includes the following for the moment:

- CompareOperator : RxOperator based on the ```scan``` operator. Unlike ```scan``` which gives 
 the operator previously emitted to the next operation, this compare operator is seeded by the previous **received**> item.
```
 --1-----2-----3--------------4----|----

		compare : (x,y) -> "y:x"

 ------"2:1"--"3:2"--------"4:3"---|---
