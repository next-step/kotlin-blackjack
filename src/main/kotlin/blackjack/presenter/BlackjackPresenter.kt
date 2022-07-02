package blackjack.presenter

import blackjack.Contract
import blackjack.domain.BlackjackGameResult
import blackjack.domain.BlackjackGameTurn
import blackjack.domain.CardDeck
import blackjack.domain.Dealer
import blackjack.domain.HIT_CARD_COUNT
import blackjack.domain.Hands
import blackjack.domain.Participants
import blackjack.domain.Player
import blackjack.domain.PlayerName
import blackjack.domain.PlayingCard
import blackjack.domain.PlayingCards
import blackjack.domain.START_CARD_COUNT
import blackjack.domain.cardrule.DistinctRule
import blackjack.domain.cardrule.ShuffleRule

class BlackjackPresenter(
    private val view: Contract.View,
    private val cardDeck: CardDeck
) : Contract.Presenter {
    private lateinit var currentTurn: BlackjackGameTurn

    override fun startGame() {
        val participants = view.getPlayerNames().toParticipants()
        view.showStartOfGameInfo(participants)

        setNextTurn(participants)
        while (!isBlackjackGameEnd()) {
            takeTurn()
            setNextTurn(participants)
        }

        view.showEndOfGameInfo(BlackjackGameResult.from(participants))
    }

    private fun List<PlayerName>.toParticipants(): Participants {
        val dealer = Dealer(DEALER_NAME, cardDeck.initialHands())
        val players = map { playerName ->
            Player(
                name = playerName,
                betAmount = view.getBetAmount(playerName),
                hands = cardDeck.initialHands()
            )
        }

        return Participants(dealer, players)
    }

    private fun CardDeck.initialHands(): Hands {
        return Hands.from(draw(START_CARD_COUNT))
    }

    private fun takeTurn() {
        when (val participant = currentTurn.participant) {
            is Player -> takePlayerTurn(participant)
            is Dealer -> takeDealerTurn()
        }
    }

    private fun takePlayerTurn(player: Player) {
        if (view.isPlayerWannaHit(player.name)) {
            currentTurn.hit(drawPlayingCardForHit())
            view.showPlayersHitEvent(player)
        } else {
            currentTurn.stay()
        }
    }

    private fun takeDealerTurn() {
        currentTurn.hit(drawPlayingCardForHit())
        view.showDealerHitEvent()
    }

    private fun drawPlayingCardForHit(): PlayingCards = cardDeck.draw(HIT_CARD_COUNT)

    private fun setNextTurn(participants: Participants) {
        currentTurn = BlackjackGameTurn.from(participants)
    }

    private fun isBlackjackGameEnd(): Boolean = currentTurn.participant is Dealer && currentTurn.isTurnEnd()

    companion object {
        private val DEALER_NAME = PlayerName("딜러")

        fun from(view: Contract.View): Contract.Presenter {
            val cardDeck = CardDeck.of(
                PlayingCard.all(),
                DistinctRule, ShuffleRule
            )

            return BlackjackPresenter(view, cardDeck)
        }
    }
}
