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
        private const val STARTING_CARD_SIZE_IS_TWO = "플레이어는 두 장의 카드로 게임을 시작해야 합니다."
        fun of(player: Player): PlayerState {
            require(player.cards.size == 2) { STARTING_CARD_SIZE_IS_TWO }

            return if (player.isBlackjack()) Blackjack else Playing(player)
        }

        private fun Player.isBlackjack(): Boolean = cards.total.value == 21

        private fun Player.isBusted(): Boolean = cards.total.isBusted
    }
}
