/*
Copyright 2023 Chris Basinger

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

object UnitTests {
    @Test
    fun deckContainsFourSuitesTest(): Unit {
        val deck = Deck()

        val clubs = deck.cards.firstOrNull { it.suite == Suite.CLUBS }
        val hearts = deck.cards.firstOrNull { it.suite == Suite.HEARTS }
        val diamond = deck.cards.firstOrNull { it.suite == Suite.Diamonds }
        val spades = deck.cards.firstOrNull { it.suite == Suite.SPADES }

        assertNotNull(clubs)
        assertNotNull(hearts)
        assertNotNull(diamond)
        assertNotNull(spades)
    }

    @Test
    fun showHandTest(){
        val hand = Hand()
        val card = Card(2, Suite.HEARTS)
        hand.addCard(card)
        val string = hand.toString()

        assertContains(string, card.toString())
    }

    @Test
    fun dealTwoCardsToEachPlayerTest(){
        val player = Player.createParticipant(Hand())
        val dealer: Dealer = Dealer.createParticipant(Hand(), Deck()) as Dealer
        dealer.deal(player.hand)

        assertEquals(dealer.hand.cards.count(), player.hand.cards.count())
    }

    /*I make sure the dealer doesn't start with a count of 17*/
    @Test
    fun hitOneCardToEachPlayerTest(){
        val player = Player.createParticipant(Hand())
        val dealer: Dealer = Dealer.createParticipant(Hand(), Deck()) as Dealer

        player.hand.addCard(Card(2, Suite.HEARTS))
        player.hand.addCard(Card(3, Suite.HEARTS))
        dealer.hand.addCard(Card(2, Suite.SPADES))
        dealer.hand.addCard(Card(3, Suite.SPADES))
        dealer.hit(player.hand)

        assertEquals(3, player.hand.cards.count())
        assertEquals(3, dealer.hand.cards.count())
    }

    @Test
    fun playerLosesWhenFoldingWithSmallerHand(){
        val player = Player.createParticipant(Hand())
        val dealer: Dealer = Dealer.createParticipant(Hand(), Deck()) as Dealer

        player.hand.addCard(Card(2, Suite.HEARTS))
        player.hand.addCard(Card(3, Suite.HEARTS))
        dealer.hand.addCard(Card(2, Suite.SPADES))
        dealer.hand.addCard(Card(4, Suite.SPADES))

        val tableState = dealer.fold(player.hand)

        assertEquals(TableState.LOSE, tableState)
    }

    @Test
    fun playerWinsWhenFoldingWithLargerHand(){
        val player = Player.createParticipant(Hand())
        val dealer: Dealer = Dealer.createParticipant(Hand(), Deck()) as Dealer

        player.hand.addCard(Card(2, Suite.HEARTS))
        player.hand.addCard(Card(4, Suite.HEARTS))
        dealer.hand.addCard(Card(2, Suite.SPADES))
        dealer.hand.addCard(Card(3, Suite.SPADES))

        val tableState = dealer.fold(player.hand)
        assertEquals(TableState.WIN, tableState)
    }
}
