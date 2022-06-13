package blackjack.domain.player

import blackjack.domain.card.Card

sealed interface PlayerState {
    class Playing(private val player: Player) : PlayerState {
        fun hit(card: Card): PlayerState {
            player.addCardToHand(card)

            return if (player.isDone()) Done else this
        }

        fun stand(): PlayerState {
            return Done
        }
    }
    object Done : PlayerState

    companion object {
        fun of(player: Player): PlayerState {
            return if (player.isDone()) Done else Playing(player)
        }

        private fun Player.isDone(): Boolean = with(cards.total) { isBusted }
    }
}
