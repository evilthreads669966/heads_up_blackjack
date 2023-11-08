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

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.qualifier.named

class Table: KoinComponent {
    val dealer: Participant by inject(qualifier = named("dealer"))
    val player: Participant by inject(qualifier = named("player"))

    fun request(request: Request): TableState {
        return when(request){
            Request.HIT -> (dealer as Dealer).hit(player.hand)
            Request.FOLD -> (dealer as Dealer).fold(player.hand)
            Request.DEAL -> (dealer as Dealer).deal(player.hand)
        }
    }

    fun showHandsAndScores(){
        println("Player Score: ${player.score}")
        println("Dealer Score: ${dealer.score}")
        println("Player hand ${player.hand}")
        println("Dealer hand ${dealer.hand}")
    }
}