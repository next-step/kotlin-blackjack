package blackjack.domain

import blackjack.domain.card.CardDeck
import blackjack.domain.dto.GameResult
import blackjack.domain.dto.ParticipantResult
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

    companion object {
        private const val BASIC_CARD_COUNT = 2
    }
}
