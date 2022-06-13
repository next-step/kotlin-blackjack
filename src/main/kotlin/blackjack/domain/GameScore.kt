package blackjack.domain

data class GameScore(
    var win: Int = 0,
    var lose: Int = 0,
    var draw: Int = 0
) {
    fun win() {
        win += 1
    }

    fun lose() {
        lose += 1
    }

    fun draw() {
        draw += 1
    }
}
