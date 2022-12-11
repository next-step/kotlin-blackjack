package blackjack.domain.player

import blackjack.domain.card.PlayingCard
import blackjack.domain.card.PlayingCards
import blackjack.domain.card.state.State
import blackjack.domain.card.state.rule.Blackjack
import blackjack.domain.card.state.rule.Hit
import blackjack.domain.player.state.Name
import blackjack.domain.player.state.role.PlayerRole

data class Player(val name: Name, val cards: PlayingCards) : PlayerRole() {
    init {
        require(cards.size() == NUMBER_OF_STARTING_CARDS) { "플레이어는 2장의 카드를 가지고 시작해야 합니다." }
        initState()
    }

    override fun draw(playingCard: PlayingCard): State {
        return state.draw(playingCard)
    }

    override fun stay(): State {
        return state.stay()
    }

    private fun initState() {
        if (cards.isBlackjack()) {
            state = Blackjack(cards)
            return
        }
        state = Hit(cards)
    }

    companion object {
        private const val NUMBER_OF_STARTING_CARDS = 2
    }
}
