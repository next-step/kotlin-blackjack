package blackjack

import blackjack.domain.*
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.throwable.shouldHaveMessage
import java.lang.IllegalArgumentException

class BlackJackGameTest : StringSpec({
    "게임을 시작하면 딜러에게 카드를 두장 (딜러는 한 장만 보여준다) 플레이어에게 카드를 두장씩 나눠준다." {
        //given
        val game = BlackJackGame(
            listOf(Player("harris")),
            Dealer(),
            CardDeck(),
        )

        //when
        val playerDtos = game.start()

        //then
        playerDtos.size shouldBe 2
        playerDtos[0].cards.size shouldBe 1
        playerDtos[1].cards.size shouldBe 2
    }

    "유효하지 않은 플레이어 이름으로 addCard 함수를 호출하면 IllegalArgumentException이 발생한다." {
        //given
        val game = BlackJackGame(
            listOf(Player("harris")),
            Dealer(),
            CardDeck(),
        )

        //when
        game.start()

        //then
        shouldThrow<IllegalArgumentException> { game.addCard("hurris") }
            .shouldHaveMessage("존재하지 않는 플레이어 이름입니다.")
    }

    "딜러의 카드 점수가 16점일 때 hit을 한 번 수행한다." {
        //given
        val game = BlackJackGame(
            listOf(),
            Dealer(playingCards = PlayingCards(mutableSetOf(Card.CLOVER_10, Card.DIAMOND_6))),
            CardDeck()
        )
        //when
        val hitCount = game.hitDealer()
        //then
        hitCount shouldBe 1
    }

    "harris가 베팅금액 10000으로 딜러에게 이겼을 때 수익은 10000이다." {
        //given
        val game = BlackJackGame(
            listOf(Player("harris", playingCards = PlayingCards(mutableSetOf(Card.CLOVER_10, Card.DIAMOND_8)), 10000)),
            Dealer(playingCards = PlayingCards(mutableSetOf(Card.CLOVER_10, Card.DIAMOND_7))),
            CardDeck(),
        )

        //when
        val playerDtos = game.result()
        val dealer = playerDtos[0]
        val harris = playerDtos[1]

        //then
        playerDtos.size shouldBe 2
        harris.winningAmount shouldBe 10000
        dealer.winningAmount shouldBe -10000
    }

})
