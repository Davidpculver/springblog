package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;

@Controller
public class RollDiceController {

    @GetMapping("/roll-dice")
    public String rollDice() {
        return "roll-dice";
    }


//    @GetMapping("/roll-dice/{n}")
//    @ResponseBody
//    public String displayGuess(@PathVariable String n) {
//        String randomNumber = String.valueOf((int) (Math.random() * 6) + 1);
//        String result;
//        if (String.valueOf(n).equals(randomNumber)) {
//            result = "You win, good guess!";
//        } else {
//            result = "You lose";
//        }
//        return "<h1>Your guess was: " + n + "</h1><br>" +
//                "<h1>Your random number was: " + randomNumber + "</h1><br>" +
//                "<h1>" + result + "</h1>";
//    }

//    refactoring - using the model, returning back to the roll-dice.html and setting attributes

    @GetMapping("/roll-dice/{n}")
    public String displayGuess(@PathVariable int n, Model model) {
        int randomNumber = new Random().nextInt(6 - 1 + 1) + 1;
        model.addAttribute("randomNumber", "Your roll: " + randomNumber);
        model.addAttribute("n", "Your guess: " + n);
        if (n == randomNumber) {
            model.addAttribute("result", "You win, good guess!");
        } else {
            model.addAttribute("result", "You lose, try again!");
        }
        return "roll-dice";
    }
}
