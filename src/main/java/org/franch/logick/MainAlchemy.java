package org.franch.logick;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ibm.watson.developer_cloud.natural_language_classifier.v1.model.Classification;
import com.ibm.watson.developer_cloud.text_to_speech.v1.TextToSpeech;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.AudioFormat;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.CustomVoiceModel;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.Voice;
import com.ibm.watson.developer_cloud.text_to_speech.v1.util.WaveUtils;
import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifyImagesOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassification;
import okhttp3.*;
import okhttp3.MediaType;
import org.franch.model.ClassifyDiscription;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.franch.model.Resp;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.*;


@Component
public class MainAlchemy {

    public static final MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");

    public Resp proccess(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        HttpUrl.Builder urlBuilder = HttpUrl.parse("http://gateway-a.watsonplatform.net/calls/url/URLGetRankedImageFaceTags").newBuilder();
        urlBuilder.addQueryParameter("apikey", "18c8e929a58bdb62feec88457972cfde34774ceb");
        urlBuilder.addQueryParameter("url", url);
        urlBuilder.addQueryParameter("outputMode", "json");
        String uri = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(uri)
                .build();

        Response response = client.newCall(request).execute();
        ObjectMapper mapper = new ObjectMapper();
        String jsonBody = response.body().string();
        Resp resp = mapper.readValue(jsonBody, Resp.class);

        return resp;
    }

    public ClassifyDiscription classify(String url) throws IOException
    {

        VisualRecognition service = new VisualRecognition(VisualRecognition.VERSION_DATE_2016_05_20);
        service.setApiKey("facad32e6ecc8e9ca608fb004e4ccb686e560229");

        System.out.println("Classify an image");
        ClassifyImagesOptions options = new ClassifyImagesOptions.Builder().url(url).build();
        VisualClassification classification = service.classify(options).execute();

        JsonElement jsonParser = new JsonParser().parse(String.valueOf(classification));
        JsonObject jsonObject = jsonParser.getAsJsonObject();
        jsonObject = jsonObject.getAsJsonArray("images").get(0).getAsJsonObject();
        jsonObject = jsonObject.getAsJsonArray("classifiers").get(0).getAsJsonObject();
        System.out.println(String.valueOf(classification));


        ObjectMapper mapper = new ObjectMapper();
        String jsonBody = String.valueOf(classification);
        ClassifyDiscription classification1 = mapper.readValue(jsonBody, ClassifyDiscription.class);


        return classification1;

    }



}
