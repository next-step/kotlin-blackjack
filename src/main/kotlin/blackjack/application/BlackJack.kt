package blackjack.application

import blackjack.application.dto.PlayerResult
import blackjack.application.dto.PlayerResults
import blackjack.application.dto.PlayerStatus
import blackjack.application.dto.PlayerStatuses
import blackjack.domain.card.CardDeck
import blackjack.domain.card.setupCardDeck
import blackjack.domain.player.Player
import blackjack.domain.player.Players

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

    val isEnd: Boolean
        get() = players.allStay

    val hittablePlayers: List<Player>
        get() = players.hittable

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
