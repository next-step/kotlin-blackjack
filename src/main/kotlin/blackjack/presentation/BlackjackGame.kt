package blackjack.presentation

import blackjack.domain.card.CardsDeck
import blackjack.domain.player.Dealer
import blackjack.domain.player.Participant
import blackjack.domain.player.Player
import blackjack.domain.player.Participants
import blackjack.service.DecisionMatch

class BlackjackGame(
    private val decisionMatch: DecisionMatch
) {

    fun start(
        players: List<Player>,
        cardsDeck: CardsDeck,
    ): Participants {
        val dealer = Dealer(Participant("딜러"))
        return Participants.build(
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

    fun existsBlackjack(participants: Participants): Boolean {
        return participants
            .getAllPlayers()
            .any { participant -> participant.isBlackjack() }
    }

    fun match(participants: Participants) {
        decisionMatch.match(participants.dealer, participants.players)
    }

    fun matchWhenFirstCardBlackjack(participants: Participants) {
        decisionMatch.matchWhenFirstCardBlackjack(
            participants.dealer,
            participants.players
        )
    }
}
