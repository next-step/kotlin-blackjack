package blackjack

import blackjack.state.Ready
import blackjack.state.State

class Dealer(override val name: String = "딜러", override val state: State = Ready(PlayerDeck())) : UserRole(name, state) {

    override fun stand(): UserRole {
        return super.stand()
    }

    override fun draw(card: Card): UserRole {
        return super.draw(card)
    }

    override fun isDealer(): Boolean = true
}
