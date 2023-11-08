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

class Hand{
    private val _cards = mutableListOf<Card>()
    val cards: List<Card>
        get() = _cards

    val score: Int
        get() {
            val score = _cards.fold(0){ score, card ->
                score + card.score
            }
            return score
        }

    fun addCard(card: Card){
        _cards.add(card)
    }

    fun removeCard(card: Card): Boolean{
        return _cards.remove(card)
    }

    override fun toString(): String {
        return _cards.joinToString(" ") { it.toString() }
    }
}