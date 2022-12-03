package model

data class PokerNumber private constructor(val value: String) {

    companion object {
        private val pokerNumbers: List<String> =
            listOf("A", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King")

        fun pokerNumbers(): List<PokerNumber> {
            return pokerNumbers.map { PokerNumber(it) }.toList()
        }
    }
}
