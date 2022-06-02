package blackjack.application

import blackjack.domain.card.CardDeck
import blackjack.domain.card.setupCardDeck
import blackjack.domain.player.Player
import blackjack.domain.player.Players
import blackjack.ui.dto.PlayerStatus

class BlackJack private constructor(
    private val players: Players,
    private val cardDeck: CardDeck
) {
    val statuses: List<PlayerStatus>
        get() = players.players.map { PlayerStatus(it.name, it.cardsInHand.cards, it.cardsInHand.calculateScore()) }

    fun ready() {
        players.ready(cardDeck)
    }

    fun play(player: Player): PlayerStatus {
        player.hit(cardDeck)
        return PlayerStatus(player.name, player.cardsInHand.cards, player.cardsInHand.calculateScore())
    }

    companion object {
        fun setup(players: Players): BlackJack {
            val cardDeck = setupCardDeck {
                spade()
                diamond()
                heart()
                club()
            }
            return BlackJack(players, cardDeck)
        }
    }
}
