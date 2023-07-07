package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Participant
import blackjack.domain.participant.Player
import blackjack.domain.participant.Players
import blackjack.domain.result.GameResultGenerator

class BlackJackGame(
    private val deck: Deck = Deck.create(),
    private val dealer: Dealer = Dealer(),
    private val players: Players,
) {
    fun start() {
        dealer.start { deck.drawCard() }
        for (player in players) {
            player.start { deck.drawCard() }
        }
    }

    fun playerPlay(
        isHit: (Player) -> Boolean,
        afterDrawCard: (name: String, List<Card>) -> Unit
    ) {
        for (player in players) {
            val drawCard: () -> Card = {
                deck.drawCard().also { card ->
                    afterDrawCard(player.name, player.cards() + card)
                }
            }
            player.play(isHit = { isHit(player) }, drawCard = drawCard)
        }
    }

    fun dealerPlay(
        afterDrawCard: (Dealer) -> Unit
    ) {
        val drawCard: () -> Card =
            {
                deck.drawCard().also {
                    afterDrawCard(dealer)
                }
            }
        dealer.play(drawCard)
    }

    fun getParticipants(): List<Participant> {
        return players + dealer
    }

    fun getGameResult(): GameResultGenerator {
        return GameResultGenerator(dealer, players)
    }
}
