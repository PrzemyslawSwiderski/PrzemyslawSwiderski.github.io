---
title: "Own Patrolling Robot"
created: 2025-04-27
---

{toc.placeholder}

# Introduction

About a month ago, I decided to build my own patrolling robot.

Why?

* To remotely monitor my home and check what's happening when I'm away.
* To receive notifications if any movement is detected in my room.
* To maneuver the camera across different rooms to inspect for issues, such as leaks.
* To avoid off-the-shelf solutions and ensure my data remains private, bypassing potentially untrustworthy servers.
* To explore how AI can assist in this project.
* To refresh my knowledge of embedded systems and C++ programming. 😊

# Hardware

* ESP32-CAM
* L298N Motor Drive Module
* HC-SR501 PIR Sensor
* Three 18650 3.7V Li-ion Batteries
* Battery Case
* Power Switch
* Acrylic Platform
* Four Motors With Wheels
* Cables, Screws And Other Accessories

I bought parts from Allegro, Temu and [LAFVIN](https://lafvintech.com/) store.
They provide learning kits and other stuff.

Everything costed me 210PLN (~55$).

# Software

To program the robot, I used the [PioArduino](https://github.com/pioarduino) extension for Visual Studio Code.
It performed remarkably well in terms of project setup and support for Arduino dependencies.
Thanks to its incremental compilation, it was significantly faster than the Arduino IDE for building the project.

![Visual Studio Code](studio_code.png)

The initial code installed on my ESP32 chip included a basic HTTP server with a console dashboard.
I decided to enhance it by using WebSockets as the primary communication protocol
and migrating the console to Cloudflare's static hosting.

The complete code is available [here](https://github.com/PrzemyslawSwiderski/arduino-playground).

The repository contains the following directories:

- `orzel7`: Contains the C++ code installed on the ESP chip
- `web-console`: Hosts the static Web Console resources

## Features

The main features I added are as follows:

* WiFi connection with the possibility to switch modes between Client and Access Point modes.
* Camera video streaming with dynamic FPS.
* Possibility to change video brightness, quality, size and others.
* Joystick to control the robot remotely.
* LED light control.
* Sleep mode to save the battery power for the WiFi client mode.
* PIR sensor movement detection and notifications sending.
* Way to retrieve basic heap usage and clients connected from the web console.
* Internet access.

# Result

<video src="roaming_around.mp4" class="markdown-img" controls>Result Vid</video>

As you can see above, the robot is working fine.
Responsiveness is pretty good, and the user is able to steer it by using a web HTML page on desktop or Android. 😎

# AI Support Reflections

I vividly recall the challenges of writing low-level code for embedded systems during my studies.
To simplify the process this time, I turned to AI assistants for support.
I primarily used [Grok](https://x.com/i/grok), with occasional help from ChatGPT and DeepSeek to cross-check answers.

These tools significantly speeded up my implementation.
Without AI assistance, I would have spent hours scouring GitHub or Google for code snippets.
What took about a month with AI would have likely taken two to three times longer without it.

The AI was particularly valuable for web console development.
Using simple prompts, I generated a complete HTML site and, with minor tweaks,
integrated it with my device via a WebSocket endpoint.

One challenge I faced was that the generated code didn’t compile with `arduino-esp32` version 3.2.
I had to manually adjust it to make it compatible, likely because version 3.x is relatively new,
and the AI models had not fully adapted to its codebase.

Despite this issue, the overall experience was excellent.
Beyond generating functional code, the AI provided clear explanations of specific lines and features,
eliminating the need to dig through documentation.
The AI assistant drastically reduced the initial learning curve,
making the process much smoother 😺.
