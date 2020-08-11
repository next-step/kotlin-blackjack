package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class WinnerTest {

    @Test
    fun `딜러가 1승 1패일 경우`() {
        val moshi = Player("moshi").apply {
            requestCard(Card(Kinds.TWO, Shape.HEART))
            requestCard(Card(Kinds.EIGHT, Shape.SPADE))
            requestCard(Card(Kinds.ACE, Shape.CLOVER))
        }
        val gson = Player("gson").apply {
            requestCard(Card(Kinds.SEVEN, Shape.HEART))
            requestCard(Card(Kinds.KING, Shape.SPADE))
        }
        val dealer = Dealer().apply {
            requestCard(Card(Kinds.THREE, Shape.HEART))
            requestCard(Card(Kinds.NINE, Shape.HEART))
        }.also {
            if (it.checkIfGetExtraCardOrNot()) {
                it.requestCard(Card(Kinds.EIGHT, Shape.HEART))
            }
        }

        val totalScore = Winner.getTotalScore(dealer, listOf(moshi, gson))

        assertThat(totalScore.first().win).isEqualTo(1)
        assertThat(totalScore.first().lose).isEqualTo(1)
    }
}
