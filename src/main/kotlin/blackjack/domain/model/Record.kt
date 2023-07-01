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

    override fun toString(): String {
        if (win == 0 && lose == 0) return "무승부"

        return when {
            win == 1 && lose == 0 -> "승"
            win == 0 && lose == 1 -> "패"
            win == 0 -> "${lose}패"
            lose == 0 -> "${win}승"
            else -> "${win}승 ${lose}패"
        }
    }
}
