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

class Deck{
    private val _cards = mutableListOf<Card>()
    val cards: List<Card>
        get() = _cards

    init {
        Suite.values().forEach { suite ->
            val cards = mutableListOf(
                Card(2, suite),
                Card(3, suite),
                Card(4, suite),
                Card(5, suite),
                Card(6, suite),
                Card(7, suite),
                Card(8, suite),
                Card(9, suite),
                Card(10, suite),
                Card(11, suite),
                Card(12, suite),
                Card(13, suite),
                Card(14, suite)
            )
            _cards.addAll(cards)
        }
        _cards.shuffle()
    }

    fun drawCard(): Card = _cards.removeLast()
}