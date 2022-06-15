package blackjack

import blackjack.state.Ready
import blackjack.state.State

class Player(override val name: String, override val state: State = Ready(PlayerDeck())) : UserRole(name, state) {
}
