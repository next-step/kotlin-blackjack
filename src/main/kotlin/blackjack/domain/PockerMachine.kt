package blackjack.domain

import blackjack.domain.card.CardDeck
import blackjack.domain.dto.GameResult
import blackjack.domain.dto.ParticipantMoneyResult
import blackjack.domain.dto.ParticipantResult
import blackjack.domain.enums.WinOrLose
import blackjack.domain.person.Dealer
import blackjack.domain.person.Participant
import blackjack.domain.person.Player

class PockerMachine(
    private val cardDeck: CardDeck = CardDeck(),
    private val dealer: Dealer,
    private val players: List<Player>
) {
    fun initialize() {
        List(BASIC_CARD_COUNT) {
            players.map { player -> player.addCard(dealer.pickCard(cardDeck)) }
        }
    }

    fun addCard(
        retryFunc: (person: Player) -> Boolean,
        printFunc: (person: Player) -> Unit
    ) {
        players.forEach { player ->
            when (player) {
                is Dealer -> player.pickIfRequired(cardDeck)
                is Participant -> pickOrNot(player, retryFunc, printFunc)
            }
        }
    }

    fun getBettingResult(): List<ParticipantMoneyResult> {
        val participants = players.filterIsInstance<Participant>()
        return participants.map { participant -> calculateBetting(participant) }
    }

    fun getGameResult(): GameResult {
        return GameResult(
            dealerName = dealer.name,
            participantResult = players.filterIsInstance<Participant>().map { ParticipantResult(it.name, it.getGameResult(dealer)) }
        )
    }

    private tailrec fun pickOrNot(
        player: Player,
        retryFunc: (player: Player) -> Boolean,
        printFunc: (player: Player) -> Unit
    ) {
        // 최고 점수보다 많을시 종료
        if (player.isBurst()) {
            return
        }

        // 사용자가 종료할시 종료
        if (retryFunc(player).not()) {
            printFunc(player)
            return
        }

        val card = dealer.pickCard(cardDeck)
        player.addCard(card)
        printFunc(player)

        pickOrNot(player, retryFunc, printFunc)
    }

    private fun calculateBetting(participant: Participant): ParticipantMoneyResult {
        if (dealer.isBlackJack() && participant.isBlackJack()) {
            return ParticipantMoneyResult(participant.name, participant.getWinMoney())
        }

        if (participant.getCardSize() == BASIC_CARD_COUNT && participant.isBlackJack()) {
            return ParticipantMoneyResult(participant.name, participant.getBlackJackMoney())
        }

        if (dealer.isBurst()) {
            return ParticipantMoneyResult(participant.name, participant.getWinMoney())
        }

        if (participant.getGameResult(dealer) == WinOrLose.WIN) {
            return ParticipantMoneyResult(participant.name, participant.getWinMoney())
        }

        return ParticipantMoneyResult(participant.name, participant.getLoseMoney())
    }

    companion object {
        private const val BASIC_CARD_COUNT = 2
    }
}
