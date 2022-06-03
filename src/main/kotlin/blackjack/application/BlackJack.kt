package blackjack.application

import blackjack.application.dto.PlayerResult
import blackjack.application.dto.PlayerResults
import blackjack.domain.card.CardDeck
import blackjack.domain.card.setupCardDeck
import blackjack.domain.player.Player
import blackjack.domain.player.Players
import blackjack.application.dto.PlayerStatus
import blackjack.application.dto.PlayerStatuses

private fun Players.getPlayerStatuses(): PlayerStatuses =
    players.map { PlayerStatus(it.name, it.cardsInHand.cards) }.let(::PlayerStatuses)

private fun Players.getPlayerResults(): PlayerResults =
    players.map { PlayerResult(it.name, it.cardsInHand.cards, it.cardsInHand.calculateScore()) }.let(::PlayerResults)

class BlackJack private constructor(
    private val players: Players,
    private val cardDeck: CardDeck
) {
    val statuses: PlayerStatuses
        get() = players.getPlayerStatuses()

    val results: PlayerResults
        get() = players.getPlayerResults()

    fun ready() {
        players.ready(cardDeck)
    }

    fun play(player: Player): PlayerStatus {
        player.hit(cardDeck)
        return PlayerStatus(player.name, player.cardsInHand.cards)
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
