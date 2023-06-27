package blackjack.domain.gamer

import blackjack.domain.card.CardDenomination
import blackjack.domain.card.heartCard
import blackjack.domain.card.initCard
import blackjack.domain.state.Bust
import blackjack.domain.state.Hit
import blackjack.domain.state.Stay
import blackjack.domain.state.Wait
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class PlayerTest : StringSpec({

    "최초 플레이어의 상태는 wait 상태이다" {
        val player = Player(PlayerName("test"))
        (player.state is Wait) shouldBe true
    }

    "최초 카드를 전달하면 hit 상태이다" {
        val player = Player(PlayerName("test"))
        player.init(initCard(heartCard(CardDenomination.ACE), heartCard(CardDenomination.JACK)))
        (player.state is Hit) shouldBe true
    }

    "hit 이후에도 21점을 초과하지 않는다면 hit 상태이다" {
        val player = Player(PlayerName("test"))
        player.init(initCard(heartCard(CardDenomination.TWO), heartCard(CardDenomination.THREE))) // 2 + 3 = 5
        player.hit(heartCard(CardDenomination.FOUR)) // 5 + 4 = 9
        player.hit(heartCard(CardDenomination.FIVE)) // 9 + 5 = 14
        (player.state is Hit) shouldBe true
    }

    "최초 카드를 전달하지 않은 상태로 hit를 하면 RuntimeException 예외 처리를 한다" {
        val player = Player(PlayerName("test"))
        shouldThrow<RuntimeException> {
            player.hit(heartCard(CardDenomination.KING))
        }
    }

    "카드를 삽입 했다면 카드 목록이 비어있지 않다" {
        val player = Player(PlayerName("test"))
        player.init(initCard(heartCard(CardDenomination.ACE), heartCard(CardDenomination.JACK)))
        player.hasCard() shouldBe true
    }

    "카드를 삽입하지 않았다면 카드 목록이 비어있다" {
        val player = Player(PlayerName("test"))
        player.notHasCard() shouldBe true
    }

    "카드 삽입 후 21점을 초과하면 bust 상태가 된다" {
        val player = Player(PlayerName("test"))
        player.init(initCard(heartCard(CardDenomination.QUEEN), heartCard(CardDenomination.JACK))) // 20
        player.hit(heartCard(CardDenomination.KING))
        (player.state is Bust) shouldBe true
    }

    "stay를 하면 stay 상태가 된다" {
        val player = Player(PlayerName("test"))
        player.init(initCard(heartCard(CardDenomination.QUEEN), heartCard(CardDenomination.JACK))) // 20
        player.stay()
        (player.state is Stay) shouldBe true
    }

    "최초 카드를 전달하지 않은 상태로 stay를 하면 RuntimeException 예외 처리를 한다" {
        val player = Player(PlayerName("test"))
        shouldThrow<RuntimeException> {
            player.stay()
        }
    }
})
