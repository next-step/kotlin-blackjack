package blackjack.domain.member

import blackjack.domain.Cards

class LosePlayer(name: String, cards: Cards) : Player(name, cards) {
    companion object {
        fun init(player: Player): LosePlayer = LosePlayer(player.name, player.cards)
    }
}
