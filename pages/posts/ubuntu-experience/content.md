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

The whole installation process went pretty smoothly. It took about 10 minutes to complete.  
Actually, the most time-consuming part was finding a free pen drive I could use for the bootable device
and uploading the system onto it.

### Hardware support

All the laptop's components Wi-Fi, touchpad, sound, webcam worked out of the box, which surprised me pleasantly.

Even the external monitor speakers connect automatically every time.  
That wasn't the case on Windows, where I had to reconnect the HDMI cable manually for them to work properly.

### Price

One of positives is that I could simply download the latest Ubuntu entirely for free
from [**here**](https://ubuntu.com/download/desktop).
This way I saved about 100$ I would have had to spend on Windows license.

### Faster Code Development

The last time I used Ubuntu was about 5 years ago when I worked at TomTom.  
Even back then, I already knew it was better than Windows in terms of compilation speed.  
After installing IntelliJ on my new Ubuntu setup and building some sample projects,  
it's clear to me: I won't be coming back to Windows anytime soon 🤣.

Gradle build and test speeds are significantly faster.  
Building this entire blog project repo took about 30 seconds.  
Before, I had to wait more than 5 minutes to complete it on Windows.

Because of the different file system (ext4) and no background processes like Windows Defender,  
the IntelliJ project indexing process is also much faster.

### Configurability

Ubuntu outshines Windows in terms of customization and configurability.  
It's possible to tweak every pixel of the UI—keybindings, layout, animations, etc.  
I can configure nearly everything via dotfiles (`.bashrc`, `.gitconfig`, or `.config/monitors.xml`).

It's also easy to launch additional apps or scripts using the **Startup Applications** utility.  
Thanks to that, I created a bash script that plays an internet radio station automatically on system startup
via VLC.

Here's the full `radio.sh` script:

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

I also recommend adding symbolic link to the script in `/usr/local/bin/` with the following command:

```sh
sudo ln -s <FULL PATH TO radio.sh> /usr/local/bin/radio.sh
```

This way, you can launch the radio directly from the terminal with a simple `radio` command.

### System and application updates

On my corporate laptop, I use Windows, and sometimes system or application updates are triggered
without the user's knowledge. It's pretty annoying and can interrupt daily work.

Updating Ubuntu is always triggered by the user. It's also much faster.
Suggested updates are downloaded and installed in the background, and system restarts are always optional.

Moreover, Ubuntu supports different package managers like apt (default), snap, or flatpak.
They allow the user to update applications with a single command, pin specific versions,
or even build packages from source.

### Privacy

Ubuntu doesn't collect or send data by default.
You can fully inspect and modify all processes.

## Disadvantages

### Desktop Environment

Windows (like the name suggests 🙂) still has better GUI support and reliability for window-based applications.
I recognize that Ubuntu's GNOME desktop environment has improved compared to 7 years ago,
but it still has issues.

For example, in IntelliJ, I occasionally experience flickering tab names in the UI.
Some other application windows also show weird font resizing issues when switching display modes.

### Gaming

Some game developers like EA are still neglecting the Linux users. Games like Battlefield 1, DCS World or FIFA 23
can not even be launched on Ubuntu due to kernel-level anti-cheat or other limitations.
This is quite disappointing and hopefully will change in the future.

### Microsoft Office Apps Compatibility

One of the biggest drawbacks of switching to Ubuntu is the lack of native Microsoft Office support.
Yes, I can use the **web versions** of Word, Excel, and PowerPoint in the browser,
and they're fine for quick edits or light usage.
But for more advanced features—like working with large spreadsheets,
track changes in Word documents, or inserting complex charts—these web versions fall short.

I also tried using **LibreOffice** and **OnlyOffice**,
which are great open-source alternatives. However,
formatting often breaks when opening `.docx` or `.pptx` files created in Microsoft Office.
This can be a dealbreaker, especially when collaborating with colleagues or clients who use Office daily.

Installing the full Microsoft Office suite using **Wine** is technically possible,
but it's inconsistent and lacks proper support.
Outlook, in particular, doesn't behave well outside of Windows.

So if your workflow depends heavily on advanced Office features, be prepared for some trade-offs.

## Conclusion

Switching to Ubuntu on my new laptop has been a refreshing experience.
It boots faster, runs smoother, and gives me complete control over my development environment.
The ability to customize nearly every part of the system (from startup scripts to dotfiles) makes it
a developer's dream.

There are, of course, some drawbacks, especially in gaming and occasional desktop quirks,
but for my daily workflow, Ubuntu wins hands down.

If you're a developer or power user tired of the limitations and overhead of Windows,
Ubuntu is absolutely worth a try.