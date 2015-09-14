/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.entities.Article;
import com.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Athanase
 */
@Controller
public class ArticleController {
    
    ArticleService service;
    
    @Autowired
    public ArticleController(ArticleService service) {
        this.service = service;
    }
    
    @RequestMapping(value="/articles", method=RequestMethod.POST)
    public String article(@ModelAttribute Article article, Model model) {
       Article art = service.creer(article);
       return "greeting";
    }
   
    @RequestMapping(value="/articles", method=RequestMethod.GET)
    public String art( Model model) {
      model.addAttribute("article", new Article());
       return "articles";
    }
}
