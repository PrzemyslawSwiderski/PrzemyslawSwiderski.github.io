"use strict";(this.webpackChunkpswidersk_page=this.webpackChunkpswidersk_page||[]).push([[4727],{4727:e=>{e.exports='<p><img class="responsive-img" src="/pages/posts/cloud-day/AWS-cloud-day.png" alt="Search Results"></img></p><h1>Intro</h1><p>On September 18th I went to the <strong><a href="https://aws.amazon.com/events/cloud-days/warsaw/">AWS Cloud Day Warsaw</a></strong>.<br />\nIn this post, I would like to share some thoughts and notes about sessions I attended.</p><h1>Sessions</h1><h2>9:00 AM - Enhancing customer experience with generative AI</h2><p>Overall session about Gen AI AWS services and their use cases.</p><p>Amazon Connect together with Amazon Q can be used to suggest actions to users.<br />\nCan also assist the customers in real time.</p><p>Interesting that <a href="https://www.traeger.com/">Traeger</a> grill company uses those services to deliver a premier customer\nexperience.<br />\nIt wasn\'t said how exactly though.</p><h2>10:00 AM - AWS Cloud Day Warsaw Main Keynote</h2><p>Initial welcoming hour-long session with multiple presentations.</p><p>It took Moderna only two days to find the proper COVID vaccine model by using AWS LLM.</p><p>AWS offers Nvidia GPUs up to 20 exaflops.</p><p><a href="https://aws.amazon.com/nvidia/project-ceiba/">Project Ceiba</a> -&gt; 414 exaflops.</p><p>There are GenAI-specific chips.</p><p>Luxmed is using AWS Sagemaker for risk modeling and cost calculation.</p><p>Luxmed Insurance is using GenAI to classify data.</p><p>Not sure why but 3 presenters spoke in Polish.\nIt was not very hospitable for the non-polish speaking participants 😕.</p><h2>11:15 AM - Successful GenAI Implementation - Enterprise Perspective</h2><p>Chaos Gears and Clariant, GenAI\nsuccessful <a href="https://aws.amazon.com/solutions/case-studies/clariant-generative-ai/">implementations</a>.</p><p>Some technical issues at the beginning.</p><p>Clariant uses AI to validate chemical formulas.</p><p>Testing is everything. Metrics, not the developer\'s feelings, are crucial to verify which solution/model is better.</p><p>Trash in, trash out. Sanitize the input data first before teaching the model.</p><p><strong>Team Topologies</strong> book recommendation.</p><h2>12:00 PM - Breaking Out to a New Developer Productivity: Amazon Q and Beyond</h2><p>Two people from PrivatBank.</p><p>After war in Ukraine broke out, they were forced to migrate the infrastructure to AWS.<br />\nAmazon Q Developer supports developers across the whole SDLC. It can save development time.</p><p>Q can scan not only the repositories but resources (IAM, RDS) in AWS account to support development.<br />\nIt was very helpful with Java migration.</p><p><strong>Drawbacks</strong>:</p><ul><li>Sometimes it takes 10 seconds for the hints to appear (in Ukraine).</li><li>Security scans on large projects are slow.</li><li>Needs more precise prompts, less forgiving than ChatGPT.</li></ul><p>Due to law restrictions, SLA includes that the code must stay internally in the bank\'s AWS account.</p><p>The generic code model is extended with the one from the bank to provide proper autocompletion.</p><h2>1:00 PM - Generative AI panel: Moving beyond the hype and realizing value</h2><p>Very good Q&amp;A session with 5 startup founders/investors.</p><p>GenAI is rule-changing in terms of time saved for initial development.<br />\nSomeone can create a solution in much less time than before.</p><p>The biggest investor\'s question: what will the GenAI startup do in 6 years?<br />\nWhat is the plan if every week breaking changes are made in the AI field?<br />\nFocusing on a niche is very important.</p><p>One founder had an idea to replace HR with AI for unbiased decision-making.</p><p>Goldman Sachs has about 200 petabytes of financial data.<br />\nIt is hard for smaller startups to compete with tech giants since they have much more resources and training data.</p><p>You don’t need ChatGPT if the problem can be solved with just 4 &quot;if&quot; statements in the code instead.</p><p>Your AI is as good as your data.</p><p>Some startups are not aware of the legal actions needed when training a model with customer data.<br />\nThey are risking costly lawsuits.</p><p>The current AI market state resembles the dot-com bubble.</p><p>All agreed that there are too high expectations (fueled by hype) about AI now.</p><h2>3:00 PM - Platform engineering with Amazon EKS</h2><p>Presentation by a senior solutions architect covering best practices and tips when dealing with AWS infrastructure.</p><p>Don’t boil the ocean. Less is more. Keep it simple 😏.</p><p><a href="https://backstage.io/">Backstage.io</a> for the ultimate visualization solution.</p><p><a href="https://karpenter.sh/">Karpenter</a> to reduce and manage cluster costs.</p><p>Instead of wiki as docs, use <a href="https://chatboxai.app/en">Chatbox AI</a> to save time.</p><h2>4:00 PM - Build without limits: lessons from using AI-powered developer tools</h2><p><a href="https://community.aws/@ricsueaws">Ricardo Sueiras</a> sharing his experience with Amazon Q.</p><p>AI saves time when writing repetitive and boilerplate code.</p><p>Always add version to prompt (e.g. Spring Boot) to help find the solution.</p><p>Generated code can be insecure. Trust, but always verify.</p><p>AI will not replace developers yet (at least for the next 10 years 😅).</p><p>Too bad the demo did not work 😞 and Amazon Q had some connectivity issues.</p><h2>5:00 PM - How security teams can strengthen security using generative AI</h2><p>Two security advisors from AWS.</p><p>When creating solutions with GenAI, we should be aware\nof <a href="https://en.wikipedia.org/wiki/Hallucination_(artificial_intelligence)">Hallucinations</a>.<br />\nThese can result in responses that are authoritative but wrong.</p><p>They created a chatbot that scans AWS services and finds potential loopholes.<br />\nIt suggests solutions and good practices in response.</p><h1>Conclusion</h1><p>I think it was a very productive day, and besides the stickers and pens 😊, I gained valuable knowledge about AWS GenAI\nservices.</p><p>There were some technical issues, but overall, the organization was good, and I hope there will be more sessions like\nthat in Poland in the future.</p><p>When working remotely, it\'s refreshing to meet and talk with IT professionals in person.</p><p>P.S. This post was redacted with GenAI 😉</p>'}}]);