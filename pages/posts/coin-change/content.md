---
title: "Coin Change Problem"
created: 2025-02-14
readTime: "14 mins"
---

## About

Recently, I encountered some
interesting [problem](https://www.hackerrank.com/challenges/coin-change/problem?isFullScreen=true)
on [HackerRank](https://www.hackerrank.com/)
platform.

The main task is to:

> Given an amount and the denominations of coins available, determine how many ways change can be made for amount.
> There is a limitless supply of each coin type.

I will try to explain possible solution to this problem.

## Solution

I had some ideas how to solve it by using 2D helper arrays.
When I tried to find a similar solution to validate my approach, I found even simpler one:

```python
def get_ways(n, c):
    count = [0] * (n + 1)
    count[0] = 1
    for coin in c:
        for i in range(coin, n + 1):
            count[i] += count[i - coin]
    return count[n]
```

It is quite impressive that we can solve this issue with only 6 lines of Python code.
I decided to debug this code in order to have a better view about how it works.

So, assuming:

- `n` -> amount for which we will have to find number of **ways**
- `c` -> input array with denominations
- `count` -> helper 1D array which will store **ways** number per specific amount

for the input `n=6` and `c=[2,3]` we will get the following `count` content:

```text
amount=0 ways=1
amount=1 ways=0
amount=2 ways=1
amount=3 ways=1
amount=4 ways=1
amount=5 ways=1
amount=6 ways=2
```

As we can see above, for the `n=6` the change can be created in 2 ways.
I am guessing those would be `[2,2,2]` and `[3,3]` but I improved code a little to print possible sets as well:

```python
def get_ways(n, c):
    counts = []
    for i in range(n + 1):
        counts.append({"ways_count": 0, "coin_sets": []})
    counts[0]["ways_count"] = 1
    counts[0]["coin_sets"] = [[]]
    for coin in c:
        for amount in range(coin, n + 1):
            prev_count = counts[amount - coin]
            counts[amount]["ways_count"] += prev_count["ways_count"]
            coin_sets = counts[amount]["coin_sets"]
            for coins_set in prev_count["coin_sets"]:
                prev_extended = [*coins_set, coin]
                coin_sets.append(prev_extended)

    for amount, c in enumerate(counts):
        ways = c["ways_count"]
        coin_sets = c["coin_sets"]
        print(f"{amount=} {ways=} {coin_sets=}")

    return counts[n]["ways_count"]
```

The whole code can be found
**[here](https://github.com/PrzemyslawSwiderski/algorithms-playground/blob/main/problems/coin-change/script.py)**.

The important bits are `count[0]` initial case assignments. We are specifying the base for all coins. It will only be
used in the first per coin iteration. `count[0]["ways_count"]` is having `1` value because when
`amount = coin denomination` we can always return single coin as a change.

The `counts[0]["coin_sets"]` is being set to `[[]]` so that for the initial coin iteration it will be extended
with the current coin value inside of `for coins_set in prev_count["coin_sets"]:` loop.

New output:

```text
amount=0 ways=1 coin_sets=[[]]
amount=1 ways=0 coin_sets=[]
amount=2 ways=1 coin_sets=[[2]]
amount=3 ways=1 coin_sets=[[3]]
amount=4 ways=1 coin_sets=[[2, 2]]
amount=5 ways=1 coin_sets=[[2, 3]]
amount=6 ways=2 coin_sets=[[2, 2, 2], [3, 3]]
```

Now it looks cleaner as we have all change sets listed.

We can also notice that following amounts are using previous calculations.

For example for the coin `2`:

<pre>
amount=4 ways=1 coin_sets=[<b>[2, 2]</b>]
(...)
amount=6 ways=2 coin_sets=[[2, 2, <b>2</b>], (...)
</pre>

We are extending previous change retrieved from `amount=4`.

Other interesting example:

`n=10, c=[2,3,5]`

```text
amount=0 ways=1 coin_sets=[[]]
amount=1 ways=0 coin_sets=[]
amount=2 ways=1 coin_sets=[[2]]
amount=3 ways=1 coin_sets=[[3]]
amount=4 ways=1 coin_sets=[[2, 2]]
amount=5 ways=2 coin_sets=[[2, 3], [5]]
amount=6 ways=2 coin_sets=[[2, 2, 2], [3, 3]]
amount=7 ways=2 coin_sets=[[2, 2, 3], [2, 5]]
amount=8 ways=3 coin_sets=[[2, 2, 2, 2], [2, 3, 3], [3, 5]]
amount=9 ways=3 coin_sets=[[2, 2, 2, 3], [3, 3, 3], [2, 2, 5]]
amount=10 ways=4 coin_sets=[[2, 2, 2, 2, 2], [2, 2, 3, 3], [2, 3, 5], [5, 5]]
```

Coin `5` result:

```text
amount=5 ways=2 coin_sets=[[2, 3], [5]]
```

uses:

```text
amount=0 ways=1 coin_sets=[[]]
```

and since there is `[[]]` array inside, `[5]` change set is added.

For the `amount=6`:

```text
amount=6 ways=2 coin_sets=[[2, 2, 2], [3, 3]]
```

the following is considered:

```text
amount=1 ways=0 coin_sets=[]
```

as a result no `5` coin is added to `coin_sets`.

For the `amount=7`:

```text
amount=7 ways=2 coin_sets=[[2, 2, 3], [2, 5]]
```

we are reusing:

```text
amount=2 ways=1 coin_sets=[[2]]
```

so that `[2, 5]` is appended

and so on...

## Summary

Adding printing of the coin sets to the initial code helped me understand shorter solution.

Other problems solutions using [Dynamic Programming](https://en.wikipedia.org/wiki/Dynamic_programming)
or [Memoization](https://en.wikipedia.org/wiki/Memoization) technique can also be reworked in a similar way
so that they can be easier _consumed_ by programmers 😄
