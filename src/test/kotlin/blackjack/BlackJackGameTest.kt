package blackjack

import blackjack.domain.*
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class BlackJackGameTest : StringSpec({
    "게임을 시작하면 딜러에게 카드를 한장 플레이어에게 카드를 두장씩 나눠준다." {
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

    "harris가 딜러에게 이겼을 때 winningCount는 1, losingCount는 0이다." {
        //given
        val game = BlackJackGame(
            listOf(Player("harris", playingCards = PlayingCards(mutableSetOf(Card.CLOVER_10, Card.DIAMOND_8)))),
            Dealer(playingCards = PlayingCards(mutableSetOf(Card.CLOVER_10, Card.DIAMOND_7))),
            CardDeck(),
        )

        //when
        val playerDtos = game.result()
        val dealer = playerDtos[0]
        val harris = playerDtos[1]

        //then
        playerDtos.size shouldBe 2
        dealer.losingCount shouldBe 1
        dealer.winningCount shouldBe 0
        harris.losingCount shouldBe 0
        harris.winningCount shouldBe 1
    }

})
