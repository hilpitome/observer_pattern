### Basic setup
The application was written in python and therefore for the code to run you have to ensure that:
* You have installed and setup mongoDB
* Installed the pymongo driver
* Ensured that the mongoDB service is running in the backgraound
### Importing the database
The data was imported into the mongoDB database each time the runMongo.py application was ran even though it could be commented out after the first run.
The function populateMongo takes in the "json" directory as a parameter and reads every json file as a document in the googleTagged collection of the database. It also performs the first aggregation query which is query 0.

### Code and results of the assignment

## Query 1
**Task** Count the total number of JSON documents in the database
* Here I used single-purpose aggregation operation db.collection.count() to get the answer as 100.
## Query 2
**Task** Count the number of unique Labels, Landmarks, Locations,Pages, and WebEntities in the database.
* I also used a single-purpose aggregation operation db.collection.distinct() for all the items stated above. The result was then presented with a simple string conctatenation.
## Query 3
**Task** Count the total number of unique images in the database.This should include both those that have been directly submitted
to the google cloud vision API as well as those that are referred to in the returned analyses.
* The query was created using the map reduce function. The mapper(map) function attempts to iterate through the documents using the url key and performing a single emit function.
* The reducer function uses the array created by the mapper function to create a single value.
* The end result of this function on the collection was to be created using collection.map_reduce(mapper, reducer, "meResults")
* The code did not run because of the error **pymongo.errors.OperationFailure: ReferenceError: collection is not defined**. For this reason the collection.map_reduce(mapper, reducer, "meResults") was commented out to allow other aggregation functions to work.
## Query 4
**Task** List the 10 most frequent WebEntities that are applied to the same Images as the Label with an id of "/m/015kr" (which
has the description "bridge"). List them in descending order of the number of times they appear together, followed by their entityId
alphabetically
* piping of various aggregation functions was used for the first time here. The goes through the following steps:
1. Unwinding the response.webDetection.webEntities arrays.
2. filtering url, entityId and score of the document to be passed to the next stage using $project.
3. filtering all the documents with an entity Id of "/m/015kr"
## Query 5
**Task** Find Images associated with Landmarks that are not "New York" (id "/m/059rby") or "New York City" (id "/m/02nd_") with an association score of at least 0.6 ordered alphabetically by landmark description and then by image URL.
1. unwinding the response.landmarkAnnotations to get the landmarks array
2. filtering out mid, score and url using $project
3. matching the documents using $and, $ne (not equal to "New York" (id "/m/059rby") or "New York City" (id "/m/02nd_") and $gte(greator than 0.6) conditions. 
## Query 6
**Task** List the 10 Labels that have been applied to the most Images along with the number of Images each has been applied to sorted by the number of Images each has been applied to from most to least.
1. unwinding $response.labelAnnotations
2. grouping the documents using "$response.labelAnnotations.description" and counting the number of images for each
3. sorting using $sort on the "num_images" in descending order.
4. using $limit to return the first ten rows or documents
## Query 7
**Task** List the 10 Pages that are linked to the most Images through the webEntities.pagesWithMatchingImages JSON property
    along with the number of Images linked to each one. Sort them by count (descending) and then by page URL.
* An attempt was made to come up with the answer to the above requirement as follows
1. unwinding $response.labelAnnotations
2. grouping the documents using "$response.webDetection.pagesWithMatchingImages.url" and counting the number of images for each
3. sorting using $sort on the "num_images" in descending order.
4. using $limit to return the first ten rows or documents
## Query 8
**Task** List the 10 pairs of Images that appear on the most Pages together through the webEntities.pagesWithMatchingImages JSON property. Order them by the number of pages that they appear on together (descending), then by the URL of the first. Make sure that each pair is only listed once regardless of which is first and which is second.
* An attempt was made to come up with the answer to the above requirement as follows
1. unwinding $response.webDetection.pagesWithMatchingImages
2. grouping the documents using "pagesWithMatchingImages" and obtaining the first item in the group using the $first aggregator
3. sorting using $sort on the "num_images" in descending order.
4. using $limit to return the first ten rows or documents
* The code did not run because of the error **pymongo.errors.OperationFailure: The field '$first' must be an accumulator object**. I had a hard time with debugging this problem. It can be seen at the end of the code output when the file is ran.









