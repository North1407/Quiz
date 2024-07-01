package com.vti.examwebsise.examonline.admin.topic.controller;

import com.vti.examwebsise.examonline.common.entity.Topic;
import com.vti.examwebsise.examonline.admin.topic.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/manage")
public class TopicController {
    private String defaultRedirectURL = "redirect:/manage/topics";
    @Autowired
    private TopicService topicService;

    @GetMapping("/topics")
    public String getAllTopics(Model model) {
        List<Topic> topics = topicService.getAllTopics();
        model.addAttribute("topics", topics);
        return "admins/topics";
    }

    @GetMapping("/topics/{id}/enabled/{status}")
    public String enableTopic(@PathVariable("id") Integer id, @PathVariable("status") boolean status,RedirectAttributes re) {
        topicService.enableTopic(id, status);
        re.addFlashAttribute("message", "The topic ID " + id + " has been " + (status ? "enabled" : "disabled") + " successfully");
        return defaultRedirectURL;
    }
    @GetMapping("/topics/edit/{id}")
    public String editTopic(@PathVariable("id") Integer id, RedirectAttributes model) {
        Topic topic = topicService.getTopic(id);
        model.addFlashAttribute("topicName", topic.getName());
        model.addFlashAttribute("topicId", topic.getId());
        return defaultRedirectURL;
    }

    @PostMapping("/topics/save")
    public String saveTopic(Integer topicId,String topicName,RedirectAttributes re) {
        if(topicId!=null){
            Topic topic = topicService.getTopic(topicId);
            topic.setName(topicName);
            topicService.save(topic);
            re.addFlashAttribute("message", "The topic has been saved successfully");
            return defaultRedirectURL;
        }
        Topic topic = new Topic(topicName);
        topicService.save(topic);
        re.addFlashAttribute("message", "The topic has been saved successfully");
        return defaultRedirectURL;
    }

    @GetMapping("/topics/delete/{id}")
    public String deleteTopic(@PathVariable("id") Integer id, RedirectAttributes re) {
        try {
            topicService.deleteTopic(id);
        } catch (RuntimeException e) {
            re.addFlashAttribute("dangerMessage", "Topic is used in Exam");
            return defaultRedirectURL;
        }
        re.addFlashAttribute("message", "The topic has been deleted successfully");
        return defaultRedirectURL;
    }
}
