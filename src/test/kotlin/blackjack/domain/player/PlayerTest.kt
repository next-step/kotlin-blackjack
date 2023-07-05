package blackjack.domain.player

import blackjack.domain.GameMoney
import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardType
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class PlayerTest : BehaviorSpec({

    given("플레이어 생성") {
        val name = "pobi"
        val player = Player(PlayerName(name), GameMoney(10000))
        player.addCard(Card(CardNumber.CARD_ACE, CardType.DIAMOND))
        `when`("이름을 ${name}라고 설정") {
            then("이름은 ${name}이다") {
                player.name.name shouldBe "pobi"
            }
        }
        `when`("추가 카드로 10을 넣을 때") {
            player.addCard(Card(CardNumber.CARD_QUEEN, CardType.DIAMOND))
            then("에이스는 11로 쓰인다") {
                player.getScore() shouldBe 21
            }
        }
        `when`("추가 카드로 10을 넣을 때") {
            player.addCard(Card(CardNumber.CARD_JACK, CardType.DIAMOND))
            then("에이스는 1로 쓰인다") {
                player.getScore() shouldBe 21
            }
        }
    }
})
