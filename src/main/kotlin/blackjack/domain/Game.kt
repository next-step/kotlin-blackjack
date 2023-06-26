package blackjack.domain

import blackjack.domain.deck.Deck
import blackjack.domain.deck.DeckShuffleStarategy
import blackjack.exception.PlayerLoseException

class Game(deckShuffleStrategy: DeckShuffleStarategy) {

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

    fun checkPlayerIsLose(player: Player) {
        if (!ruleChecker.checkSumOfCardNumbers(player.calculateSumOfCardNumbers())) {
            throw PlayerLoseException("${player.name}의 카드가 ${RuleChecker.CONDITION_TO_WIN_BLACK_JACK}을 넘었습니다.")
        }
    }
}
