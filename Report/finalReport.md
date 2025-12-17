**Requirements**

Add images to this folder so we can eventually add it to the drive

- References
  - [Project 1](https://docs.google.com/document/d/18yjuf6_u7FrdDD5El6dm0gQjciRBoqCNS2pqEc45X1E/edit?usp=sharing)
  - [Project 2](https://docs.google.com/document/d/14q9LlTMxdtRLbnDi2xUTHpx3G7e0w8Clx0i63cAe3_k/edit?usp=sharing)

Video and code is extra credit, not too hard to work with either

# Title Page (separate nicely formatted page)

- Copy paste from previous [reports](https://docs.google.com/document/d/14q9LlTMxdtRLbnDi2xUTHpx3G7e0w8Clx0i63cAe3_k/edit?usp=sharing)

# Table of contents

- We've never done this before, intruiging: Best for last, TOC is easiest when everything already exists

# Abstract (Kevin)

- Writing

# Introduction (Kevin)

- Discuss the project, introduce the problem you are trying to solve, how you solve it, and sumarize your conclusions.
- Keywords

# Background (Kevin)

- Discuss the background of the problem you’re trying to solve.
- Discuss the background of your solution.

# Problem Formulation (Israel)

- Go over the complete formulation of your problem. This can be broken into subsections:
  - task environment,
    - The task environment is the specific context that an intelligent agent works within. It has defining characteristics that inform the type of agent that will be built to solve the problem. The properties of the Nine Men's Morris Solver task environment are listed below (Fig. **ADD A NUMBER HERE**).
    - ![Image](./Task_Environment.png "Task Environment")
  - PEAS,
    - The PEAS structure works to further specify the environment the agent will be working in. PEAS stands for performance measure, environment, actuators, and sensors.
    - The performance measure tells the agent how well it is performing. In Nine Men's Morris, the agent is trying to reach the best possible state against it's opponent. To achieve this, the agent is given a score for its actions:
      - Each Man left alive - 10 points
      - Each Milli made - 12 points
      - Amount of possible moves - 1 point
    - This is them compared to the opponent's points, which determines the board state's evaluation

    - The environment of Nine Men's Morris is 3 3x3 boards, where the center tile is instead a smaller board, except for the last one which is empty
    - ![Image](./Board.png "Board")
    - (Fig.  **ADD A NUMBER HERE**) Above is the board as most people commonly know it as
    - We chose to represent this as a 2D array [i][j], where i represents which board, and j represent's which tile from 0-7. 
    - As for the pieces, 'O','*', and '.' are our white, empty, and black spaces respectively

    - Space {{0,1,2,3,4,5,6,7},{0,1,2,3,4,5,6,7},{0,1,2,3,4,5,6,7}}
    - Tiles {O,*,.}

    - As for the actuators, the agents have 2 different ones depending on the game phase.
    - When each agent places their 9 men:
      - Placing thir pieces on empty tiles
      - Place tile {*, O}
    - However, during the normal phase:
      - Swap own man tile with valid empty tile
      - Swap {left, right, up, down} or Swap {any} (if down to 3 men remaining)
    
    - The sensor for this agent is counting the amount of allied and enemy tiles, millis, and possible moves
  - Algorithm: include pseudo code of the algorithm you use. There are LaTeX libraries for formatting pseudo code.
- Each project will be different depending on the methods that you use so be sure to include all relevant details for your particular methods.
- In each subsection, include figures such as UML diagrams, flow charts, etc. to help your reader understand your solution method. You must discuss the problem solving performance
    - completeness
      - Completeness is how we determine the solvability of our solution. In our case, our algorithm is complete because it delivers the outcome we desire (the best possible move against another optimal player)
    - optimality
      - Optimality is how we determine the efficiency of our solution. In our case, our algorithm is mostly optimal, we use an alpha-beta algorithm to cut down on the amount of paths we have to search through
    - time complexity
      - Time complexity is how we represent the speed of our solution. In our case, our algorithm is O(p^d), where p is the amount of pieces left on the board, and d is how many moves deep our algorithm goes
    - space complexity
      - Space complexity is how we represent the space efficiency of our solution. In our case, or algorithm's spcae efficiency is O(pd), where p is the amount of pieces, and d is how many moves deep our algorithm goes

# Project Results (Israel)

- Our program uns correctly, and although it may not be the most efficient, the alpha-beta pruning definitely helps cut out a lot more paths to make it run smoother.
- The biggest challenge facing us suprisingly wasn't implementing the alpha-beta pruning, but actually keeping track of removed pieces on each board, as well as just visualizing the 2D array we ended up creating
- Once we overcame that issue, however, our algorithm ended up running smoothly and efficiently enough. And our static variables helped cut down on some calculations to help speed things along

# Contributions (Israel)

- This section should be used to list all individual contributions of every group member. It should be clear what everyone worked on.
- Israel Velazquez
  - Design, Code, Diagrams, Testing, and Report

# Conclusion (Israel)

- In the end, our project satisfied all the goals we set out to achieve. We also learned important mechanics about Nine Men's Morris, including the fact that the number of moves a player has available to them is also a very valuable resource. We also learned, that more often than not, Nine Men's Morris games will end in a draw if the AI is allowed to look far enough ahead, almost like a chess game. We were very satisfied with what we were able to accomplish and what we learned, especially the fact that all games played perfectly end in a never ending loop.
- We definitely would like to improve our code structure, and our use of magic numbers. We also plan on allowing a human vs AI mode in the future so we can play against our own AI agents. Possibly allowing the player to choose how deep an AI agent can see, to make sure the game isn't *too* hard to win.

# References

---

The report needs to be written as a two-column IEEE standard paper. There will be a template on Canvas with LaTeX code with all sections. If you do not like using LaTeX, you can create the same style in Word or Google Docs. All images need to be labeled as figures and numbered. There should be absolutely no hand-drawn or handwritten elements of the paper. All figures should be clear and look professional. All citations need to be done using IEEE standard format.