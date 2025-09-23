---
title: "My Ubuntu Experience"
created: 2025-09-23
---

{toc.placeholder}

## Intro

My laptop was so outdated it was practically a museum exhibit, so I swapped it for a shiny new model that
doesn't sound like a jet engine 🙂.
The new model is a 14" [Lafité](https://www.pcspecialist.co.uk/notebooks/lafite-pro-V-14M/) from PCSpecialist.
Like a regular IT nerd, I took an opportunity to buy it without an operating system and install Ubuntu myself.

I will write about positive and negative sides of using it every day.

## Advantages

### Installation

The whole installation process went pretty fluent. It took about 10 minutes to complete.
Actually, the most time-consuming part was finding a free pen drive I could use for the bootable device
and uploading the system into it.

### Hardware support

All the laptop's components Wi-Fi, touchpad, sound, webcam worked out of the box, which surprised me pleasantly.

Even external monitor speakers are connecting all the time. 
It was not the case in Windows when I had to reconnect HDMI cable manually for them to work properly.

### Price

One of positives is that I could simply download the latest Ubuntu entirely for free
from [**here**](https://ubuntu.com/download/desktop).
This way I saved about 100$ I would have to spend on Windows license.

### Faster Code Development

Last time I used Ubuntu was about 5 years ago when I worked at TomTom.
At that time, I already know it was better than Windows in terms of compilation speed.
After installing IntelliJ on my new Ubuntu setup and building some sample projects,
it is clear for me, I won't be coming back to Windows anytime soon 🤣.
Gradle build and test speeds are so much faster.
Building this entire blog project repo took about 30 seconds.
Before, I had to wait more than 5 minutes to complete it on Windows.

Because of different file system (ext4) and no background processes like Windows Defender,
IntelliJ project indexing process is also much faster.

### Configurability

Ubuntu outshines Windows in terms of customization and configurability.
It is possible to configure every pixel of the UI — keybindings, layout, animations, etc.
I am able to configure nearly everything via dotfiles (`.bashrc`, `.gitconfig` or `.config/monitors.xml`).

It is also easy to launch additional apps or scripts with "Startup Application Preferences" system util.
Thanks to that, I created some bash script which plays an internet radio automatically on system startup
with the VLC player.

Full `radio.sh` script:

```bash
#!/bin/bash
# Define radio stations
declare -A stations
stations=(
  [1]="https://livestreaming-node-4.srg-ssr.ch/srgssr/rsc_de/aac/96"
  [2]="https://media-ice.musicradio.com/ClassicFMMP3"
  [3]="https://centova87.shoutcastservices.com/proxy/revolution935/stream"
  [4]="https://stream.trance.ie/tpmixes"
  [5]="https://fr.ah.fm/live"
)
# Get user choice from script argument or default to 1
choice=${1:-1}
# Validate input and launch VLC
if [[ ${stations[$choice]} ]]; then
  echo "Launching VLC with stream #$choice..."
  vlc "${stations[$choice]}"
else
  echo "Invalid choice: '$choice'"
  echo "Choices:"
  echo "  1) Radio Swiss Classic (default)"
  echo "  2) Classic FM"
  echo "  3) Revolution 935"
  echo "  4) TrancePulse"
  echo "  5) AH.fm"
  exit 1
fi
```

I also recommend adding symbolic link to script in `/usr/local/bin/` with the following command:

```sh
sudo ln -s <FULL PATH TO radio.sh> /usr/local/bin/radio.sh
```

This way it is possible to launch the radio directly from the terminal with a `radio` command.

### System and application updates

On my corporate laptop I am using Windows, and sometimes system or application updates are triggered
without the user knowledge. It is pretty annoying and can interrupt daily work.

Updating Ubuntu is always triggered by user. It is also much faster.
Suggested updates are downloaded and installed in background and system restart is always optional.
Moreover, Ubuntu can use different package managers like apt (shipped) snap or flatpak.
They are allowing the user to update applications in a single command, pin specific versions or even build
packages from sources.

### Privacy

Ubuntu doesn't collect or send data by default.
You can fully inspect and modify all processes.

## Disadvantages

### Desktop Environment

The Windows system (like the name would suggest 🙂) has still better GUI support and reliability
for the window based applications. I recognise that Ubuntu GNOME desktop environment got better
compared to 7 years ago, but it still has got issues. For example in IntelliJ, I am randomly experiencing
flickering code tab names in UI. Some other application windows are also getting weird font resizes
when changing the monitor display mode.

### Gaming

Some game developers like EA are still neglecting the Linux users. Games like Battlefield 1, DCS World or FIFA 23
are not even able to be started on Ubuntu system because of Kernel level anti cheat or other reasons.
This is quite disappointing and hopefully will change in the future.

## Conclusion