package org.example.controller;

import org.example.domain.Message;
import org.example.domain.User;
import org.example.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Controller
public class MainController {
    @Autowired
    private MessageRepo messageRepo;

    @Value("${upload.path}")                                                            //from properties
    private String uploadPath;

    @GetMapping("/main")
    public String main(@RequestParam(required = false)String filter, Model model){
        Iterable<Message> messages;

        if (filter!=null && !filter.isEmpty()){
            messages = messageRepo.findByTag(filter);
        } else {
            messages = messageRepo.findAll();
        }

        model.addAttribute("messages",messages);
        model.addAttribute("filter",filter);
        return "main";
    }

    @PostMapping("/main")                                                                //без параметра -> в input без action
    public String add(
            @AuthenticationPrincipal User user,                         //получаем ТЕКУЩЕГО пользователя в качестве параметра метода
            @Valid Message message,                                     //запускает валидацию(?то что прописали в Message)
            BindingResult bindingResult,                                //список аргументов и сообщения ошибок валидации(Обязательно должен быть перед model, а то все ошибки будут сыпаться в View)
            Model model,      // @RequestParam means it is a parameter from the GET(если с URL) or POST(если с form(html)) request
            @RequestParam("file")MultipartFile file) throws IOException {

        message.setAuthor(user);

        if (bindingResult.hasErrors()){                         //если есть ошибки
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            model.addAttribute("message",message);
        } else {
        //Load file
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));

            message.setFilename(resultFilename);
        }
        model.addAttribute("message",null);      //if validation ok
        messageRepo.save(message);
        }

        Iterable<Message> messages = messageRepo.findAll();

        model.addAttribute("messages", messages);

        return "main";
    }


}