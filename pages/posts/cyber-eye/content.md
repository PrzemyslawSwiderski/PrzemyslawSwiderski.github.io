---
title: "👁️ Cyber Eye"
created: 2026-02-22
updated: 2026-09-22
readTime: "10 min"
---

**POST_IN_PROGRESS**

{toc.placeholder}

# Introduction

Reasons for the creation of my very own IP camera:

* open source, so the software can be shared with others
* prevent connection to shady remote servers
* cheaper price
* browser based Web UI
* possibility to mount the camera as VTX for drone 

# Hardware

* Luckfox Pico Mini B
* LuckFox Pico Camera SC3336 3MP
* LB-Link M8812EU2 WiFi module
* 9V → 5V Step-down Converter
* 2 x IPEX 5G 4 dBi antenna

# Software

**[Repository](https://github.com/PrzemyslawSwiderski/cyber-eye)**

# Schematics

```text
Drone Camera 9V socket GND -> Step Down Converter IN-
Drone Camera 9V socket V+ -> Step Down Converter IN+
Step Down Converter OUT+ -> WiFi module IN+
Step Down Converter OUT- -> WiFi module GND
WiFi module IN+ -> 
Luckfox IN+

```

# Results

<video src="cyber-eye-result.mp4" class="markdown-img" controls>Cyber Eye Result Video</video>


# Conclusion

