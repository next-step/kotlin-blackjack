package blackjack.domain.player

import blackjack.domain.card.PlayingCard
import blackjack.domain.card.state.State
import blackjack.domain.player.state.Name
import blackjack.domain.player.state.role.PlayerRole

data class Player(val name: Name, override val state: State) : PlayerRole() {
    init {
        require(state.cards.size() >= NUMBER_OF_STARTING_CARDS) { "플레이어는 2장의 카드를 가지고 시작해야 합니다." }
    }

    override fun draw(playingCard: PlayingCard): Player {
        return Player(name, state.draw(playingCard))
    }

    override fun stay(): Player {
        return Player(name, state.stay())
    }

    override fun getScore(): Int {
        return state.cards.getScore()
    }

    fun isBlackjack(): Boolean {
        return state.cards.isBlackjack()
    }

    fun isBust(): Boolean {
        return state.cards.isBust()
    }

    fun getCardsSize(): Int {
        return state.cards.size()
    }

    fun getCardsAsString(): String {
        return state.cards.toString()
    }

    companion object {
        private const val NUMBER_OF_STARTING_CARDS = 2
    }
}
