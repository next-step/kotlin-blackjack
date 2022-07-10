package blackjack.entity

import blackjack.ui.GetResult
import blackjack.ui.Input

class Player(override val name: String, override val hands: Hands, override val limit: Int = 21) : Person {
    override fun draw(hands: Hands): Hands {
        return hands.addNewCard()
    }
}
