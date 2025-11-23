---
title: "Slicing in Python"
created: 2024-11-30
readTime: "7 mins"
---

## About

Python's slicing is tricky and I always forget how to use it after a while 😊.

I asked ChatGPT to provide a consolidated guide with notes and examples for easy reference.

## Content

### Basic Slicing Syntax

The general form of slicing is:

```python
sequence[start:stop:step]
```

- **`start`**: The index where the slice begins (inclusive).
- **`stop`**: The index where the slice ends (exclusive).
- **`step`**: The step size or interval between indices. It defaults to `1` if not specified.

---

### Key Points

#### 1. Default Values:

- If **`start`** is omitted, it defaults to `0` (start of the sequence).
- If **`stop`** is omitted, it defaults to the length of the sequence (end of the sequence).
- If **`step`** is omitted, it defaults to `1` (move to the next element).

**Example:**

```python
lst = [0, 1, 2, 3, 4, 5]
print(lst[:3])  # [0, 1, 2] (equivalent to lst[0:3])
print(lst[2:])  # [2, 3, 4, 5] (equivalent to lst[2:len(lst)])
print(lst[::2])  # [0, 2, 4] (every 2nd element)
```

#### 2. Negative Indices:

You can use negative indices to count from the end of the sequence:

- `-1` refers to the last element,
- `-2` refers to the second-to-last element, and so on.

**Example:**

```python
lst = [0, 1, 2, 3, 4, 5]
print(lst[-1])  # 5 (last element)
print(lst[-3:])  # [3, 4, 5] (slice from the third-to-last element to the end)
print(lst[:-2])  # [0, 1, 2, 3] (slice up to the second-to-last element)
```

#### 3. Negative Step (`[::-1]`):

A negative step reverses the direction of slicing:

- `[::-1]` reverses the entire sequence.
- `start` and `stop` positions adjust accordingly when the step is negative.

**Example:**

```python
lst = [0, 1, 2, 3, 4, 5]
print(lst[::-1])  # [5, 4, 3, 2, 1, 0] (entire sequence reversed)
print(lst[4:1:-1])  # [4, 3, 2] (reverse slice from index 4 to index 2)
```

#### 4. Out-of-Bounds Indices:

If **`start`** or **`stop`** indices are out of the range of the sequence, Python will gracefully handle it without
raising an error. It adjusts to the sequence's length.

**Example:**

```python
lst = [0, 1, 2, 3]
print(lst[:10])  # [0, 1, 2, 3] (stop index exceeds length, no error)
print(lst[-10:])  # [0, 1, 2, 3] (start index is less than 0, adjusts to 0)
```

#### 5. Combining Features:

You can mix positive and negative indices and steps to create complex slices.

**Example:**

```python
lst = [0, 1, 2, 3, 4, 5]
print(lst[5:1:-1])  # [5, 4, 3, 2] (reverse slice from index 5 to index 2)
print(lst[:-3:-1])  # [5, 4] (reverse slice starting at the end, stopping before -3)
```

---

### Practical Use Cases of Slicing

#### Reversing a Sequence:

```python
lst = [1, 2, 3, 4]
print(lst[::-1])  # [4, 3, 2, 1]
```

#### Extracting Odd or Even Indexed Elements:

```python
lst = [10, 20, 30, 40, 50]
print(lst[::2])  # [10, 30, 50] (even-indexed elements)
print(lst[1::2])  # [20, 40] (odd-indexed elements)
```

#### Custom Ranges:

```python
lst = [0, 1, 2, 3, 4, 5, 6, 7]
print(lst[2:6])  # [2, 3, 4, 5] (from index 2 to 5)
print(lst[::3])  # [0, 3, 6] (every 3rd element)
```

#### Complex Manipulation:

```python
lst = [0, 1, 2, 3, 4, 5]
print(lst[-1:1:-2])  # [5, 3] (start from the end, step backward by 2)
```

---

By mastering slicing, you can write concise, elegant Python code for many common tasks.

### Combining slices

You can **combine (or join)** slicing operations to achieve more complex results, but slicing itself does not provide an
explicit "joining" mechanism. However, you can apply one slice operation to the result of another slice. This
effectively "chains" the slicing.

Here’s how it works:

---

### Joining Slices: Example

When you apply slicing sequentially, the first slicing operation creates a new sequence, and the second slicing operates
on that result.

```python
lst = [0, 1, 2, 3, 4, 5, 6]

# First slice extracts a sublist
sublist = lst[1:5]  # [1, 2, 3, 4]

# Second slice operates on the sublist
result = sublist[::2]  # [1, 3]
print(result)  # Output: [1, 3]

# Or directly combine
result = lst[1:5][::2]  # [1, 3]
print(result)  # Output: [1, 3]
```

---

### Important Notes

1. **Intermediate Sequence**: Each slicing creates a new sequence. The second slicing operates on the new sequence, not
   the original.

- `lst[1:5]` creates `[1, 2, 3, 4]`,
- then `[::2]` operates on `[1, 2, 3, 4]`.

2. **Efficiency**: While combining slices is syntactically valid, performing multiple slicing operations can be less
   efficient for large sequences because intermediate sequences are created.

---

### Alternative: Combine Slicing Logic

If possible, calculate the combined slicing logic to avoid intermediate steps.

**Example:**

```python
lst = [0, 1, 2, 3, 4, 5, 6]
# Combining logic
result = lst[1:5:2]  # [1, 3]
print(result)  # Output: [1, 3]
```

- The combined slice `[1:5:2]` skips the intermediate sublist, extracting elements directly from the original list.

---

### When Joining Is Practical

1. **Sequential Slicing**:
   Useful when intermediate results need to be reused or understood independently.

   ```python
   lst = [0, 1, 2, 3, 4, 5]
   sublist = lst[2:5]      # [2, 3, 4]
   result = sublist[::-1]  # [4, 3, 2]
   print(result)           # Output: [4, 3, 2]
   ```

2. **Direct Slicing**:
   Preferable when the final result can be directly extracted:
   ```python
   lst = [0, 1, 2, 3, 4, 5]
   result = lst[4:1:-1]  # [4, 3, 2]
   print(result)         # Output: [4, 3, 2]
   ```

---

### Summary

- You can join slicing by applying slices sequentially (e.g., `lst[a:b][c:d]`).
- When possible, combine slicing logic into a single operation to improve efficiency (e.g., `lst[a:b:c]`).
- Choose the approach that best fits the problem and your need for intermediate results or clarity.

