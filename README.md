# Microservice-Communication

Two Types :

      1. Synchronous HTTP communication.
            a. RestTemplate   ( Deprecated from Spring 5.0 )
            
      2. Asynchronous communication over AMQP.
            b.  WebClient     ( Spring Webflux / Mano )
       
RestTemplate Blocking Client :

		- Servlet Stack
		- Thread-per-request model [ It means thread will block until the web client recives the reponse ].
		- Consequently,the application will create many threads,which will exhaust the thread pool or occupy all the available memory.
    
    Code Snippet for RestTemplate :
    
              RestTemplate restTemplate = new RestTemplate();
    
              Movie movie = restTemplate.getForObject("http://localhost:8082/movies/"+rating.getMovieId(),Movie.class);


WebClient Non-Blocking Client :

		- Reactive Stack
		- Webclient uses an asynchronous , non-blocking solution provided by the spring reactive framework.

    Code Snippet for WebClient :

              WebClient.Builder webClientBuilder = WebClient.builder();

              Movie movie = webClientBuilder.build()
							              .get()
							              .uri("http://localhost:8082/movies/"+rating.getMovieId())
							              .retrieve()
							              .bodyToMono(Movie.class)
							              .block();
    
