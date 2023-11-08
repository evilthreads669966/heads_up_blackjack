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

class Dealer(override val hand: Hand, val deck: Deck): Participant {
    override val score: Int
        get() = hand.score

    companion object: ParticipantFactory {
        override fun createParticipant(hand: Hand, deck: Deck?): Participant = Dealer(hand, deck!!)
    }

    fun deal(playerHand: Hand): TableState {
        var cardOne = deck.drawCard()
        var cardTwo = deck.drawCard()
        hand.addCard(cardOne)
        hand.addCard(cardTwo)
        cardOne = deck.drawCard()
        cardTwo = deck.drawCard()
        playerHand.addCard(cardOne)
        playerHand.addCard(cardTwo)
        return TableState.PLAY
    }

    fun hit(playerHand: Hand): TableState {
        var card = deck.drawCard()
        playerHand.addCard(card)
        if(playerHand.score > 21 && card.value == 14){
            playerHand.removeCard(card)
            playerHand.addCard(Card(1, card.suite))
        }

        if(hand.score < 17){
            card = deck.drawCard()
            hand.addCard(card)
            if(hand.score > 21 && card.value == 14){
                hand.removeCard(card)
                hand.addCard(Card(1, card.suite))
            }
        }
        if(playerHand.score > 21)
            return TableState.LOSE
        if(hand.score == 21 && playerHand.score < 21)
            return TableState.LOSE
        if(hand.score > 21 && playerHand.score <= 21)
            return TableState.WIN
        return TableState.PLAY
    }

    fun fold(playerHand: Hand): TableState {
        if(playerHand.score > 21)
            return TableState.LOSE
        if(hand.score == 21 && playerHand.score < 21)
            return TableState.LOSE
        if(hand.score > 21 && playerHand.score <= 21)
            return TableState.WIN
        if(hand.score < playerHand.score)
            return TableState.WIN
        return TableState.LOSE
    }
}