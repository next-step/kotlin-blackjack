package blackjack.domain.result

class PlayerResult(private var isDeckComplete: Boolean = false) {
    var isWin: Boolean = false

    fun win() {
        isWin = true
    }

    fun isDeckComplete(): Boolean {
        return isDeckComplete
    }

    fun deckComplete() {
        isDeckComplete = true
    }
}
