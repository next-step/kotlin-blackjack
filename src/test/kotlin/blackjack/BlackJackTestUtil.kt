package blackjack

import blackjack.domain.card.Card
import blackjack.domain.card.Hand
import blackjack.domain.card.Symbol
import blackjack.domain.card.Type
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

private val TWO = Card(Symbol.TWO, Type.CLUB)
private val NINE = Card(Symbol.NINE, Type.CLUB)
private val TEN = Card(Symbol.TEN, Type.CLUB)

@Suppress("TestFunctionName")
fun Hand(score: Int, isBlackJack: Boolean = false): Hand {
    return Hand(findCards(score, isBlackJack))
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
    if (score <= 30) {
        val symbol = Symbol.values().first { it.score.value == score - 20 }
        return listOf(TEN, TEN, Card(symbol, Type.CLUB))
    }
    throw IllegalArgumentException("$score 은/는 테스트에서 지원하지 않는 점수입니다.")
}

@Suppress("NonAsciiCharacters")
class BlackJackTestUtil {

    @Test
    fun `score를 입력하면 그에 맞는 Hand가 반환된다`() {
        (2..30).forEach {
            assertThat(Hand(it).score.value).isEqualTo(it)
        }
    }
}
