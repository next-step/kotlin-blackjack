package blackjack

import blackjack.domain.model.Card
import blackjack.domain.model.Cards
import blackjack.domain.model.player.Dealer
import blackjack.domain.model.player.Gambler
import blackjack.domain.model.Name
import blackjack.domain.model.Pattern
import blackjack.domain.model.Sign
import blackjack.domain.model.WinDrawLose
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

fun Gambler(name: Name = Name.from("kim"), cards: Cards = Cards.empty()): Gambler {
    return Gambler.from(name, cards)
}

class GamblerTest : StringSpec({
    "갬블러 생성시 카드를 가지고 있지 말아야 한다." {
        val gambler = Gambler()
        gambler.cards.values.size shouldBe 0
    }

    "카드들의 점수 합이 21점 이상이면 겜블러는 더이상 카드를 뽑을 수 없다." {
        val gambler = Gambler(
            cards = Cards.from(
                setOf(
                    Card.of(Pattern.SPACE, Sign.SEVEN),
                    Card.of(Pattern.HEART, Sign.SEVEN),
                    Card.of(Pattern.CLOVER, Sign.SEVEN)
                )
            )
        )
        gambler.shouldDraw() shouldBe false
    }

    "카드들의 점수 합이 1 ~ 20점 이내면 겜블러는 카드를 뽑을 수 있다." {
        Sign.values().forEach {
            val gambler = Gambler(
                cards = Cards.from(
                    setOf(
                        Card.of(Pattern.SPACE, Sign.NINE),
                        Card.of(Pattern.HEART, it)
                    )
                )
            )

            gambler.shouldDraw() shouldBe true
        }
    }

    "겜블러의 카드들의 합이 21을 넘으면 버스트다." {
        val gambler = Gambler(
            cards = Cards.from(
                setOf(
                    Card.of(Pattern.SPACE, Sign.EIGHT),
                    Card.of(Pattern.HEART, Sign.SEVEN),
                    Card.of(Pattern.CLOVER, Sign.SEVEN)
                )
            )
        )
        gambler.cards.score().isBust() shouldBe true
    }

    "겜블러와 딜러가 버스트가 되면 딜러가 이긴다." {
        val gambler = Gambler(
            cards = Cards.from(
                setOf(
                    Card.of(Pattern.SPACE, Sign.EIGHT),
                    Card.of(Pattern.HEART, Sign.SEVEN),
                    Card.of(Pattern.CLOVER, Sign.SEVEN)
                )
            )
        )
        val dealer = Dealer(
            cards = Cards.from(
                setOf(
                    Card.of(Pattern.SPACE, Sign.EIGHT),
                    Card.of(Pattern.HEART, Sign.SEVEN),
                    Card.of(Pattern.CLOVER, Sign.SEVEN)
                )
            )
        )
        gambler.winDrawLose(dealer) shouldBe WinDrawLose.LOSE
    }

    "겜블러는 버스트가 안되고, 딜러가 버스트가 되면 겜블러가 이긴다." {
        val gambler = Gambler(
            cards = Cards.from(
                setOf(
                    Card.of(Pattern.HEART, Sign.SEVEN),
                    Card.of(Pattern.CLOVER, Sign.SEVEN)
                )
            )
        )
        val dealer = Dealer(
            cards = Cards.from(
                setOf(
                    Card.of(Pattern.SPACE, Sign.EIGHT),
                    Card.of(Pattern.HEART, Sign.SEVEN),
                    Card.of(Pattern.CLOVER, Sign.SEVEN)
                )
            )
        )
        gambler.winDrawLose(dealer) shouldBe WinDrawLose.WIN
    }

    "겜블러와 딜러가 버스트가 안되고, 겜블러의 숫자가 높을 경우 겜블러가 이긴다." {
        val gambler = Gambler(
            cards = Cards.from(
                setOf(
                    Card.of(Pattern.HEART, Sign.EIGHT),
                    Card.of(Pattern.CLOVER, Sign.SEVEN)
                )
            )
        )
        val dealer = Dealer(
            cards = Cards.from(
                setOf(
                    Card.of(Pattern.HEART, Sign.SEVEN),
                    Card.of(Pattern.CLOVER, Sign.SEVEN)
                )
            )
        )
        gambler.winDrawLose(dealer) shouldBe WinDrawLose.WIN
    }

    "겜블러와 딜러가 버스트가 안되고, 딜러의 숫자가 높을 경우 딜러가 이긴다." {
        val gambler = Gambler(
            cards = Cards.from(
                setOf(
                    Card.of(Pattern.HEART, Sign.SEVEN),
                    Card.of(Pattern.CLOVER, Sign.SEVEN)
                )
            )
        )
        val dealer = Dealer(
            cards = Cards.from(
                setOf(
                    Card.of(Pattern.HEART, Sign.EIGHT),
                    Card.of(Pattern.CLOVER, Sign.SEVEN)
                )
            )
        )
        gambler.winDrawLose(dealer) shouldBe WinDrawLose.LOSE
    }

    "겜블러와 딜러가 버스트가 안되고, 딜러의 숫자가 같을 경우 무승부다." {
        val gambler = Gambler(
            cards = Cards.from(
                setOf(
                    Card.of(Pattern.HEART, Sign.SEVEN),
                    Card.of(Pattern.CLOVER, Sign.SEVEN)
                )
            )
        )
        val dealer = Dealer(
            cards = Cards.from(
                setOf(
                    Card.of(Pattern.HEART, Sign.SEVEN),
                    Card.of(Pattern.CLOVER, Sign.SEVEN)
                )
            )
        )
        gambler.winDrawLose(dealer) shouldBe WinDrawLose.DRAW
    }
})
