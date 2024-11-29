package blackjack

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class HandTest : StringSpec({
    "손패는 카드를 추가할 수 있다" {
        val sut = Hand()

        sut.add(Card(CardNumber.KING, Suit.SPADES))

        sut.cards.size shouldBe 1
        sut.cards[0] shouldBe Card(CardNumber.KING, Suit.SPADES)
    }

    "손패는 가진 카드의 숫자 합을 구할 수 있다(ACE 0개)" {
        forAll(
            row(
                listOf(
                    Card(CardNumber.TEN, Suit.SPADES),
                    Card(CardNumber.KING, Suit.SPADES),
                ),
                20,
            ),
            row(
                listOf(
                    Card(CardNumber.TEN, Suit.SPADES),
                    Card(CardNumber.SEVEN, Suit.SPADES),
                ),
                17,
            ),
            row(
                listOf(
                    Card(CardNumber.TEN, Suit.SPADES),
                    Card(CardNumber.SEVEN, Suit.SPADES),
                    Card(CardNumber.FIVE, Suit.SPADES),
                ),
                22,
            ),
            row(
                listOf(
                    Card(CardNumber.TEN, Suit.SPADES),
                    Card(CardNumber.SEVEN, Suit.SPADES),
                    Card(CardNumber.FOUR, Suit.SPADES),
                ),
                21,
            ),
        ) { initialCards, expected ->
            val sut = Hand(initialCards)
            val result = sut.sumOfHand()
            result shouldBe expected
        }
    }

    "손패는 가진 카드의 숫자 합을 21에 최대한 가깝게 구할 수 있다(ACE 1개)" {
        forAll(
            row(
                listOf(
                    Card(CardNumber.ACE, Suit.SPADES),
                    Card(CardNumber.KING, Suit.SPADES),
                ),
                21,
            ),
            row(
                listOf(
                    Card(CardNumber.ACE, Suit.SPADES),
                    Card(CardNumber.NINE, Suit.SPADES),
                ),
                20,
            ),
            row(
                listOf(
                    Card(CardNumber.ACE, Suit.SPADES),
                    Card(CardNumber.NINE, Suit.SPADES),
                    Card(CardNumber.KING, Suit.SPADES),
                ),
                20,
            ),
            row(
                listOf(
                    Card(CardNumber.ACE, Suit.SPADES),
                    Card(CardNumber.TEN, Suit.SPADES),
                    Card(CardNumber.KING, Suit.SPADES),
                ),
                21,
            ),
            row(
                listOf(
                    Card(CardNumber.ACE, Suit.SPADES),
                    Card(CardNumber.TWO, Suit.SPADES),
                ),
                13,
            ),
            row(
                listOf(
                    Card(CardNumber.ACE, Suit.SPADES),
                    Card(CardNumber.TWO, Suit.SPADES),
                    Card(CardNumber.NINE, Suit.SPADES),
                ),
                12,
            ),
        ) { initialCards, expected ->
            val sut = Hand(initialCards)
            val result = sut.sumOfHand()
            result shouldBe expected
        }
    }

    "손패는 가진 카드의 숫자 합을 21에 최대한 가깝게 구할 수 있다(ACE 2개)" {
        forAll(
            row(
                listOf(
                    Card(CardNumber.ACE, Suit.SPADES),
                    Card(CardNumber.ACE, Suit.HEARTS),
                ),
                12,
            ),
            row(
                listOf(
                    Card(CardNumber.ACE, Suit.SPADES),
                    Card(CardNumber.ACE, Suit.HEARTS),
                    Card(CardNumber.NINE, Suit.HEARTS),
                ),
                21,
            ),
            row(
                listOf(
                    Card(CardNumber.ACE, Suit.SPADES),
                    Card(CardNumber.ACE, Suit.HEARTS),
                    Card(CardNumber.TEN, Suit.HEARTS),
                ),
                12,
            ),
            row(
                listOf(
                    Card(CardNumber.ACE, Suit.SPADES),
                    Card(CardNumber.ACE, Suit.HEARTS),
                    Card(CardNumber.TEN, Suit.HEARTS),
                    Card(CardNumber.KING, Suit.HEARTS),
                ),
                22,
            ),
        ) { initialCards, expected ->
            val sut = Hand(initialCards)
            val result = sut.sumOfHand()
            result shouldBe expected
        }
    }

    "손패는 가진 카드의 숫자 합을 21에 최대한 가깝게 구할 수 있다(ACE 3개)" {
        forAll(
            row(
                listOf(
                    Card(CardNumber.ACE, Suit.SPADES),
                    Card(CardNumber.ACE, Suit.HEARTS),
                    Card(CardNumber.ACE, Suit.CLUBS),
                ),
                13,
            ),
            row(
                listOf(
                    Card(CardNumber.ACE, Suit.SPADES),
                    Card(CardNumber.ACE, Suit.HEARTS),
                    Card(CardNumber.ACE, Suit.CLUBS),
                    Card(CardNumber.EIGHT, Suit.CLUBS),
                ),
                21,
            ),
            row(
                listOf(
                    Card(CardNumber.ACE, Suit.SPADES),
                    Card(CardNumber.ACE, Suit.HEARTS),
                    Card(CardNumber.ACE, Suit.CLUBS),
                    Card(CardNumber.NINE, Suit.CLUBS),
                ),
                12,
            ),
            row(
                listOf(
                    Card(CardNumber.ACE, Suit.SPADES),
                    Card(CardNumber.ACE, Suit.HEARTS),
                    Card(CardNumber.ACE, Suit.CLUBS),
                    Card(CardNumber.NINE, Suit.CLUBS),
                    Card(CardNumber.NINE, Suit.CLUBS),
                ),
                21,
            ),
            row(
                listOf(
                    Card(CardNumber.ACE, Suit.SPADES),
                    Card(CardNumber.ACE, Suit.HEARTS),
                    Card(CardNumber.ACE, Suit.CLUBS),
                    Card(CardNumber.NINE, Suit.CLUBS),
                    Card(CardNumber.TEN, Suit.CLUBS),
                ),
                22,
            ),
        ) { initialCards, expected ->
            val sut = Hand(initialCards)
            val result = sut.sumOfHand()
            result shouldBe expected
        }
    }

    "손패는 가진 카드의 숫자 합을 21에 최대한 가깝게 구할 수 있다(ACE 4개)" {
        forAll(
            row(
                listOf(
                    Card(CardNumber.ACE, Suit.SPADES),
                    Card(CardNumber.ACE, Suit.HEARTS),
                    Card(CardNumber.ACE, Suit.CLUBS),
                    Card(CardNumber.ACE, Suit.DIAMONDS),
                ),
                14,
            ),
            row(
                listOf(
                    Card(CardNumber.ACE, Suit.SPADES),
                    Card(CardNumber.ACE, Suit.HEARTS),
                    Card(CardNumber.ACE, Suit.CLUBS),
                    Card(CardNumber.ACE, Suit.DIAMONDS),
                    Card(CardNumber.SEVEN, Suit.CLUBS),
                ),
                21,
            ),
            row(
                listOf(
                    Card(CardNumber.ACE, Suit.SPADES),
                    Card(CardNumber.ACE, Suit.HEARTS),
                    Card(CardNumber.ACE, Suit.CLUBS),
                    Card(CardNumber.ACE, Suit.DIAMONDS),
                    Card(CardNumber.EIGHT, Suit.CLUBS),
                ),
                12,
            ),
            row(
                listOf(
                    Card(CardNumber.ACE, Suit.SPADES),
                    Card(CardNumber.ACE, Suit.HEARTS),
                    Card(CardNumber.ACE, Suit.CLUBS),
                    Card(CardNumber.ACE, Suit.DIAMONDS),
                    Card(CardNumber.EIGHT, Suit.CLUBS),
                    Card(CardNumber.NINE, Suit.CLUBS),
                ),
                21,
            ),
            row(
                listOf(
                    Card(CardNumber.ACE, Suit.SPADES),
                    Card(CardNumber.ACE, Suit.HEARTS),
                    Card(CardNumber.ACE, Suit.CLUBS),
                    Card(CardNumber.ACE, Suit.DIAMONDS),
                    Card(CardNumber.EIGHT, Suit.CLUBS),
                    Card(CardNumber.TEN, Suit.CLUBS),
                ),
                22,
            ),
        ) { initialCards, expected ->
            val sut = Hand(initialCards)
            val result = sut.sumOfHand()
            result shouldBe expected
        }
    }

    "손패는 스스로 파산했는지 체크할 수 있다" {
        forAll(
            row(
                listOf(
                    Card(CardNumber.NINE, Suit.SPADES),
                    Card(CardNumber.EIGHT, Suit.HEARTS),
                    Card(CardNumber.FOUR, Suit.SPADES),
                ),
                false,
            ),
            row(
                listOf(
                    Card(CardNumber.NINE, Suit.SPADES),
                    Card(CardNumber.EIGHT, Suit.HEARTS),
                    Card(CardNumber.FIVE, Suit.SPADES),
                ),
                true,
            ),
        ) { initialCards, expected ->
            val sut = Hand(initialCards)
            val result = sut.isBust()
            result shouldBe expected
        }
    }
})
