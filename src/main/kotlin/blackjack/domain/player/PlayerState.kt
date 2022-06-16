package blackjack.domain.player

import blackjack.domain.card.Card

sealed interface PlayerState {
    class Playing(private val player: Player) : PlayerState {
        fun hit(card: Card): PlayerState {
            player.addCardToHand(card)

            return if (player.isBusted()) Busted else this
        }

        fun stand(): PlayerState {
            return Stand
        }
    }
    sealed interface Done : PlayerState
    object Blackjack : Done
    object Busted : Done
    object Stand : Done

    companion object {
        fun of(player: Player): PlayerState {
            return if (player.isBlackjack()) Blackjack else Playing(player)
        }

        private fun Player.isBlackjack(): Boolean = cards.total.value == 21

        private fun Player.isBusted(): Boolean = cards.total.isBusted
    }
}
