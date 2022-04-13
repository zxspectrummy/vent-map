# Hyperthermal Venture
A case-study coding challenge based on https://adventofcode.com/2021/day/5


## Task
1. Draw class diagrams of the used data structures.
2. Write down how you came up with the solution.
3. Prepare to present and defend your path to the solution and the solution itself during the  interview.
4. Calculate at how many points do at least two lines overlap?
5. During the interview we will extend the quiz on the fly so you have the chance to present  your quiz solving skills on the spot.

## Documentation
[Presentation for the decision-making process](https://zxspectrummy.github.io/vent-map/)

## Getting Started

### Prerequisites
* Java 11

### Executing program
generate hashmap-based vent map
```
./gradlew -PmainClass=com.demo.base.OccupancyGridBuilder run
```

generate quadtree-based vent map
```
./gradlew -PmainClass=com.demo.advanced.OccupancyGridBuilder run
```