package blackjack.domain.card

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class CardSetTest : FunSpec({
    test("카드 뭉치에 새로운 카드를 추가할 수 있다") {
        val cardSet = CardSet.empty()
        val newCardSet = cardSet.addCard(Card.of(CardKind.DIAMOND, CardNumber.EIGHT))
        newCardSet.cards shouldBe listOf(
            Card.of(CardKind.DIAMOND, CardNumber.EIGHT)
        )
    }

    test("카드 뭉치에 새로운 카드뭉치를 추가할 수 있다") {
        val cardSet = CardSet.empty()
        val addedCardSet = CardSet.of(
            Card.of(CardKind.DIAMOND, CardNumber.EIGHT),
            Card.of(CardKind.DIAMOND, CardNumber.NINE),
            Card.of(CardKind.DIAMOND, CardNumber.TEN)
        )
        val newCardSet = cardSet.addCard(addedCardSet)
        newCardSet.cards shouldBe listOf(
            Card.of(CardKind.DIAMOND, CardNumber.EIGHT),
            Card.of(CardKind.DIAMOND, CardNumber.NINE),
            Card.of(CardKind.DIAMOND, CardNumber.TEN)
        )
    }

    test("카드 뭉치에 중복된 카드가 있으면 예외가 발생한다") {
        shouldThrow<IllegalArgumentException> {
            CardSet.of(
                Card.of(CardKind.DIAMOND, CardNumber.EIGHT),
                Card.of(CardKind.DIAMOND, CardNumber.EIGHT)
            )
        }
    }

    test("카드 뭉치에 중복된 카드를 추가하면 예외가 발생한다") {
        val cardSet = CardSet.of(Card.of(CardKind.DIAMOND, CardNumber.EIGHT))
        shouldThrow<IllegalArgumentException> { cardSet.addCard(Card.of(CardKind.DIAMOND, CardNumber.EIGHT)) }
    }
    test("카드 뭉치에 몇 장의 카드가 들어있는지 알 수 있다") {
        val cardSet = CardSet.of(Card.of(CardKind.DIAMOND, CardNumber.EIGHT))
        cardSet.size shouldBe 1
    }

    test("카드 뭉치의 점수를 합산 할 수 있다") {
        val cardSet = CardSet.of(
            Card.of(CardKind.DIAMOND, CardNumber.EIGHT),
            Card.of(CardKind.DIAMOND, CardNumber.NINE)
        )

        val cardScorePolicy = object : CardScorePolicy {
            override fun getScore(card: Card): CardScore {
                return CardScore(
                    when (card.number) {
                        CardNumber.EIGHT -> 8
                        CardNumber.NINE -> 9
                        else -> 0
                    }
                )
            }
        }
        cardSet.sum(cardScorePolicy) shouldBe CardScore(17)
    }

    test("카드 뭉치의 합을 계산하는 방법 중 21에 가장 가까울 때의 값을 계산할 수 있다.") {
        val cardSet = CardSet.of(
            Card.of(CardKind.DIAMOND, CardNumber.ACE),
            Card.of(CardKind.DIAMOND, CardNumber.EIGHT)
        )

        val cardScorePolicyGroup = CardScorePolicyGroup(listOf(CardScoreNormalAcePolicy, CardScoreSpecialAcePolicy))

        val actual = cardSet.sumOfBest(cardScorePolicyGroup, 21)

        actual shouldBe CardScore(19)
    }

    test("카드 뭉치의 합을 계산하는 여러가지 방법 중 가장 작은 값을 계산할 수 있다.") {
        val cardSet = CardSet.of(
            Card.of(CardKind.DIAMOND, CardNumber.ACE),
            Card.of(CardKind.DIAMOND, CardNumber.EIGHT)
        )

        val cardScorePolicyGroup = CardScorePolicyGroup(listOf(CardScoreNormalAcePolicy, CardScoreSpecialAcePolicy))

        val actual = cardSet.sumOfMin(cardScorePolicyGroup)

        actual shouldBe CardScore(9)
    }
})
