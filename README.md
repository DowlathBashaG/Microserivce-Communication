# Microservice-Communication

Two Types :

      1. Synchronous communication.
            a. RestTemplate   ( Deprecated from Spring 5.0 )
            
      2. Asynchronous communication.
            b.  WebClient     ( Spring Webflux / Mano )
       
RestTemplate Blocking Client

		- Servlet Stack
		- Thread-per-request model [ It means thread will block until the web client recives the reponse ].
		- Consequently,the application will create many threads,which will exhaust the thread pool or occupy all the available memory.
    
    Code Snippet for RestTemplate :-
    
              RestTemplate restTemplate = new RestTemplate();
    
              Movie movie = restTemplate.getForObject("http://localhost:8082/movies/"+rating.getMovieId(),Movie.class);

Note:

Instead of locahost , give the name as mentioned in the application.yml/properties file -> spring.application.name.
It is assigned in the Serivce Discovery ( Eureka Server ).

WebClient Non-Blocking Client

		- Reactive Stack
		- Webclient uses an asynchronous , non-blocking solution provided by the spring reactive framework.

    Code Snippet for WebClient :-

              WebClient.Builder webClientBuilder = WebClient.builder();

              Movie movie = webClientBuilder.build()
			    .get()
		            .uri("http://localhost:8082/movies/"+rating.getMovieId())
			    .retrieve()
			    .bodyToMono(Movie.class)
		            .block();
    
Note:

Instead of locahost , give the name as mentioned in the application.yml/properties file -> spring.application.name.
It is assigned in the Serivce Discovery ( Eureka Server ).


External API accessing :

Step 1 : 

	https://www.themoviedb.org/
			
	create the user profile and generate API ( its under your profile and get the API key ). 
			Its very important for accessing external themoviedb API.
			
Step 2 :

	Update API Key info in application property file.
			


Project Folders :

1. discovery-server

	Using Eureka technology	, register eureka clients in the eureka server. Add the following lines in (application.properties) the eureka server :
   
	server.port : 8761
	eureka.client.register-with-eureka = false
	eureka.client.fetch-registry = false
	
	

2. movie-catalog-service

	It receives movie info and ratings of the movies from other two services.
	
	Input :
			http://localhost:9091/catalog/foo
	
	Output :
	
				[
					{
					userId: "Lock, Stock and Two Smoking Barrels",
					desc: null,
					rating: 3
					},
					{
					userId: "Star Trek: Insurrection",
					desc: null,
					rating: 4
					}
				]
	
	I
	
3. movie-info-service

	It's having movie info service and it will accessing external API and deliver the movie info.
	
	Input :
	
			http://localhost:9092/movies/500
			
	
	Output :
	
			{
				movieId: "500",
				movieName: "Reservoir Dogs",
				desc: null
			}
	
	Note:
	
	application.properties 
								Update API Key.
								spring.application.name = movie-info-service
server.port = 9092
api.key=api-key-here


4. ratings-data-service

	It receive movie id and sends the ratings of the movie.
	
	
	Input :
	
			http://localhost:9093/ratingsdata/user/foo
			
	
	Output:
	
			{
				userId: "foo",
				ratings: [
						{
						movieId: "100",
						rating: 3
						},
						{
						movieId: "200",
						rating: 4
						}
				]
            }


		
