## Code Review

Reviewed by: Yuxuan Shi, u7789617

Reviewing code written by: James Engeman u7484227

Component: https://gitlab.cecs.anu.edu.au/u7789617/comp1110-ass2/-/blob/main/src/comp1110/ass2/gui/Viewer.java?ref_type=heads#L97-165

### Comments 

1. This code is structurally clear. The modular design encapsulates logic such as hand acquisition in a single method, which improves the readability and reusability of the code.

2. Most of the variable and method names use camel nomenclature. And all of them use descriptive names such as cardSpacingVertical, cardsPerColumn, columnWidth to make them easy to be understood.

3. The code lacks sufficient comments.

4. The code has some areas of duplicate code that could be simplified.

https://gitlab.cecs.anu.edu.au/u7789617/comp1110-ass2/-/blob/main/src/comp1110/ass2/gui/Viewer.java?ref_type=heads#L123-150

We can save the state variable and its corresponding file path in a Map collection. When in use, get the corresponding file path through the state variable, and then only one line of the imageViewHand.setImage() method needs to exist.
