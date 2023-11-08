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

class Card(val value: Int, val suite: Suite){
    val score: Int
        get() {
            val range = IntRange(11,13)
            if(value in 11..13)
                return 10
            else if(value == 14)
                return 11
            return value
        }

    override fun toString(): String {
        return when(value){
            11 -> "${suite.format} Jack"
            12 -> "${suite.format} Queen"
            13 -> "${suite.format} King"
            14 -> "${suite.format} Ace"
            1 -> "${suite.format} Ace"
            else -> "${suite.format} ${value}"
        }
    }
}