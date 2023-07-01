package blackjack.domain

import blackjack.domain.deck.Deck
import blackjack.domain.deck.DeckShuffleStarategy

class BlackJackGame(deckShuffleStrategy: DeckShuffleStarategy) {

    private val deck = Deck(deckShuffleStrategy)
    private val ruleChecker = RuleChecker()

    fun firstDraw(playerList: List<Player>) {
        playerList.forEach {
            it.addCards(deck.drawTwoCard())
        }
    }

    fun onePlayerDraw(player: Player) {
        player.addCard(deck.drawCard())
    }

    fun checkPlayerIsLose(player: Player): Boolean {
        return ruleChecker.checkSumOfCardNumbers(player.calculateSumOfCardNumbers())
    }
}
