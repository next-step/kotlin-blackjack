package blackjack.application

import blackjack.application.dto.PlayerResult
import blackjack.application.dto.PlayerResults
import blackjack.application.dto.PlayerStatus
import blackjack.application.dto.PlayerStatuses
import blackjack.domain.card.CardDeck
import blackjack.domain.card.setupCardDeck
import blackjack.domain.player.Dealer
import blackjack.domain.player.Player
import blackjack.domain.player.Players
import blackjack.domain.player.vo.Name

private fun Dealer.getPlayerStatus(): PlayerStatus = PlayerStatus(this.name, listOf(this.cardsInHand.cards.first()))

private fun Players.getPlayerStatuses(): List<PlayerStatus> =
    players.map { PlayerStatus(it.name, it.cardsInHand.cards) }

private fun Dealer.getPlayerResult(): PlayerResult =
    PlayerResult(this.name, this.cardsInHand.cards, this.cardsInHand.calculateScore())

private fun Players.getPlayerResults(): List<PlayerResult> =
    players.map { PlayerResult(it.name, it.cardsInHand.cards, it.cardsInHand.calculateScore()) }

class BlackJack private constructor(
    private val dealer: Dealer,
    private val players: Players,
    private val cardDeck: CardDeck
) {
    val statuses: PlayerStatuses
        get() = PlayerStatuses(
            buildList {
                add(dealer.getPlayerStatus())
                addAll(players.getPlayerStatuses())
            }
        )

    val results: PlayerResults
        get() = PlayerResults(
            buildList {
                add(dealer.getPlayerResult())
                addAll(players.getPlayerResults())
            }
        )

    val isPlayerAllStay: Boolean
        get() = players.allStay

    val isDealerStay: Boolean
        get() = dealer.isStay()

    val names: List<Name>
        get() = buildList {
            add(dealer.name)
            addAll(players.names)
        }

    fun ready() {
        dealer.ready(cardDeck)
        players.ready(cardDeck)
    }

    fun play(player: Player): PlayerStatus {
        player.hit(cardDeck)
        return PlayerStatus(player.name, player.cardsInHand.cards)
    }

    fun hit(hit: (Player) -> Unit) {
        players.hittable.forEach { hit(it) }
    }

    fun hitDealer(hitAfter: () -> Unit) {
        dealer.hit(cardDeck)
        hitAfter()
    }

    companion object {
        fun setup(players: Players): BlackJack {
            val cardDeck = setupCardDeck {
                spade()
                diamond()
                heart()
                club()
            }
            return BlackJack(Dealer.sit(), players, cardDeck)
        }
    }
}
