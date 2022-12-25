package blackjack.domain.player

import blackjack.domain.card.PlayingCard
import blackjack.domain.card.state.State
import blackjack.domain.player.state.Name
import blackjack.domain.player.state.role.PlayerRole

data class Player(override val name: Name, override val state: State) : PlayerRole() {
    init {
        require(state.cards.size() >= NUMBER_OF_STARTING_CARDS) { "플레이어는 2장의 카드를 가지고 시작해야 합니다." }
    }

    override fun draw(playingCard: PlayingCard): Player {
        return Player(name, state.draw(playingCard))
    }

    override fun stay(): Player {
        return Player(name, state.stay())
    }

    companion object {
        private const val NUMBER_OF_STARTING_CARDS = 2
    }
}
