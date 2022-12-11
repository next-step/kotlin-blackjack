package blackjack.domain.player.state.role

import blackjack.domain.card.PlayingCard
import blackjack.domain.card.state.State

interface Role {
    fun draw(playingCard: PlayingCard): State

    fun stay(): State

    fun getScore(): Int

    fun isDealer(): Boolean
}
