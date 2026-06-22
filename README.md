# Java DSA

This is where I keep all my data structures and algorithms practice in Java. I'm
self-taught, from Mumbai, currently grinding through Kunal Kushwaha's Java + DSA
bootcamp and a bunch of LeetCode on the side. The plan is to move to Germany for an
Ausbildung as Fachinformatiker Anwendungsentwicklung, so I'm trying to get my
fundamentals genuinely solid, not just memorize solutions.

Right now there are **206 Java files** in here covering basically every core topic,
plus interview prep checklists and some system design notes.

## How I actually use this repo

I don't just read and move on. I open a file, read the approach comment until I
actually get *why* it works, then close it and type the whole thing out myself from
scratch — in a new file or on paper. If I can't reproduce it, I didn't really learn
it. That's the whole point of writing the explanations the way I did (casual, like
I'm telling a friend), so future-me can re-learn fast.

Every file is a complete program with a `main()` and test cases, so I can just:

```bash
javac SomeProblem.java && java SomeProblem
```

## What's in here

### Core topics
| Topic | Files | What it covers |
|-------|-------|----------------|
| `basics/` | 4 | hello world, loops, patterns |
| `arrays/` | 21 | two sum, kadane, trapping rain water, 3sum, spiral matrix, next permutation... |
| `strings/` | 11 | palindromes, anagrams, KMP, zigzag, count and say... |
| `sorting/` | 8 | bubble, selection, insertion, merge, quick, counting, heap, radix |
| `binary-search/` | 9 | rotated array, koko, median of two arrays, peak element, sqrt... |
| `recursion/` | 13 | N-queens, sudoku, permutations, combination sum, rat in a maze, word search... |
| `stack/` | 11 | valid parens, min stack, largest rectangle, stock span, celebrity... |
| `queue/` | 10 | circular queue, deque, LRU cache, rotten oranges, sliding window max... |
| `linked-list/` | 12 | reverse, cycle detection, merge, palindrome, LRU from scratch, doubly linked... |
| `trees/` | 12 | traversals, level order, LCA, validate BST, serialize/deserialize, max path sum... |
| `heap/` | 6 | min/max heap from scratch, kth largest, merge k lists, median stream, top k... |
| `graphs/` | 13 | BFS, DFS, topo sort, Dijkstra, Bellman-Ford, Kruskal, Prim, islands, word ladder... |
| `trie/` | 3 | trie implementation, word search II, autocomplete |
| `dp/` | 18 | climbing stairs, knapsack, LCS, edit distance, coin change, burst balloons... |
| `greedy/` | 6 | activity selection, fractional knapsack, job sequencing, huffman, jump game... |
| `bitmanipulation/` | 6 | single number, count bits, power of two, reverse bits, missing number... |
| `math/` | 6 | GCD/LCM, sieve, fast exponentiation, modular arithmetic, count primes... |

### LeetCode (`leetcode/`)
37 files split into `easy/`, `medium/`, `hard/`. A bunch of these are the same
problems I already solved in a topic folder — in those cases I left a little pointer
file linking to the real solution instead of duplicating it.

### Interview prep (`interview-prep/`)
- `blind75/` — the Blind 75 checklist with links to where each one lives here
- `neetcode150/` — NeetCode 150 grouped by pattern, same idea
- `companywise/` — top-asked problems for Amazon, Google, Meta, Microsoft, Apple

### System design (`system-design/`)
My own notes (not textbook stuff): CAP theorem, load balancing, caching, sharding,
message queues, plus short case studies for a URL shortener, Twitter, and a chat
system. This is further-off for me but good to have the vocabulary.

## Folder structure

```
java-dsa-journey-/
├── basics/
├── arrays/
├── strings/
├── sorting/
├── binary-search/
├── recursion/
├── stack/
├── queue/
├── linked-list/
├── trees/
├── heap/
├── graphs/
├── trie/
├── dp/
├── greedy/
├── bitmanipulation/
├── math/
├── leetcode/
│   ├── easy/
│   ├── medium/
│   └── hard/
├── interview-prep/
│   ├── blind75/
│   ├── neetcode150/
│   └── companywise/
├── system-design/
└── projects/
```

## Resources I'm using
- **Kunal Kushwaha** — Java + DSA bootcamp (the main thing I'm following)
- **LeetCode** — daily practice
- **NeetCode 150** — for structured interview prep
- **Striver's SDE sheet** — when I want more problems on a topic

---

Still adding to this as I go. Some folders are deeper than others just because
that's the order I've been learning in. If a file's comments sound like a confused
student figured it out 5 minutes ago — yeah, that's the idea.
