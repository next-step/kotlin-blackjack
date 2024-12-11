package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardTest {
    @Test
    fun isOverMaxSumTest() {
        val ace = Card(Rank.ACE, Suit.HEARTS)
        val currentSum = 20
        val isOverMaxSumResultFalse = ace.isOverMaxSum(currentSum)
        assertThat(isOverMaxSumResultFalse).isFalse()

        val currentSum2 = 21
        val isOverMaxSumResultTrue = ace.isOverMaxSum(currentSum2)
        assertThat(isOverMaxSumResultTrue).isTrue()
    }

    @Test
    fun sumValuesTest() {
        val listOfCards =
            listOf(
                Card(Rank.TWO, Suit.HEARTS),
                Card(Rank.THREE, Suit.HEARTS),
                Card(Rank.FOUR, Suit.HEARTS),
            )
        assertThat(listOfCards.sumValues()).isEqualTo(2 + 3 + 4)

        val listOfCardsWithAce =
            listOf(
                Card(Rank.ACE, Suit.HEARTS),
                Card(Rank.TEN, Suit.HEARTS),
            )
        assertThat(listOfCardsWithAce.sumValues()).isEqualTo(11 + 10)

        val listOfCardsWithAceTreatedAsOne =
            listOf(
                Card(Rank.ACE, Suit.HEARTS),
                Card(Rank.ACE, Suit.HEARTS),
            )
        assertThat(listOfCardsWithAceTreatedAsOne.sumValues()).isEqualTo(11 + 1) // 11 + 11 = 22 이므로 두번째 ACE 는 1로 간주
    }
}
