package next.step.blackjack.domain

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class GameCardsTest : DescribeSpec({

    describe("GameCards init") {
        context("카지노가 생성되면") {
            it("6덱 만큼의 카드를 가지는 GameCards를 만들어준다.") {
                GameCards.shuffled().size() shouldBe 6 * CardFace.values().size * CardSymbol.values().size
            }
        }
    }

    describe("GameCards method") {
        context("pop") {
            it("가지고 있는 카드리스트의 첫번째를 제공한다.") {
                GameCards.of(
                    listOf(
                        Card.of(CardFace.ACE, CardSymbol.CLUB),
                        Card.of(CardFace.ACE, CardSymbol.DIAMOND)
                    )
                ).pop() shouldBe Card.of(CardFace.ACE, CardSymbol.CLUB)
            }

            it("가지고 있는 카드리스트의 첫번째를 제거한다.") {
                val gameCards = GameCards.of(
                    listOf(
                        Card.of(CardFace.ACE, CardSymbol.CLUB),
                        Card.of(CardFace.ACE, CardSymbol.DIAMOND)
                    )
                )

                gameCards.pop()

                gameCards shouldBe GameCards.of(
                    listOf(Card.of(CardFace.ACE, CardSymbol.DIAMOND))
                )
            }
        }
    }
})
