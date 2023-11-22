package fwb.auditech1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;


import fwb.auditech1.model.AudioPalavraPair;
import fwb.auditech1.service.TextToSpeechService;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/tratamento")
public class TextToSpeechController {

    @Autowired
    private TextToSpeechService textToSpeechService;

    @GetMapping("/audio")
    public ResponseEntity<List<AudioPalavraPair>> speakText() {
        List<AudioPalavraPair> audioPalavraPairs = textToSpeechService.convertTextToSpeech();

        if (audioPalavraPairs != null && !audioPalavraPairs.isEmpty()) {
            return ResponseEntity.ok().body(audioPalavraPairs);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    //@PostMapping("/feedback")
    //@ResponseStatus(HttpStatus.CREATED)
    //public void receiveFeedback(@RequestBody FeedbackRequest feedbackRequest) {
}

