**RXExtended**
-------------
Simple project to add some functionalities to the great RxJava library. Project is at its beginning and only includes the following for the moment:

- CompareOperator : RxOperator based on the ```scan``` operator but only gets the previously emitted items on each new emitted items (comparatively ```scan``` gets the previous emitted item after process)
```
 --1-----2-----3--------------4----|----

		compare : (x,y) -> "y:x"

 ------"2:1"--"3:2"--------"4:3"---|---
