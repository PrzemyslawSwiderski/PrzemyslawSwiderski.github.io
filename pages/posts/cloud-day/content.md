---
title: "AWS Cloud Day Warsaw"
created: 2024-09-19
---

<img class="responsive-img" src="/pages/posts/cloud-day/AWS-cloud-day.png" alt="Search Results"></img>

# Intro

On September 18th I went to the **[AWS Cloud Day Warsaw](https://aws.amazon.com/events/cloud-days/warsaw/)**.  
In this post, I would like to share some thoughts and notes about sessions I attended.

# Sessions

## 9:00 AM - Enhancing customer experience with generative AI

Overall session about Gen AI AWS services and their use cases.

Amazon Connect together with Amazon Q can be used to suggest actions to users.  
Can also assist the customers in real time.

Interesting that [Traeger](https://www.traeger.com/) grill company uses those services to deliver a premier customer
experience.  
It wasn't said how exactly though.

## 10:00 AM - AWS Cloud Day Warsaw Main Keynote

Initial welcoming hour-long session with multiple presentations.

It took Moderna only two days to find the proper COVID vaccine model by using AWS LLM.

AWS offers Nvidia GPUs up to 20 exaflops.

[Project Ceiba](https://aws.amazon.com/nvidia/project-ceiba/) -> 414 exaflops.

There are GenAI-specific chips.

Luxmed is using AWS Sagemaker for risk modeling and cost calculation.

Luxmed Insurance is using GenAI to classify data.

Not sure why but 3 presenters spoke in Polish.
It was not very hospitable for the non-polish speaking participants 😕.

## 11:15 AM - Successful GenAI Implementation - Enterprise Perspective

Chaos Gears and Clariant, GenAI
successful [implementations](https://aws.amazon.com/solutions/case-studies/clariant-generative-ai/).

Some technical issues at the beginning.

Clariant uses AI to validate chemical formulas.

Testing is everything. Metrics, not the developer's feelings, are crucial to verify which solution/model is better.

Trash in, trash out. Sanitize the input data first before teaching the model.

**Team Topologies** book recommendation.

## 12:00 PM - Breaking Out to a New Developer Productivity: Amazon Q and Beyond

Two people from PrivatBank.

After war in Ukraine broke out, they were forced to migrate the infrastructure to AWS.  
Amazon Q Developer supports developers across the whole SDLC. It can save development time.

Q can scan not only the repositories but resources (IAM, RDS) in AWS account to support development.  
It was very helpful with Java migration.

**Drawbacks**:

* Sometimes it takes 10 seconds for the hints to appear (in Ukraine).
* Security scans on large projects are slow.
* Needs more precise prompts, less forgiving than ChatGPT.

Due to law restrictions, SLA includes that the code must stay internally in the bank's AWS account.

The generic code model is extended with the one from the bank to provide proper autocompletion.

## 1:00 PM - Generative AI panel: Moving beyond the hype and realizing value

Very good Q&A session with 5 startup founders/investors.

GenAI is rule-changing in terms of time saved for initial development.  
Someone can create a solution in much less time than before.

The biggest investor's question: what will the GenAI startup do in 6 years?  
What is the plan if every week breaking changes are made in the AI field?  
Focusing on a niche is very important.

One founder had an idea to replace HR with AI for unbiased decision-making.

Goldman Sachs has about 200 petabytes of financial data.  
It is hard for smaller startups to compete with tech giants since they have much more resources and training data.

You don’t need ChatGPT if the problem can be solved with just 4 "if" statements in the code instead.

Your AI is as good as your data.

Some startups are not aware of the legal actions needed when training a model with customer data.  
They are risking costly lawsuits.

The current AI market state resembles the dot-com bubble.

All agreed that there are too high expectations (fueled by hype) about AI now.

## 3:00 PM - Platform engineering with Amazon EKS

Presentation by a senior solutions architect covering best practices and tips when dealing with AWS infrastructure.

Don’t boil the ocean. Less is more. Keep it simple 😏.

[Backstage.io](https://backstage.io/) for the ultimate visualization solution.

[Karpenter](https://karpenter.sh/) to reduce and manage cluster costs.

Instead of wiki as docs, use [Chatbox AI](https://chatboxai.app/en) to save time.

## 4:00 PM - Build without limits: lessons from using AI-powered developer tools

[Ricardo Sueiras](https://community.aws/@ricsueaws) sharing his experience with Amazon Q.

AI saves time when writing repetitive and boilerplate code.

Always add version to prompt (e.g. Spring Boot) to help find the solution.

Generated code can be insecure. Trust, but always verify.

AI will not replace developers yet (at least for the next 10 years 😅).

Too bad the demo did not work 😞 and Amazon Q had some connectivity issues.

## 5:00 PM - How security teams can strengthen security using generative AI

Two security advisors from AWS.

When creating solutions with GenAI, we should be aware
of [Hallucinations](https://en.wikipedia.org/wiki/Hallucination_(artificial_intelligence)).  
These can result in responses that are authoritative but wrong.

They created a chatbot that scans AWS services and finds potential loopholes.  
It suggests solutions and good practices in response.

# Conclusion

I think it was a very productive day, and besides the stickers and pens 😊, I gained valuable knowledge about AWS GenAI
services.

There were some technical issues, but overall, the organization was good, and I hope there will be more sessions like
that in Poland in the future.

When working remotely, it's refreshing to meet and talk with IT professionals in person.

P.S. This post was redacted with GenAI 😉
