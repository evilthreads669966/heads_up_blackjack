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

object Game{
    @JvmStatic
    fun start(){
        var count = 1
        loop@while(true){
            val table = Table()
            println("Dealing cards")
            table.request(Request.DEAL)
            print("Your hand is: ")
            println(table.player.hand)
            println("Your score is ${table.player.score}")
            hitfold@while(true){
                println("HIT OR FOLD")
                var input = readlnOrNull()?.trim()!!.lowercase()
                if(input.isNullOrBlank())
                    continue@hitfold
                if(input == "hit"){
                    val state = table.request(Request.HIT)
                    if(state == TableState.LOSE){
                        table.showHandsAndScores()
                        println("You lose!")
                        break@hitfold
                    }
                    if(state == TableState.WIN){
                        table.showHandsAndScores()
                        println("You win!")
                        break@hitfold
                    }
                    if(state == TableState.PLAY){
                        println("Player Score: ${table.player.score}")
                        println("Player hand ${table.player.hand}")
                        continue@hitfold
                    }
                }
                if(input == "fold"){
                    table.showHandsAndScores()
                    val state = table.request(Request.FOLD)
                    if(state == TableState.WIN)
                        println("You win!")
                    else
                        println("You lose!")

                    break@hitfold
                }
            }
            println()
            println()
            count++
            if(count % 10 == 0)
                System.gc()
        }
    }
}