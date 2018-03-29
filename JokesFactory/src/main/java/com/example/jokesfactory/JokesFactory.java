package com.example.jokesfactory;

import java.util.Random;

public class JokesFactory {

    private String[] mJokes = {"Q: Why couldn't the blonde add 10 and seven on a calculator?\n\nA: She couldn't find the 10 key.",
            "Most people believe that if it ain't broke, don't fix it.\n\nEngineers believe that if it ain't broke, it doesn't have enough features yet.",
            "There are 10 types of people in the world: those who understand binary, and those who don't.",
            "How many programmers does it take to change a light bulb? \n\nNone. It's a hardware problem.",
            "A SEO couple had twins. For the first time they were happy with duplicate content.",
            "Why is it that programmers always confuse Halloween with Christmas? \n\nBecause 31 OCT = 25 DEC.",
            "Why do Java developers wear glasses? Because they can't C#.",
            "What do you call 8 hobbits? \n\nA hobbyte.",
            "Why did the developer go broke? \n\nBecause he used up all his cache",
            "Why did the geek add body { padding-top: 1000px; } to his Facebook profile? \nHe wanted to keep a low profile.",
            "An SEO expert walks into a bar, bars, pub, tavern, public house, Irish pub, drinks, beer, alcohol",
            "8 bytes walk into a bar, the bartenders asks \"What will it be?\"\n\nOne of them says, \"Make us a double.\"",
            "Two bytes meet. The first byte asks, \"Are you ill?\" \n\nThe second byte replies, \"No, just feeling a bit off.\""
    };

    public String getJoke(){
        Random r = new Random();
        int index = r.nextInt(mJokes.length);
        return mJokes[index];
    }
}
