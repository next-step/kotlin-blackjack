package blackjack.controller

import blackjack.common.Policy.INITIAL_CARD_COUNT
import blackjack.domain.CardDeck
import blackjack.domain.Participant
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackJackMachine(
    private val cardDeck: CardDeck = CardDeck(),
) {
    fun execute() {
        val players = initialize()
        OutputView.printInitialCards(players)
    }

    private fun initialize(): List<Participant> {
        val playerNameList = InputView.readName().split(",")
        val players = playerNameList.map { Participant(name = it) }

        players.forEach { player ->
            repeat(INITIAL_CARD_COUNT) {
                player.addCard(cardDeck.hit())
            }
        }

        return players
    }
}
