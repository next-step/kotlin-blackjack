package blackjack.model.player

import blackjack.view.InputView

const val BLACKJACK_MAX_NUMBER = 21

class Gamer(override val name: String) : Player(name) {

    override fun call(): Boolean {
        while (continueToTurn() && InputView.askToDraw(this)) {
            return true
        }
        return false
    }

    private fun continueToTurn(): Boolean {
        return cards.sumByPoint() < BLACKJACK_MAX_NUMBER
    }
}
