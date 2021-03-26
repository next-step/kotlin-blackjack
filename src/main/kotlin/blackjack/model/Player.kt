package blackjack.model

import blackjack.model.trump.Cards

class Player(val name: String) {
    var cards = Cards.Builder().cards(Cards.firstDraw()).build()
        private set

    fun keepDrawing(userResponse: String): Boolean {
        if (userResponse == "y") {
            draw()
            return true
        }
        return false
    }

    private fun draw() {
        cards = cards.draw()
    }
}
