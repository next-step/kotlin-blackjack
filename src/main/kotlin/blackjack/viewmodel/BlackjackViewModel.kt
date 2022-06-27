package blackjack.viewmodel

import blackjack.domain.BlackjackGameResult
import blackjack.domain.BlackjackGameTurn
import blackjack.domain.CardDeck
import blackjack.domain.Dealer
import blackjack.domain.HIT_CARD_COUNT
import blackjack.domain.Hands
import blackjack.domain.HitEvent
import blackjack.domain.Participants
import blackjack.domain.Player
import blackjack.domain.PlayerInfo
import blackjack.domain.PlayerName
import blackjack.domain.PlayingCard
import blackjack.domain.START_CARD_COUNT
import blackjack.domain.cardrule.DistinctRule
import blackjack.domain.cardrule.ShuffleRule

class BlackjackViewModel private constructor(
    val participants: Participants,
    private val cardDeck: CardDeck,
    private val isPlayerWannaHit: (Player) -> Boolean
) {
    val hitEvent: HitEvent = HitEvent()

    private var currentTurn: BlackjackGameTurn = BlackjackGameTurn.from(participants)

    fun startGame() {
        while (!currentTurn.isBlackjackGameEnd()) {
            takeTurn()
            setNextTurn()
        }
    }

    private fun takeTurn() {
        when (val participant = currentTurn.participant) {
            is Player -> takePlayerTurn(participant)
            is Dealer -> takeDealerTurn(participant)
        }
    }

    private fun takePlayerTurn(player: Player) {
        if (isPlayerWannaHit(player)) {
            currentTurn.hit(cardDeck.draw(HIT_CARD_COUNT))
            hitEvent.emit(player)
        } else {
            currentTurn.stay()
        }
    }

    private fun takeDealerTurn(dealer: Dealer) {
        currentTurn.hit(cardDeck.draw(HIT_CARD_COUNT))
        hitEvent.emit(dealer)
    }

    private fun setNextTurn() {
        currentTurn = BlackjackGameTurn.from(participants)
    }

    fun getBlackjackGameResult(): BlackjackGameResult {
        return BlackjackGameResult.from(participants)
    }

    companion object {
        fun from(
            dealerName: PlayerName,
            playerInfos: List<PlayerInfo>,
            isPlayerWannaHit: (Player) -> Boolean
        ): BlackjackViewModel {
            val cardDeck = CardDeck.of(
                PlayingCard.all(),
                DistinctRule, ShuffleRule
            )
            val participants = Participants(
                dealer = dealerName.toDealer(cardDeck),
                players = playerInfos.toPlayers(cardDeck)
            )

            return BlackjackViewModel(
                participants,
                cardDeck,
                isPlayerWannaHit
            )
        }

        fun of(dealerName: PlayerName, playerInfos: List<PlayerInfo>, cardDeck: CardDeck): BlackjackViewModel {
            val participants = Participants(
                dealer = dealerName.toDealer(cardDeck),
                players = playerInfos.toPlayers(cardDeck)
            )
            return BlackjackViewModel(participants, cardDeck) { false }
        }

        private fun PlayerName.toDealer(cardDeck: CardDeck): Dealer {
            return Dealer(this, cardDeck.initialHands())
        }

        private fun CardDeck.initialHands(): Hands {
            return Hands.from(draw(START_CARD_COUNT))
        }

        private fun List<PlayerInfo>.toPlayers(cardDeck: CardDeck): List<Player> {
            return map { playerInfo ->
                Player(
                    name = playerInfo.name,
                    betAmount = playerInfo.betAmount,
                    hands = cardDeck.initialHands()
                )
            }
        }
    }
}
