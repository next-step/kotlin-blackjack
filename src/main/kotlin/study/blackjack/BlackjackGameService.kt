package study.blackjack

import study.blackjack.model.BlackjackPlayer
import study.blackjack.model.Card
import study.blackjack.model.Suit
import kotlin.random.Random


/**
 * @author 이상준
 */
class BlackjackGameService {
    fun addCard(player: BlackjackPlayer) {
        val suit = Suit.findSuit(randomSuit()) ?: throw IllegalArgumentException("잘못된 카드 모양입니다.")
        player.addCard(Card(suit, randomNumber()))
    }

    private fun randomNumber(): Int {
        return Random.nextInt(MIN_RANDOM_CARD_NUMBER, MAX_RANDOM_CARD_NUMBER)
    }

    private fun randomSuit(): Int {
        return Random.nextInt(MAX_RANDOM_CARD_SUIT)
    }

    companion object {
        const val BLACKJACK = 21
        private const val MIN_RANDOM_CARD_NUMBER = 1
        private const val MAX_RANDOM_CARD_NUMBER = 13
        private const val MAX_RANDOM_CARD_SUIT = 3
    }

}

