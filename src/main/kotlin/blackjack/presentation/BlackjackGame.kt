package blackjack.presentation

import blackjack.domain.card.CardsDeck
import blackjack.domain.player.Dealer
import blackjack.domain.player.Participant
import blackjack.domain.player.Player
import blackjack.domain.player.Participants
import blackjack.service.DetermineMatch

class BlackjackGame(
    private val determineMatch: DetermineMatch
) {

    fun start(
        players: List<Player>,
        cardsDeck: CardsDeck,
    ): Participants {
        val dealer = Dealer(Participant("딜러"))
        return Participants(
            dealer = dealer,
            players = players,
            cardsDeck
        )
    }

    fun addCard(
        player: Participant,
        cardsDeck: CardsDeck
    ): Participant {
        val card = cardsDeck.divide()

        player.addCard(card)

        return player
    }

    fun match(participants: Participants) {
        determineMatch.match(participants.dealer, participants.players)
    }
}
