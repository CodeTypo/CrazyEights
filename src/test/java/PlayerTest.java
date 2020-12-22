import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void dealCard_When_PlayerDealsTwoCards_ShouldPlayerGetsTwoCards() {
        //Arrange
        Player player = new Player();
        List<Card> cards = player.getCards();
        Card sevenSpades = new Card(Suit.SPADES, Denomination.SEVEN);
        Card queenHeart = new Card(Suit.HEARTS, Denomination.QUEEN);

        //Act
        player.dealCard(sevenSpades);
        player.dealCard(queenHeart);
        int actual = cards.size();

        //Assert
        assertEquals(2, actual);
    }

    @Test
    void selectCard_When_PlayerHasZeroSelectedCardBeforeSelectsCard_ShouldReturnTrue() {
        //Arrange
        Player player = new Player();
        Card randomCard = new Card(Suit.CLUBS, Denomination.QUEEN);

        //Act
        boolean actual = player.selectCard(randomCard);

        //Assert
        assertTrue(actual);
    }

    @Test
    void selectCard_When_PlayerSelectsSomeCardsWithTheSameDenominations_ShouldReturnTrue() {
        //Arrange
        Player player = new Player();
        Card firstCardSelected = new Card(Suit.SPADES, Denomination.JACK);
        Card secondCardSelected = new Card(Suit.HEARTS, Denomination.JACK);
        List<Card> selectedCards = player.getSelectedCards();
        selectedCards.add(firstCardSelected);

        //Act
        boolean actual = player.selectCard(secondCardSelected);

        //Assert
        assertTrue(actual);
    }

    @Test
    void selectCard_When_PlayerSelectsSomeCardsWithOtherDenomination_ShouldReturnFalse() {
        //Arrange
        Player player = new Player();
        Card firstCardSelected = new Card(Suit.SPADES, Denomination.JACK);
        Card secondCardSelected = new Card(Suit.HEARTS, Denomination.QUEEN);
        List<Card> selectedCards = player.getSelectedCards();
        selectedCards.add(firstCardSelected);

        //Act
        boolean actual = player.selectCard(secondCardSelected);

        //Assert
        assertFalse(actual);
    }

    @Test
    void removeSelectedCards_ShouldRemoveAllSelectedCards(){
        //Arrange
        Player player = new Player();
        List<Card> selectedCards = player.getSelectedCards();
        selectedCards.add(new Card(Suit.SPADES, Denomination.KING));
        selectedCards.add(new Card(Suit.SPADES, Denomination.TWO));
        selectedCards.add(new Card(Suit.SPADES, Denomination.JACK));
        selectedCards.add(new Card(Suit.SPADES, Denomination.FIVE));

        //Act
        player.removeSelectedCards();

        //Assert
        assertEquals(0, selectedCards.size());
    }

    @Test
    void unselectCard_When_PlayerResignsFromSelectedCard_ShouldRemoveThatCardFromSelectedCards(){
        //Arrange
        Player player = new Player();
        List<Card> selectedCards = player.getSelectedCards();
        Card randomUnselectedCard = new Card(Suit.CLUBS, Denomination.QUEEN);
        selectedCards.add(randomUnselectedCard);
        selectedCards.add(new Card(Suit.CLUBS, Denomination.JACK));
        selectedCards.add(new Card(Suit.CLUBS, Denomination.THREE));
        selectedCards.add(new Card(Suit.CLUBS, Denomination.ACE));

        //Act
        player.unselectCard(randomUnselectedCard);

        //Assert
        for(Card card : selectedCards)
            assertNotEquals(randomUnselectedCard,card);
    }

    @Test
    void unselectCard_When_PlayerResignsFromSelectedCard_ShouldAddThatCardToOtherPlayerCards(){
        //Arrange
        Player player = new Player();
        List<Card> selectedCards = player.getSelectedCards();
        List<Card> cards = player.getCards();
        Card randomUnselectedCard = new Card(Suit.CLUBS, Denomination.QUEEN);
        selectedCards.add(randomUnselectedCard);
        cards.add(new Card(Suit.CLUBS, Denomination.JACK));
        cards.add(new Card(Suit.CLUBS, Denomination.THREE));
        cards.add(new Card(Suit.CLUBS, Denomination.ACE));

        //Act
        player.unselectCard(randomUnselectedCard);

        //Assert
        assertEquals(4,cards.size());
    }
}
