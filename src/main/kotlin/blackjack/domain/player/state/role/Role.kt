package blackjack.domain.player.state.role

import blackjack.domain.card.PlayingCard
import blackjack.domain.card.state.State
import blackjack.domain.player.Player

interface Role {
    val state: State

    fun draw(playingCard: PlayingCard): Player

    fun stay(): Player

    fun getScore(): Int

    fun isDealer(): Boolean
}
