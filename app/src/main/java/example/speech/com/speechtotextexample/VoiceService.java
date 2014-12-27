package example.speech.com.speechtotextexample;

import android.content.Intent;
import android.speech.RecognitionListener;
import android.speech.RecognitionService;
import android.speech.SpeechRecognizer;
import android.util.Log;

/**
 * Created by Jigar on 2014-12-26.
 */
public class VoiceService extends RecognitionService {

    private SpeechRecognizer _SpeechRecognizer;

    @Override
    public void onCreate(){
        super.onCreate();
        Log.i("SpeechRecognizer", "Speech Recognizer service has started");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.i("SpeechRecognizer","Speech Recognizer service stopped");
    }

    @Override
    public void onCancel(Callback listener){
      _SpeechRecognizer.cancel();
    }

    @Override
    protected void onStartListening(Intent recognizerIntent, Callback listener){
        _SpeechRecognizer.setRecognitionListener(new VoiceServiceListener(listener));
        _SpeechRecognizer.startListening(recognizerIntent);
    }

    @Override
    protected void onStopListening(Callback listener){
       Log.i("SpeechRecognizer","Speech Recognizer service stopped listening");
    }


    private class VoiceServiceListener implements RecognitionListener{
        private Callback _userListener;

        public VoiceServiceListener(Callback _userListener){
            this._userListener = _userListener;
        }

        public void onBeginningOfSpeech(){
            try{
                _userListener.beginningOfSpeech();
            }catch(Exception e){
                e.printStackTrace();
            }
        }

        public void onBufferReceived(byte[] buffer){
            try{
                _userListener.bufferReceived(buffer);
            }catch(Exception e){
                e.printStackTrace();
            }
        }

        public void onEndOfSpeech(){
            try{
                _userListener.endOfSpeech();
            }
        }
    }
}
