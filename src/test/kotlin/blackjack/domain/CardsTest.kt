package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.util.LinkedList

class CardsTest {
    @Test
    fun `카드를 맨 앞의 카드 한장을 뽑아서 리턴 해야 한다`() {
        val cards = GameCards(
            LinkedList(
                mutableListOf(
                    Card(CardSuite.DIAMOND, CardNumber.ACE),
                    Card(CardSuite.DIAMOND, CardNumber.TWO)
                )
            )
        )

        val card = cards.poll()

        assertThat(card).isEqualTo(Card(CardSuite.DIAMOND, CardNumber.ACE))
    }

    @ParameterizedTest
    @MethodSource("cardGenerate")
    fun `카드에 맞게 점수를 가져야 한다`(cardAndScore: Pair<Set<Card>, Int>) {
        val cards: Cards = Cards(cardAndScore.first)

        assertThat(cards.score)
            .isEqualTo(cardAndScore.second)
    }

    companion object {
        @JvmStatic
        fun cardGenerate(): List<Pair<Set<Card>, Int>> {
            return listOf(
                Pair(setOf(Card(CardSuite.CLOVER, CardNumber.QUEEN), Card(CardSuite.CLOVER, CardNumber.KING)), 20),
                Pair(setOf(Card(CardSuite.CLOVER, CardNumber.QUEEN), Card(CardSuite.CLOVER, CardNumber.KING), Card(CardSuite.CLOVER, CardNumber.ACE)), 21),
                Pair(setOf(Card(CardSuite.CLOVER, CardNumber.ACE), Card(CardSuite.CLOVER, CardNumber.KING)), 21),
                Pair(setOf(Card(CardSuite.CLOVER, CardNumber.TWO), Card(CardSuite.CLOVER, CardNumber.THREE), Card(CardSuite.CLOVER, CardNumber.NINE)), 14)

            )
        }
    }
}

// [kotlin.Pair<? extends java.util.Set<blackjack.domain.Card>, java.lang.Integer> arg0]
// kotlin.Pair<? extends java.util.Set<blackjack.domain.Card>, java.lang.Integer>)].
