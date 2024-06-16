BoardState
1. public BoardState(String str)
   Test if the Board String can be correctly parsed as a two-dimensional array of type State.

2. public String toString()
   Test if the BoardState object can be correctly converted to the corresponding Board String.

CatCards
1. public CatCards(String str)
   Test whether the corresponding cat cards can be correctly created from the given string.

Challenge
1. public Challenge newChallenge(int difficulty)
   Test to see if the expected challenges are correctly generated when given different difficulty levels.

2. public String toString()
   String representation of the challenges.

Decks
1. public boolean isRequestValid(String str)
   Test whether the validity of a request can be correctly determined when given different requests to draw cards.

2. public String draw_cards(String str)
   Test whether the method is able to correctly draw the appropriate cards when given a different request to draw cards.


ExhaustedCats
1. public String toString()
   Test whether an object can be converted into a corresponding string that meets the formatting requirements.



FireCards
1. public FireCards(String str)
   Test that an array of fire card positions is correctly created when given a different string.



FireTile
1. public void rotate(Orientation o)
   Test whether the fire tiles rotate correctly when given different orientations.

2. public void flip()
   Test that the layout of the fire tile flips correctly when this method is called.



FireTileBag
1. public String draw_tile()
   Test that the extraction of fire tiles behaves as expected.



Hand
1. public void discard_card(PathwayCard card)
   Test whether the method can correctly discard the corresponding card from the hand when given different path card.



IslandBoard
1. public void rotate(Orientation o)
   Test whether a given orientation can be rotated correctly to change the layout.



PathwayCard
1. public void rotate(Orientation o)
   Test whether a given orientation can be rotated correctly to change the layout.