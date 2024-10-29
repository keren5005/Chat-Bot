//package com.handson.chatbot.controller;
//
//import com.handson.chatbot.service.ImdbService;
//import com.handson.chatbot.service.chucknoriesJokesService;
//import com.handson.chatbot.service.chucknoriesJokesService.JokeObject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.io.IOException;
//import java.util.List;
//
//@RestController
//@RequestMapping("/bot")
//public class BotController {
//
//    @Autowired
//    private ImdbService imdbService;
//
//    @Autowired
//    private chucknoriesJokesService jokesService;
//
//    @GetMapping("/imdb")
//    public ResponseEntity<?> getImdbData(@RequestParam String keyword) {
//        try {
//            String html = imdbService.getProductHtml(keyword);
//            String parsedData = imdbService.parseProductHtml(html);
//            return new ResponseEntity<>(parsedData, HttpStatus.OK);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return new ResponseEntity<>("Error fetching data from IMDb", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @GetMapping("/jokes")
//    public ResponseEntity<?> getJokes(@RequestParam String keyword) {
//        try {
//            List<JokeObject> jokes = jokesService.getJokes(keyword);
//            return new ResponseEntity<>(jokes, HttpStatus.OK);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return new ResponseEntity<>("Error fetching jokes from Chuck Norris API", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//}

//package com.handson.chatbot.controller;
//
//import com.handson.chatbot.service.ImdbService;
//import com.handson.chatbot.service.chucknoriesJokesService;
//import com.handson.chatbot.service.chucknoriesJokesService.JokeObject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.io.IOException;
//import java.util.List;
//
//@RestController
//@RequestMapping("/bot")
//public class BotController {
//
//    @Autowired
//    private ImdbService imdbService;
//
//    @Autowired
//    private chucknoriesJokesService jokesService;
//
//    @GetMapping("/imdb")
//    public ResponseEntity<?> getImdbData(@RequestParam String keyword) {
//        try {
//            String html = imdbService.getProductHtml(keyword);
//            String parsedData = imdbService.parseProductHtml(html);
//            return new ResponseEntity<>(parsedData, HttpStatus.OK);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return new ResponseEntity<>("Error fetching data from IMDb", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @GetMapping("/jokes")
//    public ResponseEntity<?> getJokes(@RequestParam String keyword) {
//        try {
//            List<JokeObject> jokes = jokesService.getJokes(keyword);
//            return new ResponseEntity<>(jokes, HttpStatus.OK);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return new ResponseEntity<>("Error fetching jokes from Chuck Norris API", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//}

package com.handson.chatbot.controller;

import com.handson.chatbot.service.ImdbService;
import com.handson.chatbot.service.chucknoriesJokesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;

@RestController
@RequestMapping("/bot")
public class BotController {

    @Autowired
    private chucknoriesJokesService jokesService;

    @PostMapping
    public ResponseEntity<?> getBotResponse(@RequestBody BotQuery query) {
        String responseText = "I'm sorry, I couldn't find any information.";

        // Check if the intent is "getJoke" and respond with a joke
        if (query.getQueryResult().getIntent().getDisplayName().equals("getJoke")) {
            responseText = jokesService.getJoke("random"); // Fetches a random joke
        }

        return new ResponseEntity<>(BotResponse.of(responseText), HttpStatus.OK);
    }

    // Inner classes for JSON parsing
    public static class BotQuery {
        private QueryResult queryResult;

        public QueryResult getQueryResult() {
            return queryResult;
        }
    }

    public static class QueryResult {
        private Intent intent;

        public Intent getIntent() {
            return intent;
        }
    }

    public static class Intent {
        private String displayName;

        public String getDisplayName() {
            return displayName;
        }
    }

    public static class BotResponse {
        private String fulfillmentText;
        private String source = "BOT";

        public String getFulfillmentText() {
            return fulfillmentText;
        }

        public String getSource() {
            return source;
        }

        public static BotResponse of(String fulfillmentText) {
            BotResponse res = new BotResponse();
            res.fulfillmentText = fulfillmentText;
            return res;
        }
    }
}
