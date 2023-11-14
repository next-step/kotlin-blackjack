package blackjack.controller

import blackjack.model.Card
import blackjack.model.CardInfo
import blackjack.model.CardType
import blackjack.model.Participant
import blackjack.ui.InputView
import blackjack.ui.ResultView

class BlackJackGame(
    val participants: List<Participant>,
) {

    private val cardsPool = mutableSetOf<Card>()

    init {
        makeCardsPool()
        allocateDefaultCards()
    }

    fun allocateCards() {
        participants.forEach { participant ->
            while (participant.isPossibleToTakeMoreCard()) {
                if (InputView.askCardPicking(participant.name)) {
                    allocateOneCard(participant)
                    ResultView.showStatusOfParticipant(participant)
                } else {
                    ResultView.showStatusOfParticipant(participant)
                    break
                }
            }
        }
    }

    fun isPossibleToAllocation() = cardsPool.isNotEmpty()

    private fun makeCardsPool() {
        CardType.values().forEach { type ->
            CardInfo.values().forEach { cardInfo ->
                cardsPool.add(Card(type, cardInfo))
            }
        }
    }

    private fun allocateDefaultCards() {
        participants.forEach {
            it.cards.addAll(pickRandomCards(DEFAULT_CARD_COUNTS))
        }
    }

    fun allocateOneCard(participant: Participant) {
        participant.cards.addAll(pickRandomCards(count = 1))
    }

    private fun pickRandomCards(count: Int): List<Card> {
        val pickedCards = cardsPool.shuffled().take(count)
        pickedCards.forEach { pickedCard ->
            cardsPool.remove(pickedCard)
        }
        return pickedCards
    }

    companion object {
        const val DEFAULT_CARD_COUNTS = 2
        const val BEST_SCORE = 21
    }
}

fun main() {
    val participants = InputView.registerParticipants()

    val blackJackGame = BlackJackGame(participants)

    ResultView.showInitialStatusOfParticipants(participants)

    blackJackGame.allocateCards()

    ResultView.showGameResult(blackJackGame.participants)
}
