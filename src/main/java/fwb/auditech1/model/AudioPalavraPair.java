package fwb.auditech1.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AudioPalavraPair {
    private byte[] audio;
    private String palavra;

    public AudioPalavraPair(byte[] audio, String palavra){
        this.audio = audio;
        this.palavra = palavra;
    }
    public byte[] getAudio(){
        return audio;
    }
    public String getPalavra(){
        return palavra;
    }

}
