# Lab 12 — Immutable Fraction Class

| | |
|---|---|
| **Week** | 13 |
| **Textbook** | Liang — **Chapters 9–10** (Class Abstraction, Immutable Objects) |
| **Time budget** | 60 minutes |
| **Points** | 100 |

---

## Learning Objectives

1. Design an immutable class using `final` fields and no setters.
2. Normalise object state inside the constructor so that no invalid object can exist.
3. Reject invalid arguments by throwing `IllegalArgumentException`.
4. Write a `private static` helper method and justify its visibility.
5. Override `toString` and `equals` correctly.
6. Write a static factory method that builds an object from text.

---

## Background

**Immutability.** Both fields are `final`, there are no setters, and every arithmetic method
returns a **new** `Fraction`:

```java
Fraction half = new Fraction(1, 2);
half.add(new Fraction(1, 4));    // returns 3/4 -- and DISCARDS it
System.out.println(half);        // still 1/2: the object was never modified
```

This is the same design used by `String`, `Integer`, and `LocalDate` in the Java library. An
immutable object cannot be corrupted by a caller, and it is safe to share freely. The test driver
verifies that `add` leaves the receiver unchanged.

**Normalisation in the constructor.** Every fraction is stored in exactly one canonical form, so
that comparison is trivial afterwards. Three rules, applied in order:

1. A zero denominator is impossible — throw `IllegalArgumentException("Denominator cannot be zero")`.
2. The sign lives in the numerator: `1/-2` is stored as `-1/2`.
3. Reduce by the greatest common divisor: `6/8` is stored as `3/4`.

Because normalisation happens once, in the constructor, `equals` needs only to compare two `int`
pairs — no cross-multiplication. This is the payoff for enforcing invariants at construction time.

Take care with `gcd(0, 5)`: the mathematical gcd is 5, so `0/5` reduces to `0/1`. Guard against a
gcd of 0, which would divide by zero.

**Arithmetic.**

| Operation | Formula |
|---|---|
| `a/b + c/d` | `(a*d + c*b) / (b*d)` |
| `a/b - c/d` | `(a*d - c*b) / (b*d)` |
| `a/b * c/d` | `(a*c) / (b*d)` |
| `a/b ÷ c/d` | `(a*d) / (b*c)` |

None of these need to reduce the result themselves — pass the raw values to the constructor and
let it normalise.

**`equals` must accept `Object`.** Writing `public boolean equals(Fraction other)` *overloads*
rather than *overrides*, and the inherited reference comparison is used instead. Test with
`instanceof`, then cast.

---

## Instructions

| Step | Member | Requirement |
|---|---|---|
| 1 | Constructor | Throw on a zero denominator; normalise the sign; reduce |
| 2 | `private static gcd(int, int)` | Euclid on absolute values; private because it is an implementation detail |
| 3 | `getNumerator`, `getDenominator` | Return the normalised fields |
| 4–6 | `add`, `subtract`, `multiply` | Return a **new** `Fraction` |
| 7 | `divide` | Throw `IllegalArgumentException("Cannot divide by zero")` when the other numerator is 0 |
| 8 | `doubleValue()` | Cast before dividing, or the result truncates |
| 9 | `toString()` | `"3/4"`, but `"5"` when the denominator is 1 |
| 10 | `equals(Object)` | `instanceof`, cast, compare both fields; `false` for `null` |
| 11 | `static parse(String)` | `"3/4"` → 3/4; `"5"` → 5/1; use `split("/")` and `Integer.parseInt` |
| 12 | `Lab12FractionDemo` | Demonstrate every operation |

---

## Commenting Standard (20 points)

```java
// WEAK
if (denominator < 0) { numerator = -numerator; denominator = -denominator; } // fix sign

// STRONG
// Canonical form keeps the sign in the numerator, so that -1/2 and 1/-2 produce
// identical field values and equals() can compare the two ints directly.
if (denominator < 0) { numerator = -numerator; denominator = -denominator; }
```

---

## Compile, Run, and Test

```bash
bash tools/run_lab.sh lab12          # 28 official checks
python3 tools/comment_check.py labs/lab12
```

---

## Grading Rubric

| Criterion | Points |
|---|---|
| All 28 checks pass | 60 |
| Line comments explain every statement and every invariant | 20 |
| Fields `final`, no setters, no mutation, exceptions thrown correctly | 10 |
| Committed and pushed on time | 10 |

---

## Submission Checklist

- [ ] `bash tools/run_lab.sh lab12` reports 28 of 28.
- [ ] The check "add() did not modify the original object" passes.
- [ ] `new Fraction(3, 0)` throws `IllegalArgumentException`.
- [ ] `new Fraction(4, 2)` prints `2`, not `2/1`.

```bash
git add . && git commit -m "Lab 12 complete - all 28 checks passing" && git push
```

---

## Stretch Goal

Implement `public int compareTo(Fraction other)` returning a negative number, zero, or a positive
number, and use it to order an array of fractions. Then override `hashCode` so that equal
fractions produce equal hash codes — the contract that `equals` implicitly commits you to.

---

## Troubleshooting

| Symptom | Cause | Fix |
|---|---|---|
| `cannot assign a value to final variable` | Assigned a field outside the constructor | Compute into local variables, assign each field once |
| `1/2 equals 2/4` fails | Reduction missing | Divide both parts by the gcd in the constructor |
| `equals("1/2")` returns true | Compared `toString` values | Compare fields after an `instanceof` test |
| `ArithmeticException: / by zero` | `gcd` returned 0 for `0/5` | Guard the zero case in the helper |
| `add` changed the receiver | Assigned to `this.numerator` | Return a new `Fraction` instead |

