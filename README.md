# Game of Life (Terminal)

A small, fun Java implementation of **Conway's Game of Life** that runs in your terminal using the [Lanterna](https://github.com/mabe02/lanterna) library.

---

## Overview

This project renders Game of Life generations in the terminal. It ships with several preset starting patterns and updates the grid automatically every second.

### Included starting patterns

* `BLOCK`
* `BLINKER`
* `GLIDER`
* `GOSPER_GLIDER_GUN`
* `R_PENTOMINO`

---

## Requirements

* Java 17 (or later)
* Lanterna (add the JAR to your classpath)

---

## Quick Start

1. Clone the repo:

```bash
git clone https://github.com/yourusername/game-of-life-terminal.git
cd game-of-life-terminal
```

2. Compile (adjust lanterna JAR filename/version as needed):

```bash
javac -cp lanterna-3.1.1.jar com/project/game/*.java
```

3. Run:

```bash
java -cp .:lanterna-3.1.1.jar com.project.game.GameOfLife
```

> On Windows use `;` instead of `:` in the classpath.

---

## How it works (brief)

* The `Logic` class holds three grids: `prevGen`, `currGen`, and `grid`.
* Each tick (every second) the program computes the next generation using Conway's rules:

    * Any live cell with 2 or 3 neighbors survives.
    * Any dead cell with exactly 3 neighbors becomes alive.
    * Otherwise, a cell dies or remains dead.
* Output is drawn using Lanterna characters (`■` for alive, `.` for dead).

---

## Tips & Ideas

* Increase `ROW` and `COL` for a larger board (beware terminal size).
* Replace the fixed delay with a configurable argument or input key controls.
* Add an option to randomize the first generation.
* Export generations to a file or add pause/step controls for debugging.

---

## License

MIT — feel free to fork, modify, and learn.

---

Made for fun 🎲 — tweak patterns, sizes, and rules and enjoy watching life evolve!
