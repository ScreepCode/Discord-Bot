# Discord-Bot

[![GitHub last commit](https://img.shields.io/github/last-commit/ScreepCode/Discord-Bot?logo=github)](https://github.com/ScreepCode/Discord-Bot/)

## About Discord-Bot

This Project is a Discord Bot based on [Java Discord API (JDA)](https://github.com/DV8FromTheWorld/JDA).
It is a multifunctional Bot with following features:
- Clearing chat history
- Moderator functions (kick, ban, ...)
- Surveys and announcements
- Temporary voice channels
- Music features incl. lyrics

## Pre requisites

[![GitHub top language](https://img.shields.io/github/languages/top/ScreepCode/Discord-Bot?label=Java&logo=Java)](https://github.com/ScreepCode/Discord-Bot/) [![GitHub code size in bytes](https://img.shields.io/github/languages/code-size/ScreepCode/Discord-Bot?logo=github&color=teal)](https://github.com/ScreepCode/Discord-Bot/)

## How to run?

To run this Discord Bot, you need to compile an ```.jar``` File from this Project.
For this you need ```maven install```, it will automaticly build an shaded jar File.

In addition, you need to make a Enviromental Variable ```DisordBotToken``` to store the Token from Discord´s [Application Dashboard](https://discord.com/login?redirect_to=%2Fdevelopers%2Fapplications). 

## How to use?

When the Bot is invited to a server, you can use following commands:

- General commands
    - !help -> To show all commands
    - !hug -> To get attention
    - !ping -> To get the response time of the bot

- Music commands - Can still lead to errors!
    - !play [name or source] -> To start a song
    - !stop -> To stop the song and disconnect the bot
    - !pause -> To pause the song
    - !resume -> To continue the song
    - !seek [seconds] -> Fast forward the number of seconds
    - !seekto [ss] or [mm:ss] or [hh:mm:ss] -> Jumps to the specified position
    - !queue -> Lists commands that work with queue
    - !lyrics [song] -> Search a song text

- General commands with preveleges (Only if you have the rights):
    - !clear [number] -> To delete messages 
    - !kick/ban [name] -> Kick/Ban the mentioned user
    - !umfrage [survey text] -> To create a survey in provided channel
    - !addacastc [channel id] -> Add this channel for temporary channel function
    - !changestatus [play/stream/listen] -> Change Status of the bot (across all Guilds)
    - !info -> List owner specifics

## Documentation
For technical information and details use the wiki from [Java Discord API (JDA)](https://jda.wiki/introduction/jda/).


## Author
### [Niklas Buse](https://github.com/ScreepCode) [![GitHub followers](https://img.shields.io/github/followers/ScreepCode.svg?label=Follow%20@ScreepCode&style=social)](https://github.com/ScreepCode/)

Feel free to reach out to me via email. Shoot your doubts at [mail@niklas-buse.de](mailto:mail@niklas-buse.de?Subject=Discord-Bot).

> Glad to see you here! **Show some ❤️ by starring [this](https://github.com/ScreepCode/Discord-Bot/) repository.**
