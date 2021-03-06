package com.dicoding.ImplementasiFiturJava;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.client.LineSignatureValidator;
import com.linecorp.bot.client.MessageContentResponse;
import com.linecorp.bot.model.Multicast;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.event.message.*;
import com.linecorp.bot.model.event.source.GroupSource;
import com.linecorp.bot.model.event.source.RoomSource;
import com.linecorp.bot.model.event.source.UserSource;
import com.linecorp.bot.model.message.FlexMessage;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.StickerMessage;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.message.flex.container.FlexContainer;
import com.linecorp.bot.model.objectmapper.ModelObjectMapper;
import com.linecorp.bot.model.profile.UserProfileResponse;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.ExecutionException;

import static java.lang.String.valueOf;



@RestController
public class Controller {


    @Autowired
    @Qualifier("lineMessagingClient")
    private LineMessagingClient lineMessagingClient;


    @Autowired
    @Qualifier("lineSignatureValidator")
    private LineSignatureValidator lineSignatureValidator;
    private String textMsg;
    private String[] Kunci1;
    private String[] Jawaban1;



    @RequestMapping(value="/webhook", method= RequestMethod.POST)
    public ResponseEntity<String> callback(
            @RequestHeader("X-Line-Signature") String xLineSignature,
            @RequestBody String eventsPayload)
    {
        try {
            if (!lineSignatureValidator.validateSignature(eventsPayload.getBytes(), xLineSignature)) {
                throw new RuntimeException("Invalid Signature Validation");
            }


            ObjectMapper objectMapper = ModelObjectMapper.createNewObjectMapper();
            EventsModel eventsModel = objectMapper.readValue(eventsPayload, EventsModel.class);


            eventsModel.getEvents().forEach((event)->{
                if (event instanceof MessageEvent) {

                    MessageEvent messageEvent = (MessageEvent) event;
                    TextMessageContent textMessageContent = (TextMessageContent) messageEvent.getMessage();

                    //System.out.print("\n \n \n USERID DEBUG \n"+eventsPayload+"\n \n \n");


                    //int len = Kunci1.length;
                    //return IntStream.range(0, len)
                    //.filter(i -> textMessageContent.getText() == Kunci1[i])
                    //.findFirst()
                    //.orElse(-1);

                    //Jawaban1 = new String[]{" ", " ", " "};
                    //Kunci1 = new String[]{"Tsuwaibah", "Halimah", "Bani Saad"};
                    //String[] Jawaban1 = new String[3];
                    //String[] Kunci1 = new String[3];
                    //Jawaban1 = new String[]{" ", " ", " "};
                    //int i;
                    //for (i = 0; i < Kunci1.length; i++) ;


                    //switch(textMessageContent.getText().toLowerCase(Locale.ROOT)){
                    //case "baca sirah":
                    //replyFlexMessage(((MessageEvent<?>) event).getReplyToken());
                    //break;
                    //case "library":
                    //replyText(messageEvent.getReplyToken(), "Free e-Book Sirah Nabawiyah bisa Anda download di http://bit.ly/almunawwir_library");
                    //break;
                    //case "referensi":
                    //replyText(messageEvent.getReplyToken(), "Referensi yang dipakai dalam penulisan Sirah Nabawiyah adalah sebagai berikut. \n1. Masa Kelahiran \nNanti diisi daftar refensi \n2. Masa Kecil \nIsi");
                    //break;
                    //case "help":
                    //replyText(messageEvent.getReplyToken(), "nanti diisi bantuan penggunaan personal ataupun grup");
                    //break;
                    //case "kritik dan saran":
                    //replyText(messageEvent.getReplyToken(), "Kami sangat membutuhkan kritik dan saran Anda. Anda dapat mengirimkannya ke munawwirain2@gmail.com");
                    //break;
                    //case "tentang":
                    //replyText(messageEvent.getReplyToken(), "al-munawwir adalah chatbot yang dibuat oleh OrionTechnoX untuk membantu kaum muslimin belajar Sirah Nabawiyah secara praktis menggunakan chat app");
                    //break;
                    //case "menu":
                    //replyText(messageEvent.getReplyToken(), "Menu \n1. Baca Sirah \n2. Library \n3. Referensi \n4. Help \n5. Kritik dan Saran \n6. Tentang");
                    //break;
                    //case "kuis masa kelahiran":
                    //replyText(messageEvent.getReplyToken(), "Nama-Nama Ibu Susu Nabi: \n1. _____ \n2. _____ \n3. _____");
                    //break;
                    //case Kunci1 [i] == textMessageContent.getText():
                    //Jawaban1 [i] = textMessageContent.getText();
                    //replyText(messageEvent.getReplyToken(),"Nama-Nama Ibu Susu Nabi: " + Jawaban1[i]);
                    //break;
                    //default:
                    //replyText(messageEvent.getReplyToken(), "Maaf, saya tidak paham. Mohon balas sesuai daftar menu. Jika ingin mendapatkan daftar menu balas \"menu\" atau balas \"help\" untuk mendapat bantuan penggunaan");


                    //}

                   // List<Message> msgArray = new ArrayList<>();
                    //msgArray.add(new TextMessage("BENAR"));
                    //msgArray.add(new StickerMessage("1", "114"));

                    //List<Message> msgArray2 = new ArrayList<>();
                    //msgArray2.add(new TextMessage("SALAH. Nabi ??? lahir pada hari Senin berdasarkan HR. Muslim No.1162"));
                    //msgArray2.add(new FlexMessage("Kuis 1 Soal 1", flexContainer));
                    //msgArray2.add(new StickerMessage("1", "114"));
                    //ReplyMessage replyMessage = new ReplyMessage(((MessageEvent<?>) event).getReplyToken(), msgArray);
                    //reply(replyMessage);


                    if(textMessageContent.getText().equalsIgnoreCase("Library")) {
                        replyText(messageEvent.getReplyToken(), "Free e-Book Sirah Nabawiyah bisa Anda download di http://bit.ly/almunawwir_library");
                    //}else if(textMessageContent.getText().equalsIgnoreCase("Referensi")) {
                        //replyText(messageEvent.getReplyToken(), "Referensi yang dipakai dalam penulisan Sirah Nabawiyah adalah sebagai berikut. \n1. Masa Kelahiran \nNanti diisi daftar refensi \n2. Masa Kecil \nIsi");
                    }else if(textMessageContent.getText().equalsIgnoreCase("Baca Sirah")) {
                        replyFlexMessage(((MessageEvent<?>) event).getReplyToken());
                    }else if(textMessageContent.getText().equalsIgnoreCase("Help")) {
                        replyText(messageEvent.getReplyToken(), "al-munawwir adalah chatbot yang berfungsi untuk mempelajarin sirah Nabawiyah secara praktis dengan LINE. Silahkan balas pesan ini dengan \"menu\" untuk memunculkan menu dan menikmati fitur dari kami. Al-munawwir memiliki fitur: \n1. Baca Sirah \nFitur ini menyediakan sirah yang dapat dibaca secara online maupun diunduh dengan format file pdf juga dilengkapi dengan kuis \n2. Library \nFitur ini memiliki sumber penulisan sirah dan rujukan sirah Nabawiyah lain yang dapat diunduh \n3. Help \nFitur ini adalah tempat user mendapatkan panduan penggunaan \n4. Kritik dan Saran \nMelalui fitur ini kami menerima kritik dan saran agar dapat mengembangkan chatbot ini dengan baik \n5. Tentang \nTempat dimana user bisa membaca tentang kami");
                    }else if(textMessageContent.getText().equalsIgnoreCase("Kritik dan Saran")) {
                        replyText(messageEvent.getReplyToken(), "Kami sangat membutuhkan kritik dan saran Anda. Anda dapat mengirimkannya ke munawwirain2@gmail.com");
                    }else if(textMessageContent.getText().equalsIgnoreCase("Tentang")) {
                        replyText(messageEvent.getReplyToken(), "al-munawwir adalah chatbot yang dibuat oleh OrionTechnoX untuk membantu kaum muslimin belajar Sirah Nabawiyah secara praktis menggunakan chat app");
                    }else if(textMessageContent.getText().equalsIgnoreCase("Menu")) {
                        replyText(messageEvent.getReplyToken(), "Menu \n1. Baca Sirah \n2. Library \n3. Help \n4. Kritik dan Saran \n5. Tetang");
                    }else if(textMessageContent.getText().equalsIgnoreCase("Kuis Masa Kelahiran")) {
                        replyFlexMessage2(((MessageEvent<?>) event).getReplyToken());
                    }else if(textMessageContent.getText().equalsIgnoreCase("Kuis Masa Kecil")) {
                        replyFlexMessage4(((MessageEvent<?>) event).getReplyToken());
                    }else if(textMessageContent.getText().equalsIgnoreCase("Kuis Pra Kenabian")) {
                        replyFlexMessage6(((MessageEvent<?>) event).getReplyToken());
                    }else if((textMessageContent.getText().equalsIgnoreCase("Senin"))){
                        //replyMessage(messageEvent.getReplyToken(), msgArray, event);
                        try {
                            List<Message> msgArray = new ArrayList<>();
                            msgArray.add(new TextMessage("BENAR"));

                            ClassLoader classLoader = getClass().getClassLoader();
                            String flexTemplate = IOUtils.toString(classLoader.getResourceAsStream("flex_message_3.json"));
                            FlexContainer flexContainer = objectMapper.readValue(flexTemplate, FlexContainer.class);

                            msgArray.add(new FlexMessage("Kuis 1 Soal 2", flexContainer));
                            ReplyMessage replyMessage = new ReplyMessage(messageEvent.getReplyToken(), msgArray);
                            reply(replyMessage);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }else if((textMessageContent.getText().equalsIgnoreCase("Selasa"))){
                        try {
                            List<Message> msgArray = new ArrayList<>();
                            msgArray.add(new TextMessage("SALAH. Nabi ??? lahir pada hari Senin berdasarkan HR. Muslim No.1162"));

                            ClassLoader classLoader = getClass().getClassLoader();
                            String flexTemplate = IOUtils.toString(classLoader.getResourceAsStream("flex_message_3.json"));
                            FlexContainer flexContainer = objectMapper.readValue(flexTemplate, FlexContainer.class);

                            msgArray.add(new FlexMessage("Kuis 1 Soal 2", flexContainer));
                            ReplyMessage replyMessage = new ReplyMessage(messageEvent.getReplyToken(), msgArray);
                            reply(replyMessage);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }else if((textMessageContent.getText().equalsIgnoreCase("Rabu"))){
                        try {
                            List<Message> msgArray = new ArrayList<>();
                            msgArray.add(new TextMessage("SALAH. Nabi ??? lahir pada hari Senin berdasarkan HR. Muslim No.1162"));

                            ClassLoader classLoader = getClass().getClassLoader();
                            String flexTemplate = IOUtils.toString(classLoader.getResourceAsStream("flex_message_3.json"));
                            FlexContainer flexContainer = objectMapper.readValue(flexTemplate, FlexContainer.class);

                            msgArray.add(new FlexMessage("Kuis 1 Soal 2", flexContainer));
                            ReplyMessage replyMessage = new ReplyMessage(messageEvent.getReplyToken(), msgArray);
                            reply(replyMessage);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }else if((textMessageContent.getText().equalsIgnoreCase("Kamis"))){
                        try {
                            List<Message> msgArray = new ArrayList<>();
                            msgArray.add(new TextMessage("SALAH. Nabi ??? lahir pada hari Senin berdasarkan HR. Muslim No.1162"));

                            ClassLoader classLoader = getClass().getClassLoader();
                            String flexTemplate = IOUtils.toString(classLoader.getResourceAsStream("flex_message_3.json"));
                            FlexContainer flexContainer = objectMapper.readValue(flexTemplate, FlexContainer.class);

                            msgArray.add(new FlexMessage("Kuis 1 Soal 2", flexContainer));
                            ReplyMessage replyMessage = new ReplyMessage(messageEvent.getReplyToken(), msgArray);
                            reply(replyMessage);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }else if((textMessageContent.getText().equalsIgnoreCase("Jum'at"))) {
                        try {
                            List<Message> msgArray = new ArrayList<>();
                            msgArray.add(new TextMessage("SALAH. Nabi ??? lahir pada hari Senin berdasarkan HR. Muslim No.1162"));

                            ClassLoader classLoader = getClass().getClassLoader();
                            String flexTemplate = IOUtils.toString(classLoader.getResourceAsStream("flex_message_3.json"));
                            FlexContainer flexContainer = objectMapper.readValue(flexTemplate, FlexContainer.class);

                            msgArray.add(new FlexMessage("Kuis 1 Soal 2", flexContainer));
                            ReplyMessage replyMessage = new ReplyMessage(messageEvent.getReplyToken(), msgArray);
                            reply(replyMessage);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }else if((textMessageContent.getText().equalsIgnoreCase("Abdul Muthalib"))){
                        replyText(messageEvent.getReplyToken(), "BENAR");
                    }else if((textMessageContent.getText().equalsIgnoreCase("Ubay bin Khalaf"))){
                        replyText(messageEvent.getReplyToken(), "SALAH. Utusan Quraisy untuk berunding dengan Abrahah adalah Abdul Muthalib sebagai pemimpin Quraisy pada saat itu");
                    }else if((textMessageContent.getText().equalsIgnoreCase("Hatake Kakashi"))){
                        replyText(messageEvent.getReplyToken(), "SALAH. Utusan Quraisy untuk berunding dengan Abrahah adalah Abdul Muthalib sebagai pemimpin Quraisy pada saat itu. Oalah wibu wibu");
                    }else if((textMessageContent.getText().equalsIgnoreCase("Mughirah bin Syu'bah"))){
                        replyText(messageEvent.getReplyToken(), "SALAH. Utusan Quraisy untuk berunding dengan Abrahah adalah Abdul Muthalib sebagai pemimpin Quraisy pada saat itu");
                    }else if((textMessageContent.getText().equalsIgnoreCase("Tsuwaibah Al-Aslamiyah"))) {
                        try {
                            List<Message> msgArray = new ArrayList<>();
                            msgArray.add(new TextMessage("BENAR"));

                            ClassLoader classLoader = getClass().getClassLoader();
                            String flexTemplate = IOUtils.toString(classLoader.getResourceAsStream("flex_message_5.json"));
                            FlexContainer flexContainer = objectMapper.readValue(flexTemplate, FlexContainer.class);

                            msgArray.add(new FlexMessage("Kuis 2 Soal 2", flexContainer));
                            ReplyMessage replyMessage = new ReplyMessage(messageEvent.getReplyToken(), msgArray);
                            reply(replyMessage);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }else if((textMessageContent.getText().equalsIgnoreCase("Ummi Maktum"))) {
                        try {
                            List<Message> msgArray = new ArrayList<>();
                            msgArray.add(new TextMessage("SALAH. Ibu susu Nabi ??? antara lain adalah Tsuwaibah Al-Aslamiyah, Halimah Al-Sa???diyah, dan seorang perempuan dari Bani Sa'ad"));

                            ClassLoader classLoader = getClass().getClassLoader();
                            String flexTemplate = IOUtils.toString(classLoader.getResourceAsStream("flex_message_5.json"));
                            FlexContainer flexContainer = objectMapper.readValue(flexTemplate, FlexContainer.class);

                            msgArray.add(new FlexMessage("Kuis 2 Soal 2", flexContainer));
                            ReplyMessage replyMessage = new ReplyMessage(messageEvent.getReplyToken(), msgArray);
                            reply(replyMessage);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }else if((textMessageContent.getText().equalsIgnoreCase("Halimah Ash-Shafawiyah"))) {
                        try {
                            List<Message> msgArray = new ArrayList<>();
                            msgArray.add(new TextMessage("SALAH. Ibu susu Nabi ??? antara lain adalah Tsuwaibah Al-Aslamiyah, Halimah Al-Sa???diyah, dan seorang perempuan dari Bani Sa'ad"));

                            ClassLoader classLoader = getClass().getClassLoader();
                            String flexTemplate = IOUtils.toString(classLoader.getResourceAsStream("flex_message_5.json"));
                            FlexContainer flexContainer = objectMapper.readValue(flexTemplate, FlexContainer.class);

                            msgArray.add(new FlexMessage("Kuis 2 Soal 2", flexContainer));
                            ReplyMessage replyMessage = new ReplyMessage(messageEvent.getReplyToken(), msgArray);
                            reply(replyMessage);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }else if((textMessageContent.getText().equalsIgnoreCase("Ummu Jamil"))) {
                        try {
                            List<Message> msgArray = new ArrayList<>();
                            msgArray.add(new TextMessage("SALAH. Ibu susu Nabi ??? antara lain adalah Tsuwaibah Al-Aslamiyah, Halimah Al-Sa???diyah, dan seorang perempuan dari Bani Sa'ad"));

                            ClassLoader classLoader = getClass().getClassLoader();
                            String flexTemplate = IOUtils.toString(classLoader.getResourceAsStream("flex_message_5.json"));
                            FlexContainer flexContainer = objectMapper.readValue(flexTemplate, FlexContainer.class);

                            msgArray.add(new FlexMessage("Kuis 2 Soal 2", flexContainer));
                            ReplyMessage replyMessage = new ReplyMessage(messageEvent.getReplyToken(), msgArray);
                            reply(replyMessage);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }else if((textMessageContent.getText().equalsIgnoreCase("Abdullah bin Abdul Muthalib"))){
                        replyText(messageEvent.getReplyToken(), "BENAR. Abdullah bin Abdul Muthalib adalah nama ayah Nabi ???");
                    }else if((textMessageContent.getText().equalsIgnoreCase("Hamzah bin Abdul Muthalib"))){
                        replyText(messageEvent.getReplyToken(), "SALAH. Hamzah bin Abdul Muthalib adalah salah satu paman Nabi ???");
                    }else if((textMessageContent.getText().equalsIgnoreCase("Abu Thalib bin Abdul Muthalib"))){
                        replyText(messageEvent.getReplyToken(), "SALAH. Abu Thalib bin Abdul Muthalib adalah salah satu paman Nabi ???");
                    }else if((textMessageContent.getText().equalsIgnoreCase("Abu Lahab bin Abdul Muthalib"))){
                        replyText(messageEvent.getReplyToken(), "SALAH. Abu Lahab bin Abdul Muthalib adalah salah satu paman Nabi ???");
                    }else if((textMessageContent.getText().equalsIgnoreCase("Abbas bin Abdul Muthalib"))){
                        replyText(messageEvent.getReplyToken(), "SALAH. Abbas bin Abdul Muthalib adalah salah satu paman Nabi ???");
                    }else if((textMessageContent.getText().equalsIgnoreCase("Khadijah binti Khuwailid"))) {
                        try {
                            List<Message> msgArray = new ArrayList<>();
                            msgArray.add(new TextMessage("BENAR"));

                            ClassLoader classLoader = getClass().getClassLoader();
                            String flexTemplate = IOUtils.toString(classLoader.getResourceAsStream("flex_message_7.json"));
                            FlexContainer flexContainer = objectMapper.readValue(flexTemplate, FlexContainer.class);

                            msgArray.add(new FlexMessage("Kuis 3 Soal 2", flexContainer));
                            ReplyMessage replyMessage = new ReplyMessage(messageEvent.getReplyToken(), msgArray);
                            reply(replyMessage);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }else if((textMessageContent.getText().equalsIgnoreCase("Khadijah binti As'ad"))) {
                        try {
                            List<Message> msgArray = new ArrayList<>();
                            msgArray.add(new TextMessage("SALAH. Istri pertama Nabi ??? adalah Ummul Mu'minin Khadijah binti Khuwailid"));

                            ClassLoader classLoader = getClass().getClassLoader();
                            String flexTemplate = IOUtils.toString(classLoader.getResourceAsStream("flex_message_7.json"));
                            FlexContainer flexContainer = objectMapper.readValue(flexTemplate, FlexContainer.class);

                            msgArray.add(new FlexMessage("Kuis 3 Soal 2", flexContainer));
                            ReplyMessage replyMessage = new ReplyMessage(messageEvent.getReplyToken(), msgArray);
                            reply(replyMessage);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }else if((textMessageContent.getText().equalsIgnoreCase("Maimunah binti Al-Harits"))) {
                        try {
                            List<Message> msgArray = new ArrayList<>();
                            msgArray.add(new TextMessage("SALAH. Istri pertama Nabi ??? adalah Ummul Mu'minin Khadijah binti Khuwailid"));

                            ClassLoader classLoader = getClass().getClassLoader();
                            String flexTemplate = IOUtils.toString(classLoader.getResourceAsStream("flex_message_7.json"));
                            FlexContainer flexContainer = objectMapper.readValue(flexTemplate, FlexContainer.class);

                            msgArray.add(new FlexMessage("Kuis 3 Soal 2", flexContainer));
                            ReplyMessage replyMessage = new ReplyMessage(messageEvent.getReplyToken(), msgArray);
                            reply(replyMessage);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }else if((textMessageContent.getText().equalsIgnoreCase("Hafsah binti Umar"))) {
                        try {
                            List<Message> msgArray = new ArrayList<>();
                            msgArray.add(new TextMessage("SALAH. Istri pertama Nabi ??? adalah Ummul Mu'minin Khadijah binti Khuwailid"));

                            ClassLoader classLoader = getClass().getClassLoader();
                            String flexTemplate = IOUtils.toString(classLoader.getResourceAsStream("flex_message_7.json"));
                            FlexContainer flexContainer = objectMapper.readValue(flexTemplate, FlexContainer.class);

                            msgArray.add(new FlexMessage("Kuis 3 Soal 2", flexContainer));
                            ReplyMessage replyMessage = new ReplyMessage(messageEvent.getReplyToken(), msgArray);
                            reply(replyMessage);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }else if((textMessageContent.getText().equalsIgnoreCase("Aisyah binti Abu Bakar"))) {
                        try {
                            List<Message> msgArray = new ArrayList<>();
                            msgArray.add(new TextMessage("SALAH. Istri pertama Nabi ??? adalah Ummul Mu'minin Khadijah binti Khuwailid"));

                            ClassLoader classLoader = getClass().getClassLoader();
                            String flexTemplate = IOUtils.toString(classLoader.getResourceAsStream("flex_message_7.json"));
                            FlexContainer flexContainer = objectMapper.readValue(flexTemplate, FlexContainer.class);

                            msgArray.add(new FlexMessage("Kuis 3 Soal 2", flexContainer));
                            ReplyMessage replyMessage = new ReplyMessage(messageEvent.getReplyToken(), msgArray);
                            reply(replyMessage);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }else if((textMessageContent.getText().equalsIgnoreCase("Qushay"))){
                        replyText(messageEvent.getReplyToken(), "BENAR. Nama lengkap Nabi ??? adalah Muhammad  bin ???Abdill??h  bin  ???Abdil  Muththalib  bin  H??syim  bin  ???Abdi  Manaf  bin  Qushay  bin  Kil??b sementara Khadijah adalah  Khad??jah  binti  Khuwailid  bin  As???ad  bin Abdil  ???Uzza  bin  Qushay  bin  Kil??b");
                    }else if((textMessageContent.getText().equalsIgnoreCase("Murrah"))){
                        replyText(messageEvent.getReplyToken(), "SALAH. Nama lengkap Nabi ??? adalah Muhammad  bin ???Abdill??h  bin  ???Abdil  Muththalib  bin  H??syim  bin  ???Abdi  Manaf  bin  Qushay  bin  Kil??b sementara Khadijah adalah  Khad??jah  binti  Khuwailid  bin  As???ad  bin Abdil  ???Uzza  bin  Qushay  bin  Kil??b");
                    }else if((textMessageContent.getText().equalsIgnoreCase("Kilab"))){
                        replyText(messageEvent.getReplyToken(), "SALAH. Nama lengkap Nabi ??? adalah Muhammad  bin ???Abdill??h  bin  ???Abdil  Muththalib  bin  H??syim  bin  ???Abdi  Manaf  bin  Qushay  bin  Kil??b sementara Khadijah adalah  Khad??jah  binti  Khuwailid  bin  As???ad  bin Abdil  ???Uzza  bin  Qushay  bin  Kil??b");
                    }else if((textMessageContent.getText().equalsIgnoreCase("Quraisy"))){
                        replyText(messageEvent.getReplyToken(), "SALAH. Nama lengkap Nabi ??? adalah Muhammad  bin ???Abdill??h  bin  ???Abdil  Muththalib  bin  H??syim  bin  ???Abdi  Manaf  bin  Qushay  bin  Kil??b sementara Khadijah adalah  Khad??jah  binti  Khuwailid  bin  As???ad  bin Abdil  ???Uzza  bin  Qushay  bin  Kil??b");
                    }else if((textMessageContent.getText().equalsIgnoreCase("Sa'ad"))){
                        replyText(messageEvent.getReplyToken(), "SALAH. Nama lengkap Nabi ??? adalah Muhammad  bin ???Abdill??h  bin  ???Abdil  Muththalib  bin  H??syim  bin  ???Abdi  Manaf  bin  Qushay  bin  Kil??b sementara Khadijah adalah  Khad??jah  binti  Khuwailid  bin  As???ad  bin Abdil  ???Uzza  bin  Qushay  bin  Kil??b");
                    }else{
                        replyText(messageEvent.getReplyToken(), "Maaf, saya tidak paham. Mohon balas sesuai daftar menu. Jika ingin mendapatkan daftar menu balas \"menu\" atau balas \"help\" untuk mendapat bantuan penggunaan");
                    }


                    //if  ((  (MessageEvent) event).getMessage() instanceof AudioMessageContent
                    //|| ((MessageEvent) event).getMessage() instanceof ImageMessageContent
                    //|| ((MessageEvent) event).getMessage() instanceof VideoMessageContent
                    //|| ((MessageEvent) event).getMessage() instanceof FileMessageContent
                    // ) {
                    //  String baseURL     = "https://al-munawwir.herokuapp.com";
                    // String contentURL  = baseURL+"/content/"+ ((MessageEvent) event).getMessage().getId();
                    // String contentType = ((MessageEvent) event).getMessage().getClass().getSimpleName();
                    // String textMsg     = contentType.substring(0, contentType.length() -14)
                    // + " yang kamu kirim bisa diakses dari link:\n "
                    // + contentURL;

                    // replyText(((MessageEvent) event).getReplyToken(), textMsg);
                    // } else {
                    //     MessageEvent messageEvent = (MessageEvent) event;
                    //     TextMessageContent textMessageContent = (TextMessageContent) messageEvent.getMessage();
                    //    replyText(messageEvent.getReplyToken(), textMessageContent.getText());
                    // }

                    //if (event.getSource() instanceof GroupSource || event.getSource() instanceof RoomSource) {
                    // handleGroupRoomChats((MessageEvent) event);
                    //} else {
                    //  handleOneOnOneChats((MessageEvent) event);
                }



            });


            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }



    @RequestMapping(value = "/content/{id}", method = RequestMethod.GET)
    public ResponseEntity content(
            @PathVariable("id") String messageId
    ){
        MessageContentResponse messageContent = getContent(messageId);

        if(messageContent != null) {
            HttpHeaders headers = new HttpHeaders();
            String[] mimeType = messageContent.getMimeType().split("/");
            headers.setContentType(new MediaType(mimeType[0], mimeType[1]));

            InputStream inputStream = messageContent.getStream();
            InputStreamResource inputStreamResource = new InputStreamResource(inputStream);

            return new ResponseEntity<>(inputStreamResource, headers, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @RequestMapping(value="/multicast", method=RequestMethod.GET)
    public ResponseEntity<String> multicast(){
        String[] userIdList = {
                "Uabb9a2257d767aba284d917a378884be",
                "U4d34cb185d4ec3b8d80c4e5f9f1f1aab",
                "U8dfe52c73c5dd89f2350048edfb05f70",
                //"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx",
                //"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"
                };
        Set<String> listUsers = new HashSet<String>(Arrays.asList(userIdList));
        if(listUsers.size() > 0){
            String textMsg = "Tes satu dua dicoba";
            sendMulticast(listUsers, textMsg);
        }
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @RequestMapping(value = "/profile/{id}", method = RequestMethod.GET)
    public ResponseEntity<String> profile(
            @PathVariable("id") String userId
    ){
        UserProfileResponse profile = getProfile(userId);

        if (profile != null) {
            String profileName = profile.getDisplayName();
            TextMessage textMessage = new TextMessage("Hello, " + profileName);
            PushMessage pushMessage = new PushMessage(userId, textMessage);
            push(pushMessage);

            return new ResponseEntity<String>("Hello, "+profileName, HttpStatus.OK);
        }
        return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
    }


    @RequestMapping(value="/pushmessage/{id}/{message}", method=RequestMethod.GET)
    public ResponseEntity<String> pushmessage(
           @PathVariable("id") String userId,
           @PathVariable("message") String textMsg)
    {
      TextMessage textMessage = new TextMessage(textMsg);
      PushMessage pushMessage = new PushMessage(userId, textMessage);
        push(pushMessage);


       return new ResponseEntity<String>("Push message:"+textMsg+"\nsent to: "+userId, HttpStatus.OK);
    }


    private void reply(ReplyMessage replyMessage) {
        try {
            lineMessagingClient.replyMessage(replyMessage).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }


    private void replyText(String replyToken, String messageToUser){
        TextMessage textMessage = new TextMessage(messageToUser);
        ReplyMessage replyMessage = new ReplyMessage(replyToken, textMessage);
        reply(replyMessage);
    }
    private void replySticker(String replyToken, String packageId, String stickerId){
        StickerMessage stickerMessage = new StickerMessage(packageId, stickerId);
        ReplyMessage replyMessage = new ReplyMessage(replyToken, stickerMessage);
        reply(replyMessage);
    }

    private void replyMessage(String replyToken, List<Message> msgArray, Object event) {
        ReplyMessage replyMessage = new ReplyMessage(((MessageEvent<?>) event).getReplyToken(), msgArray);
        reply(replyMessage);
    }

    private void replyMessage2(String replyToken, List<Message> msgArray2, Object event) {
        ReplyMessage replyMessage = new ReplyMessage(((MessageEvent<?>) event).getReplyToken(), msgArray2);
        reply(replyMessage);
    }

    private void sendMulticast(Set<String> sourceUsers, String txtMessage){
        TextMessage message = new TextMessage(txtMessage);
        Multicast multicast = new Multicast(sourceUsers, message);

        try {
            lineMessagingClient.multicast(multicast).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    private UserProfileResponse getProfile(String userId){
        try {
            return lineMessagingClient.getProfile(userId).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    private void push(PushMessage pushMessage){
        try {
            lineMessagingClient.pushMessage(pushMessage).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
       }
    }

    private MessageContentResponse getContent(String messageId) {
        try {
            return lineMessagingClient.getMessageContent(messageId).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

  //private void handleOneOnOneChats(MessageEvent event) {

       //if  (event.getMessage() instanceof AudioMessageContent
             // || event.getMessage() instanceof ImageMessageContent
             // || event.getMessage() instanceof VideoMessageContent
             // || event.getMessage() instanceof FileMessageContent
       // ) {
        //    handleContentMessage(event);
      // } else if(event.getMessage() instanceof TextMessageContent) {
         //handleTextMessage(event);
       //} else {
            //replyText(event.getReplyToken(), "Unknown Message");
        //}

   // }

    private void handleContentMessage(MessageEvent event) {
        String baseURL     = "https://al-munawwir.herokuapp.com";
        String contentURL  = baseURL+"/content/"+ event.getMessage().getId();
        String contentType = event.getMessage().getClass().getSimpleName();
        String textMsg     = contentType.substring(0, contentType.length() -14)
                + " yang kamu kirim bisa diakses dari link:\n "
                + contentURL;

        replyText(event.getReplyToken(), textMsg);
    }

    //private void handleTextMessage(MessageEvent event) {
        //TextMessageContent textMessageContent = (TextMessageContent) event.getMessage();

        //if (textMessageContent.getText().toLowerCase().contains("Belajar Sirah")) {
          //replyFlexMessage(event.getReplyToken());
        //} else {
           // replyText(event.getReplyToken(), textMessageContent.getText());
       // }
   // }

    private void handleGroupRoomChats(MessageEvent event) {
        if(!event.getSource().getUserId().isEmpty()) {
            String userId = event.getSource().getUserId();
            UserProfileResponse profile = getProfile(userId);
            replyText(event.getReplyToken(), "Hello, " + profile.getDisplayName());
        } else {
            replyText(event.getReplyToken(), "Hello, what is your name?");
        }
    }

    private void replyFlexMessage(String replyToken) {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            String flexTemplate = IOUtils.toString(classLoader.getResourceAsStream("flex_message.json"));


            ObjectMapper objectMapper = ModelObjectMapper.createNewObjectMapper();
            FlexContainer flexContainer = objectMapper.readValue(flexTemplate, FlexContainer.class);


            ReplyMessage replyMessage = new ReplyMessage(replyToken, new FlexMessage("Masa Kelahiran", flexContainer));
            reply(replyMessage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void replyFlexMessage2(String replyToken) {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            String flexTemplate = IOUtils.toString(classLoader.getResourceAsStream("flex_message_2.json"));


            ObjectMapper objectMapper = ModelObjectMapper.createNewObjectMapper();
            FlexContainer flexContainer = objectMapper.readValue(flexTemplate, FlexContainer.class);


            ReplyMessage replyMessage = new ReplyMessage(replyToken, new FlexMessage("Kuis 1 Soal 1", flexContainer));
            reply(replyMessage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

        private void replyFlexMessage3(String replyToken) {
            try {
                ClassLoader classLoader = getClass().getClassLoader();
                String flexTemplate = IOUtils.toString(classLoader.getResourceAsStream("flex_message_3.json"));


                ObjectMapper objectMapper = ModelObjectMapper.createNewObjectMapper();
                FlexContainer flexContainer = objectMapper.readValue(flexTemplate, FlexContainer.class);


                ReplyMessage replyMessage = new ReplyMessage(replyToken, new FlexMessage("Kuis 1 Soal 2", flexContainer));
                reply(replyMessage);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }

    private void replyFlexMessage4(String replyToken) {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            String flexTemplate = IOUtils.toString(classLoader.getResourceAsStream("flex_message_4.json"));


            ObjectMapper objectMapper = ModelObjectMapper.createNewObjectMapper();
            FlexContainer flexContainer = objectMapper.readValue(flexTemplate, FlexContainer.class);


            ReplyMessage replyMessage = new ReplyMessage(replyToken, new FlexMessage("Kuis 2 Soal 1", flexContainer));
            reply(replyMessage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void replyFlexMessage5(String replyToken) {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            String flexTemplate = IOUtils.toString(classLoader.getResourceAsStream("flex_message_5.json"));


            ObjectMapper objectMapper = ModelObjectMapper.createNewObjectMapper();
            FlexContainer flexContainer = objectMapper.readValue(flexTemplate, FlexContainer.class);


            ReplyMessage replyMessage = new ReplyMessage(replyToken, new FlexMessage("Kuis 2 Soal 2", flexContainer));
            reply(replyMessage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void replyFlexMessage6(String replyToken) {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            String flexTemplate = IOUtils.toString(classLoader.getResourceAsStream("flex_message_6.json"));


            ObjectMapper objectMapper = ModelObjectMapper.createNewObjectMapper();
            FlexContainer flexContainer = objectMapper.readValue(flexTemplate, FlexContainer.class);


            ReplyMessage replyMessage = new ReplyMessage(replyToken, new FlexMessage("Kuis 3 Soal 1", flexContainer));
            reply(replyMessage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void replyFlexMessage7(String replyToken) {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            String flexTemplate = IOUtils.toString(classLoader.getResourceAsStream("flex_message_7.json"));


            ObjectMapper objectMapper = ModelObjectMapper.createNewObjectMapper();
            FlexContainer flexContainer = objectMapper.readValue(flexTemplate, FlexContainer.class);


            ReplyMessage replyMessage = new ReplyMessage(replyToken, new FlexMessage("Kuis 3 Soal 2", flexContainer));
            reply(replyMessage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}