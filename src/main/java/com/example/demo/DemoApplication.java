package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


	@RestController
	static class Controller {

		/**
		 *
		 * Here the using path value and PathVariable param you can get the value
		 */
		@GetMapping(path = "/{age}", produces = MediaType.APPLICATION_JSON_VALUE)
		public Object getExample1(@PathVariable String age) { //age variable name should be same
			return Map.of("name", "abhishek", "age", String.valueOf(age));
		}


		/**
		 *
		 * Here optional PathVariables can be passed but make sure @PathVariable should have required as false.
		 */
		@GetMapping({"/{count}", "/"})
		public Object getExample2(@PathVariable(required = false) Integer count) {
			return Optional.ofNullable(count).orElse(Integer.MIN_VALUE);
		}

		/**
		 *
		 * @RequestParams extract values from the query string, @PathVariables extract values from the URI path,
         * As @PathVariable is extracting values from the path, it's not encoded. On the other hand @RequestParam is
         * Example
		 * Using PathVariable
		 * http://localhost:8080/foos/ab+c
		 * ----
		 * ID: ab+c
		 *
		 * Using RequestParam
		 * http://localhost:8080/foos?id=ab+c
		 * ----
		 * ID: ab c
		 */
		@GetMapping(path = "/test")
		public Object getExample3(@RequestParam String id) {
			return id;
		}



		/**
		 * Can also make RequestParam as optional value
		 */
		@GetMapping(path = "/test2")
		public Object getExample4(@RequestParam(required = false) String id, @RequestParam String id1) {
			return id + id1;
		}

	}

}
