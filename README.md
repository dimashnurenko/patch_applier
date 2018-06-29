Small library which uses Java reflection to merge 'source' and 'target' objects. Can be used in cases when 
object partially update is needed. 

Real use case: REST API PATCH method 

Benefits:
 1. Easy to use
 2. Don't have third party dependency
 3. Very small
 
 How to use:
 
 See
 
 com.tarde.merger.DataProvider - target object data structure
 
 com.tarde.merger.ObjectMergerTest - use cases
 
 Code example:
 
 ObjectMerger.mergerOf(source, target).merge();
