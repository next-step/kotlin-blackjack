package blackjack

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.card.Symbol
import blackjack.domain.card.Type
import blackjack.domain.game.Hand
import blackjack.domain.game.HandResult
import blackjack.domain.player.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

private val TWO = Card(Symbol.TWO, Type.CLUB)
private val NINE = Card(Symbol.NINE, Type.CLUB)
private val TEN = Card(Symbol.TEN, Type.CLUB)
private val SIX = Card(Symbol.SIX, Type.CLUB)

@Suppress("NonAsciiCharacters")
class BlackJackTestUtil {

    @Test
    fun `score를 입력하면 그에 맞는 Cards가 반환된다`() {
        (2..22).forEach {
            assertThat(Cards(it).score.value).isEqualTo(it)
        }
    }
}

@Suppress("TestFunctionName")
fun Cards(score: Int, isBlackJack: Boolean = false): Cards {
    return Cards(findCards(score, isBlackJack))
}

@Suppress("TestFunctionName")
fun DealerHand(score: Int, isBlackJack: Boolean = false): Hand {
    val cards = Cards(score, isBlackJack)
    val dealerHand: Hand = DealerHand()
    return cards.cards.fold(dealerHand) { hand, card ->
        hand.hit(card)
    }
}

@Suppress("TestFunctionName")
fun PlayerHand(score: Int, isBlackJack: Boolean = false): Hand {
    val cards = Cards(score, isBlackJack)
    val playerHand: Hand = PlayerHand()
    return cards.cards.fold(playerHand) { hand, card ->
        hand.hit(card)
    }
}

private fun findCards(score: Int, isBlackJack: Boolean): List<Card> {
    if (score in 2..10) {
        val symbol = Symbol.values().first { it.score.value == score }
        return listOf(Card(symbol, Type.CLUB))
    }
    if (score == 11) {
        return listOf(TWO, NINE)
    }
    if (score <= 20) {
        val symbol = Symbol.values().first { it.score.value == score - 10 }
        return listOf(TEN, Card(symbol, Type.CLUB))
    }
    if (score == 21 && isBlackJack) {
        return listOf(TEN, Card(Symbol.ACE, Type.CLUB))
    }
    if (score == 21 && !isBlackJack) {
        return listOf(TWO, NINE, TEN)
    }
    if (score == 22) {
        return listOf(TEN, SIX, SIX)
    }
    throw IllegalArgumentException("$score 은/는 테스트에서 지원하지 않는 점수입니다.")
}


class AnswerYesThird : AnswerProvider {

    private var count = YES_COUNT

    override fun getAnswer(gamer: Gamer): PlayerAnswer {
        if (count > 0) {
            count--
            return PlayerAnswer.YES
        }
        return PlayerAnswer.NO
    }

    companion object {
        const val YES_COUNT = 3
    }
}

object FinishedHand : Hand {
    override val cards = Cards.createEmpty()

    override fun canHit() = false

    override fun getResult(): HandResult {
        throw UnsupportedOperationException()
    }

    override fun hit(card: Card): Hand {
        throw UnsupportedOperationException()
    }
}

class NotFinishHand : Hand {

    var drawCount = 0
        private set

    override val cards = Cards.createEmpty()

    override fun canHit() = true

    override fun getResult(): HandResult {
        throw UnsupportedOperationException()
    }

    override fun hit(card: Card): Hand {
        drawCount++
        return this
    }
}
