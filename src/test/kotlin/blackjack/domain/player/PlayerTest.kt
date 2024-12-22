package blackjack.domain.player

import blackjack.domain.card.Denomination
import blackjack.domain.card.PlayingCard
import blackjack.domain.card.Suit
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class PlayerTest : StringSpec({
    "플레이어를 생성한다" {
        val player = Player.of("aimbe")
        player.cards().size shouldBe 0
        player.isRunning() shouldBe true
    }

    "플레이어의 이름이 빈 문자열이면 예외가 발생한다" {
        shouldThrow<IllegalArgumentException> {
            Player.of("")
        }.message shouldBe "플레이어 이름은 비어있을 수 없습니다."
    }

    "플레이어는 카드를 한장 받을 수 있다" {
        val player = Player.of("aimbe")
        val card = PlayingCard.of(Suit.HEARTS, Denomination.ACE)

        player.drawCard(card)

        player.cards().size shouldBe 1
        player.cards().first() shouldBe card
    }

    "플레이어가 카드를 2장 받으면 Hit 상태가 된다" {
        val player = Player.of("aimbe")
        player.drawCard(PlayingCard.of(Suit.HEARTS, Denomination.NINE))
        player.drawCard(PlayingCard.of(Suit.SPADES, Denomination.THREE))

        player.isRunning() shouldBe true
    }

    "플레이어가 블랙잭을 받으면 게임이 종료되고 1.5배의 수익을 얻는다" {
        val player = Player.of("aimbe")
        player.drawCard(PlayingCard.of(Suit.HEARTS, Denomination.ACE))
        player.drawCard(PlayingCard.of(Suit.SPADES, Denomination.KING))

        player.calculateProfit(1000) shouldBe 1500.0
    }

    "플레이어가 bust되면 게임이 종료되고 수익이 0이 된다" {
        val player = Player.of("aimbe")
        player.drawCard(PlayingCard.of(Suit.HEARTS, Denomination.KING))
        player.drawCard(PlayingCard.of(Suit.SPADES, Denomination.QUEEN))
        player.drawCard(PlayingCard.of(Suit.DIAMONDS, Denomination.JACK))

        player.isRunning() shouldBe false
        player.calculateProfit(1000) shouldBe 0.0
    }

    "플레이어가 stay하면 게임이 종료된다" {
        val player = Player.of("aimbe")
        player.drawCard(PlayingCard.of(Suit.HEARTS, Denomination.TEN))
        player.drawCard(PlayingCard.of(Suit.SPADES, Denomination.EIGHT))

        player.stay()

        player.isRunning() shouldBe false
    }
})
