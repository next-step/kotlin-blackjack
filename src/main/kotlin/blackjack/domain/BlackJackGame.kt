package blackjack.domain

import blackjack.domain.deck.Deck
import blackjack.domain.deck.DeckShuffleStarategy

class BlackJackGame(deckShuffleStrategy: DeckShuffleStarategy) {

    private val deck = Deck(deckShuffleStrategy)
    private val ruleChecker = RuleChecker()

    fun firstDraw(blackJackGamerList: List<BlackJackGamer>) {
        blackJackGamerList.forEach {
            it.addCards(deck.drawTwoCard())
        }
    }

    fun oneGamerDraw(blackJackGamer: BlackJackGamer) {
        blackJackGamer.addCard(deck.drawCard())
    }

    fun checkBlackJackGamerIsDraw(blackJackGamer: BlackJackGamer): Boolean {
        return ruleChecker.checkSumOfCardNumbers(blackJackGamer)
    }
}
