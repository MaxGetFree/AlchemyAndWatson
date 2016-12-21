package org.franch.controller;


import com.ibm.watson.developer_cloud.text_to_speech.v1.TextToSpeech;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.Voice;
import org.franch.logick.MainAlchemy;
import org.franch.model.ClassifyDiscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Controller
public class MainController {


    @Autowired
    private MainAlchemy alchemy;

    ClassifyDiscription textForSpeech;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView model = new ModelAndView();
        model.setViewName("index");
        return model;
    }

    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public ModelAndView send(@RequestParam(required = true) String url) throws IOException {
        ModelAndView model = new ModelAndView();
        model.addObject("picture_url", url);
        model.addObject("resp", alchemy.proccess(url));
        model.setViewName("index");
        return model;
    }

    @RequestMapping(value = "/classify", method = RequestMethod.POST)
    public ModelAndView classify(@RequestParam(required = true) String url) throws IOException {
        ModelAndView model = new ModelAndView();
        model.addObject("picture_url", url);
        model.addObject("classification", alchemy.classify(url));
        model.setViewName("index");
        textForSpeech = alchemy.classify(url);
        return model;
    }

    @RequestMapping(value = "/speechText", method = { RequestMethod.GET }, produces = { org.springframework.http.MediaType.APPLICATION_OCTET_STREAM_VALUE })
    public HttpEntity<byte[]> speechText(@RequestParam(value = "voice") String voice) {
        InputStream in = null;
        OutputStream out = null;
        TextToSpeech TTSservice = new TextToSpeech("738fb2d8-b8a5-409b-b7a6-608d31969517", "IhVeQWckdIIA");
        Voice v1 = null;
        switch(voice)
        {
            case "Lisa": v1=Voice.EN_LISA;
                break;
            case "Michael":v1=Voice.EN_MICHAEL;
                break;
            case "Allison":v1=Voice.EN_ALLISON;
                break;
        }
        try {
            in = TTSservice.synthesize(textForSpeech.toString(), v1).execute();
            byte[] bytes = toBytes(in);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(new org.springframework.http.MediaType("audio", "ogg"));
            headers.setContentLength(bytes.length);
            return new HttpEntity<byte[]>(bytes, headers);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] toBytes(InputStream is) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[16384];
        while ((nRead = is.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }
        buffer.flush();
        buffer.close();
        return buffer.toByteArray();
    }



}
