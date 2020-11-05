# SpigotCaseStudy
Break down:
1. Write a function to get all interesting device information.(Get a UUID would be a trick one)
2. In an EditText, we can input the url in it. The input url could be an encoded URL. (copy and paste into it). Do a data validation and Decode the URL.
3. For the decoded url, it might have key value pairs in its parameters. Extract the those pairs into two parts:URL Base and Pairs string.
4. Then combine Pairs String with device information.
5. Store combined data into a database.
6. The dao model should have three columns:Generated primary key, URL BASE and All the pairs.
7. Display those data per URL as a RecyclerView item. (subscribe on the room db)(Give at two different UI for the item)
8. For the RecyclerView item, put a button in it. click button to post the whole dao model as raw json to the server.
9. Using retrofit to do the post request.
10. Put a hidden view (Or Fragment) in the item layout, and then set it visible to display the response from the post.
