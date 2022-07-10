package blackjack.entity

import blackjack.ui.GetResult

class Dealer(override val hands: Hands, override val name: String = "딜러", override val limit: Int = 17) : Person {
    override fun draw(hands: Hands): Hands {
        return hands.addNewCard()
    }
}
