"use strict";(this.webpackChunkpswidersk_page=this.webpackChunkpswidersk_page||[]).push([[9607],{9607:e=>{e.exports='<ul><li><a href="/posts/deepseek-local-setup#intro"> Intro</a></li><li><a href="/posts/deepseek-local-setup#open-webui-ollama-and-cpu-setup"> Open WebUI, Ollama and CPU setup</a></li><li><a href="/posts/deepseek-local-setup#gpu-setup"> GPU setup</a></li><li><a href="/posts/deepseek-local-setup#conclusion"> Conclusion</a></li></ul><h2>Intro <a id="intro" href="/posts/deepseek-local-setup#intro" class="anchor-link">🔗</a></h2><p>The DeepSeek R1 LLM model was <strong><a href="https://api-docs.deepseek.com/news/news250120">released</a></strong> this week.</p><p>It seems to be a revolutionary development step in AI.</p><p>In this post I will focus on its biggest advantage (IMHO 😊) -&gt;\nbecause it is open source, everyone can download it, use locally, offline and for free.</p><p>Some time ago I created a little home lab with a Tiny Dell OptiPlex 3050.\nBelow, I will describe how I started my own ChatGPT-like service 😉.</p><h2>Open WebUI, Ollama and CPU setup <a id="open-webui-ollama-and-cpu-setup" href="/posts/deepseek-local-setup#open-webui-ollama-and-cpu-setup" class="anchor-link">🔗</a></h2><p>To make the job easier I used pretty cool <a href="https://docs.openwebui.com/">Open WebUI</a> project.\nIt is basically an application which lets the user install and use different LLMs.\nThanks to Docker images, anyone can run it locally pretty easily.</p><p>For my homelab I am using the Docker compose to manage services as a code.\nOpen WebUI\'s Docker compose setup is described\n<strong><a href="https://docs.openwebui.com/getting-started/quick-start/#example-docker-composeyml">here</a></strong>,\nso what I initially did was adding the following service to my <code>compose.yaml</code>:</p><pre><code class="language-yaml">(...)\nopen-web-ui:\n  container_name: &quot;open-web-ui&quot;\n  image: &quot;ghcr.io/open-webui/open-webui:latest-ollama&quot;\n  ports:\n    - &quot;8099:8080&quot;\n  volumes:\n    - &quot;../ai_data/:/app/backend/data&quot;\n</code></pre><p>I skipped the GPU parameters for now and wanted to run it on CPU for the smoke test purpose.</p><p>After hitting <code>docker compose up -d</code> on server, Docker started to build image.\nIt took some time because it was about 3GB big.</p><p>Once the image was build and container started, I was able to enter the web app at <code>http://localhost:8099</code>\naddress in my browser.</p><p>What I had to do after entering the home page was:</p><ol><li>Creating the web application admin user,</li><li>Downloading the smallest available DeepSeek R1 model (<code>deepseek-r1:1.5b</code> tag).\n   It could be done in Admin settings (&quot;Settings&quot; -&gt; &quot;Advanced&quot; -&gt; &quot;Models&quot; -&gt; &quot;Manage Models&quot; tab).</li><li>After the model was downloaded by <a href="https://ollama.com/library/deepseek-r1">Ollama</a>, it was ready to be used.</li></ol><p>To perform some test query I asked a simple question as belows:</p><p><img src="/pages/posts/deepseek-local-setup/DeepSeek_query.png" alt="DeepSeekquerypng" class="markdown-img" /></p><p>You can notice it took 20 min for the model to respond. Probably because I used the CPU only.</p><h2>GPU setup <a id="gpu-setup" href="/posts/deepseek-local-setup#gpu-setup" class="anchor-link">🔗</a></h2><p>IN_PROGRESS</p><h2>Conclusion <a id="conclusion" href="/posts/deepseek-local-setup#conclusion" class="anchor-link">🔗</a></h2><p>In about 2 hours I was able to start my private and limitless ChatGPT-like AI agent 😲.\nPretty impressive and quite scary considering current OpenAI and NVIDIA values.</p>'}}]);