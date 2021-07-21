package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RollDiceController {

    @GetMapping("/roll-dice")
    public String rollDice(){
        return "roll-dice";
    }


    @GetMapping("/roll-dice/{n}")
    @ResponseBody
    public String displayGuess(@PathVariable String n){
       String randomNumber = String.valueOf((int) (Math.random() * 6) + 1);
return "<h1>Your guess was: " + n + "</h1><br><h1>Your random number was: " + randomNumber + "</h1>";

    }

}
