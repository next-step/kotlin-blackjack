package blackjack.domain

import blackjack.domain.Score.Companion.toScore
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardGameScoreTest {

    private lateinit var sut: CardGameScore

    @Test
    fun `블랙잭 카드게임에 맞는 최종 스코어를 반환할 수 있다`() {
        // given
        sut = BlackJackScore()

        val heartTwo = Card.newCard {
            CardImage.HEART with CardLevel.TWO
        }
        val heartThree = Card.newCard {
            CardImage.HEART with CardLevel.THREE
        }
        val cards = Cards(listOf(heartTwo, heartThree))

        // when
        val score = sut.calculateScore(cards)

        // then
        assertThat(score).isEqualTo(5.toScore())
    }

    @Test
    fun `ACE 값이 1로 계산될 수 있다`() {
        // given
        sut = BlackJackScore()

        val heartAce = Card.newCard {
            CardImage.HEART with CardLevel.ACE
        }
        val heartTen = Card.newCard {
            CardImage.HEART with CardLevel.TEN
        }
        val diamondTen = Card.newCard {
            CardImage.DIAMOND with CardLevel.TEN
        }
        val cards = Cards(listOf(heartAce, heartTen, diamondTen))

        // when
        val score = sut.calculateScore(cards)

        // then
        assertThat(score).isEqualTo(21.toScore())
    }

    @Test
    fun `ACE 값이 11로 계산될 수 있다`() {
        // given
        sut = BlackJackScore()

        val heartAce = Card.newCard {
            CardImage.HEART with CardLevel.ACE
        }
        val heartTen = Card.newCard {
            CardImage.HEART with CardLevel.TEN
        }
        val cards = Cards(listOf(heartAce, heartTen))

        // when
        val score = sut.calculateScore(cards)

        // then
        assertThat(score).isEqualTo(21.toScore())
    }

    @Test
    fun `2개의 ACE 값이 각각 11 로 계산될 수 있다`() {
        // given
        sut = BlackJackScore()

        val heartAce = Card.newCard {
            CardImage.HEART with CardLevel.ACE
        }
        val diamondTen = Card.newCard {
            CardImage.DIAMOND with CardLevel.ACE
        }
        val cards = Cards(listOf(heartAce, diamondTen))

        // when
        val score = sut.calculateScore(cards)

        // then
        assertThat(score).isEqualTo(22.toScore())
    }

    @Test
    fun `3개의 ACE 값이 각각 1, 11, 11 로 계산될 수 있다`() {
        // given
        sut = BlackJackScore()

        val heartAce = Card.newCard {
            CardImage.HEART with CardLevel.ACE
        }
        val diamondTen = Card.newCard {
            CardImage.DIAMOND with CardLevel.ACE
        }
        val cloverTen = Card.newCard {
            CardImage.CLOVER with CardLevel.ACE
        }
        val cards = Cards(listOf(heartAce, diamondTen, cloverTen))

        // when
        val score = sut.calculateScore(cards)

        // then
        assertThat(score).isEqualTo(23.toScore())
    }

    @Test
    fun `4개의 ACE 값이 각각 1, 1, 11, 11 로 계산될 수 있다`() {
        // given
        sut = BlackJackScore()

        val heartAce = Card.newCard {
            CardImage.HEART with CardLevel.ACE
        }
        val diamondTen = Card.newCard {
            CardImage.DIAMOND with CardLevel.ACE
        }
        val cloverTen = Card.newCard {
            CardImage.CLOVER with CardLevel.ACE
        }
        val spadeTen = Card.newCard {
            CardImage.SPADE with CardLevel.ACE
        }
        val cards = Cards(listOf(heartAce, diamondTen, cloverTen, spadeTen))

        // when
        val score = sut.calculateScore(cards)

        // then
        assertThat(score).isEqualTo(24.toScore())
    }
}
