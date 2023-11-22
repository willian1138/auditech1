package fwb.auditech1.service;

import com.google.cloud.texttospeech.*;
import com.google.cloud.texttospeech.v1.AudioConfig;
import com.google.cloud.texttospeech.v1.AudioEncoding;
import com.google.cloud.texttospeech.v1.SsmlVoiceGender;
import com.google.cloud.texttospeech.v1.SynthesisInput;
import com.google.cloud.texttospeech.v1.SynthesizeSpeechResponse;
import com.google.cloud.texttospeech.v1.TextToSpeechClient;
import com.google.cloud.texttospeech.v1.VoiceSelectionParams;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import fwb.auditech1.model.AudioPalavraPair;
import fwb.auditech1.model.Palavras;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class TextToSpeechService {

    private final Random random;
    private final Palavras palavras;

    @Value("${google.api.key}")
    private String googleApiKey;

    public TextToSpeechService(Random random, Palavras palavras) {
        this.random = random;
        this.palavras = palavras;
    }

    public List<AudioPalavraPair> convertTextToSpeech() {
        int listaAleatoria = random.nextInt(4);

        try (TextToSpeechClient textToSpeechClient = TextToSpeechClient.create()) {
            List<String> palavrasList;
            switch (listaAleatoria) {
                case 0:
                    palavrasList = palavras.getPrimeiroNivel();
                    break;
                case 1:
                    palavrasList = palavras.getSegundoNivel();
                    break;
                case 2:
                    palavrasList = palavras.getTerceiroNivel();
                    break;
                case 3:
                    palavrasList = palavras.getQuartoNivel();
                    break;
                default:
                    palavrasList = palavras.getPrimeiroNivel();
                    break;
            }

            List<AudioPalavraPair> audioPalavraPairs = new ArrayList<>();

            for (String palavra : palavrasList) {
                SynthesisInput input = SynthesisInput.newBuilder().setText(palavra).build();
                VoiceSelectionParams voice = VoiceSelectionParams.newBuilder()
                        .setLanguageCode("en-US")
                        .setSsmlGender(SsmlVoiceGender.NEUTRAL)
                        .build();
                AudioConfig audioConfig = AudioConfig.newBuilder().setAudioEncoding(AudioEncoding.LINEAR16).build();

                SynthesizeSpeechResponse response = textToSpeechClient.synthesizeSpeech(input, voice, audioConfig);

                byte[] audioData = response.getAudioContent().toByteArray();
                AudioPalavraPair pair = new AudioPalavraPair(audioData, palavra);
                audioPalavraPairs.add(pair);
            }

            return audioPalavraPairs;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
