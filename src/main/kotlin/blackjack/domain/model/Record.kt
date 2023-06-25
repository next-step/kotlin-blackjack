package blackjack.domain.model

class Record {
    var win = 0
        private set
    var lose = 0
        private set

    fun win() {
        win++
    }

    fun lose() {
        lose++
    }
}
