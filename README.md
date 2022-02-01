# Wordle

This simple game is everywhere right now (the beginning of 2022) and it seems like everyone is playing it.  As I was writing this application, it was announced that Wordle was sold to the New York Times, who will host it in the future.

If you don't know about Wordle you should go and take a look at it [here](https://www.powerlanguage.co.uk/wordle/){:target="_blank"}.  The website has all of the information that you need to understand how the game works, and you can play it (once a day) to see how it looks when it's running.

The game mechanics are very similar to "Mastermind", where you get clues about which letters are correct, and which are letters in the word but not in the correct place.  It's different in these respects:

- The indicators are on the guessed letters themselves
- You know exactly which letters are correct
- You know exactly which letters are in the wrong place
- The guess has to be an actual word in the English language
- You only get six guesses

All of these differences make the game a little easier than "Mastermind", but the requirement to use real words makes it feel tougher even though this factor limits the field of possible answers.

Finally, Wordle generates just one target word each day, for the entire world, and you only get to play it once per day.

# WordleFX

WordleFX is written to be an absolute clone of Wordle in terms of look and game play.  You get one word per day, and it's the same word as on the website.  It doesn't lock you out if you've lost and restart it, or just restart it part-way through so you can start over.  So it has no feature to share your results - that wouldn't be fair.

WordleFX wasn't really created to be replacement for playing Wordle on the Web.  It's intended to be a demonstration of how Reactive JavaFX can be used to create a highly interactive and interesting interface in an application.  It's also intended to be a demonstration of how CSS styling can be used as an integral part of a user interface.

## Kotlin

WordleFX is written in Kotlin because ... Kotlin.  There's nothing too crazy in here, and if you understand Java should should be able to scratch your head a little bit and figure out what most of the code is doing.  Maybe you'll decide that Kotlin looks really cool and you'll want to learn it - that would be awesome.

Probably the strangest thing to Java programmers will be "Scope Functions", especially `apply` which is used copiously in WordleFX to configure `Nodes`.  You can find out more about `apply` [here](https://kotlinlang.org/docs/scope-functions.html#this).


![The Game](https://github.com/PragmaticCoding/WordleFX/blob/master/WordleFX1.png)
